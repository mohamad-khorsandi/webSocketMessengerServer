package root;

import java.io.Serializable;

public class User implements Serializable {
    public User(String phone, String pass) {
        this.phone = phone;
        this.pass = pass;
        this.id = idCounter++;
    }
    private String phone;
    private String pass;
    private int id;
    static int idCounter = 0;

    @Override
    public boolean equals(Object obj) {
        User u2 = (User)obj;
        return u2.pass.equals(this.pass) && u2.phone.equals(this.phone);
    }

    public int getId() {
        return id;
    }
}
