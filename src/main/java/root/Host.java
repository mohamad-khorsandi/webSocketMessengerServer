package root;


import root.utils.connections.NormalConnectionPack;
import root.Workspace;

import java.util.ArrayList;

public class Host {
    public Host(int firstPort, int lastPort, String ip, NormalConnectionPack con) {
        this.firstPort = firstPort;
        this.lastPort = lastPort;
        this.ip = ip;
        this.con = con;
        firstFreePort = firstPort;
    }
    public static ArrayList<Host> list = new ArrayList<>();

    public ArrayList<Workspace> workspaceList = new ArrayList<>();
    int firstPort;
    int lastPort;
    public String ip;
    public NormalConnectionPack con;
    private int firstFreePort;

    public static Host get(int index) {
        return list.get(index);
    }

    public static int size() {
        return list.size();
    }

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

    public static void add(Host newHost){
        list.add(newHost);
    }
}