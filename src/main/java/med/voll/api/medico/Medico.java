package med.voll.api.medico;


import jakarta.persistence.*;
import lombok.Data;
import med.voll.api.endereco.Endereco;

@Table(name = "medico")
@Entity(name = "Medico")
@Data
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String crm;
    private String email;
    private String telefone;
    private Boolean ativo = true;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.crm = dados.crm();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.ativo = true;
        this.endereco = new Endereco(dados.endereco());
    }


    public Medico() {

    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        this.nome = dados.nome() != null ? dados.nome() : this.nome;
        this.telefone = dados.telefone() != null ? dados.telefone() : this.telefone;
        this.endereco = dados.endereco() != null ? new Endereco(dados.endereco()) : this.endereco;
    }

    public void excluir() {
        this.ativo = false;
    }
}
