package root.operation;
import root.CentralServer;
import root.User;

public class RegisterClient extends Operation {

    @Override
    public Void operate(){
        //2 -------------------------
        String phone = con.next();
        String pass = con.next();
        //3 -------------------------
        CentralServer.centralServer.addUser(new User(phone, pass));
        con.format("OK");
        return null;
    }
}
