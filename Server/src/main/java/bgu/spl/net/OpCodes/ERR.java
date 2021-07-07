package bgu.spl.net.OpCodes;

public class ERR extends FromServer {
    private final short opcode = 13;

    protected ERR(String msg, int msgOpCode) {
        super(msg, msgOpCode);
    }


    @Override
    public short getOpcode() {
        return opcode;
    }

    @Override
    public short getMsgOpCode() {
        return (short)msgOpCode;
    }

}
