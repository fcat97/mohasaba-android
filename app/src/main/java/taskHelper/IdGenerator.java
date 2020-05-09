package taskHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class IdGenerator {

    public static Long generate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US);
        Date dNow = new Date( );
        return Long.parseLong(dateFormat.format(dNow));
    }
}