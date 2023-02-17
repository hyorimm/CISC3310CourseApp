import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        SchoolSystem school1 = new SchoolSystem();
        school1.read_all();
        school1.backup_all();

        boolean canContinue = true;
        while (canContinue)
            canContinue = school1.displayMainMenu();
    }
}
