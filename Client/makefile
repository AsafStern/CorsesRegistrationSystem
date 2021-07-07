CFLAGS:=-c -Wall -Weffc++ -g -std=c++11 -Iinclude
LDFLAGS:=-lboost_system -pthread -lboost_thread

all: BGRSclient

BGRSclient: bin/EncoderDecoder.o bin/Protocol.o bin/ConnectionHandler.o bin/IOThread.o bin/ServerReaderThread.o bin/Client.o
	g++ -Wall -Weffc++ -o bin/BGRSclient bin/EncoderDecoder.o bin/Protocol.o bin/ConnectionHandler.o bin/IOThread.o bin/ServerReaderThread.o bin/Client.o $(LDFLAGS)

bin/EncoderDecoder.o: src/EncoderDecoder.cpp
	g++ $(CFLAGS) -o bin/EncoderDecoder.o src/EncoderDecoder.cpp
		
bin/Protocol.o: src/Protocol.cpp
	g++ $(CFLAGS) -o bin/Protocol.o src/Protocol.cpp
	
bin/ConnectionHandler.o: src/ConnectionHandler.cpp
	g++ $(CFLAGS) -o bin/ConnectionHandler.o src/ConnectionHandler.cpp
	
bin/IOThread.o: src/IOThread.cpp
	g++ $(CFLAGS) -o bin/IOThread.o src/IOThread.cpp

bin/ServerReaderThread.o: src/ServerReaderThread.cpp
	g++ $(CFLAGS) -o bin/ServerReaderThread.o src/ServerReaderThread.cpp

bin/Client.o: src/Client.cpp
	g++ $(CFLAGS) -o bin/Client.o src/Client.cpp

.PHONY: clean
clean:
	rm -f bin/*

