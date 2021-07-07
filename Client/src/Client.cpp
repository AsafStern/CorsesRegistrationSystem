#include <ConnectionHandler.h>
#include <IOThread.h>
#include <ServerReaderThread.h>
#include <thread>

//* This code assumes that the server replies the exact text the client sent it (as opposed to the practical session example)
//*/
int main (int argc, char *argv[]) {
    std::string host = argv[1];
    short port = atoi(argv[2]);

    ConnectionHandler* connectionHandler = new ConnectionHandler(host, port);
    if (!connectionHandler->connect()) {
        std::cerr << "Cannot connect to " << host << ":" << port << std::endl;
        return 1;
    }

    IOThread io(connectionHandler);
    ServerReaderThread sr(connectionHandler);

    std::thread th1(std::ref(io));
    std::thread th2(std::ref(sr));
    th1.join();
    th2.join();

    delete(connectionHandler);
//    connectionHandler = nullptr;

}