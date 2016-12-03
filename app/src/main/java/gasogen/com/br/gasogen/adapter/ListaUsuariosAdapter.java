package gasogen.com.br.gasogen.adapter;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import gasogen.com.br.gasogen.R;
import gasogen.com.br.gasogen.modelo.Usuario;

/**
 * Created by Dell on 14/11/2016.
 */

public class ListaUsuariosAdapter extends BaseAdapter {

    private final List<Usuario> usuarios;
    private final Activity activity;

    public ListaUsuariosAdapter(Activity activity, List<Usuario> usuarios) {
        this.usuarios = usuarios;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return usuarios.size();
    }

    @Override
    public Object getItem(int position) {
        return usuarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return usuarios.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_usuario, parent, false);
        Usuario usuario = this.usuarios.get(position);

        ((TextView) view.findViewById(R.id.item_usuario_login)).setText(usuario.getLogin());
        ((TextView) view.findViewById(R.id.item_usuario_senha)).setText(usuario.getSenha());
        ((TextView) view.findViewById(R.id.item_usuario_nome)).setText(usuario.getNome());
        ((TextView) view.findViewById(R.id.item_usuario_email)).setText(usuario.getEmail());
        ((TextView) view.findViewById(R.id.item_usuario_telefone)).setText(usuario.getTelefone());

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
