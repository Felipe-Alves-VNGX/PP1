package br.com.ifpe.Beto364.servicos.cliente;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.Beto364.modelo.acesso.Usuario;
import br.com.ifpe.Beto364.modelo.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

    @Size(max = 1000)
    @NotNull
    @NotEmpty
    @NotBlank(message = "O Cpf é de preenchimento obrigatório")
    private String cpf;
    
    @NotNull
    @NotEmpty
    @NotBlank(message = "O Nome é de preenchimento obrigatório")
    private String nome;
    
    @NotNull
    @NotEmpty
    @NotBlank(message = "O Fone é de preenchimento obrigatório")

    private String fone;
    
    private String foneAlternativo;
    
    private Double saldo;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    @Past
    private Date dataNascimento;
    
    
    @NotBlank(message = "O Email é de preenchimento obrigatório")
    @Email
    private String email;
    
    @NotBlank(message = "A senha é de preenchimento obrigatório")
    private String password;

    public Cliente buildCliente() {

	return Cliente.builder()
			.cpf(cpf)
			.nome(nome)
			.fone(fone)
			.foneAlternativo(foneAlternativo)
			.usuario(buildUsuario())
			.dataNascimento(dataNascimento)
			.email(email)
			.build();
    }
    public Usuario buildUsuario() {
    	
    	return Usuario.builder()
    		.username(email)
    		.password(password)
    		.build();
        }

}

