import time
import random

class Arpeggiator():
	"""
	Generates a whole-number of loops in an array.
	a, b, c e[60, 71] eZ
	a < b < c

	One loop is a half of a bar -> two beats (2/4)
	"""
	def __generateLoop(self, a, b, c, loopTimes, currentLoop=None):
		# constants for MIDI Status
		ON = 1		# note on
		OFF = 0		# note off

		if a > b or b > c or a < 60 or a > 71 or b < 60 or b > 71 or c < 60 or c > 71 or loopTimes < 1:
			return None
		
		loop = [	
			[[a - 24, 100, ON]],
			[[a - 24, 100, OFF], [c - 24, 100, ON]],
			[[c - 24, 100, OFF], [a - 12, 100, ON]],
			[[a - 12, 100, OFF], [b - 12, 100, ON]],
			[[b - 12, 100, OFF], [c - 12, 100, ON]],
			[[c - 12, 100, OFF], [a, 100, ON]],
			[[a, 100, OFF], [b, 100, ON]],
			[[b, 100, OFF], [c, 100, ON]]
		]
		
		if (currentLoop != None):
			notesToClose = list()
			for lastNote in currentLoop[len(currentLoop) - 1]:
				# if a note was played
				if lastNote[2] == 1:
					noteToClose = [lastNote[0], lastNote[1], 0]
					notesToClose.append(noteToClose)
			loop[0] = notesToClose + loop[0]
			
			loop = currentLoop + loop
		
		if loopTimes > 1:
			return self.__generateLoop(a, b, c, loopTimes - 1, loop)
		else:
			return loop
			
	def getLoop(self, a, b, c):
		myLoop = self.__generateLoop(a, b, c,2)
		myLoop = self.__generateLoop(a, b, c, 2, myLoop)
		myLoop = self.__generateLoop(a,b, c, 2, myLoop)			
		return myLoop
		
	def getUgh(self):
		myLoop = self.__generateLoop(62, 65, 69, 4)
		myLoop = self.__generateLoop(62, 67, 70, 2, myLoop)
		myLoop = self.__generateLoop(62, 65, 70, 2, myLoop)			
		return myLoop
		
	def getRandomLoop(self):		
		loop=None
		i=1
		while(loop==None):
			print 'RAANANDOM Attempt ' + str(i)
			i=i+1
			a = random.randint(61, 70)
			b = random.randint(61, 70)
			c = random.randint(61, 70)
			print a,b,c
			loop = self.getLoop(a,b,c)
		
		return loop
		