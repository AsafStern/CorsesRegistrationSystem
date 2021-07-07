package bgu.spl.net.OpCodes;

import bgu.spl.net.api.TMFProtocol;
import bgu.spl.net.api.User;
import bgu.spl.net.impl.BGRSServer.Database;

import static bgu.spl.net.api.User.Permission.student;

public class COURSEREG extends FromClient {

    protected COURSEREG(short opcode) {
        super(opcode, new Class[] {short.class});
    }

    @Override
    public FromServer opp(Database db, TMFProtocol protocol) {
        if (protocol.getUser() == null)
            return new ERR("ERROR 5 you are not logged in",5);
        User u = protocol.getUser();
        if(u.getPermission() != student)
            return new ERR("ERROR 5 only students",5);
        if(db.regCourse(msg, u))
            return new  ACK("ACK 5",5);
        return new ERR("ERROR 5",5);
    }
}
