#include "TouchConnect.h"

//-------------------------------------------------------------------
// socket connection
// source: ws2_32.lib
//-------------------------------------------------------------------

SOCKET _socket;

void initConnection(u_short port)
{
	WSADATA Data;
    SOCKADDR_IN recvSockAddr;
    
    int status;
    int numrcv = 0;

    /* initialize the Windows Socket DLL */
    status = WSAStartup(MAKEWORD(1, 1), &Data);
    if (status != 0){
        //cerr << "ERROR: WSAStartup unsuccessful" << endl;
		return;
    }

    /* zero the sockaddr_in structure */
    memset(&recvSockAddr, 0, sizeof(recvSockAddr));
    /* specify the port portion of the address */
    recvSockAddr.sin_port = htons(port);
    /* specify the address family as Internet */
    recvSockAddr.sin_family = AF_INET;
    /* specify that the address does not matter */
    recvSockAddr.sin_addr.s_addr = inet_addr("127.0.0.1");

	/* create a socket */
	_socket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
	if (_socket == INVALID_SOCKET){
		//cerr << "ERROR: socket unsuccessful" << endl;
		return;
	}

    //Try to connect
    if (connect(_socket, (SOCKADDR*)&recvSockAddr, sizeof(SOCKADDR_IN)) == SOCKET_ERROR){
        //cout << "Socket connection Failed" << endl;
        closesocket(_socket);
        WSACleanup();
    }
}

void closeConnection()
{
	if(_socket)
		closesocket(_socket);
}

void sendTouchData(PRUint32 x, PRUint32 y, PRUint32 id, PRUint32 time, PRUint32 type)
{
	ostringstream s;
	s << type << " " << id << " " << x << " " << y << " " << time;
	send(_socket, s.str().data(), s.str().length(), 0);
}

