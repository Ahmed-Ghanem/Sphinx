package utils;

import gui.About;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ahmed Ghanem
 */
public class MyTrayIcon {

    private TrayIcon trayIcon;
    private SystemTray systemTray;
    private MenuItem showItem;
    private MenuItem exitItem;
    private MenuItem aboutItem;
    private TrayIconListener trayIconListener;
    private About about = new About(new JFrame(), true);
    private JFrame mainFrame;

    public MyTrayIcon(Image image) {
        makeTrayIcon(image);
    }

    public void guiComponent(JFrame f) {
        this.mainFrame = f;
    }

    private void makeTrayIcon(Image image) {
        trayIconListener = new TrayIconListener();
        try {
            if (!SystemTray.isSupported()) {
                JOptionPane.showMessageDialog(null, "SystemTray is not supported");
                return;
            }
            trayIcon = new TrayIcon(image, "Sphinx", getMyPopupMenu());
            systemTray = SystemTray.getSystemTray();
            systemTray.add(trayIcon);
            trayIcon.displayMessage("INFO MESSAGE",
                    "Sphinx Running on System Tray", TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
    }

    private PopupMenu getMyPopupMenu() {
        PopupMenu popupMenu = new PopupMenu();
        showItem = new MenuItem("Show Sphinx");
        showItem.addActionListener(trayIconListener);
        popupMenu.add(showItem);
        aboutItem = new MenuItem("About");
        aboutItem.addActionListener(trayIconListener);
        popupMenu.addSeparator();
        popupMenu.add(aboutItem);
        ////////////////////////////////
        exitItem = new MenuItem("Exit");
        exitItem.addActionListener(trayIconListener);
        popupMenu.add(exitItem);
        ////////////////////////////////
        return popupMenu;
    }

    class TrayIconListener implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            MenuItem item = (MenuItem) evt.getSource();
            if (item.getLabel().equals(showItem.getLabel())) {
                systemTray.remove(trayIcon);
                mainFrame.setVisible(true);
            } else if (item.getLabel().equals(aboutItem.getLabel())) {
                about.show();
            } else if (item.getLabel().equals(exitItem.getLabel())) {
                systemTray.remove(trayIcon);
                System.exit(0);
            }
        }
    }
}
