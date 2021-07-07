//
// Created by spl211 on 09/01/2021.
//

#include <ServerReaderThread.h>

ServerReaderThread::ServerReaderThread(ConnectionHandler* _ch): ch(_ch) {}

void ServerReaderThread::operator()() {
    while (!ch->shouldTerminate()){
        std::string answer;
        if (!ch->getLine(answer)) {
            std::cout << "Disconnected. Exiting...\n" << std::endl;
            break;
        }
        std::cout << answer << std::endl;
    }
}