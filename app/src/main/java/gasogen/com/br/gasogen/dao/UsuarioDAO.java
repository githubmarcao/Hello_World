package gasogen.com.br.gasogen.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import gasogen.com.br.gasogen.modelo.Usuario;

/**
 * Created by Dell on 11/11/2016.
 */
public class UsuarioDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String TABELA = "Usuario";
    private static final String DATABASE = "GasoGen";

    public UsuarioDAO(Context context) {
        super(context, TABELA, null, VERSAO);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA +
                " (id INTEGER PRIMARY KEY," +
                " login TEXT NOT NULL," +
                " senha TEXT," +
                " nome TEXT," +
                " email TEXT," +
                " telefone TEXT" +
                ");";

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

    private void insere(Usuario usuario) {
        ContentValues values = new ContentValues();

        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());
        values.put("telefone", usuario.getTelefone());

        getWritableDatabase().insert(TABELA, null, values);
    }

//    public List<Posto> getPostos() {
//        List<Posto> postos = new ArrayList<>();
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM "+TABELA+";", null);
//
//        try {
//            while (c.moveToNext()) {
//                Posto posto = new Posto();
//
//                posto.setId(c.getLong(c.getColumnIndex("id")));
//                posto.setNome(c.getString(c.getColumnIndex("nome")));
//                posto.setDescricao(c.getString(c.getColumnIndex("descricao")));
//                posto.setLatitude(c.getLong(c.getColumnIndex("latitude")));
//                posto.setLongitude(c.getLong(c.getColumnIndex("longitude")));
//
//                postos.add(posto);
//            }
//        } finally {
//            if (c != null) {
//                c.close();
//            }
//        }
//
//        return postos;
//    }

    public Usuario getUsuario(Long id) {
        Usuario usuario = new Usuario();
        SQLiteDatabase db = getReadableDatabase();
        String[] param = {id.toString()};
        Cursor c = db.rawQuery("SELECT * FROM "+TABELA+" WHERE id=?;", param);

        try {
            if (c.moveToNext()) {
                usuario.setId(c.getLong(c.getColumnIndex("id")));
                usuario.setLogin(c.getString(c.getColumnIndex("login")));
                usuario.setSenha(c.getString(c.getColumnIndex("senha")));
                usuario.setNome(c.getString(c.getColumnIndex("nome")));
                usuario.setEmail(c.getString(c.getColumnIndex("email")));
                usuario.setTelefone(c.getString(c.getColumnIndex("telefone")));
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return usuario;
    }

    public Usuario getUsuarioAutenticacao(String login, String senha) {
        Usuario usuario = new Usuario();
        SQLiteDatabase db = getReadableDatabase();
        String[] param = {login, login, login, senha};
        Cursor c = db.rawQuery("SELECT * FROM "+TABELA+" WHERE (login=? OR email=? OR telefone=?) AND (senha=?);", param);

        try {
            if (c.moveToNext()) {
                usuario.setId(c.getLong(c.getColumnIndex("id")));
                usuario.setLogin(c.getString(c.getColumnIndex("login")));
                usuario.setSenha(c.getString(c.getColumnIndex("senha")));
                usuario.setNome(c.getString(c.getColumnIndex("nome")));
                usuario.setEmail(c.getString(c.getColumnIndex("email")));
                usuario.setTelefone(c.getString(c.getColumnIndex("telefone")));
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return usuario;
    }

    public void deletar(Usuario usuario) {
        String[] param = {usuario.getId().toString()};
        getWritableDatabase().delete(TABELA, "id=?", param);
    }

    private void editar(Usuario usuario) {
        ContentValues values = new ContentValues();
        String[] param = {usuario.getId().toString()};

        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());
        values.put("telefone", usuario.getTelefone());

        getWritableDatabase().update(TABELA, values, "id=?", param);
    }

    public void insereOuAtualiza(Usuario usuario) {
        if (usuario != null) {
            if (usuario.getId() == null) {
                insere(usuario);
            } else {
                editar(usuario);
            }
        }
    }
}
