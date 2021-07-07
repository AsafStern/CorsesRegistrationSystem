package bgu.spl.net.OpCodes;

import bgu.spl.net.api.TMFProtocol;
import bgu.spl.net.api.User;
import bgu.spl.net.impl.BGRSServer.Database;

import static bgu.spl.net.api.User.Permission.student;

public class UNREGISTER extends FromClient {

    protected UNREGISTER(short opcode) {
        super(opcode, new Class[] {short.class});
    }

    @Override
    public FromServer opp(Database db, TMFProtocol protocol) {
        if (protocol.getUser() == null)
            return new ERR("ERROR 10 you are not logged in",10);
        User u = protocol.getUser();
        if(u.getPermission() != student)
            return new ERR("ERROR 10 only students",10);
        if(!db.unRegister(msg,u))
            return new ERR("ERROR 10 course not found or user did not register",10);
        return new ACK("ACK 10",10);
    }
}
