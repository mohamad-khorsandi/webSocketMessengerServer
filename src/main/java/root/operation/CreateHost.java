package root.operation;
import root.CentralServer;
import root.Host;
import root.utils.Utils;
import root.utils.connections.ConnectionPack;
import root.utils.connections.NormalConnectionPack;

import java.io.IOException;

public class CreateHost extends Operation {
    public CreateHost() {
        this.shouldClosed = false;
    }

    @Override
    protected Object operate() throws IOException {
        //1-------------------------------
        String ip = con.next();
        int firstPort = con.nextInt();
        int lastPort = con.nextInt();
        //2-------------------------------
        try {
            checkPortRange(firstPort, lastPort);
        } catch (Exception e) {
            con.format(e.getMessage());
            return null;
        }
        //3-------------------------------
        int testPort = Utils.randInRange(firstPort, lastPort);
        con.format("OK %d", testPort);
        //4-------------------------------
        con.next();//check
        //5-------------------------------
        String testCode = genTestCode();
        sendTestCode(ip, testPort, testCode);
        //6-------------------------------
        String receivedTestCode = con.next();
        //7,8,9-------------------------------
        if (!receivedTestCode.equals(testCode)) {
            con.format("ERROR invalid code");
            return null;
        }
        con.format("OK");

        CentralServer.centralServer.addHost(new Host(firstPort, lastPort, ip, con));
        return null;
    }

    private static String genTestCode(){
        final long tenToTen = 10000000000L;
        long tmpNum = (long) Math.floor(Math.random() * tenToTen);
        return String.valueOf(tmpNum);
    }

    private static void sendTestCode(String ip, int port, String testCode) throws IOException {
        try (
            NormalConnectionPack tmpCon = ConnectionPack.newNormConnectionPack(ip, port);
        ){
            tmpCon.format("OK "+testCode);
        }
    }

    private static void checkPortRange(int s, int e) throws Exception {
        if (s >= e)
            throw new Exception("range end should be greater");
        if (e - s > 1000)
            throw new Exception("ERROR At most 1000 ports is allowed");
        if (s < 10000)
            throw new Exception("port number must be at least 10000");
    }
}
