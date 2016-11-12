package gasogen.com.br.gasogen.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

//import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by embasa on 02/10/2015.
 */
public class Localizador {
    private Geocoder geo;

    public Localizador(Context context) {
        this.geo = new Geocoder(context);
    }

//    public LatLng getCoordenada(String endereco) {
//        try {
//            List<Address> listaEnderecos;
//            listaEnderecos = geo.getFromLocationName(endereco, 1); // Esse 1, está informando que é para trazer so ele
//
//            if (!listaEnderecos.isEmpty()) {
//                Address address = listaEnderecos.get(0);
//                return new LatLng(address.getLatitude(), address.getLongitude());
//            } else {
//                return null;
//            }
//        } catch (IOException e) {
//            return null;
//        }
//    }
}
