package bgu.spl.net.OpCodes;

import bgu.spl.net.api.TMFProtocol;
import bgu.spl.net.api.User;
import bgu.spl.net.impl.BGRSServer.Database;

import java.util.List;

import static bgu.spl.net.api.User.Permission.admin;
import static bgu.spl.net.api.User.Permission.student;


public class STUDENTSTAT extends FromClient { // Admin only
    protected STUDENTSTAT(short opcode) {
        super(opcode, new Class[] {String.class});

    }
    @Override
    public FromServer opp(Database db, TMFProtocol protocol) {
        if (protocol.getUser() == null)
            return new ERR("ERROR 8 you are not logged in",8);
        User u = protocol.getUser();
        if (u.getPermission() != admin)
            return new ERR("ERROR 8 only admin",8);
        u = db.getUser(msg);
        if (u == null || u.getPermission()!=student)
            return new ERR("ERROR 8 no such student",8);
        List<String> courses = db.orderCourses(u.getCourses());
        StringBuilder sb = new StringBuilder();
        sb.append("Student: " + u.getUsername());
        sb.append("\nCourses: " + courses);
        return new ACK("ACK 8 \n" + sb.toString(),8);
    }

}
