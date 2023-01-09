package br.com.ifpe.Beto364.modelo.cliente;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import br.com.ifpe.Beto364.modelo.acesso.Usuario;
import br.com.ifpe.Beto364.util.entity.EntidadeAuditavel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CategoriaProduto")
@Where(clause = "habilitado = true")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Cliente  extends EntidadeAuditavel{
	 
	private static final long serialVersionUID = 1L;
	
	public static final String LABEL = "Cliente";
	@ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;
	@NotNull
    @Column(nullable = false)
	private String cpf;
	@Column(nullable = false, length = 100)
	private String nome;
	@Column(nullable = false)
	private String fone;
	@Column
	private String foneAlternativo;
	@Column
	private Double saldo;
	@Column(nullable = false)
	@OneToMany(mappedBy = "cliente", orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
    private List<ApostaCliente> apostas;

	@Column
    private String email;
    @Column
	private Date dataNascimento;
	public void updateFrom(Cliente clienteAlterado) {
		this.setCpf(clienteAlterado.getCpf());
		this.setNome(clienteAlterado.getNome());
		this.setFone(clienteAlterado.getFone());
		this.setFoneAlternativo(clienteAlterado.getFoneAlternativo());
		this.setDataNascimento(clienteAlterado.getDataNascimento());
		this.setEmail(clienteAlterado.getEmail());
	}

}
