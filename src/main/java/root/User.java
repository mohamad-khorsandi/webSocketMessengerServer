package root;

import java.util.ArrayList;

public class User{
    public User(String phone, String pass) {
        this.phone = phone;
        this.pass = pass;
        this.id = idCounter++;
    }
    private String phone;
    private String pass;
    private int id;
    static int idCounter = 0;
    static ArrayList<User> list = new ArrayList<>();

    public static void add(User newUser) {
        list.add(newUser);
    }

    public static User get(String phone, String pass) throws Exception{
        int index = list.indexOf(new User(phone, pass));
        if (index < 0)
            throw new Exception("to do this operation you have to register first.");

        return list.get(index);
    }

    @Override
    public boolean equals(Object obj) {
        User u2 = (User)obj;
        return u2.pass.equals(this.pass) && u2.phone.equals(this.phone);
    }

    public int getId() {
        return id;
    }
}
