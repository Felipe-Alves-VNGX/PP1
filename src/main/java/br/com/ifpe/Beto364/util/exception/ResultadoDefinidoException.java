package br.com.ifpe.Beto364.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ResultadoDefinidoException extends RuntimeException {

	private static final long serialVersionUID = 659037105504452726L;

	private static final String MSG_RESULTADO_DEFINIDO = "Esta aposta não pode ser deletada ou auterada pois o resultado já foi definido";
	
	public ResultadoDefinidoException() {
		super(MSG_RESULTADO_DEFINIDO);
	}
}
