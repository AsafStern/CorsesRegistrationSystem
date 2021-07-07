package bgu.spl.net.api;

import bgu.spl.net.OpCodes.Command;
import bgu.spl.net.impl.BGRSServer.Database;

public class TMFProtocol implements MessagingProtocol<Command>{
    private User user;

    @Override
    public Command process(Command cmd) {
//        return cmd.opp(Database.getInstance(), this);
        Command c = cmd.opp(Database.getInstance(), this);
        return c;
    }

    @Override
    public boolean shouldTerminate() {
        if (user == null)
            return false;
        return user.ShouldTerminate();
    }

    public boolean setUser(User u){
        user = u;
        return u != null;
    }

    public User getUser() {
        return user;
    }
}