package bgu.spl.net.impl.BGRSServer;
import bgu.spl.net.api.TMFEncoderDecoder;
import bgu.spl.net.api.TMFProtocol;
import bgu.spl.net.srv.Server;

public class TPCMain {
    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);

        Server TPCserver = Server.threadPerClient(port, ()->new TMFProtocol(), ()-> new TMFEncoderDecoder());
        TPCserver.serve();
    }
}

