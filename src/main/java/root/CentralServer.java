package root;

import lombok.Getter;
import root.fileOperations.FileOperations;
import root.operation.Command;
import root.operation.Operation;
import root.utils.connections.ConnectionPack;
import root.utils.connections.NormalConnectionPack;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CentralServer implements Serializable {
    private CentralServer() {}

    public static void main(String[] args) {
        centralServer = new CentralServer();

        executor.submit(() -> centralServer.listen());

        centralServer.listenSysAdminCmd();
        System.exit(0);
    }


    static private Scanner sc = new Scanner(System.in);
    static public ExecutorService executor = Executors.newCachedThreadPool();

    static public CentralServer centralServer;
    @Getter
    private ArrayList<Host> hostList = new ArrayList<>();
    private ArrayList<User> userList = new ArrayList<>();

    Void listen() throws Exception {
        while (true) {
            NormalConnectionPack con = ConnectionPack.newNormConnectionPack(8000);

            Command cmd = Command.type(con.next());
            executor.submit(Operation.newOperation(cmd, con));
        }
    }
    void listenSysAdminCmd() {
        while (true){
            if(sc.next().equals("shutdown"))
                break;
        }
    }

    public Host getHost(int index) {
        return hostList.get(index);
    }

    public int hostListSize() {
        return hostList.size();
    }

    public void addHost(Host newHost){
        hostList.add(newHost);
    }

    public void addUser(User newUser) {
        userList.add(newUser);
    }

    public User getUser(String phone, String pass) throws Exception{
        int index = userList.indexOf(new User(phone, pass));
        if (index < 0)
            throw new Exception("to do this operation you have to register first.");

        return userList.get(index);
    }
}