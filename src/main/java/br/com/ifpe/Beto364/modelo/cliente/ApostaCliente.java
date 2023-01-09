package br.com.ifpe.Beto364.modelo.cliente;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifpe.Beto364.util.entity.EntidadeNegocio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Builder
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "habilitado = true")	
public class ApostaCliente extends EntidadeNegocio implements Serializable {

	private static final long serialVersionUID = 5552397017530707954L;
	
	public static final String LABEL = "Aposta";
	
	public static final Boolean RESULTADO_CORRETO = Boolean.TRUE;
	
	public static final Boolean RESULTADO_INCORRETO = Boolean.FALSE;
	
	static final Boolean SEM_RESULTADO = null;
	@JsonIgnore
    @ManyToOne
    private Cliente cliente;

	@Column(nullable=false)
	private Double valor;
	
	@Column(nullable=false)
	private String timeDaCasa;
	
	@Column(nullable=false)
	private String timeVisitante;
	
	@Column(nullable=false)
	private int timeDaCasaPlacar;
	
	@Column(nullable=false)
	private int timeVisitantePlacar;
	
	@Column
	private boolean resultado;
	
	public void updateFrom(ApostaCliente aposta) {
		this.setValor(aposta.getValor());
		this.setTimeDaCasa(aposta.getTimeDaCasa());
		this.setTimeVisitante(aposta.getTimeVisitante());
		this.setTimeDaCasaPlacar(aposta.getTimeDaCasaPlacar());
		this.setTimeVisitantePlacar(aposta.getTimeVisitantePlacar());
		
	}
}
