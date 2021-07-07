package bgu.spl.net.OpCodes;

import bgu.spl.net.api.TMFProtocol;
import bgu.spl.net.impl.BGRSServer.Database;

import static bgu.spl.net.api.User.Permission.student;

public class STUDENTREG extends FromClient {

    protected STUDENTREG(short opcode) {
        super(opcode, new Class[] {String.class, String.class});
    }

    @Override
    public FromServer opp(Database db, TMFProtocol protocol) {
        if (protocol.getUser() != null)
            return new ERR("ERROR 2 you are already logged in",2);
        // lets assume the message is string
        String[] values = msg.split("\u0000");
        // values[0] = username, values[1] = password
        if (db.Registerd(values[0],values[1], student))
            return new ACK("ACK 2",2);
        return new ERR("ERROR 2 username is already used!",2);
    }
}
