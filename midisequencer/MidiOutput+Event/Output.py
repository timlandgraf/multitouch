import sys
import os
import time
import threading
import random

import pygame
import pygame.midi
from pygame.locals import *


################################################################################

class Output(threading.Thread): # Output runs in its own Thread

	def __init__(self, **kwargs):
		# logging on/off
		kwargs.setdefault('logging', True)
		self.__logging = kwargs.get('logging')

		self.__log('initializing Output')

		# mandatory for threading
		threading.Thread.__init__(self)

		# ticktime
		kwargs.setdefault('ticktime', 0.125)
		self.__ticktime = kwargs.get('ticktime')

		# get reference to manager. if no -> exception
		self.manager=kwargs.get('manager')
		if self.manager==None:
			raise Exception('Output must be instantiated with an associated EventManager!')

		#######################################
		device_id = None
		GRAND_PIANO = 0
		CHURCH_ORGAN = 19

		instrument = CHURCH_ORGAN
		#instrument = GRAND_PIANO
		instrument = 32
		instrument = 124
		instrument = 100
		instrument = 0
		instrument=1
		start_note = 53  # F3 (white key note), start_note != 0



		pygame.init()
		pygame.midi.init()

		self._print_device_info()

		if device_id is None:
			port = pygame.midi.get_default_output_id()
		else:
			port = device_id

		print ("using output_id :%s:" % port)

		self.midi_out = pygame.midi.Output(port, 0)
		self.midi_out.set_instrument(instrument)

		self.__on_notes = set()

	def __del__(self):
		del self.midi_out
		pygame.midi.quit()

	''' sets ticktime (in seconds) -> speed '''
	def setTickTime(self, time):
		self.__ticktime=time

	''' tunnel for log messages '''
	def __log(self, msg):
		if(self.__logging):
			print 'Output:\t\t' + msg

	def run(self):
		oldtime=pygame.time.get_ticks()

		while 1:
			queue=self.manager.playDataQueue

			playdata = queue.get()
			self.__log('got music data to play')
			if(playdata!=None):
				self.play(playdata)
			queue.task_done()

			time.sleep(self.__ticktime)

	def play(self, playdata):
		if(playdata==[]):
			return


		# constants for MIDI Status
		ON = 1		# note on
		OFF = 0		# note off

		switch___on_notes = set()
		switch_off_notes = set()

		# look which note to switch on or off
		for mididata in playdata:
			instrument = mididata[0]
			channel = mididata[1]
			note = mididata[2]
			velocity = mididata[3]
			status = mididata[4]

			# set instrument and output channel
			self.midi_out.set_instrument(instrument, channel = channel)


			if status == ON:
				self.midi_out.note_on(note, velocity, channel = 0)
				self.__log('\tnote on>\t' + str(note)) #LOG
			elif status == OFF:
				self.midi_out.note_off(note, channel = 0)
				self.__log('\t< note off\t' + str(note)) # LOG

			self.__log('played notes: ' + str(self.__on_notes)) # LOG


	def print_device_info(self):
		pygame.midi.init()
		self._print_device_info()
		pygame.midi.quit()

	def _print_device_info(self):
		for i in range( pygame.midi.get_count() ):
			r = pygame.midi.get_device_info(i)
			(interf, name, input, output, opened) = r

			in_out = ""
			if input:
				in_out = "(input)"
			if output:
				in_out = "(output)"

			print ("%2i: interface :%s:, name :%s:, opened :%s:  %s" %
				(i, interf, name, opened, in_out))