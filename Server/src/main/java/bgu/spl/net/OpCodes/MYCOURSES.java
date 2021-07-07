package bgu.spl.net.OpCodes;

import bgu.spl.net.api.TMFProtocol;
import bgu.spl.net.api.User;
import bgu.spl.net.impl.BGRSServer.Database;

import java.util.List;

public class MYCOURSES extends FromClient {

    protected MYCOURSES(short opcode) {
        super(opcode, new Class[] {});
    }

    @Override
    public FromServer opp(Database db, TMFProtocol protocol) {
        if (protocol.getUser() == null)
            return new ERR("ERROR 11 you are not logged in",4);
        User u = protocol.getUser();
        List<String> courses = db.orderCourses(u.getCourses());
        return new ACK("ACK 11" + courses,4);
    }
}
