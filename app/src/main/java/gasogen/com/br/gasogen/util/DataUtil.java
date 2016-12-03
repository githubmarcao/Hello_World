package gasogen.com.br.gasogen.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Dell on 11/11/2016.
 */

public class DataUtil {

    public static final String FORMATO_BANCO_DE_DADOS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMATO_TELA = "dd-MM-yyyy HH:mm:ss";
    public static final String FORMATO_TELA_DATA = "dd/MM/yyyy";

    // VER NA HORA DE SALVAR A DATA
    public static String getDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                FORMATO_TELA_DATA, Locale.getDefault());
        if (date == null) {
            date = new Date();
        }
        return dateFormat.format(date);
    }
    public static String getDateTimeSalvarNoBanco(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                FORMATO_BANCO_DE_DADOS, Locale.getDefault());
        if (date == null) {
            date = new Date();
        }
        return dateFormat.format(date);
    }
    public static Date getDateTime(String data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                FORMATO_TELA_DATA, Locale.getDefault());
        try {
            return (Date) dateFormat.parse(data);
        } catch (Exception e) {
            Log.i("DataUtil", "Problema ao converter a data: " + data);
        }
        return null;
    }
    public static Date getDateTimeVindaDoBanco(String data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                FORMATO_BANCO_DE_DADOS, Locale.getDefault());
        try {
            return (Date) dateFormat.parse(data);
        } catch (Exception e) {
            Log.i("DataUtil", "Problema ao converter a data: " + data);
        }
        return null;
    }
}