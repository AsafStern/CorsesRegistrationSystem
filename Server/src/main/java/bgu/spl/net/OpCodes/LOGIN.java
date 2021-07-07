package bgu.spl.net.OpCodes;

import bgu.spl.net.api.TMFProtocol;
import bgu.spl.net.api.User;
import bgu.spl.net.impl.BGRSServer.Database;

public class LOGIN extends FromClient {

    protected LOGIN(short opcode) {
        super(opcode, new Class[] {String.class, String.class});
    }

    @Override
    public FromServer opp(Database db, TMFProtocol protocol) {
        User u = protocol.getUser();
        if (u != null)
            return new ERR("ERROR 3 you are already logged in!",3);
//        protocol.setUser();
        String[] values = msg.split("\u0000");
        boolean ok = protocol.setUser(db.logIn(values[0],values[1]));
        if (ok)
            return new ACK("ACK 3",3);
        return new ERR("ERROR 3",3);
    }
}
