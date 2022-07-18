package root.operation;
import root.User;

public class RegisterClient extends Service{

    @Override
    public Void operate(){
        //2 -------------------------
        String phone = receive.next();
        String pass = receive.next();
        //3 -------------------------
        User.add(new User(phone, pass));
        send.format("OK");
        return null;
    }
}
