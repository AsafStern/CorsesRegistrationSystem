package bgu.spl.net.api;


import bgu.spl.net.OpCodes.Command;
import bgu.spl.net.OpCodes.FromClient;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TMFEncoderDecoder implements MessageEncoderDecoder<Command> {
    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private int len = 0;
    private FromClient fc;
    private int curIns = 0;
    private short curShort = 0;
    private byte[] shortBytes = new byte[2];

    @Override
    public Command decodeNextByte(byte nextByte) {
        if (nextByte == '\n' && len != 1) return null; // the client sends '\n' at the end.
        if (len < 2) {
            pushByte(nextByte);
            if (len == 2) {
                fc = FromClient.Create(bytesToShort());
                if (fc.getDecodeIns().length == 0){ // Todo: changed here because didn't work with LOGOUT (a little ugly)
                    len = 0;
                    curIns = 0;
                    return fc;
                }
            }
            return null;
        }
        if (curIns < fc.getDecodeIns().length) {
            if (fc.getDecodeIns()[curIns] == String.class)
                StringsHandler(nextByte);
            else if (fc.getDecodeIns()[curIns] == short.class)
                ShortHandler(nextByte);
        }
        if (curIns == fc.getDecodeIns().length) {
            fc.fill(new String(bytes, 2, len - 2, StandardCharsets.UTF_8).trim());
            len = 0;
            curIns = 0;
            return fc;
        }
        return null;
    }

    private void ShortHandler(byte nextByte) {
        pushByte(nextByte);
        if (++curShort == 2){
            String n = String.valueOf(bytesToShort()); // convert the short representation to string and push it into the array
            len--; // to push the bytes, and overloading the last pushByte().
            for (int i = 0; i < n.length(); i++)
                pushByte((byte) n.charAt(i));
            curShort = 0;
            curIns++;
        }
    }

    private void StringsHandler(byte nextByte) {
        pushByte(nextByte);
        if(nextByte == '\0'){
            curIns++;
        }
    }


    public short bytesToShort()
    {
        short result = (short)((bytes[len-2] & 0xff) << 8);
        result += (short)(bytes[len-1] & 0xff);
        return result;
    }

    public byte[] shortToBytes(short num)
    {
        byte[] bytesArr = new byte[2];
        bytesArr[0] = (byte)((num >> 8) & 0xFF);
        bytesArr[1] = (byte)(num & 0xFF);
        return bytesArr;
    }

    private void pushByte(byte nextByte) {
        if (len >= bytes.length) {
            bytes = Arrays.copyOf(bytes, len * 2);
        }

        bytes[len++] = nextByte;
    }

    @Override
    public byte[] encode(Command cmd) {
        byte[] shortBytes = shortToBytes(cmd.getOpcode());
        byte[] msgOpCode = shortToBytes(cmd.getMsgOpCode());
        byte[] messageByte = (cmd.okaki()+"\n").getBytes(); // Todo: added '\n' only to test with the echo client (otherwise does'nt work)
        byte[] res = new byte[shortBytes.length + msgOpCode.length + messageByte.length];

        System.arraycopy(shortBytes,0, res, 0, shortBytes.length);
        System.arraycopy(msgOpCode,0, res, 2, msgOpCode.length);
        System.arraycopy(messageByte,0, res, 4, messageByte.length);

        return res;
    }

}