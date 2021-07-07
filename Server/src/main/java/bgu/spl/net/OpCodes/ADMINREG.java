package bgu.spl.net.OpCodes;

import bgu.spl.net.api.TMFProtocol;
import bgu.spl.net.impl.BGRSServer.Database;

import static bgu.spl.net.api.User.Permission.admin;

public class ADMINREG extends FromClient {

    protected ADMINREG(short opcode) {
        super(opcode, new Class[] {String.class, String.class});
    }

    @Override
    public FromServer opp(Database db, TMFProtocol protocol) {

        if (protocol.getUser() != null)
            return new ERR("", 1);

        // lets assume the message is string
        String[] values = msg.split("\u0000");
        // values[0] = username, values[1] = password
        if (db.Registerd(values[0],values[1], admin))
            return new ACK("ACK 1", 1);
        return new ERR("", 1);
    }
}
