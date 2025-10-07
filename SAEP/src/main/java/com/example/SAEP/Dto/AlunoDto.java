package com.example.SAEP.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class AlunoDto {

    private Long id;
    private String nome;
    private String cpf;
    private String sobrenome;
    private String curso;

    public AlunoDto (){};

    public AlunoDto(Long id, String nome, String cpf, String sobrenome, String curso) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.sobrenome = sobrenome;
        this.curso = curso;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getCurso() {
        return curso;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
