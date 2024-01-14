import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

abstract class SerializationAbstract implements DAOInterface{
    public void serialiseToFile(Object object,String filePath)throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        FileOutputStream fos = new FileOutputStream(filePath);
        objectMapper.writeValue(fos, object);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(fos, object);
        fos.flush();
        fos.close();
    }

    public <T> T deserializeFromFile(String filePath,Class<T> clazz)throws IOException, ClassCastException, ClassNotFoundException{
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        return (T) objectMapper.readValue(file, clazz);
    }
}