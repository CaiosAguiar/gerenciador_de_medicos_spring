package br.edu.senac.gerenciamento_medicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Medico {

    private Integer id;

    @NotBlank @Size(max = 100)
    private String nome;

    @NotBlank @Size(max = 20)
    private String crm;

    @NotBlank @Size(max = 50)
    private String especialidade;

    private Boolean cadastroAtivado;

    @Size(max = 500)
    private String observacoes;

    public Medico(){
    }

    public Medico (Integer id, String nome,
                   String crm, String especialidade,
                   Boolean cadastroAtivado,
                   String observacoes){
        this.id = id;
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.cadastroAtivado = cadastroAtivado;
        this.observacoes = observacoes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Boolean getCadastroAtivado() {
        return cadastroAtivado;
    }

    public void setCadastroAtivado(Boolean cadastroAtivado) {
        this.cadastroAtivado = cadastroAtivado;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
