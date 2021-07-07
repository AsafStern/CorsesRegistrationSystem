package bgu.spl.net.OpCodes;

import bgu.spl.net.api.TMFProtocol;
import bgu.spl.net.impl.BGRSServer.Database;

/*
    A marker interface
 */
public interface Command {
    Command opp(Database db, TMFProtocol protocol);
    //      XOR      //
    String okaki();


    short getOpcode();

    short getMsgOpCode();
}
