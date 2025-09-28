import java.io.FileInputStream;
import java.util.Properties;
import java.util.Vector;
import javax.swing.SwingUtilities;


public class Main {
    public static final String PROPERTIES_FILE = "credenziali_database.properties";
    public static boolean dbSQLMode = false;

    public static void main(String[] args) {
        Vector<Utente> utenti = new Vector<>();
        Vector<Persona> rubrica = new Vector<>();


        System.out.println("Rubrica Telefonica");
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(PROPERTIES_FILE));
            dbSQLMode = Boolean.parseBoolean(props.getProperty("db.sql").trim());
            System.out.println("db.sql = " + dbSQLMode);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (!Main.dbSQLMode) {
            utenti.add(new Utente("admin", "admin"));
            utenti.add(new Utente("user", "user"));
        }
        SwingUtilities.invokeLater(() -> new Login(utenti, rubrica));
    }

}