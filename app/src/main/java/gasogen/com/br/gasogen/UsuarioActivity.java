package gasogen.com.br.gasogen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import gasogen.com.br.gasogen.dao.UsuarioDAO;
import gasogen.com.br.gasogen.modelo.Usuario;
import gasogen.com.br.gasogen.util.Constantes;

public class UsuarioActivity extends AppCompatActivity {

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        Intent intent = getIntent();
        this.usuario = (Usuario) intent.getSerializableExtra(Constantes.USUARIO_SELECIONADO);
        if (this.usuario != null) {
            ((EditText) findViewById(R.id.item_usuario_login)).setText(this.usuario.getLogin());
            ((EditText) findViewById(R.id.item_usuario_senha)).setText(this.usuario.getSenha());
            ((EditText) findViewById(R.id.item_usuario_nome)).setText(this.usuario.getNome());
            ((EditText) findViewById(R.id.item_usuario_email)).setText(this.usuario.getEmail());
            ((EditText) findViewById(R.id.item_usuario_telefone)).setText(this.usuario.getTelefone());
        } else {
            this.usuario = new Usuario();
        }

        Button botaoSalvar = (Button) findViewById(R.id.item_usuario_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.setLogin(((EditText) findViewById(R.id.item_usuario_login)).getText().toString());
                usuario.setSenha(((EditText) findViewById(R.id.item_usuario_senha)).getText().toString());
                usuario.setNome(((EditText) findViewById(R.id.item_usuario_nome)).getText().toString());
                usuario.setEmail(((EditText) findViewById(R.id.item_usuario_email)).getText().toString());
                usuario.setTelefone(((EditText) findViewById(R.id.item_usuario_telefone)).getText().toString());

                if (!validaUsuario(usuario)) {
                    return;
                }

                UsuarioDAO dao = new UsuarioDAO(UsuarioActivity.this);
                dao.insereOuAtualiza(usuario);

                if (usuario.getId() == null || usuario.getId() == 0) {
                    Toast.makeText(UsuarioActivity.this, "'" + usuario.getNome() + "' inserido.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UsuarioActivity.this, "'" + usuario.getNome() + "' editado.", Toast.LENGTH_SHORT).show();
                }
                dao.close();

                Intent intent = new Intent(UsuarioActivity.this, ListaUsuariosActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validaUsuario(Usuario usuario) {
        if (usuario.getLogin() == null || usuario.getLogin().isEmpty()) {
            Toast.makeText(UsuarioActivity.this, "Digite o login do usuario para continuar.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
