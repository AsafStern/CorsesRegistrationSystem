package bgu.spl.net.OpCodes;

import bgu.spl.net.api.TMFProtocol;
import bgu.spl.net.impl.BGRSServer.Database;

public abstract class FromServer implements Command {
    protected final String msg;
    protected final int msgOpCode;

    protected FromServer(String msg, int msgOpCode) {
        this.msg = msg;
        this.msgOpCode = msgOpCode;
    }

    public String okaki(){
        return msg;
    }

    @Override
    public Command opp(Database db, TMFProtocol protocol) {
        return null;
    }


}
