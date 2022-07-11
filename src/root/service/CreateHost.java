package root.service;
import root.Host;
import root.Utils;

import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;

public class CreateHost extends Service {
    public CreateHost() {
        this.shouldClosed = false;
    }

    @Override
    protected Object operate() throws IOException {
        //1-------------------------------
        String ip = receive.next();
        int firstPort = receive.nextInt();
        int lastPort = receive.nextInt();
        //2-------------------------------
        try {
            checkPortRange(firstPort, lastPort);
        } catch (Exception e) {
            send.format(e.getMessage());
            return null;
        }
        //3-------------------------------
        int testPort = Utils.randInRange(firstPort, lastPort);
        send.format("OK %d", testPort);
        //4-------------------------------
        receive.next();//check
        //5-------------------------------
        String testCode = genTestCode();
        sendTestCode(ip, testPort, testCode);
        //6-------------------------------
        String receivedTestCode = receive.next();
        //7,8,9-------------------------------
        if (!receivedTestCode.equals(testCode)) {
            send.format("ERROR invalid code");
            return null;
        }
        send.format("OK");

        Host.add(new Host(firstPort, lastPort, ip, send, receive));

        return null;
    }

    private static String genTestCode(){
        final long tenToTen = 10000000000L;
        long tmpNum = (long) Math.floor(Math.random() * tenToTen);
        return String.valueOf(tmpNum);
    }

    private static void sendTestCode(String ip, int port, String testCode) throws IOException {
        try (
                Socket socket = new Socket(ip, port);
                Formatter sf = new Formatter(socket.getOutputStream());
        ){
            sf.format("OK "+testCode+"\n");
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
