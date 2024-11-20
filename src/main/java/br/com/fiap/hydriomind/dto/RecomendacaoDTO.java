package br.com.fiap.hydriomind.dto;

import jakarta.validation.constraints.NotBlank;

public class RecomendacaoDTO {

    private Long id;  // Alterado de String para Long

    @NotBlank(message = "A descrição da recomendação é obrigatória")
    private String descricao;

    @NotBlank(message = "O status de implementação é obrigatório")
    private Boolean implementada;  // Alterado de String para Boolean

    // Relacionamento com AnaliseDesperdicio pode ser incluído, se necessário.
    // private AnaliseDesperdicioDTO analiseDesperdicio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {  // Alterado para Long
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getImplementada() {
        return implementada;
    }

    public void setImplementada(Boolean implementada) {
        this.implementada = implementada;
    }
}
