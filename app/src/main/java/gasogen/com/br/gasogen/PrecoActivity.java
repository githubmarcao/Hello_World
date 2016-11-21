package gasogen.com.br.gasogen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class PrecoActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preco);

        listView = (ListView) findViewById(R.id.item_preco_posto_lista);
    }
}
