package bgu.spl.net.OpCodes;

import bgu.spl.net.api.TMFProtocol;
import bgu.spl.net.api.User;
import bgu.spl.net.impl.BGRSServer.Database;

import java.util.List;

public class KDAMCHECK extends FromClient {

    protected KDAMCHECK(short opcode) {
        super(opcode, new Class[] {short.class});
    }

    @Override
    public FromServer opp(Database db, TMFProtocol protocol) {
        if (protocol.getUser() == null)
            return new ERR("ERROR 6 you are not logged in",6);
        List<String> kdams = db.getKdams(msg);
        if (kdams == null)
            return new ERR("ERROR 6 course not found",6);
        return new ACK("ACK 6 \n" + kdams.toString(),6);
    }
}
