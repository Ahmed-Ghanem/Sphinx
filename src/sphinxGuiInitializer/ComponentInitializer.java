package sphinxGuiInitializer;

/**
 * ComponentInitializer.java.
 * Created on 1-Mar-2011, 1:16:01AM.
 */
import database.DatabaseConnection;
import gui.SphinxMainFrame;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * responsible for initializing the data to gui components.
 * @author Ahmed Gahenm.
 * class Methods:-
 * 1- guiComponents(JComboBox c)=> set the main combo.
 * 2- guiComponents(JTextField f, JTextArea t) => new project components components .
 * 3- guiComponents(SphinxMainFrame f)=> set the main frame.
 * 4- projectInitializer() => initialize the main combo with DB values.
 * 5- projectNameDescriptionInitializer() => set the name and description.
 * 6- update()=> update the values to edit the projects
 * 7- updateProject() = > store new updated values to the datebase
 * 8- isValidTextSource(JTextArea t, JTextField f) > there is any repeated withe the names in DB.
 * @see NewProject
 */
public class ComponentInitializer {

    private JComboBox projects;
    private JTextArea projectDescription;
    private JTextField projectName;
    private DatabaseConnection dbConnection;
    private Statement stmt;
    private SphinxMainFrame mainFrame;

    public ComponentInitializer() {
    }

    public ComponentInitializer(String url) {
        dbConnection = new DatabaseConnection(url);
    }

    public void guiComponents(JComboBox c) {
        this.projects = c;
    }

    public void guiComponents(JTextField f, JTextArea t) {
        projectName = f;
        projectDescription = t;
    }

    public void guiComponents(SphinxMainFrame f) {
        mainFrame = f;
    }

    public void projectInitializer() {
        String projectsQuery = "select name from project";
        projects.setModel(
                new javax.swing.DefaultComboBoxModel(
                dbConnection.getQueryRes(projectsQuery)));
    }

    public void projectNameDescriptionInitializer() {
        update();
    }

    public void update() {
        String projectsQuery = null;
        String selectedProject = null;
        projectsQuery = "select name from project where name = '" + projects.getSelectedItem() + "'";
        try {
            selectedProject = (String) (dbConnection.getQueryRes(projectsQuery)).get(0);
            if (selectedProject != null) {
                projectName.setText(selectedProject);
                projectsQuery = "select desc from project where name = '" + projects.getSelectedItem() + "'";
                projectDescription.setText((String) (dbConnection.getQueryRes(projectsQuery)).get(0));

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            projectName.setText("");
            projectDescription.setText("");
        }

    }

    public void updateProject() {
        if (isValidTextSource(projectDescription, projectName)) {
            try {
                stmt = dbConnection.getConnection().createStatement();
                stmt.executeUpdate("update project set name = '" + projectName.getText()
                        + "',desc = '" + projectDescription.getText()
                        + "' where name = '" + projects.getSelectedItem() + "'");
                projectInitializer();
                JOptionPane.showMessageDialog(null, "Projects updated", "Ingo", JOptionPane.INFORMATION_MESSAGE);
                update();
                projectInitializer();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Loading Data Error", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please Complete all Fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isValidTextSource(JTextArea t, JTextField f) {
        if ((t.getText().trim().length() != 0) && (f.getText().trim().length() != 0)) {
            return true;
        } else {
            return false;
        }
    }
}
