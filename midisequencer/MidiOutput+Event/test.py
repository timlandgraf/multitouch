import sys
import os
import time
import threading 

import pygame
import pygame.midi
from pygame.locals import *



oldtime=time.clock()
waittime=125
arr=[]

while(1):
	now=time.clock()
	diff=abs(now-oldtime-0.125)
	ext=''
	if(diff!=waittime):
		ext='        >>>>>>>>>>>>UGH<<<<<<<<<<<<<'
	print str(now) + ' - ' + str(oldtime) + ' = ' + str(diff) + ext
	
	arr.append(diff)
	if len(arr)>1000:
		break
		
	
	oldtime=time.clock()
		
	
	pygame.time.delay(waittime)
	#time.sleep(0.125)	

sum=0.0
for ugh in arr:
	sum=sum+ugh
	
print sum/len(arr)