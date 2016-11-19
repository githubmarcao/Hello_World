package gasogen.com.br.gasogen.adapter;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.List;

import gasogen.com.br.gasogen.R;
import gasogen.com.br.gasogen.modelo.Posto;

/**
 * Created by Dell on 14/11/2016.
 */

public class ListaPostosAdapter extends BaseAdapter {

    private final List<Posto> postos;
    private final Activity activity;

    public ListaPostosAdapter(Activity activity, List<Posto> postos) {
        this.postos = postos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return postos.size();
    }

    @Override
    public Object getItem(int position) {
        return postos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return postos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_posto, parent, false);
        Posto posto = this.postos.get(position);

        EditText campoNome = (EditText) view.findViewById(R.id.item_posto_nome);
        campoNome.setText(posto.getNome());

        EditText campoDescricao = (EditText) view.findViewById(R.id.item_posto_descricao);
        campoDescricao.setText(posto.getDescricao().toString());

        EditText campoLatitude = (EditText) view.findViewById(R.id.item_posto_latitude);
        campoLatitude.setText(posto.getLatitude().toString());

        EditText campoLongitude = (EditText) view.findViewById(R.id.item_posto_longitude);
        campoLongitude.setText(posto.getLongitude().toString());

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
