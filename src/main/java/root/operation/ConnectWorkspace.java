package root.operation;

import root.*;
import root.utils.Utils;

import java.util.concurrent.atomic.AtomicBoolean;

import static root.CentralServer.centralServer;

public class ConnectWorkspace extends Operation {
    public ConnectWorkspace() {
        shouldClosed = false;
    }

    @Override
    Object operate() throws Exception {
        //2 ------------------------------
        User user = (User) newOperation(Command.LOGIN_CLIENT, con).operate();
        Workspace workspace = getWorkspace(con.next());
        //3 ------------------------------
        AtomicBoolean isExpired = new AtomicBoolean(false);
        String token = genToken();
        con.format("OK %s %d %s", workspace.getIp(), workspace.port, token);
        CentralServer.executor.submit(() -> expireAfter5Min(isExpired));
        //4 ------------------------------
        con.close();
        //5 ------------------------------
        workspace.host.con.next();//whois
        String receivedToken = workspace.host.con.next();
        //6 ------------------------------
        if (!token.equals(receivedToken) || isExpired.get())
            throw new Exception("token doesn't match");
        workspace.host.con.format("OK %d", user.getId());
        return null;
    }

    private String genToken(){
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (Utils.randInRange(0 ,1) == 0)
                token.append((char)Utils.randInRange('a', 'z'));
            else
                token.append((char)Utils.randInRange('0', '9'));
        }
        return token.toString();
    }

    Workspace getWorkspace(String name) throws Exception {
        for (Host host : centralServer.getHostList())
            for (Workspace ws : host.workspaceList)
                if (ws.name.equals(name))
                    return ws;
        throw new Exception("there no workspace with this name : " + name);
    }
    Void expireAfter5Min(AtomicBoolean isExpired) throws InterruptedException {
        Thread.sleep(300000);
        isExpired.set(true);
        return null;
    }
}

