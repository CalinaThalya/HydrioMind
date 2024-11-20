package br.com.fiap.hydriomind.dto;

public class AnaliseDesperdicioDTO {
    
    private Long id;
    private String nomeProduto;
    private Double quantidadeDesperdiciada;
    private String unidadeMedida;
    private String motivoDesperdicio;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
