package manager;

/**
 * ProjectManager.java.
 * Created on 1-Mar-2011, 1:16:01AM.
 */
import database.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import utils.Time;

/**
 * responsible for controlling Sphinx tables m.
 * @author Ahmed Gahenm.
 * class Methods:-
 * 1- tableRef(JTable t, DefaultTableModel m)=> references to main table and it's model.
 * 2- clearVector() => clearing all vectors to repaint a new table.
 * 3-  updateTableData(String query) => update the table with new query.
 * @see NewProject
 */
public class TableManager {

    private DatabaseConnection dbConnection;
    private Statement stmt;
    private ResultSet result;
    private ResultSet summryResult;
    private DefaultTableModel tableModel;
    private JTable projectsTable;
    private JTable summryTable;
    private DefaultTableModel summryModel;
    private Vector singleRow;
    private Vector dataRows;
    private Vector colHeaders;
    private Vector summrySingleRow;
    private Vector summryDataRows;
    private Vector summryColHeaders;
    private int colsCount;

    public TableManager() {
    }

    public TableManager(String url) {
        dbConnection = new DatabaseConnection(url);
        dataRows = new Vector();
        colHeaders = new Vector();
        summryDataRows = new Vector();
        summryColHeaders = new Vector();
    }

    public void tableRef(JTable t, DefaultTableModel m, JTable t1) {
        this.projectsTable = t;
        this.tableModel = m;
        this.summryTable = t1;
        summryModel = (DefaultTableModel) summryTable.getModel();
    }

    public void clearVector() {
        dataRows.clear();
        colHeaders.clear();
    }

    public void summryClearVector() {
        summryDataRows.clear();
        summryColHeaders.clear();
    }

    public void updateProjectsTableData(String query) {
        clearVector();
        tableModel.setDataVector(dataRows, colHeaders);
        try {
            stmt = dbConnection.getConnection().createStatement();
            result = stmt.executeQuery(query);
            colsCount = result.getMetaData().getColumnCount();
            while (result.next()) {
                singleRow = new Vector();
                for (int i = 0; i < colsCount; i++) { // Store cells to a row
                    singleRow.addElement(result.getObject(i + 1));
                }
                dataRows.addElement(singleRow);
            }
            //table headres
            colHeaders.add("Project");
            colHeaders.add("Day");
            colHeaders.add("Start");
            colHeaders.add("End");
            colHeaders.add("Activity");
            colHeaders.add("Duration");
            tableModel.setDataVector(dataRows, colHeaders);


        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Loading Data Error", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        updateSummryTableData();
    }

    public void updateSummryTableData() {
        summryClearVector();
        summryModel.setDataVector(summryDataRows, summryColHeaders);
        try {
            stmt = dbConnection.getConnection().createStatement();
            summryResult = stmt.executeQuery("select name from project");
            colsCount = summryResult.getMetaData().getColumnCount();
            while (summryResult.next()) {
                summrySingleRow = new Vector();
                for (int i = 0; i < colsCount; i++) { // Store cells to a row
                    summrySingleRow.addElement(summryResult.getObject(i + 1));
                }
                summryDataRows.addElement(summrySingleRow);
            }
            //table headres
            summryColHeaders.add("Project");
            summryColHeaders.add("Total Duration");
            summryModel.setDataVector(summryDataRows, summryColHeaders);


        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Loading Data Error", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        totalTime();
    }

    public void totalTime() {
        Time time = null;
        time = new Time();
        for (int i = 0; i < summryTable.getRowCount(); i++) {
            String total = time.addMoreTimes(dbConnection.getQueryRes(
                    "select duration from activities where id = (select id from project where name = '" + summryTable.getValueAt(i, 0) + "')"));
            summryTable.setValueAt(total, i, 1);
        }
    }
}
