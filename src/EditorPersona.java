import javax.swing.*;
import java.awt.*;


public class EditorPersona extends JDialog {
    private JTextField nomeField, cognomeField, indirizzoField, telefonoField, etaField;
    private Persona persona;

    
    public EditorPersona(Frame owner, Persona persona) {
        super(owner, "Editor Persona", true);
        this.persona = persona;

        //Form
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.add(new JLabel("Nome:"));
        nomeField = new JTextField(persona != null ? persona.getNome() : "");
        panel.add(nomeField);
        panel.add(new JLabel("Cognome:"));
        cognomeField = new JTextField(persona != null ? persona.getCognome() : "");
        panel.add(cognomeField);
        panel.add(new JLabel("Indirizzo:"));
        indirizzoField = new JTextField(persona != null ? persona.getIndirizzo() : "");
        panel.add(indirizzoField);
        panel.add(new JLabel("Telefono:"));
        telefonoField = new JTextField(persona != null ? persona.getTelefono() : "");
        panel.add(telefonoField);
        panel.add(new JLabel("Età:"));
        etaField = new JTextField(persona != null ? String.valueOf(persona.getEta()) : "");
        panel.add(etaField);

        /*
        //Bottoni
        JPanel buttonPanel = new JPanel();
        JButton salvaBtn = new JButton("Salva");
        JButton annullaBtn = new JButton("Annulla");
        buttonPanel.add(salvaBtn);
        buttonPanel.add(annullaBtn);
        */

        //ToolBar
        JToolBar toolBar = new JToolBar();
        JButton salvaBtn = new JButton("Salva");
        JButton annullaBtn = new JButton("Annulla");
        toolBar.add(salvaBtn);
        toolBar.add(annullaBtn);


        //Layout
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        //this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(toolBar, BorderLayout.NORTH);
        this.setPreferredSize(new Dimension(500, 300));
        this.pack();
        this.setLocationRelativeTo(owner);

        //Azioni
        salvaBtn.addActionListener(e -> {
            try {
                int eta = Integer.parseInt(etaField.getText());
                String nome = nomeField.getText();
                int id = (this.persona != null) ? this.persona.getId() : -1;
                if (nome == null || nome.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "È obbligatorio inserire il nome", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (eta < 0 || eta > 150) {
                    JOptionPane.showMessageDialog(this, "Inserisci un'età valida (0-150)", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                this.persona = new Persona(
                    id,
                    nome,
                    cognomeField.getText(),
                    indirizzoField.getText(),
                    telefonoField.getText(),
                    eta
                );
                setVisible(false);
            } 
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Età deve essere un numero valido!", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        annullaBtn.addActionListener(e -> {
            this.persona = null;
            setVisible(false);
        });
    }

    public Persona showEditorPersona() {
        this.setVisible(true);
        return persona;
    }
}
