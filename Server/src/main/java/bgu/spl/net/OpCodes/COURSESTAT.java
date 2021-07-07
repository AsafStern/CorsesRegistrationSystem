package bgu.spl.net.OpCodes;

import bgu.spl.net.api.TMFProtocol;
import bgu.spl.net.api.User;
import bgu.spl.net.impl.BGRSServer.Database;

import static bgu.spl.net.api.User.Permission.admin;

public class COURSESTAT extends FromClient { // Admin only

    protected COURSESTAT(short opcode) {
        super(opcode, new Class[] {short.class});
    }

    @Override
    public FromServer opp(Database db, TMFProtocol protocol) {
        if (protocol.getUser() == null)
            return new ERR("ERROR 7 you are not logged in",7);
        User u = protocol.getUser();
        if(u.getPermission() != admin)
            return new ERR("ERROR 7  Admin only",7);
        String courseStat = db.getCourseStat(msg);
        if (courseStat == null)
            return new ERR("ERROR 7  Course not found",7);
        return new ACK("ACK 7\n" + courseStat,7);
    }
}
