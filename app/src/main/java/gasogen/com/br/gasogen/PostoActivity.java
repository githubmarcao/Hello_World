package gasogen.com.br.gasogen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import gasogen.com.br.gasogen.dao.PostoDAO;
import gasogen.com.br.gasogen.modelo.Posto;
import gasogen.com.br.gasogen.util.Constantes;

public class PostoActivity extends AppCompatActivity {

    private Posto posto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posto);

        Intent intent = getIntent();
        posto = (Posto) intent.getSerializableExtra(Constantes.POSTO_SELECIONADO);
        if (posto != null) {
            EditText nome = (EditText) findViewById(R.id.item_posto_nome);
            EditText descricao = (EditText) findViewById(R.id.item_posto_descricao);
            EditText latitude = (EditText) findViewById(R.id.item_posto_latitude);
            EditText longitude = (EditText) findViewById(R.id.item_posto_longitude);
            
            nome.setText(posto.getNome());
            descricao.setText(posto.getDescricao());
            latitude.setText(posto.getLatitude().toString());
            longitude.setText(posto.getLongitude().toString());
        } else {
            this.posto = new Posto();
        }

        Button botaoSalvar = (Button) findViewById(R.id.item_posto_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posto.setNome(((EditText) findViewById(R.id.item_posto_nome)).getText().toString());
                posto.setDescricao(((EditText) findViewById(R.id.item_posto_descricao)).getText().toString());
                String latitudeTexto = ((EditText) findViewById(R.id.item_posto_latitude)).getText().toString();
                if ("".equals(latitudeTexto)) {
                    posto.setLatitude(0l);
                } else {
                    posto.setLatitude(Long.parseLong(latitudeTexto));
                }
                String longitudeTexto = ((EditText) findViewById(R.id.item_posto_longitude)).getText().toString();
                if ("".equals(longitudeTexto)) {
                    posto.setLongitude(0l);
                } else {
                    posto.setLongitude(Long.parseLong(longitudeTexto));
                }

                PostoDAO dao = new PostoDAO(PostoActivity.this);
                dao.insereOuAtualiza(posto);

                if (posto.getId() == null || posto.getId() == 0) {
                    Toast.makeText(PostoActivity.this, "'" + posto.getNome() + "' inserido.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PostoActivity.this, "'" + posto.getNome() + "' editado.", Toast.LENGTH_SHORT).show();
                }
                dao.close();

                Intent intent = new Intent(PostoActivity.this, ListaPostosActivity.class);
                startActivity(intent);
            }
        });
    }
}
