package gasogen.com.br.gasogen.modelo;

import java.io.Serializable;

/**
 * Created by Dell on 11/11/2016.
 */

public class Posto implements Serializable {

    private Long id;
    private String nome;
    private String descricao;
    private Long latitude;
    private Long longitude;

    public Posto() {

    }

    public Posto(Long id, String nome, String descricao, Long latitude, Long longitude) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }
}
