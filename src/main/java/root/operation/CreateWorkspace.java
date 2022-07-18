package root.operation;

import root.*;
import root.utils.Utils;
import static root.CentralServer.centralServer;

import static root.operation.Command.LOGIN_CLIENT;

public class CreateWorkspace extends Operation {
    User creator;
    @Override
    public Void operate() throws Exception {
        //2 -------------------------------
        creator = (User) newOperation(LOGIN_CLIENT, con).operate();//todo directly login
        String workspaceName = con.next();
        //3 -------------------------------
        Host host = centralServer.getHost(Utils.randInRange(0, (centralServer.hostListSize())-1));
        int freePort;
        try {
            freePort = host.getFreePort();
        }catch (Exception e){
            con.format(e.getMessage());
            return null;
        }
        host.con.format("create-workspace %d %d", freePort, creator.getId());
        //4 -------------------------------
        String result = host.con.next();
        if (!result.equals("OK"))
            throw new Exception(result);
        //5 -------------------------------
        con.format("OK %s %d", host.ip, freePort);
        host.addWorkSpace(workspaceName, freePort);
        return null;
    }
}
