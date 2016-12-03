package gasogen.com.br.gasogen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import gasogen.com.br.gasogen.adapter.ListaUsuariosAdapter;
import gasogen.com.br.gasogen.dao.UsuarioDAO;
import gasogen.com.br.gasogen.modelo.Usuario;
import gasogen.com.br.gasogen.util.Constantes;

public class ListaUsuariosActivity extends AppCompatActivity {

    private ListView listaUsuarios;
    private List<Usuario> usuarios;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        UsuarioDAO dao = new UsuarioDAO(this);
        usuarios = dao.getUsuarios();
        dao.close();

        ListaUsuariosAdapter adapter = new ListaUsuariosAdapter(this, usuarios);
        this.listaUsuarios = (ListView) findViewById(R.id.lista_usuarios);
        this.listaUsuarios.setAdapter(adapter);

        Button botaoAdiciona = (Button) findViewById(R.id.lista_usuarios_floating_button);
        botaoAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaUsuariosActivity.this, UsuarioActivity.class);
                startActivity(intent);
            }
        });

        this.listaUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Intent intent = new Intent(ListaUsuariosActivity.this, UsuarioActivity.class);
                intent.putExtra(Constantes.USUARIO_SELECIONADO, (Usuario) adapter.getItemAtPosition(position));
                startActivity(intent);
            }
        });

        this.listaUsuarios.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                // TODO: Opcão de excluir
                Toast.makeText(ListaUsuariosActivity.this, "Em Breve opção de Excluir: "
                        + ((Usuario) adapter.getItemAtPosition(position)).getNome() + ".", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }
}
