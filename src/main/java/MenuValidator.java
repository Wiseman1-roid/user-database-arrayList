public class MenuValidator{
	
	public static boolean validate(String operation) {
     
         if (operation.equals("1") || operation.equals("2") || operation.equals("3") || operation.equals("4") || operation.equals("5") || operation.equals("6")){
             return true; 
         }
         return false;
         }    
    }
