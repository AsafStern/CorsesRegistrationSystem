//
// Created by spl211 on 09/01/2021.
//

#ifndef BOOST_ECHO_CLIENT_SERVERREADERTHREAD_H
#define BOOST_ECHO_CLIENT_SERVERREADERTHREAD_H

#endif //BOOST_ECHO_CLIENT_SERVERREADERTHREAD_H

#include <ConnectionHandler.h>

class ServerReaderThread{
private:
    ConnectionHandler* ch;

public:
    ServerReaderThread(ConnectionHandler* _ch);

    void operator()();
};