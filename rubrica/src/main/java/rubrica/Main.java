package rubrica;
import java.util.Vector;
import javax.swing.SwingUtilities;


public class Main {
    public static void main(String[] args) {
        Vector<Utente> utenti = new Vector<>();
        Vector<Persona> rubrica = new Vector<>();

        utenti.add(new Utente("admin", "admin"));
        utenti.add(new Utente("user", "user"));

        System.out.println("Rubrica Telefonica");
        SwingUtilities.invokeLater(() -> new Login(utenti, rubrica));
    }

}