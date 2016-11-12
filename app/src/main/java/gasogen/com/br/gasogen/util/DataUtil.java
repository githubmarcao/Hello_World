package gasogen.com.br.gasogen.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Dell on 11/11/2016.
 */

public class DataUtil {
    public static String getDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        if (date == null) {
            date = new Date();
        }
        return dateFormat.format(date);
    }
    public static Date getDateTime(String data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date retorno = null;
        try {
            retorno = (Date) dateFormat.parse(data);
        } catch (Exception e) {
            Log.i("DataUtil", "Problema ao converter a data: " + data);
        }
        return retorno;
    }
}