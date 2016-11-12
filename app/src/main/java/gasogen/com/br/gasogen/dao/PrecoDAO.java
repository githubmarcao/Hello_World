package gasogen.com.br.gasogen.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import gasogen.com.br.gasogen.modelo.Preco;

/**
 * Created by Dell on 11/11/2016.
 */
public class PrecoDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 0;
    private static final String TABELA = "Preco";
    private static final String DATABASE = "GasoGen";

    public PrecoDAO(Context context) {
        super(context, TABELA, null, VERSAO);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA +
                " (id INTEGER PRIMARY KEY," +
                " valor REAL," +
                " data DATETIME DEFAULT CURRENT_TIMESTAMP," +
                " FOREIGN KEY(id_posto) REFERENCES Posto(id));";

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
        values.put("data", preco.getData());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public List<Aluno> getAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM alunos;", null);

        try {
            while (c.moveToNext()) {
                Aluno aluno = new Aluno();

                aluno.setId(c.getLong(c.getColumnIndex("id")));
                aluno.setNome(c.getString(c.getColumnIndex("nome")));
                aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
                aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
                aluno.setSite(c.getString(c.getColumnIndex("site")));
                aluno.setNota(c.getDouble(c.getColumnIndex("nota")));
                aluno.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));

                alunos.add(aluno);
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return alunos;
    }

    public void deletar(Aluno aluno) {
        String[] param = {aluno.getId().toString()};
        getWritableDatabase().delete(TABELA, "id=?", param);
    }

    private void edita(Aluno aluno) {
        ContentValues values = new ContentValues();
        String[] param = {aluno.getId().toString()};

        values.put("nome", aluno.getNome());
        values.put("telefone", aluno.getTelefone());
        values.put("endereco", aluno.getEndereco());
        values.put("site", aluno.getSite());
        values.put("nota", aluno.getNota());
        values.put("caminhoFoto", aluno.getCaminhoFoto());

        getWritableDatabase().update(TABELA, values, "id=?", param);
    }

    public void insereOuAtualiza(Aluno aluno) {
        if (aluno != null) {
            if (aluno.getId() == null) {
                insere(aluno);
            } else {
                edita(aluno);
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
