package br.edu.ifpb.dac.livrariaParaiba;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.edu.ifpb.dac.livrariaParaiba.modelo.Cliente;
import br.edu.ifpb.dac.livrariaParaiba.service.ClienteServiceImpl;

@SpringBootApplication
public class LivrariaParaibaApplication implements CommandLineRunner {
	
	private ClienteServiceImpl clienteService;
	
	public LivrariaParaibaApplication(ClienteServiceImpl clienteService) {
	
	}
	public static void main(String[] args) {
		SpringApplication.run(LivrariaParaibaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Scanner leitor = new Scanner(System.in);
		
		Cliente c = new Cliente();
		c.setNome("André Felipe");
		c.setBairro("São Vicente");
		c.setCidade("Monteiro");
		c.setEstado("Paraíba");
		
		SimpleDateFormat formato =  new SimpleDateFormat("dd/MM/yyyy");
		Date data = (Date) formato.parse("17/10/2000");
		c.setNascimento(data);
		c.setNumero(102);
		c.setRua("Rua Madalena");
		//clienteService.salvarCliente(c);
	
	}

}
