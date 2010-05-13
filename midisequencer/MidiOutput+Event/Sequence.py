import EventManager

import time

'''
- Class: Sequence
- Description:
This class is a sequence displayed as an object on the GUI.
'''

# cmd /K "cd C:\Users\Bao\Desktop\Studium\6. Sem SoSe 10\Multitouch Sequencer\repository\tim\midisequencer\MidiOutput+Event && python Sequence.py"

class Sequence():

	def __init__(self, **kwargs):
		self.id=""
		self.__notes=[] # mutable list for notes
		self.__velocity=[]
		
		# constants for MIDI Status
		ONOFF = 2	# plays
		ON = 1		# note on
		OFF = 0		# note off
		NOT = -1 	# nothing
		
		# DEVELOP
		self.__notes = [41,41,61,61]
		self.__velocity = [100,100,100,100]
		self.__status = [ON,OFF,ON,OFF,NOT,NOT]

	''' dummy for later actions '''
	def getMidiData(self,tick):
		index=tick % len(self.__notes)
		self.__log('getting data for tick ' + str(tick) + ' at position ' + str(index))
		return self.__notes[index], self.__velocity[index], self.__status[index]

	''' tunnel for log messages '''
	def __log(self, msg):
		print 'Sequence <'+self.id+'>:\t' + msg


''' main program for test purposes '''
if __name__ == '__main__':
	print '============================================================'
	print '=================== START =================================='
	print '============================================================'
	
	manager=EventManager.EventManager()
	manager.start()
	
	#manager.setTicktime(0.2)
	#manager.setTicktime(2)
	
	seq1=Sequence()
	seq1.id='seq1'
	manager.register(seq1, seq1.getMidiData)
	
	# if this terminates, program will terminate
	while 1:
		a=1
		
#	seq2=Sequence()
#	seq2.id='seq2'
#	manager.register(seq2, seq2.callback)
	
#	for i in range(3, 6):
#		seq=Sequence()
#		seq.id='seq'+str(i)
#		manager.register(seq, seq.callback)
		