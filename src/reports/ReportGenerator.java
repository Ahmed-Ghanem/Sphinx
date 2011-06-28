
package reports;
/**
 * ReportGenerator.java.
 * Created on 1-Mar-2011, 1:16:01AM.
 */
import database.DatabaseConnection;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import utils.ConstantManager;

/**
 * responsible for controlling and generate a new report .
 * @author Ahmed Gahenm.
 * class Methods:-
 * 1- showReport(String idParam) => show abstract report with data.
 * @see JasperFillManager
 * @see JasperPrint
 * @see JasperViewer
 */
public class ReportGenerator {

    private DatabaseConnection dbConnection;
    private HashMap map;
    private Connection connection;
    private JasperPrint jasper;
    private JasperViewer jViewer;

    public ReportGenerator(String url) {
        dbConnection = new DatabaseConnection(url);
        connection = dbConnection.getConnection();
        map = new HashMap();
    }

    public void showReport(String idParam) {
        try {
            map.put("idParam", idParam);
            jasper = JasperFillManager.fillReport(ConstantManager.JASPER_PATH, map, connection);
            JasperViewer.viewReport(jasper,false);
            jViewer = new JasperViewer(jasper);
            jViewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


            //jViewer.viewReport(jasper);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Report important files missing", "Info", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
