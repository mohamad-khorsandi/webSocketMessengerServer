package root.operation;

import root.*;

public class ConnectWorkspace extends Service{
    public ConnectWorkspace() {
        shouldClosed = false;
    }

    @Override
    Object operate() throws Exception {
        //2 ------------------------------
        User user = (User)newService(Command.LOGIN_CLIENT, socket, send, receive).operate();
        WorkSpace workspace = getWorkspace(receive.next());
        //3 ------------------------------
        String token = genToken();
        send.format("OK %s %d %s", workspace.host.ip, workspace.port, token);
        //4 ------------------------------
        Utils.closeAll(socket, send, receive);
        //5 ------------------------------
        workspace.host.receive.next();//whois
        String receivedToken = workspace.host.receive.next();
        //6 ------------------------------
        if (!token.equals(receivedToken))
            throw new Exception("token doesn't match");
        workspace.host.send.format("OK %d", user.getId());
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
        System.out.println(token);
        return token.toString();
    }

    WorkSpace getWorkspace(String name) throws Exception {
        for (Host host : Host.list)
            for (WorkSpace ws : host.workSpaces)
                if (ws.name.equals(name))
                    return ws;
        throw new Exception("there no workspace with this name : " + name);
    }
}

