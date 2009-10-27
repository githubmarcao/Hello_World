package gasogen.com.br.gasogen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import gasogen.com.br.gasogen.adapter.ListaPrecosAdapter;
import gasogen.com.br.gasogen.dao.PrecoDAO;
import gasogen.com.br.gasogen.modelo.Preco;

public class ListaPrecosActivity extends AppCompatActivity {

    private ListView listaPrecos;
    private List<Preco> precos;
    private static final String PRECO_SELECIONADO = "precoSelecionado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_precos);

        PrecoDAO dao = new PrecoDAO(this);
        precos = dao.getPrecos();
        dao.close();

        ListaPrecosAdapter adapter = new ListaPrecosAdapter(this, precos);
        this.listaPrecos = (ListView) findViewById(R.id.lista_precos);
        this.listaPrecos.setAdapter(adapter);

        Button botaoAdiciona = (Button) findViewById(R.id.lista_precos_floating_button);
        botaoAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
            
        });

    }
}