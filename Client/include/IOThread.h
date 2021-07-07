//
// Created by spl211 on 09/01/2021.
//

#ifndef BOOST_ECHO_CLIENT_INTHREAD_H
#define BOOST_ECHO_CLIENT_INTHREAD_H

#endif //BOOST_ECHO_CLIENT_INTHREAD_H

#include <ConnectionHandler.h>

class IOThread{
private:
    ConnectionHandler* ch;

public:
    IOThread(ConnectionHandler* _ch);

    void operator()();
};