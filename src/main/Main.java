package main;

/**
 * Main.java.
 * Created on 26-Feb-2011, 1:16:01AM.
 */
import utils.SplashScreen;
import com.sun.corba.se.spi.activation.Server;
import gui.ProjectChart;
import gui.SphinxMainFrame;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import manager.MusicManager;
import utils.ConstantManager;
import utils.Time;

/**
 * Main class for sphinx with look and feel initialization.
 * @author Ahmed Gahenm.
 * class Methods:-
 * there is no methods
 * @see SphinxMainFrame.
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedLookAndFeelException, IOException {
        
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        SplashScreen splash = new SplashScreen();
        splash.showSplash(ConstantManager.SPHINX_SPLASH_TIME);
        
    }
}
