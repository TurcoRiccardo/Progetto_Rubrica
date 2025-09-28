import java.sql.*;


public class UtenteDAO {

    public static boolean verificaLogin(String username, String password) {
        String sql = "SELECT * FROM utenti WHERE username=? AND password=?";
        try (PreparedStatement ps = DBsql.getConnection().prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            boolean risultato = rs.next();
            DBsql.closeConnection();
            return risultato;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

