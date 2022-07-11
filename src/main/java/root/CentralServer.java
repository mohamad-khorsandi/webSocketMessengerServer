package root;

import root.service.Command;
import root.service.Service;
import root.utils.connections.ConnectionPack;
import root.utils.connections.NormalConnectionPack;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CentralServer {
    private CentralServer() {}

    static public ExecutorService executor = Executors.newCachedThreadPool();

    static void listen() throws Exception {
        NormalConnectionPack con = ConnectionPack.newNormConnectionPack(8000);
        Command cmd = Command.type(con.next());

        executor.submit(Service.newService(cmd, con));
    }

}