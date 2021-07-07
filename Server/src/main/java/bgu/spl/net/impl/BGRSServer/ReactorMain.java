package bgu.spl.net.impl.BGRSServer;
import bgu.spl.net.api.TMFEncoderDecoder;
import bgu.spl.net.api.TMFProtocol;
import bgu.spl.net.srv.Server;

public class ReactorMain {
    public static void main(String[] args){
        int port = Integer.parseInt(args[0]);
        int numOfThreads = Integer.parseInt(args[1]);

        Server reactorServer = Server.reactor(numOfThreads, port, ()->new TMFProtocol(), ()-> new TMFEncoderDecoder());
        reactorServer.serve();
    }
}
