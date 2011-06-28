package chart;

/**
 * Sphinx2DPieChart.java.
 * Created on 6-Mar-2011, 1:16:01AM.
 */
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import utils.Time;

public class Sphinx3DPieChart {

    private Time time;

    public Sphinx3DPieChart() {
        time = new Time();
    }

    public PieDataset createPieDataSet(Vector<Comparable> key, Vector<String> value) {
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        for (int i = 0; i < key.size(); i++) {
            pieDataset.setValue(key.get(i), time.gettimeValue(value.get(i)));
        }
        return pieDataset;
    }

    public JFreeChart create3DPieChart(PieDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createPieChart3D(
                title, dataset, true, true, true);

        PiePlot3D p = (PiePlot3D) chart.getPlot();
        p.setForegroundAlpha(0.5f);
        p.setBackgroundAlpha(0.2f);

        chart.setBackgroundPaint(Color.white);
        chart.setAntiAlias(true);
        chart.setBorderVisible(true);

        return chart;

    }

    public void saveChart(JFreeChart chart, String fileLocation) {
        String fileName = fileLocation;
        try {
            ChartUtilities.saveChartAsJPEG(new File(fileName), chart, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Problem occurred creating chart.");
        }
    }
}