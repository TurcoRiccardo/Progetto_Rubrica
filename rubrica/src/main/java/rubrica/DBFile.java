package rubrica;
import java.io.*;
import java.util.*;


public class DBFile {
    private static final String FILE_NAME = "informazioni.txt";
    private static final String DIR = "";
    private static final String DIR_NAME = "informazioni";


    public static Vector<Persona> caricaRubrica() {
        Vector<Persona> rubricaFile = new Vector<>();
        File file = new File(DIR + FILE_NAME);

        if (!file.exists()) {
            System.out.println("File " + FILE_NAME + " non trovato");
            return rubricaFile;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split(";");
                    if (parts.length == 5) {
                        String nome = parts[0];
                        String cognome = parts[1];
                        String indirizzo = parts[2];
                        String telefono = parts[3];
                        int eta = Integer.parseInt(parts[4]);
                        rubricaFile.add(new Persona(nome, cognome, indirizzo, telefono, eta));
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rubricaFile;
    }

    //Salvataggio su file
    public static void salvaRubrica1(Vector<Persona> rubrica) {
        //Salvataggio su file
        try (PrintStream out = new PrintStream(new File(DIR + FILE_NAME))) {
            for (Persona p: rubrica) {
                out.println(p.getNome()+";"+p.getCognome()+";"+p.getIndirizzo()+";"+p.getTelefono()+";"+p.getEta());
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //salvataggio su un file per persona
    public static void salvaRubrica2(Vector<Persona> rubrica) {
        File dir = new File(DIR_NAME);
        int count = 1;

        
        if (!dir.exists()) {
            dir.mkdir();
        }
        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));
        if (files != null) {
            for (File f: files) {
                f.delete();
            }
        }
        for (Persona p: rubrica) {
            File file = new File(dir, "Persona" + count + ".txt");
            try (PrintStream out = new PrintStream(file)) {
                out.println(p.getNome()+";"+p.getCognome()+";"+p.getIndirizzo()+";"+p.getTelefono()+";"+p.getEta());
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
            count++;
        }

    }

}

