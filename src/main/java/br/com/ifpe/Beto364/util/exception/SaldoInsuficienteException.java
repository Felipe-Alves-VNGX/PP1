package br.com.ifpe.Beto364.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class SaldoInsuficienteException extends RuntimeException {

	private static final long serialVersionUID = 659037105504452726L;

	private static final String MSG_SALDO_INSUFICIENTE = "Saldo Insuficiente!";
	
	public SaldoInsuficienteException() {
		super(MSG_SALDO_INSUFICIENTE);
	}
}
