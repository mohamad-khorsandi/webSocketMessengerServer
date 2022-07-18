package root;


import root.utils.connections.NormalConnectionPack;
import root.Workspace;

import java.io.Serializable;
import java.util.ArrayList;

public class Host implements Serializable {
    public Host(int firstPort, int lastPort, String ip, NormalConnectionPack con) {
        this.firstPort = firstPort;
        this.lastPort = lastPort;
        this.ip = ip;
        this.con = con;
        firstFreePort = firstPort;
    }

    public ArrayList<Workspace> workspaceList = new ArrayList<>();
    int firstPort;
    int lastPort;
    public String ip;
    private int firstFreePort;

    transient public NormalConnectionPack con;

    public int getFreePort() throws Exception {
        if (firstFreePort <= lastPort)
            return firstFreePort++;
        throw new Exception("this host's ports are full");
    }

    public void addWorkSpace(String name, int port) throws Exception {
        Workspace newWp = new Workspace(name,this, port);
        if (workspaceList.contains(newWp))
            throw new Exception("workspace name has already exist");
        this.workspaceList.add(newWp);
    }


}