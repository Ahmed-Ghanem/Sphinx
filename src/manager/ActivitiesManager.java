
package manager;
/**
 * ActiveManager.java.
 * Created on 28-Feb-2011, 12:1:01AM.
 */
import database.DatabaseConnection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 * responsible for controlling the activities.
 * @author Ahmed Gahenm.
 * class Methods:-
 * 1- storeActivity() => Store new activity.
 * 2- guiComponents(JComboBox c, String d, String s, String e, String a, String du) => reference to activities components.
 */
public class ActivitiesManager {

    private JComboBox projects;
    private String day;
    private String start;
    private String end;
    private String activity;
    private String duration;
    private DatabaseConnection dbConnection;
    private Statement stmt;

    public ActivitiesManager() {
    }

    public ActivitiesManager(String url) {
        dbConnection = new DatabaseConnection(url);
    }

    public void guiComponents(JComboBox c, String d, String s, String e, String a, String du) {
        this.projects = c;
        this.day = d;
        this.start = s;
        this.end = e;
        this.activity = a;
        this.duration = du;
    }

    public void storeActivity() {
        try {
            stmt = dbConnection.getConnection().createStatement();
            //store new activity
            stmt.executeUpdate("insert into activities values((select id from project where name ='" + projects.getSelectedItem() + "'),'" + day + "','" + start + "','" + end + "','" + activity + "','" + duration + "')");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Loading Data Error", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

    }
}
