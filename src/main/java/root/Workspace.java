package root;

import java.io.Serializable;

public class Workspace implements Serializable {
    public Workspace(String name, Host host, int port) {
        this.name = name;
        this.host = host;
        this.port = port;
    }

    public String name;
    public Host host;
    public int port;

    public String getIp(){
        return host.ip;
    }

    @Override
    public boolean equals(Object obj) {
        Workspace ws2 = (Workspace)obj;
        return this.name.equals(ws2.name);
    }
}
