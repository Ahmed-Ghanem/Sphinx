package custom;

/**
 * CustomDate.java.
 * Created on 22-Feb-2011, 1:16:01AM.
 */
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * responsible for Date in Sphinx.
 * @author Ahmed Gahenm.
 * class Methods:-
 * 1- applyFormat(SimpleDateFormat format) => apply a specific format on the current date object.
 * 2- applySphinxTimeFormate() => apply a specific format on Date and return as String.
 * 3- applySphinxDayFormate() => apply day formate to the date.
 * @see Date.
 * @see SimpleDateFormat.
 */
public class CustomDate extends Date {

    public CustomDate() {
        super();
    }

    /**
     *
     * @param format format that user on the date
     * @return date with this format
     */
    public String applyFormat(SimpleDateFormat format) {
        return format.format(this);
    }

    public String applySphinxTimeFormate() {
        return applyFormat(new SimpleDateFormat("hh:mm:ss"));
    }

    public String applySphinxDayFormate() {
        return applyFormat(new SimpleDateFormat("dd/MM/yyyy"));
    }
}
