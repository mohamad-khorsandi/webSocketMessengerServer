package root.service;

import root.utils.connections.NormalConnectionPack;

import java.io.IOException;
import java.util.concurrent.Callable;

abstract public class Service implements Callable {

    public static Service newService(Command cmd, NormalConnectionPack con) throws Exception {
        Service service;
        switch (cmd){
            case CREATE_HOST:
                service = new CreateHost();
                break;

            case REGISTER_CLIENT:
                service = new RegisterClient();
                break;

            case LOGIN_CLIENT:
                service = new LoginClient();
                break;

            case CREATE_WS:
                service = new CreateWorkspace();
                break;

            case CONNECT_WS:
                service = new ConnectWorkspace();
                break;

            default:
                throw new Exception("there is no such a command");
        }
        service.con = con;
        service.cmd = cmd;
        return service;
    }
    NormalConnectionPack con;
    boolean shouldClosed = true;
    Command cmd;

    @Override
    public Object call() throws Exception {
        Object result = this.operate();
        closeIfNeeded();
        System.out.println(cmd + " was successful");
        return result;
    }

    abstract Object operate() throws Exception;

    private void closeIfNeeded() throws IOException {
        if(!shouldClosed)
            return;
        con.close();
    }
}
