import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

// JSON..................................................
// import org.json.JSONArray;
// import org.json.JSONException;
// import org.json.JSONObject;
// import org.json.JSONString;
// import java.util.ArrayList;

public class CreateUserDaoFile {
    static String [][]create = new String[10][5];
    // static ArrayList<String> create = new ArrayList<String>();
    static int count = 0;
    public static void writeArrayToFile(String [][] users, String fileName){
        PrintStream ps;
        String [] lab = {"Name: ", "Surname: ", "Email: ", "Date of Birth: ", "Age: ", "ID: "};

        try{
            ps = new PrintStream(new FileOutputStream(fileName));
            for(int row = 0; row  < users.length; row++){
                for(int col = 0; col < users[row].length; col++){
                    if(users[row][col] != null){
                    String infor = users[row][col];

                    ps.println(lab[col] + infor);
                    }
                }
            }
            ps.close();
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public static <JSONArray> void createJsonFile(String [][] users, String fileName){
        PrintStream ps;
        // JSONObject json;
        // JSONObject body = new JSONObject();
        try {
            ps = new PrintStream(new FileOutputStream(fileName));
           
            for(int row=0; row<users.length; row++){
                for(int col=0; col<users[row].length; col++){
                    if(users[row][col] != null){
                    String infor = users[row][col];
                    // create.add(col, infor);
                    create[col][row] = infor;
                    ps.append(infor);
                    ps.println(create[col][row]);
                    }
                }
            }
            // for(String [] infor : users){
            //     JSONArray arr;
            //     for(String user : infor){
            //         (JSONObject)arr.put(user);
            //     }
            // }
            ps.println(create);
           
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}