package gasogen.com.br.gasogen.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import gasogen.com.br.gasogen.modelo.Posto;

/**
 * Created by Dell on 11/11/2016.
 */
public class PostoDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 0;
    private static final String TABELA = "Posto";
    private static final String DATABASE = "GasoGen";

    public PostoDAO(Context context) {
        super(context, TABELA, null, VERSAO);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA +
                " (id INTEGER PRIMARY KEY," +
                " nome TEXT NOT NULL," +
                " descricao TEXT," +
                " latitude REAL," +
                " longitude REAL);";

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
                //String sql = "ALTER TABLE " + TABELA + " ADD COLUMN caminhoFoto TEXT;";
                //db.execSQL(sql);
            case 2:
                // E por ai vai..
            case 3:
                // Desta forma e bom pois ele vai descendo no switch
        }
    }

    private void insere(Posto posto) {
        ContentValues values = new ContentValues();

        values.put("nome", posto.getNome());
        values.put("descricao", posto.getDescricao());
        values.put("latitude", posto.getLatitude());
        values.put("longitude", posto.getLongitude());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public List<Posto> getPostos() {
        List<Posto> postos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+TABELA+";", null);

        try {
            while (c.moveToNext()) {
                Posto posto = new Posto();

                posto.setId(c.getLong(c.getColumnIndex("id")));
                posto.setNome(c.getString(c.getColumnIndex("nome")));
                posto.setDescricao(c.getString(c.getColumnIndex("descricao")));
                posto.setLatitude(c.getLong(c.getColumnIndex("latitude")));
                posto.setLongitude(c.getLong(c.getColumnIndex("longitude")));

                postos.add(posto);
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return postos;
    }

    public Posto getPosto(Long id) {
        Posto posto = new Posto();
        SQLiteDatabase db = getReadableDatabase();
        String[] param = {id.toString()};
        Cursor c = db.rawQuery("SELECT * FROM "+TABELA+" WHERE id=?;", param);

        try {
            if (c.moveToNext()) {
                posto.setId(c.getLong(c.getColumnIndex("id")));
                posto.setNome(c.getString(c.getColumnIndex("nome")));
                posto.setDescricao(c.getString(c.getColumnIndex("descricao")));
                posto.setLatitude(c.getLong(c.getColumnIndex("latitude")));
                posto.setLongitude(c.getLong(c.getColumnIndex("longitude")));
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return posto;
    }

    public void deletar(Posto posto) {
        String[] param = {posto.getId().toString()};
        getWritableDatabase().delete(TABELA, "id=?", param);
    }

    private void editar(Posto posto) {
        ContentValues values = new ContentValues();
        String[] param = {posto.getId().toString()};

        values.put("nome", posto.getNome());
        values.put("descricao", posto.getDescricao());
        values.put("latitude", posto.getLatitude());
        values.put("longitude", posto.getLongitude());

        getWritableDatabase().update(TABELA, values, "id=?", param);
    }

    public void insereOuAtualiza(Posto posto) {
        if (posto != null) {
            if (posto.getId() == null) {
                insere(posto);
            } else {
                editar(posto);
            }
        }
    }

    //+557197238843 - telefone de Marcio
    public boolean existsTelefone(String telefone) {
        Cursor c = getReadableDatabase().rawQuery("SELECT 1 FROM " + TABELA + " WHERE telefone = ?", new String[]{telefone});
        boolean resultado = c.getCount() > 0;
        c.close();

        return resultado;
    }
}
