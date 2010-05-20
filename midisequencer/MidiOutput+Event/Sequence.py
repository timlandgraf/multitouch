import EventManager
import Arpeggiator

import time
import random

'''
- Class: Sequence
- Description:
This class is a sequence displayed as an object on the GUI.
'''

# cmd /K "cd C:\Users\Bao\Desktop\Studium\6. Sem SoSe 10\Multitouch Sequencer\repository\tim\midisequencer\MidiOutput+Event && python Sequence.py"
# http://www.pygame.org/docs/ref/midi.html#Output.note_on

class Sequence():

	def __init__(self, **kwargs):
		kwargs.setdefault('id', '')
		kwargs.setdefault('logging', True)
		self.id = kwargs.get('id')
		self.__logging = kwargs.get('logging')
		self.__Arpeggiator = Arpeggiator.Arpeggiator()
		
		# for internal clock
		self.__internalTick=0
		
		# constants for MIDI Status
		ON = 1		# note on
		OFF = 0		# note off
		
		""" Midi Playdata Example:
		
			# each line is a tick
			# each column is a tone
			# [41, 100, ON] means
			#	note = 41
			#	velocity = 100
			#	ON = status (0 means note off, 1 means note on)
		
			self.__playdata = [	
								[	[41, 100, ON] , [100, 80, OFF] , [100, 100, ON] , [60,110,OFF]	], 
								[	[41, 100, ON] ,[100, 80, OFF] , [101, 100, OFF] , [60,110,OFF]	],
								[	[141, 100, ON] , [100, 80, OFF] , [101, 100, OFF] , [60,110,ON]	],
								[	[141, 100, ON] , [100, 80, OFF] , [101, 100, OFF] , [60,110,OFF]],
								[	[141, 100, ON] , [100, 80, ON] , [100, 100, OFF] , [60,110,ON]	],
								[	[41, 100, OFF] , [100, 80, OFF] , [127, 100, ON] , [60,110,OFF]]
							  ]
		"""
			
		if(self.id=='seq1'):
			# first static for reference
			self.__playdata = self.__Arpeggiator.getUgh()
		else:
			# all others are random arpgeggiator
			self.__playdata = self.__Arpeggiator.getRandomLoop()
		
	''' callback method for exposing music information '''
	def getMidiData(self,tick):
		# if sequence is shorter than the general sequence length do nothing
		if(tick>=len(self.__playdata)):
			print 'ausgelassen'
			return None
			
		# internal tick deprecated: bad synch properties!
		#self.__internalTick=(self.__internalTick+1)%len(self.__playdata)
		#index=self.__internalTick
		index=tick
		
		self.__log('getting data for tick ' + str(tick) + ' at position ' + str(index))
		return self.__playdata[index]

	''' tunnel for log messages '''
	def __log(self, msg):
		if(self.__logging):
			print 'Sequence <'+self.id+'>:\t' + msg