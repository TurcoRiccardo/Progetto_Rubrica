package rubrica;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;


public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;


    public Login(Vector<Utente> utenti, Vector<Persona> rubrica) {
        super("Login");


        //Form
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        /*
        //Bottoni
        JPanel buttonPanel = new JPanel();
        JButton loginBtn = new JButton("LOGIN");
        buttonPanel.add(loginBtn);
        */

        //ToolBar
        JToolBar toolBar = new JToolBar();
        JButton loginBtn = new JButton("LOGIN");
        toolBar.add(loginBtn);

        //Layout
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        //this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(toolBar, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 200));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //azioni
        loginBtn.addActionListener(e -> login(utenti, rubrica));
    }

    private void login(Vector<Utente> utenti, Vector<Persona> rubrica) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        boolean loggedIn = false;


        for (Utente u: utenti) {
            if (u.getUsername().equals(username) && u.checkPassword(password)) {
                loggedIn = true;
                break;
            }
        }
        if (loggedIn) {
            //Login corretto, carico rubrica
            rubrica.addAll(DBFile.caricaRubrica());
            DBFile.salvaRubrica2(rubrica);
            new FinestraPrincipale(rubrica);
            dispose();
        } 
        else {
            JOptionPane.showMessageDialog(this, "Login errato", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}
