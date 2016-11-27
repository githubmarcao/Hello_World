package gasogen.com.br.gasogen.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import gasogen.com.br.gasogen.modelo.Preco;
import gasogen.com.br.gasogen.util.DataUtil;

/**
 * Created by Dell on 11/11/2016.
 */
public class PrecoDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String TABELA = "preco";
    private static final String DATABASE = "GasoGen";
    private Context context;

    public PrecoDAO(Context context) {
        super(context, TABELA, null, VERSAO);
        context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA +
                " (id INTEGER PRIMARY KEY," +
                " valor REAL," +
                " data DATETIME DEFAULT CURRENT_TIMESTAMP," +
                " id_posto INTEGER," +
                " FOREIGN KEY(id_posto) REFERENCES posto(id));";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Nao fazer mais isso, isso era so para testarmos local
//        String sql = "DROP TABLE IF EXITS " + TABELA;
//        db.execSQL(sql);
//        onCreate(db);
        switch (oldVersion) {
            case 1:
//                String sql = "ALTER TABLE " + TABELA + " ADD COLUMN caminhoFoto TEXT;";
//                db.execSQL(sql);
            case 2:
                // E por ai vai..
            case 3:
                // Desta forma e bom pois ele vai descendo no switch
        }
    }

    private void insere(Preco preco) {
        ContentValues values = new ContentValues();

        values.put("valor", preco.getValor());
        values.put("id_posto", preco.getPosto().getId());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public List<Preco> getPrecos() {
        List<Preco> precos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+TABELA+";", null);

        try {
            while (c.moveToNext()) {
                Preco preco = new Preco();

                preco.setId(c.getLong(c.getColumnIndex("id")));
                preco.setValor(c.getDouble(c.getColumnIndex("valor")));
                preco.setData(DataUtil.getDateTime(c.getString(c.getColumnIndex("data"))));

                // Obter o posto
                PostoDAO dao = new PostoDAO(this.context);
                preco.setPosto(dao.get(c.getLong(c.getColumnIndex("id_posto"))));
                dao.close();

                precos.add(preco);
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return precos;
    }

    public void deletar(Preco preco) {
        String[] param = {preco.getId().toString()};
        getWritableDatabase().delete(TABELA, "id=?", param);
    }

    private void edita(Preco preco) {
        ContentValues values = new ContentValues();
        String[] param = {preco.getId().toString()};

        values.put("valor", preco.getValor());
        values.put("data", DataUtil.getDateTime(preco.getData()));
        values.put("id_posto", preco.getPosto().getId());

        // Editar o posto
        PostoDAO dao = new PostoDAO(this.context);
        dao.insereOuAtualiza(preco.getPosto());
        dao.close();

        getWritableDatabase().update(TABELA, values, "id=?", param);
    }

    public void insereOuAtualiza(Preco preco) {
        if (preco != null) {
            if (preco.getId() == null) {
                insere(preco);
            } else {
                edita(preco);
            }
        }
    }
}
