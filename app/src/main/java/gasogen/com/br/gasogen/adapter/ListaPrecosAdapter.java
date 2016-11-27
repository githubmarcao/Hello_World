package gasogen.com.br.gasogen.adapter;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.List;

import gasogen.com.br.gasogen.R;
import gasogen.com.br.gasogen.modelo.Preco;

/**
 * Created by Dell on 14/11/2016.
 */

public class ListaPrecosAdapter extends BaseAdapter {

    private final List<Preco> precos;
    private final Activity activity;

    public ListaPrecosAdapter(Activity activity, List<Preco> precos) {
        this.precos = precos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return precos.size();
    }

    @Override
    public Object getItem(int position) {
        return precos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return precos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_preco, parent, false);
        Preco preco = this.precos.get(position);

        EditText campoPosto = (EditText) view.findViewById(R.id.item_preco_lista_posto);
        campoPosto.setText(preco.getPosto().getNome());

        EditText campoValor = (EditText) view.findViewById(R.id.item_preco_valor);
        campoValor.setText(preco.getValor().toString());

        EditText campoData = (EditText) view.findViewById(R.id.item_preco_data);
        campoData.setText(preco.getData().toString());

        // Zebrar a lista
        if (position % 2 == 0) {
            view.setBackgroundColor(ContextCompat.getColor(activity, R.color.linha_par));
        } else {
            //view.setBackgroundColor(activity.getResources().getColor(R.color.linha_impar));
            view.setBackgroundColor(ContextCompat.getColor(activity, R.color.linha_impar));
        }

        return view;
    }
}
