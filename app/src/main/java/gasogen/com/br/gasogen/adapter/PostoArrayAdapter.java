package gasogen.com.br.gasogen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import gasogen.com.br.gasogen.R;
import gasogen.com.br.gasogen.modelo.Posto;

/**
 * Created by Dell on 26/11/2016.
 */

public class PostoArrayAdapter extends ArrayAdapter<Posto> {

    private List<Posto> postos;

    public PostoArrayAdapter(Context context, List<Posto> postos) {
        super(context, 0, postos);
        this.postos = postos;
    }

    @Override
    public int getCount() {
        return postos.size();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Posto posto = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_posto, parent, false);
        }

        TextView campoNome = (TextView) view.findViewById(R.id.item_posto_nome);
        campoNome.setText(posto.getNome());

        TextView campoDescricao = (TextView) view.findViewById(R.id.item_posto_descricao);
        campoDescricao.setText(posto.getDescricao().toString());

        TextView campoLatitude = (TextView) view.findViewById(R.id.item_posto_latitude);
        campoLatitude.setText(posto.getLatitude().toString());

        TextView campoLongitude = (TextView) view.findViewById(R.id.item_posto_longitude);
        campoLongitude.setText(posto.getLongitude().toString());

        return view;
    }
}
