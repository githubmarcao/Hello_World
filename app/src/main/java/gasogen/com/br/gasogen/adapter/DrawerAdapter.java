package gasogen.com.br.gasogen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import gasogen.com.br.gasogen.R;

/**
 * Created by Dell on 26/11/2016.
 */

public class DrawerAdapter extends ArrayAdapter<String> {

    private String[] mPlanetTitles = {"Posto", "Preco", "Usuário"};

    public DrawerAdapter(Context context, List<String> titulos) {
        super(context, 0, titulos);
    }

    @Override
    public int getCount() {
        return mPlanetTitles.length;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        String titulo = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_drawer, parent, false);
        }

//        TextView campoNome = (TextView) view.findViewById(R.id.item_drawer1);
//        campoNome.setText(mPlanetTitles[0]);
//
//        TextView campoDescricao = (TextView) view.findViewById(R.id.item_drawer2);
//        campoDescricao.setText(mPlanetTitles[1]);
//
//        TextView campoLatitude = (TextView) view.findViewById(R.id.item_drawer3);
//        campoDescricao.setText(mPlanetTitles[2]);

        return view;
    }
}
