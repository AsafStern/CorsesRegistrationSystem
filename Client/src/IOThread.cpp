//
// Created by spl211 on 09/01/2021.
//

#include <IOThread.h>
#include <thread>
#include <chrono>

IOThread::IOThread(ConnectionHandler* _ch): ch(_ch) {
}

void IOThread::operator()() {
    while (!ch->shouldTerminate()) {
        const short bufsize = 1024;
        char buf[bufsize];
        std::cin.getline(buf, bufsize);
        std::string line(buf);
        int len = line.length();
        if (!ch->sendLine(line)) {
            std::cout << "Disconnected. Exiting...\n" << std::endl;
            break;
        }
        std::this_thread::sleep_for(std::chrono::milliseconds(400));
    }
    // std::this_thread::yield(); //Gives up the remainder of the current thread's time slice, to allow other threads to run.
    // Todo: ^^
}
