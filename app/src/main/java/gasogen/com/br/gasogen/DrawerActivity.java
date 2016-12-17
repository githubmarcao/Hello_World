package gasogen.com.br.gasogen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import gasogen.com.br.gasogen.fragment.ListaPrecoFragment;
import gasogen.com.br.gasogen.modelo.Preco;

public class DrawerActivity extends AppCompatActivity {

    private ListView listaPrecos;
    private List<Preco> precos;

    // Drawer

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    LinearLayout linearLayout;

    // Fragmet
    Fragment fragment = null;
    Class fragmentClass = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_drawer);

        // Lista lista_drawer a esquerda
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        TextView tv_word = (TextView )findViewById(R.id.tv_word );
        tv_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DrawerActivity.this, "Clicado.", Toast.LENGTH_LONG).show();
                mDrawerLayout.closeDrawer(linearLayout);//don't forget it

                fragmentClass = ListaPrecoFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            }
        });
    }
}