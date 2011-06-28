package utils;
/**
 * ConstantManager.java
 * Created on 28-Feb-2011, 12:16:00AM
 */
import custom.CustomDate;
import javax.swing.ImageIcon;

/**
 * responsible for manage constants on Sphinx.
 * @author Ahmed Gahenm.
 * class Methods:-
 * There is no methods.
 */
public class ConstantManager {

    /**
     * Return Sphinx name and version.
     */
    public final static String SPHNIX_TITLE = "Sphinx 1.7";
    /**
     * Return Sphinx icon.
     */
    public final static String SPHNIX_ICON_PATH = "icons/sphinxIcon.gif";
    /**
     * Return Sphinx web site URL.
     */
    public final static String SPHNIX_URL = "http://ja-sphinx.blogspot.com/";
    /**
     * Return Sphinx splash screen.
     */
    public final static String SPHINX_SPLASH_LOGO = "data/splash.jpg";
    /**
     * Return allowed time for splash screen
     */
    public final static int SPHINX_SPLASH_TIME = 3000;
    /**
     * Return driver name to run using Class.forName()
     */
    public final static String SQLITE_DRIVER = "org.sqlite.JDBC";
    /**
     * Return Sqlite database url.
     */
    public final static String SQLITE_DB_URL = "jdbc:sqlite:data/Sphinx.dat";
    /**
     * jasper report path.
     */
    public final static String JASPER_PATH = "SphinxJSP/SphinxJAS.jasper";
     /**
     * Return the allowed file extensions allowed in Sphinx
     */
    public final static String[] ALLOWED_FILE_EXTENTIONS = {".jpg",".dat"};

}
