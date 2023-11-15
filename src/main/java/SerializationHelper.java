import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.*;

public class SerializationHelper{

    public static void serialiseToFile(Object object,String filePath)throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        objectOutputStream.close();	
    }


    public static Object deserializeFromFile(String filePath)throws IOException, ClassCastException, ClassNotFoundException{
    	FileInputStream fileInputStream = new FileInputStream(filePath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close(); 
        return object;
    }
}
