package root.fileOperations;

import root.CentralServer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOperations {
    static String fileName = "centralServer";

    public static void saveObject(CentralServer centralServer) throws IOException {
        Path file = Paths.get(fileName);
        if (!Files.exists(file))
            Files.createFile(file);

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(centralServer);
    }

    public static CentralServer loadObject() throws IOException, ClassNotFoundException {
        Path file = Paths.get(fileName);
        if (!Files.exists(file))
            return null;

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        return (CentralServer) in.readObject();
    }
}
