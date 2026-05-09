// CSVUtils safely converts CSV string fields into numeric values.
public class CSVUtils {

    // Convert text to int, returning 0 if the field is invalid.
    public static int toInt(String s) {
        try { return Integer.parseInt(s.trim()); }
        catch (Exception e) { return 0; }
    }

    // Convert text to double, returning 0.0 if the field is invalid.
    public static double toDouble(String s) {
        try { return Double.parseDouble(s.trim()); }
        catch (Exception e) { return 0.0; }
    }
}
