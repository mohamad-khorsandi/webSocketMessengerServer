package root;

import root.operation.Command;
import root.operation.Service;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CentralServer {
    private CentralServer() {}

    static private ExecutorService executor = Executors.newCachedThreadPool();

    static void listen() throws Exception {
        ServerSocket serverSocket = new ServerSocket(8000);
        Socket socket = serverSocket.accept();
        Scanner receive = new Scanner(socket.getInputStream());
        AutoFormatter send = new AutoFormatter(socket.getOutputStream());
        Command cmd = Command.type(receive.next());

        executor.submit(Service.newService(cmd, socket, send, receive));

        serverSocket.close();//todo closing serverSocket before end conn??
    }

}