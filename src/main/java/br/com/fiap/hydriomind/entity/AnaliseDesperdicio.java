package br.com.fiap.hydriomind.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "T_HM_ANALISE_DESPERDICIO")
public class AnaliseDesperdicio {

    @Id
    private String id;  // MongoDB usa String como tipo para o ID

    private String nomeProduto;

    private Double quantidadeDesperdiciada;

    private String unidadeMedida;

    private String motivoDesperdicio;

    // Construtor padrão
    public AnaliseDesperdicio() {
    }

    // Construtor completo
    public AnaliseDesperdicio(String id, String nomeProduto, Double quantidadeDesperdiciada, String unidadeMedida, String motivoDesperdicio) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.quantidadeDesperdiciada = quantidadeDesperdiciada;
        this.unidadeMedida = unidadeMedida;
        this.motivoDesperdicio = motivoDesperdicio;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getQuantidadeDesperdiciada() {
        return quantidadeDesperdiciada;
    }

    public void setQuantidadeDesperdiciada(Double quantidadeDesperdiciada) {
        this.quantidadeDesperdiciada = quantidadeDesperdiciada;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public String getMotivoDesperdicio() {
        return motivoDesperdicio;
    }

    public void setMotivoDesperdicio(String motivoDesperdicio) {
        this.motivoDesperdicio = motivoDesperdicio;
    }
}
