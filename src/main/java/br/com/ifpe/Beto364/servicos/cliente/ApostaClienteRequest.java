package br.com.ifpe.Beto364.servicos.cliente;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.ifpe.Beto364.modelo.cliente.ApostaCliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApostaClienteRequest {
	@NotNull
    @NotEmpty
    private Double valor;
	
	@NotNull
    @NotEmpty
	private String timeDaCasa;
	
	@NotNull
    @NotEmpty
	private String timeVisitante;
	
	@NotNull
    @NotEmpty
	private int timeDaCasaPlacar;
	
	@NotNull
    @NotEmpty
	private int timeVisitantePlacar;
	
	private boolean resultado;
	
	public ApostaCliente buildAposta() {
		return ApostaCliente.builder()
				.valor(valor)
				.timeDaCasa(timeDaCasa)
				.timeVisitante(timeVisitante)
				.timeDaCasa(timeDaCasa)
				.timeVisitantePlacar(timeVisitantePlacar)
				.resultado(resultado)
				.build();
	}
}
