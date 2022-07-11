package root.service;

import root.User;
public class LoginClient extends Service{
    @Override
    public Object operate(){
        //2 -------------------------
        String phone = con.next();
        String pass = con.next();
        //3 -------------------------
        User enteredUser = null;
        try {
            enteredUser = User.get(phone, pass);
            con.format("OK");
        }catch (Exception e){
            con.format(e.getMessage());
        }
        return enteredUser;
    }
}
