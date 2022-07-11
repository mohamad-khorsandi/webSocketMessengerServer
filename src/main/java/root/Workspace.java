package root;

public class Workspace {
    public Workspace(String name, Host host, int port) {
        this.name = name;
        this.host = host;
        this.port = port;
    }

    public String name;
    public Host host;
    public int port;

    @Override
    public boolean equals(Object obj) {
        Workspace ws2 = (Workspace)obj;
        return this.name.equals(ws2.name);
    }
}
