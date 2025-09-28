import java.sql.*;
import java.util.Vector;


public class PersonaDAO {

    public static int aggiungiPersona(Persona p) {
        String sql = "INSERT INTO persone (nome, cognome, indirizzo, telefono, eta) VALUES (?, ?, ?, ?, ?)";
        int generatedId = -1;

        try (PreparedStatement ps = DBsql.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getCognome());
            ps.setString(3, p.getIndirizzo());
            ps.setString(4, p.getTelefono());
            ps.setInt(5, p.getEta());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1); // recupera l'ID auto-generato
                }
            }
            DBsql.closeConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    public static void aggiornaPersona(int id, Persona p) {
        String sql = "UPDATE persone SET nome=?, cognome=?, indirizzo=?, telefono=?, eta=? WHERE id=?";
        try (PreparedStatement ps = DBsql.getConnection().prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getCognome());
            ps.setString(3, p.getIndirizzo());
            ps.setString(4, p.getTelefono());
            ps.setInt(5, p.getEta());
            ps.setInt(6, id);
            ps.executeUpdate();
            DBsql.closeConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void eliminaPersona(int id) {
        String sql = "DELETE FROM persone WHERE id=?";
        try (PreparedStatement ps = DBsql.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            DBsql.closeConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Vector<Persona> getRubrica() {
        Vector<Persona> persone = new Vector<>();
        String sql = "SELECT * FROM persone";
        try (Statement st = DBsql.getConnection().createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Persona p = new Persona(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("indirizzo"),
                    rs.getString("telefono"),
                    rs.getInt("eta")
                );
                persone.add(p);
            }
            DBsql.closeConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return persone;
    }
}
