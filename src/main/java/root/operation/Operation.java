package root.operation;

import root.utils.connections.NormalConnectionPack;

import java.io.IOException;
import java.util.concurrent.Callable;

abstract public class Operation implements Callable<Object> {

    public static Operation newOperation(Command cmd, NormalConnectionPack con) throws Exception {
        Operation operation;
        switch (cmd){
            case CREATE_HOST:
                operation = new CreateHost();
                break;

            case REGISTER_CLIENT:
                operation = new RegisterClient();
                break;

            case LOGIN_CLIENT:
                operation = new LoginClient();
                break;

            case CREATE_WS:
                operation = new CreateWorkspace();
                break;

            case CONNECT_WS:
                operation = new ConnectWorkspace();
                break;

            default:
                throw new Exception("there is no such a command");
        }
        operation.con = con;
        operation.cmd = cmd;
        return operation;
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
