package root.service;

import root.*;
import root.utils.Utils;

public class ConnectWorkspace extends Service{
    public ConnectWorkspace() {
        shouldClosed = false;
    }

    @Override
    Object operate() throws Exception {
        //2 ------------------------------
        User user = (User)newService(Command.LOGIN_CLIENT, con).operate();
        Workspace workspace = getWorkspace(con.next());
        //3 ------------------------------
        String token = genToken();
        con.format("OK %s %d %s", workspace.host.ip, workspace.port, token);
        //4 ------------------------------
        con.close();
        //5 ------------------------------
        workspace.host.con.next();//whois
        String receivedToken = workspace.host.con.next();
        //6 ------------------------------
        if (!token.equals(receivedToken))
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
        for (Host host : Host.list)
            for (Workspace ws : host.workspaceList)
                if (ws.name.equals(name))
                    return ws;
        throw new Exception("there no workspace with this name : " + name);
    }
}

