import EventManager

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
		self.id = kwargs.get('id')
		#self.id=""
		
		# constants for MIDI Status
		ON = 1		# note on
		OFF = 0		# note off
		
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
		elif self.id == 'seq2':
			self.__playdata = [	
								[	[61, 100, ON] , [80, 80, OFF] , [31, 80, ON] , [70,80,ON]	], 
								[	[61, 100, OFF] , [80, 80, ON] , [31, 80, ON] , [70,80,ON]	],
								[	[61, 100, ON] , [80, 80, ON] , [31, 80, ON] , [70,80,OFF]	],
								[	[61, 100, OFF] , [80, 80, OFF] , [31, 80, ON] , [70,80,OFF]],
								[	[61, 100, ON] , [80, 80, OFF] , [31, 80, ON] , [70,80,ON]	],
								[	[61, 100, OFF] , [80, 80, ON] , [31, 80, ON] , [70,80,OFF]]
							  ]		
		else:
			first = int(random.randrange(61, 127, 1))
			second = int(random.randrange(80, 99, 1))
			third = int(random.randrange(20, 31, 1))
			fourth = int(random.randrange(50, 60, 1))

			self.__playdata = [	
								[	[first, 100, self.getRndOnOff()] , [second, 80, self.getRndOnOff()] , [third, 80,self.getRndOnOff()] , [fourth,80,self.getRndOnOff()]	], 
								[	[first, 100, self.getRndOnOff()] , [second, 80, self.getRndOnOff()] , [third, 80,self.getRndOnOff()] , [fourth,80,self.getRndOnOff()]	],
								[	[first, 100, self.getRndOnOff()] , [second, 80, self.getRndOnOff()] , [third, 80, self.getRndOnOff()] , [fourth,80,self.getRndOnOff()]	],
								[	[first, 100, self.getRndOnOff()] , [second, 80, self.getRndOnOff()] , [third, 80, self.getRndOnOff()] , [fourth,80,self.getRndOnOff()]],
								[	[first, 100, self.getRndOnOff()] , [second, 80, self.getRndOnOff()] , [third, 80, self.getRndOnOff()] , [fourth,80,self.getRndOnOff()]	],
								[	[first, 100, self.getRndOnOff()] , [second, 80, self.getRndOnOff()] , [third, 80, self.getRndOnOff()] , [fourth,80,self.getRndOnOff()]]
							  ]
						  
		# for internal clock
		self.__internalTick=0
		
	def getRndOnOff(self):
		#return int(random.randrange(0, 2, 1))
		a=random.randint(0, 1)
		#print '>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ' + str(a)
		return a

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
	
	amount_sequences = 10
	
	manager=EventManager.EventManager()
	manager.start()
	
	#manager.setTicktime(0.2)
	#manager.setTicktime(2)
	
	seq1=Sequence(id='seq1')
	#seq1.id='seq1'
	manager.register(seq1, seq1.getMidiData)
		
	#seq2=Sequence(id='seq2')
	#seq2.id='seq2'
	#manager.register(seq2, seq2.getMidiData)
	
	for i in range(2, amount_sequences+1):
		seq=Sequence(id='seq'+str(i))
		#seq.id='seq'+str(i)
		manager.register(seq, seq.getMidiData)
		
	# if this terminates, program will terminate
	while 1:
		a=1	