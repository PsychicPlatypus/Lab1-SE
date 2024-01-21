package TimeSerialization;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteTime {
    public void write(String[] args) throws IOException {
        String filename = "time.ser";
        if (args.length > 0) {
            filename = args[0];
        }

        PersistentTime time = new PersistentTime();
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fos);

        out.writeObject(time);
        out.close();
    }
}