package root;

public class WorkSpace {
    public WorkSpace(String name, Host host, int port) {
        this.name = name;
        this.host = host;
        this.port = port;
    }

    public String name;
    public Host host;
    public int port;

    @Override
    public boolean equals(Object obj) {
        WorkSpace ws2 = (WorkSpace)obj;
        return this.name.equals(ws2.name);
    }
}
