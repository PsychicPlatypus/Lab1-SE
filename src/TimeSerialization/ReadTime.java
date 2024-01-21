package TimeSerialization;

import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;

public class ReadTime {
    public void read(String[] args) throws IOException {
        String filename = "time.ser";
        if (args.length > 0) {
            filename = args[0];
        }

        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fis);
        try {
            PersistentTime time = (PersistentTime) in.readObject();

            System.out.println("Flattened time: " + time.getTime() + "\n");
            System.out.println("Current time: " + Calendar.getInstance().getTime());
        } catch (ClassNotFoundException cnf) {
            System.out.println("Could not get Flattened Time!");
            System.out.println("Current time: " + Calendar.getInstance().getTime());
        }

        in.close();

    }
}