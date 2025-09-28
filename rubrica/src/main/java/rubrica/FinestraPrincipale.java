package rubrica;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;


public class FinestraPrincipale extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;


    public FinestraPrincipale(Vector<Persona> rubrica) {
        super("Rubrica Telefonica");

        //Tabella
        tableModel = new DefaultTableModel(new Object[]{"Nome", "Cognome", "Telefono"}, 0) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        for (Persona p : rubrica) {
            tableModel.addRow(new Object[]{p.getNome(), p.getCognome(), p.getTelefono()});
        }

        /*
        //Bottoni
        JPanel buttonPanel = new JPanel();
        JButton nuovoBtn = new JButton("Nuovo");
        JButton modificaBtn = new JButton("Modifica");
        JButton eliminaBtn = new JButton("Elimina");
        buttonPanel.add(nuovoBtn);
        buttonPanel.add(modificaBtn);
        buttonPanel.add(eliminaBtn);
        */

        //ToolBar
        JToolBar toolBar = new JToolBar();
        JButton nuovoBtn = new JButton("Nuovo");
        JButton modificaBtn = new JButton("Modifica");
        JButton eliminaBtn = new JButton("Elimina");
        nuovoBtn.setIcon(Utility.caricaIcona("icons/nuovo.png", 24, 24));
        modificaBtn.setIcon(Utility.caricaIcona("icons/modifica.png", 24, 24));
        eliminaBtn.setIcon(Utility.caricaIcona("icons/elimina.png", 24, 24));
        toolBar.add(nuovoBtn);
        toolBar.add(modificaBtn);
        toolBar.add(eliminaBtn);

        //Layout
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        //this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(toolBar, BorderLayout.NORTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //Azioni
        nuovoBtn.addActionListener(e -> {
            EditorPersona dialog = new EditorPersona(this, null);
            Persona p = dialog.showEditorPersona();
            if (p != null) {
                rubrica.add(p);
                tableModel.addRow(new Object[]{p.getNome(), p.getCognome(), p.getTelefono()});
                DBFile.salvaRubrica1(rubrica);
                DBFile.salvaRubrica2(rubrica);
            }
        });

        modificaBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                Persona p = rubrica.get(selectedRow);
                EditorPersona dialog = new EditorPersona(this, p);
                Persona updated = dialog.showEditorPersona();
                if (updated != null) {
                    rubrica.set(selectedRow, updated);
                    tableModel.setValueAt(updated.getNome(), selectedRow, 0);
                    tableModel.setValueAt(updated.getCognome(), selectedRow, 1);
                    tableModel.setValueAt(updated.getTelefono(), selectedRow, 2);
                    DBFile.salvaRubrica1(rubrica);
                    DBFile.salvaRubrica2(rubrica);
                }
            } 
            else {
                JOptionPane.showMessageDialog(this, "Seleziona una persona da modificare", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        eliminaBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                Persona p = rubrica.get(selectedRow);
                int conferma = JOptionPane.showConfirmDialog(this,
                    "Eliminare la persona " + p.getNome() + " " + p.getCognome() + "?",
                    "Conferma eliminazione",
                    JOptionPane.YES_NO_OPTION
                );
                if (conferma == JOptionPane.YES_OPTION) {
                    rubrica.remove(selectedRow);
                    tableModel.removeRow(selectedRow);
                    DBFile.salvaRubrica1(rubrica);
                    DBFile.salvaRubrica2(rubrica);
                }
            } 
            else {
                JOptionPane.showMessageDialog(this, "Seleziona una persona da eliminare", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

    }
}
