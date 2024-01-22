import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class Challenge2Main{
    public static void main(String[] args) {
        //SpringApplication.run(Challenge2Main.class, args);
        new MenuPrinter().print();
    }
}