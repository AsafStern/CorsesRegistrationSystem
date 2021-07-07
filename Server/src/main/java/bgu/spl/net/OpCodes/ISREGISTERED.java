package bgu.spl.net.OpCodes;

import bgu.spl.net.api.TMFProtocol;
import bgu.spl.net.api.User;
import bgu.spl.net.impl.BGRSServer.Database;

import static bgu.spl.net.api.User.Permission.student;

public class ISREGISTERED extends FromClient {

    protected ISREGISTERED(short opcode) {
        super(opcode, new Class[] {short.class});
    }

    @Override
    public FromServer opp(Database db, TMFProtocol protocol) {
        if (protocol.getUser() == null)
            return new ERR("ERROR 9 you are not logged in",9);
        User u = protocol.getUser();
        if (u.getPermission() != student)
            return new ERR("ERROR 9 only students",9);
        if(!db.isExist(msg))
            return new ERR("ERROR 9 Course does not exist",9);
        if(u.getCourses().contains(msg))
            return new ACK("ACK 9 \nREGISTERED",9);
        return new ACK("ACK 9 \nNOT REGISTERED",9);
    }
}
