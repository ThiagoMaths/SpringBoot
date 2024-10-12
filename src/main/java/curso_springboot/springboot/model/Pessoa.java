package curso_springboot.springboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@PersistenceContext(name = "pessoa")
public class Pessoa implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "nome obrigatório")
    @NotNull(message = "nome obrigatório")
    private String nome;

    @NotEmpty(message = "sobrenome obrigatório")
    @NotNull(message = "sobrenome obrigatório")
    private String sobrenome;

    @NotEmpty(message = "idade obrigatório")
    @NotNull(message = "idade obrigatório")
    private int idade;
    @Column(insertable=false, updatable=false)
    private String dtype ;

    @OneToMany(mappedBy = "pessoa", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Telefone> telefones;

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

}
