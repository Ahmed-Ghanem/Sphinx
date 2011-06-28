package manager;

/**
 * ProjectManager.java.
 * Created on 28-Feb-2011, 1:16:01AM.
 */
import database.DatabaseConnection;
import gui.NewProject;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * responsible for manage new projects.
 * @author Ahmed Gahenm.
 * class Methods:-
 * 1- guiComponents(JTextField f, JTextArea t, JDialog d)=> take this parameters as references.
 * 2- isValidTextSource(JTextArea t, JTextField f) => there is text on these components or not.
 * 3- storeNewProject() => Store the data on database.
 * @see NewProject
 */
public class ProjectManager {

    private JTextField projectName;
    private JTextArea projectDescription;
    private JDialog newProjectDialog;
    private DatabaseConnection dbConnection;
    private Statement stmt;
    private JComboBox projectBox;
    private boolean startFlag;

    public ProjectManager() {
    }

    /**
     *
     * @param url database url.
     */
    public ProjectManager(String url) {
        dbConnection = new DatabaseConnection(url);

    }

    /**
     *
     * @param f reference component.
     * @param t reference component.
     * @see NewProject
     */
    public void guiComponents(JTextField f, JTextArea t, JDialog d) {
        this.projectName = f;
        this.projectDescription = t;
        this.newProjectDialog = d;
    }

    public void guiComponents(JComboBox c) {
        this.projectBox = c;
    }

    /**
     *
     * @param t project description text area.
     * @param f project name text field.
     * @return true if text components not empty.
     */
    public boolean isValidTextSource(JTextArea t, JTextField f) {
        if ((t.getText().trim().length() != 0) && (f.getText().trim().length() != 0)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * store the initial project information on database.
     * @see DatabaseConnection
     */
    public void storeNewProject() {
        startFlag = true;
        if (!isRepeatedFile(dbConnection.getQueryRes("select name from project"))) {
            if (isValidTextSource(projectDescription, projectName)) {
                try {
                    stmt = dbConnection.getConnection().createStatement();
                    stmt.executeUpdate("insert into project ('name','desc') values('"
                            + projectName.getText() + "','" + projectDescription.getText()
                            + "')");
                    newProjectDialog.dispose();
                    projectName.setText("");
                    projectDescription.setText("");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Loading Data Error", "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            } else {
                startFlag = false;
                JOptionPane.showMessageDialog(null, "Please Complete all Fields", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "There is a project with name: " + projectName.getText(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public boolean isRepeatedFile(Vector listElement) {
        for (int i = 0; i < listElement.size(); i++) {
            if (listElement.get(i).equals(projectName.getText())) {
                return true;
            }
        }
        return false;
    }
    public boolean getStartFlag(){
        return startFlag;
    }
}
