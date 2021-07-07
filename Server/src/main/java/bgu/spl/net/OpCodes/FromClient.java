package bgu.spl.net.OpCodes;

import bgu.spl.net.api.TMFProtocol;
import bgu.spl.net.impl.BGRSServer.Database;

public abstract class FromClient implements Command{ // Todo: only login users are getting served (change things if needed)
    protected final short opcode;
    protected final Class[] decodeIns;
    protected String msg;

    protected FromClient(short opcode, Class[] decodeIns) {
        this.opcode = opcode;
        this.decodeIns = decodeIns;

    }

    public Class[] getDecodeIns() {
        return decodeIns;
    }

    public abstract FromServer opp(Database db, TMFProtocol protocol);
    public String okaki(){return null;}

    public short getOpcode() {
        return opcode;
    }


    public static FromClient Create(short opcode){

        switch (opcode){
            case 1:
                return new ADMINREG(opcode);
            case 2:
                return new STUDENTREG(opcode);
            case 3:
                return new LOGIN(opcode);
            case 4:
                return new LOGOUT(opcode);
            case 5:
                return new COURSEREG(opcode);
            case 6:
                return new KDAMCHECK(opcode);
            case 7:
                return new COURSESTAT(opcode);
            case 8:
                return new STUDENTSTAT(opcode);
            case 9:
                return new ISREGISTERED(opcode);
            case 10:
                return new UNREGISTER(opcode);
            case 11:
                return new MYCOURSES(opcode);
            default:
                return null;
        }
    }

    public void fill(String s){msg = s;}

    @Override
    public short getMsgOpCode() {
        return 0;
    }
}