package br.com.fiap.hydriomind.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "T_TECH_EMPRESA")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EMPRESA")
    private Long id;

    @Column(name = "NM_EMPRESA", nullable = false)
    private String nomeEmpresa;

    @Column(name = "NM_FANTASIA", nullable = true)
    private String nomeFantasia;

    @Column(name = "NR_CNPJ", nullable = false, unique = true)
    private String cnpj;

    public Empresa() {
    }

    public Empresa(Long id, String nomeEmpresa, String nomeFantasia, String cnpj) {
        this.id = id;
        this.nomeEmpresa = nomeEmpresa;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
    }


    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
