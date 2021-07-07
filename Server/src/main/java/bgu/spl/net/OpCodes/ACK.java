package bgu.spl.net.OpCodes;

public class ACK extends FromServer {
    private final short opcode = 12;

    protected ACK(String msg, int msgOpCode) {
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
