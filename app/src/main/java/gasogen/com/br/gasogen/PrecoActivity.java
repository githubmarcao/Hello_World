package gasogen.com.br.gasogen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import gasogen.com.br.gasogen.dao.PostoDAO;
import gasogen.com.br.gasogen.dao.PrecoDAO;
import gasogen.com.br.gasogen.helper.PrecoFormularioHelper;
import gasogen.com.br.gasogen.modelo.Posto;
import gasogen.com.br.gasogen.modelo.Preco;
import gasogen.com.br.gasogen.util.Constantes;

public class PrecoActivity extends AppCompatActivity {

    private PrecoFormularioHelper helper;
    private ListView listView;
    private Preco preco;
    private Posto postoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preco);

        PostoDAO dao = new PostoDAO(this);
        this.helper = new PrecoFormularioHelper(this, dao.getPostos());
        dao.close();

        Intent intent = getIntent();
        this.preco = (Preco) intent.getSerializableExtra(Constantes.PRECO_SELECIONADO);

        if (this.preco != null) {
            this.helper.selecionarPosto(this.preco.getPosto());
        } else {
            this.preco = new Preco();
        }

        Button botaoSalvar = (Button) findViewById(R.id.item_preco_botao_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preco = helper.getPreco();

                if (!validaPreco(preco)) {
                    return;
                }

                PrecoDAO dao = new PrecoDAO(PrecoActivity.this);
                dao.insereOuAtualiza(preco);
                dao.close();

                if (preco.getId() == null || preco.getId() == 0) {
                    Toast.makeText(PrecoActivity.this, "Preço inserido.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PrecoActivity.this, "Preço editado.", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(PrecoActivity.this, ListaPrecosActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validaPreco(Preco preco) {
        if (preco.getPosto() == null) {
            Toast.makeText(PrecoActivity.this, "Selecione um posto para continuar.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
