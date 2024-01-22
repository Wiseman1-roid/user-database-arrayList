import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

abstract class SerializationAbstract implements DAOInterface{
    FileOutputStream fos;
    public void serialiseToFile(Object object,String filePath)throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        fos = new FileOutputStream(filePath);
        objectMapper.writeValue(fos, object);
        fos.flush();
    }

    public <T> T deserializeFromFile(String filePath,Class<T> clazz)throws IOException, ClassCastException, ClassNotFoundException{
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(String.valueOf(filePath));
        return (T) objectMapper.readValue(file, clazz);
    }
    public void cleanUp(FileOutputStream fileOutputStream) throws IOException {
        fileOutputStream.close();
    }
    public void serialization(ArrayList<ArrayList<String>> database, String jsonFilePath){
        try {
            serialiseToFile(database, jsonFilePath);
        } catch (IOException e) {
            System.out.println("Update Serialization failed");
        }
    }
    public void trySerialization(ArrayList<ArrayList<String>> database, String jsonFilePath , String name){
        try {
            serialiseToFile(database, jsonFilePath);
            System.out.println("User " + name + " has been saved to " + jsonFilePath);
        } catch (IOException e) {
            System.out.println("Database down");
        }
        try {
            cleanUp(fos);
        } catch (IOException e) {
            System.out.println();
        }
    }
}