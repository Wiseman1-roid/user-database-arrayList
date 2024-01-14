import java.io.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ObjectMapper;

//class JSONSerializationHelper{
//
//    public static void serialiseToFile(Object object,String filePath)throws IOException{
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        FileOutputStream fos = new FileOutputStream(filePath);
//        objectMapper.writeValue(fos, object);
//        objectMapper.writerWithDefaultPrettyPrinter().writeValue(fos, object);
//        fos.flush();
//        fos.close();
//    }
//
//
//    public static <T> T deserializeFromFile(String filePath,Class<T> clazz)throws IOException, ClassCastException, ClassNotFoundException{
//        ObjectMapper objectMapper = new ObjectMapper();
//        File file = new File(filePath);
//        return (T) objectMapper.readValue(file, clazz);
//    }
//}
