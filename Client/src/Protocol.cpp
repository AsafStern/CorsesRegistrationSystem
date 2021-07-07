//
// Created by spl211 on 07/01/2021.
//

#include <Protocol.h>
#include <string>
#include <iostream>

std::string Protocol::process(std::string &msg) {
    if (indicator > 1){
        indicator--;
        return msg;
    }

    short opCode = (short)((msg[2] & 0xff) << 8);
    opCode += (short)(msg[3] & 0xff);

    short retCode = (short)((msg[0] & 0xff) << 8);
    retCode += (short)(msg[1] & 0xff);


    if (retCode == 13){
        msg = "ERROR ";
    }
    else if (retCode == 12){
        msg = "ACK ";
    }

    msg += std::to_string(opCode);


    if(retCode == 12){ // spacial ack messeges:
        if(opCode == 6)
            indicator = 2;
        if(opCode == 7)
            indicator = 4;
        if(opCode == 8)
            indicator = 3;
        if(opCode == 9)
            indicator = 2;
    }

//    msg = std::string(msg.begin()+4, msg.end());
    should_terminate = (msg == "ACK 4");
    return msg;
}

bool Protocol::shouldTerminate() {
    return should_terminate;
}

Protocol::Protocol(): should_terminate(false), indicator(1) {}