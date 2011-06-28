package tasks;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/**
 *
 * @author Ahmed Ghanem
 */
public class CopyFileTask implements Runnable {

    private int currentValue;
    private JProgressBar bar;
    private String copyFrom;
    private String copyTo;
    private JDialog dialog;

    public CopyFileTask(String f, String t) {
        this.copyFrom = f;
        this.copyTo = t;
    }

    public void progressRef(JProgressBar bar, JDialog d) {
        this.bar = bar;
        this.dialog = d;
    }

    public void run() {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            // Create file input stream
            File inFile = new File(copyFrom);
            in = new BufferedInputStream(new FileInputStream(inFile));

            // Create file output stream
            File outFile = new File(copyTo);
            out = new BufferedOutputStream(new FileOutputStream(outFile));

            // Get total bytes in the file
            long totalBytes = in.available();

            // Start progress meter bar
            bar.setValue(0);
            bar.setMaximum(100);

            int r;
            long bytesRead = 0;
            // You may increase buffer size to improve IO speed
            byte[] b = new byte[10];
            while ((r = in.read(b, 0, b.length)) != -1) {
                out.write(b, 0, r);
                bytesRead += r;
                currentValue = (int) (bytesRead * 100 / totalBytes);

                // Update the progress bar
                bar.setValue(currentValue);
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Data not found", "info", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Data not found", "info", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            dialog.dispose();
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Exception ex) {
            }
        }
    }
}
