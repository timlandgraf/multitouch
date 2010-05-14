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
		kwargs.setdefault('id', '')
		self.id = kwargs.get('id')
		#self.id=""
		
		# constants for MIDI Status
		ON = 1		# note on
		OFF = 0		# note off
		NOT = -1 	# nothing
		
		# each line is a tick
		# each column is a tone
		if self.id == 'seq1':			
			self.__playdata = [	
								[	[41, 100, ON] , [100, 80, OFF] , [31, 80, ON] , [60,110,ON]	], 
								[	[41, 100, OFF] , [100, 80, ON] , [31, 80, ON] , [60,110,ON]	],
								[	[41, 100, ON] , [100, 80, ON] , [31, 80, ON] , [60,110,OFF]	],
								[	[41, 100, OFF] , [100, 80, OFF] , [31, 80, ON] , [60,110,OFF]],
								[	[41, 100, ON] , [100, 80, OFF] , [31, 80, ON] , [60,110,ON]	],
								[	[41, 100, OFF] , [100, 80, ON] , [31, 80, ON] , [60,110,OFF]]
							  ]
		else:
			self.__playdata = [	
								[	[61, 100, ON] , [80, 80, OFF] , [31, 80, ON] , [70,80,ON]	], 
								[	[61, 100, OFF] , [80, 80, ON] , [31, 80, ON] , [70,80,ON]	],
								[	[61, 100, ON] , [80, 80, ON] , [31, 80, ON] , [70,80,OFF]	],
								[	[61, 100, OFF] , [80, 80, OFF] , [31, 80, ON] , [70,80,OFF]],
								[	[61, 100, ON] , [80, 80, OFF] , [31, 80, ON] , [70,80,ON]	],
								[	[61, 100, OFF] , [80, 80, ON] , [31, 80, ON] , [70,80,OFF]]
							  ]
						  
		# for internal clock
		self.__internalTick=0

	''' dummy for later actions '''
	def getMidiData(self,tick):
		index=tick % len(self.__playdata) #buggy
		self.__internalTick=(self.__internalTick+1)%len(self.__playdata)
		index=self.__internalTick
		self.__log('getting data for tick ' + str(tick) + ' at position ' + str(index))
		#return self.__notes[index], self.__velocity[index], self.__status[index]
		return self.__playdata[index]

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
	
	seq1=Sequence(id='seq1')
	#seq1.id='seq1'
	manager.register(seq1, seq1.getMidiData)
		
	seq2=Sequence(id='seq2')
	#seq2.id='seq2'
	manager.register(seq2, seq2.getMidiData)
	
	# if this terminates, program will terminate
	while 1:
		a=1	
	
#	for i in range(3, 6):
#		seq=Sequence()
#		seq.id='seq'+str(i)
#		manager.register(seq, seq.callback)
		