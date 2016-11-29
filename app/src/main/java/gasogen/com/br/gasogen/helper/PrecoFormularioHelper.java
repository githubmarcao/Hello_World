package gasogen.com.br.gasogen.helper;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import gasogen.com.br.gasogen.PrecoActivity;
import gasogen.com.br.gasogen.R;
import gasogen.com.br.gasogen.modelo.Posto;
import gasogen.com.br.gasogen.modelo.Preco;
import gasogen.com.br.gasogen.util.DataUtil;

/**
 * Created by Dell on 26/11/2016.
 */

public class PrecoFormularioHelper {

    private Preco preco;
    List<Posto> postos;
    Posto postoSelecionado;

    private EditText valor;
    private EditText data;
    private EditText Posto;
    private Spinner spinner;
    private PrecoActivity activity;

    public PrecoFormularioHelper(PrecoActivity activity, List<Posto> postos) {
        this.valor = (EditText) activity.findViewById(R.id.item_preco_valor);
        this.data = (EditText) activity.findViewById(R.id.item_preco_data);
        this.activity = activity;
        this.postos = postos;

        this.preco = new Preco();

        TextWatcher tw = new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (!s.toString().equals(current)) {
//                    String clean = s.toString().replaceAll("[^\\d.]", "");
//                    String cleanC = current.replaceAll("[^\\d.]", "");
//
//                    int cl = clean.length();
//                    int sel = cl;
//                    for (int i = 2; i <= cl && i < 6; i += 2) {
//                        sel++;
//                    }
//                    //Fix for pressing delete next to a forward slash
//                    if (clean.equals(cleanC)) sel--;
//
//                    if (clean.length() < 8){
//                        clean = clean + ddmmyyyy.substring(clean.length());
//                    }else{
//                        //This part makes sure that when we finish entering numbers
//                        //the date is correct, fixing it otherwise
//                        int day  = Integer.parseInt(clean.substring(0,2));
//                        int mon  = Integer.parseInt(clean.substring(2,4));
//                        int year = Integer.parseInt(clean.substring(4,8));
//
//                        if(mon > 12) mon = 12;
//                        cal.set(Calendar.MONTH, mon-1);
//                        year = (year<1900)?1900:(year>2100)?2100:year;
//                        cal.set(Calendar.YEAR, year);
//                        // ^ first set year for the line below to work correctly
//                        //with leap years - otherwise, date e.g. 29/02/2012
//                        //would be automatically corrected to 28/02/2012
//
//                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
//                        clean = String.format("%02d%02d%02d",day, mon, year);
//                    }
//
//                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
//                            clean.substring(2, 4),
//                            clean.substring(4, 8));
//
//                    sel = sel < 0 ? 0 : sel;
//                    current = clean;
//                    data.setText(current);
//                    data.setSelection(sel < current.length() ? sel : current.length());
//                }

                String working = s.toString();
                boolean isValid = true;
                if (working.length()==2 && before ==0) {
                    if (Integer.parseInt(working) < 1 || Integer.parseInt(working) > 31) {
                        isValid = false;
                    } else {
                        working+="/";
                        data.setText(working);
                        data.setSelection(working.length());
                    }
                }
                else if (working.length()==5 && before ==0) {
                    String mes = working.substring(3);
                    if (Integer.parseInt(mes) < 1 || Integer.parseInt(mes) > 12) {
                        isValid = false;
                    } else {
                        working+="/";
                        data.setText(working);
                        data.setSelection(working.length());
                    }
                }
                else if (working.length()== 12 && before == 0) {
                    String ano = working.substring(6);
                    if (Integer.parseInt(ano) < 1500) {
                        isValid = false;
                    }
                } else if (working.length() != 12) {
                    isValid = false;
                }

                if (!isValid) {
                    data.setError("Enter a valid date: DD/MM/YYYY");
                } else {
                    data.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
        this.data.addTextChangedListener(tw);

        spinner = (Spinner) activity.findViewById(R.id.item_preco_lista_posto1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                postoSelecionado = (Posto) parent.getItemAtPosition(position);
                String label = postoSelecionado.toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<Posto> dataAdapter = new ArrayAdapter<Posto>
                (activity, android.R.layout.simple_spinner_item, postos);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public Preco getPreco(){
        String valorTexto = this.valor.getText().toString();
        if ("".equals(valorTexto)) {
            this.preco.setValor(0d);
        } else {
            this.preco.setValor(Double.parseDouble(valorTexto));
        }
        String valorData = this.data.getText().toString();
        if ("".equals(valorData)) {
            this.preco.setData(new Date());
        } else {
            this.preco.setData(DataUtil.getDateTime(valorData));
        }
        this.preco.setPosto(postoSelecionado);

        return this.preco;
    }

    public void preencheFormulario(Preco preco) {
        this.valor.setText(preco.getValor().toString());
        this.data.setText(DataUtil.getDateTime(preco.getData()));

        this.preco = preco;
    }

    public void selecionarPosto(Posto posto) {
        int posicao = 0;
        for (int i = 0; i < postos.size(); i++) {
            if (postos.get(i).equals(posto)) {
                posicao = i;
                break;
            }
        }
        spinner.setSelection(posicao);
    }
}
