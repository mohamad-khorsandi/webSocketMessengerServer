package root.operation;

import root.User;

public class LoginClient extends Service{
    @Override
    public User operate(){
        //2 -------------------------
        String phone = receive.next();
        String pass = receive.next();
        //3 -------------------------
        User enteredUser = null;
        try {
            enteredUser = User.get(phone, pass);
            send.format("OK");
        }catch (Exception e){
            send.format(e.getMessage());
        }
        return enteredUser;
    }
}
