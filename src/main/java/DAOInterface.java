import java.io.IOException;
import java.util.ArrayList;

interface DAOInterface {
    public void create(String name, String surname, String email, String dob, String ageCalculate, String id);
    public void update(String email, String name, String surname, String dob);
    public void delete(String email);
    public ArrayList<ArrayList<String>> findAll();
    public ArrayList<String> getUserByEmail(String email);

    // Abstract  serialization methods
    public <T> T deserializeFromFile(String filePath,Class<T> clazz)throws IOException, ClassCastException, ClassNotFoundException;
    public void serialiseToFile(Object object,String filePath)throws IOException;
}
