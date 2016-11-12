package gasogen.com.br.gasogen.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Dell on 11/11/2016.
 */

public class Preco implements Serializable {

    private Long id;
    private Double valor;
    private Date data;
    private Posto posto;

    public Preco(Long id, Double valor, Date data, Posto posto) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.posto = posto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Posto getPosto() {
        return posto;
    }

    public void setPosto(Posto posto) {
        this.posto = posto;
    }
}
