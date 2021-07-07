//
// Created by spl211 on 07/01/2021.
//

#include <string>

#ifndef BOOST_ECHO_CLIENT_PROTOCOL_H
#define BOOST_ECHO_CLIENT_PROTOCOL_H

#endif //BOOST_ECHO_CLIENT_PROTOCOL_H



class Protocol{
private:
    bool should_terminate = false;
    int indicator;

public:
    Protocol();

    std::string process (std::string& msg);

    bool shouldTerminate();

};
