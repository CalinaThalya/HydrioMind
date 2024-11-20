package br.com.fiap.hydriomind.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "T_HM_RECOMENDACAO")
public class Recomendacao {

    @Id
    private String id;  // MongoDB usa String como tipo para o ID

    private String descricao;

    private String implementada;

    @DBRef
    private AnaliseDesperdicio analiseDesperdicio;  // Relacionamento com AnaliseDesperdicio no MongoDB

    // Construtores
    public Recomendacao() {}

    public Recomendacao(String id, String descricao, String implementada, AnaliseDesperdicio analiseDesperdicio) {
        this.id = id;
        this.descricao = descricao;
        this.implementada = implementada;
        this.analiseDesperdicio = analiseDesperdicio;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImplementada() {
        return implementada;
    }

    public void setImplementada(String implementada) {
        this.implementada = implementada;
    }

    public AnaliseDesperdicio getAnaliseDesperdicio() {
        return analiseDesperdicio;
    }

    public void setAnaliseDesperdicio(AnaliseDesperdicio analiseDesperdicio) {
        this.analiseDesperdicio = analiseDesperdicio;
    }
}
