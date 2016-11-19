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

import gasogen.com.br.gasogen.adapter.ListaPostosAdapter;
import gasogen.com.br.gasogen.dao.PostoDAO;
import gasogen.com.br.gasogen.modelo.Posto;
import gasogen.com.br.gasogen.util.Constantes;

public class ListaPostosActivity extends AppCompatActivity {

    private ListView listaPostos;
    private List<Posto> postos;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_postos);

        PostoDAO dao = new PostoDAO(this);
        postos = dao.getPostos();
        dao.close();

        ListaPostosAdapter adapter = new ListaPostosAdapter(this, postos);
        this.listaPostos = (ListView) findViewById(R.id.lista_postos);
        this.listaPostos.setAdapter(adapter);

        Button botaoAdiciona = (Button) findViewById(R.id.lista_postos_floating_button);
        botaoAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaPostosActivity.this, PostoActivity.class);
                startActivity(intent);
            }
        });

        this.listaPostos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Intent intent = new Intent(ListaPostosActivity.this, PostoActivity.class);
                intent.putExtra(Constantes.POSTO_SELECIONADO, (Posto) adapter.getItemAtPosition(position));
                startActivity(intent);
            }
        });

        this.listaPostos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                // TODO: Opcão de excluir
                Toast.makeText(ListaPostosActivity.this, "Em Breve opção de Excluir: "
                        + ((Posto) adapter.getItemAtPosition(position)).getNome() + ".", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }
}
