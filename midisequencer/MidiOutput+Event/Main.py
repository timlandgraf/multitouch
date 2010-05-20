import Sequence
import EventManager

# cmd /K "cd C:\Users\Bao\Desktop\Studium\6. Sem SoSe 10\Multitouch Sequencer\repository\tim\midisequencer\MidiOutput+Event && python Main.py"

''' main program for test purposes '''
if __name__ == '__main__':
	print '============================================================'
	print '=================== START =================================='
	print '============================================================'
	
	# Options:
	amount_sequences = 1
	ticktime=0.125
	#ticktime=0.2
	
	# Logging On/Off
	logging_sequences=0
	logging_eventSystem=0
	
	######################################################################
	
	# start manager
	manager=EventManager.EventManager(logging=logging_eventSystem)
	manager.start()
	manager.setTicktime(ticktime)
	
	# create sequences and register at manager
	seq1=Sequence.Sequence(id='seq1', logging=logging_sequences)
	manager.register(seq1, seq1.getMidiData)
		
	for i in range(2, amount_sequences+1):
		seq=Sequence.Sequence(id='seq'+str(i), logging=logging_sequences)
		#seq.id='seq'+str(i)
		manager.register(seq, seq.getMidiData)
		
		
	# if this terminates, program will terminate
	while 1:
		a=1	