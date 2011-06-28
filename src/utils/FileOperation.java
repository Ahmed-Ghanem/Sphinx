package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Ahmed Ghanme
 */
public class FileOperation {

    private File file;

    public FileOperation() {
    }

    public String[] readElemetns(String path) {
        String[] elemetns = null;
        Scanner input = null;
        try {
            file = new File(path);
            input = new Scanner(file);
            elemetns = new String[3];
            for (int i = 0; i < elemetns.length; i++) {
                elemetns[i] = input.nextLine();
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Data not found", "info", JOptionPane.ERROR_MESSAGE);
        } finally {
            input.close();
        }
        return elemetns;
    }

    public void writeElements(String path, Object alarmSession, Object alarmRest, Object tone) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File(path));
            writer.println(alarmSession);
            writer.println(alarmRest);
            writer.println(tone);
            JOptionPane.showMessageDialog(null, "Data Saved ..", "info", JOptionPane.INFORMATION_MESSAGE);

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Data not found", "info", JOptionPane.ERROR_MESSAGE);
        } finally {
            writer.close();
        }
    }

    public String openFilterFileChooser(String fileName) {
        JFileChooser fileChooser = null;
        File file = null;
        fileChooser = new JFileChooser();
        String filePath = null;
        int choice = fileChooser.showOpenDialog(fileChooser);
        if (choice == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            if (file.getName().equals(fileName)) {
                filePath = file.getAbsolutePath();
            } else {
                JOptionPane.showMessageDialog(null, "please choose Sphinx.dat File", "Info", JOptionPane.ERROR_MESSAGE);
                filePath = null;
            }
        }
        return filePath;
    }

    public String saveFilterFileChooser(String name) {
        JFileChooser saveFileChooser = null;
        File file = null;
        String filePath = null;
        saveFileChooser = new JFileChooser();
        saveFileChooser.setSelectedFile(new File(name));
        int choice = saveFileChooser.showSaveDialog(saveFileChooser); //file.ifc
        if (choice == JFileChooser.APPROVE_OPTION) {
            file = null;
            file = new File(saveFileChooser.getSelectedFile().getAbsolutePath());
            filePath = file.getAbsolutePath();
        }
        return filePath;
    }
}