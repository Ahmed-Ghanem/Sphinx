package database;

/**
 * DatabaseConnection.java.
 * Created on 27-Feb-2011, 1:16:01AM.
 */
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utils.ConstantManager;

/**
 * responsible for manage database connections on sphinx.
 * @author Ahmed Gahenm.
 * class Methods:-
 * 1- getConnection() => used to get database connection.
 * 2- getDataBaseMetaData() => get meta data of this connection.
 * 3- getQueryRes(String query) => execute query and get the result.
 * @see Vector
 * @see ResultSet
 * @see Statement
 */
public class DatabaseConnection {

    private DatabaseMetaData dbmd;
    private ResultSet res;
    private Vector tablesName;
    private Statement stmt;
    private Connection connection;

    /**
     * to get database connection
     * @param url database connection
     */
    public DatabaseConnection(String url) {
        try {
            Class.forName(ConstantManager.SQLITE_DRIVER);
            connection = DriverManager.getConnection(url);
            dbmd = connection.getMetaData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error reading Data Files", "Info", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * @return   connection to database.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     *
     * @return database meta data.
     */
    public DatabaseMetaData getDataBaseMetaData() {
        return dbmd;
    }

    /**
     *
     * @param query sql query.
     * @return vector of data as result of query.
     */
    public Vector getQueryRes(String query) {
        try {
            tablesName = new Vector();
            stmt = connection.createStatement();
            res = stmt.executeQuery(query);
            while (res.next()) {
                tablesName.add(res.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tablesName;
    }
}
