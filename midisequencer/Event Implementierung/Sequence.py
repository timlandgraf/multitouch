import EventManager

import time

'''
- Class: Sequence
- Description:
This class is a sequence displayed as an object on the GUI.
'''
class Sequence():

	def __init__(self, **kwargs):
		self.id=""
		#self.__mainloop__()

	''' dummy for later actions '''
	def callback(self):
		self.__log('me is called back!')

	''' tunnel for log messages '''
	def __log(self, msg):
		print 'Sequence <'+self.id+'>:\t' + msg


''' main program for test purposes '''
if __name__ == '__main__':
	print '============================================================'
	print '=================== START =================================='
	print '============================================================'
	
	manager=EventManager.EventManager()
	
	#manager.setTicktime(0.2)
	#manager.setTicktime(2)
	
	seq1=Sequence()
	seq1.id='seq1'
	manager.register(seq1, seq1.callback)
	
	seq2=Sequence()
	seq2.id='seq2'
	manager.register(seq2, seq2.callback)
	
	for i in range(3, 6):
		seq=Sequence()
		seq.id='seq'+str(i)
		manager.register(seq, seq.callback)
		