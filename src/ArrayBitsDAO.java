
import JDBC.ConnectionFactoryMySQL;
import com.mysql.jdbc.Blob;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.firebirdsql.jdbc.parser.JaybirdSqlParser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class ArrayBitsDAO {

    Connection con;
    PreparedStatement stmt;
    ResultSet rs;
    String sql;

    public ArrayBitsDAO() {
        con = ConnectionFactoryMySQL.getConnection();
    }

    public boolean addArquivo(InputStream in) {
        sql = "INSERT INTO new_table (informacao) values (?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setBlob(1, in);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ArrayBitsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

    public byte[] getArquivo(int id) {
        sql = "SELECT * from new_table where id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.first();
            return rs.getBytes("informacao");
        } catch (SQLException ex) {
            Logger.getLogger(ArrayBitsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            ConnectionFactoryMySQL.closeConnection(con, stmt);
        }
    }

}
