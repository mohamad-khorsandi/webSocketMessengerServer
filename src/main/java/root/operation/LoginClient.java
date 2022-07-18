package root.operation;

import root.CentralServer;
import root.User;
public class LoginClient extends Operation {
    @Override
    public Object operate(){
        //2 -------------------------
        String phone = con.next();
        String pass = con.next();
        //3 -------------------------
        User enteredUser = null;
        try {
            enteredUser = CentralServer.centralServer.getUser(phone, pass);
            con.format("OK");
        }catch (Exception e){
            con.format(e.getMessage());
        }
        return enteredUser;
    }
}
