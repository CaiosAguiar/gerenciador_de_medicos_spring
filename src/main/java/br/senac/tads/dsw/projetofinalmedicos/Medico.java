package br.senac.tads.dsw.projetofinalmedicos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity //medico é uma entidade para o banco de dados
@Table(name="tb_medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //ID auto incrementa
    private Integer id;

    @NotBlank(message="Por favor preencha o nome") 
    @Size(min = 5,max = 100, message="Nome deve ter no mínimo 5 caracteres")
    private String nome;

    @NotBlank(message="Por favor Preencha o CRM")
    @Size(min =5, max = 20, message="CRM deve ter no mínimo 5 caracteres")
    private String crm;

    @NotBlank(message="Por favor preencher Especialidade") 
    @Size(min =5, max = 50, message="Especialidade deve ter no mínimo 5 caracteres")
    private String especialidade;
    
    @NotNull
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
