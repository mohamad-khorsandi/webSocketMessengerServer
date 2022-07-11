package root.service;

import root.*;
import root.utils.Utils;

import static root.service.Command.LOGIN_CLIENT;

public class CreateWorkspace extends Service{
    User creator;
    @Override
    public Void operate() throws Exception {
        //2 -------------------------------
        creator = (User) newService(LOGIN_CLIENT, con).operate();//todo directly login
        String workspaceName = con.next();
        //3 -------------------------------
        Host host = Host.get(Utils.randInRange(0, (Host.size())-1));
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
