package bgu.spl.net.OpCodes;

import bgu.spl.net.api.TMFProtocol;
import bgu.spl.net.api.User;
import bgu.spl.net.impl.BGRSServer.Database;

public class LOGOUT extends FromClient {
    protected LOGOUT(short opcode) {
        super(opcode, new Class[] {});
    }

    //TODO: should terminate in protocol

    @Override
    public FromServer opp(Database db, TMFProtocol protocol) {
        if (protocol.getUser() == null)
            return new ERR("ERROR 4 you are not logged in",4);
        User u = protocol.getUser();
        if(db.logOut(u.getUsername()))
            return new ACK("ACK 4",4);
        return new ERR("ERROR 4",4);
    }
}
