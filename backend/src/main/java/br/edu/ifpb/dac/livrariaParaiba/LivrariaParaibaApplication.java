package br.edu.ifpb.dac.livrariaParaiba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifpb.dac.livrariaParaiba.model.Cliente;
import br.edu.ifpb.dac.livrariaParaiba.service.ClienteService;

@SpringBootApplication
public class LivrariaParaibaApplication implements CommandLineRunner {
	
	@Autowired
	private ClienteService service;
	
	@Override
	public void run(String... args) throws Exception {
		int n = service.pesquisarTodosClientes().size();
		if(n<1) {
			Cliente c = new Cliente();
			c.setCpf("000000000");
			c.setRole("ADMIN");
			c.setNome("Administrador");
			c.setSenha("adm123");
			c.setUsername("admlivrariaparaiba@gmail.com");
			c.setTelefone("(83) 9993-2323");
			service.salvarCliente(c);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(LivrariaParaibaApplication.class, args);
	}

}
