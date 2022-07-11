package root.service;
import root.User;

public class RegisterClient extends Service{

    @Override
    public Void operate(){
        //2 -------------------------
        String phone = con.next();
        String pass = con.next();
        //3 -------------------------
        User.add(new User(phone, pass));
        con.format("OK");
        return null;
    }
}
