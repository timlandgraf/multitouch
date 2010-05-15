#ifndef __TouchConnect_H__
#define __TouchConnect_H__

#include "TouchSupport.h"

#include <winsock.h>
#include <sstream>

using namespace std;

void initConnection(u_short port);
void closeConnection();
void sendTouchData(PRUint32 x, PRUint32 y, PRUint32 id, PRUint32 time, PRUint32 type);

#endif
