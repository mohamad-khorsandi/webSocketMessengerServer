package root;

import java.util.ArrayList;
import java.util.Scanner;

public class Host {
    public Host(int firstPort, int lastPort, String ip, AutoFormatter send, Scanner receive) {
        this.firstPort = firstPort;
        this.lastPort = lastPort;
        this.ip = ip;
        this.send = send;
        this.receive = receive;
        firstFreePort = firstPort;
    }
    public static ArrayList<Host> list = new ArrayList<>();

    public ArrayList<WorkSpace> workSpaces = new ArrayList<>();
    int firstPort;
    int lastPort;
    public String ip;
    public AutoFormatter send;
    public Scanner receive;
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
        WorkSpace newWp = new WorkSpace(name,this, port);
        if (workSpaces.contains(newWp))
            throw new Exception("workspace name has already exist");
        workSpaces.add(newWp);
    }

    public static void add(Host newHost){
        list.add(newHost);
    }
}