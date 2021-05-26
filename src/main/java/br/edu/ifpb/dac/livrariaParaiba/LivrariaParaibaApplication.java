package br.edu.ifpb.dac.livrariaParaiba;

import java.util.Date;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifpb.dac.livrariaParaiba.modelo.Cliente;

import br.edu.ifpb.dac.livrariaParaiba.service.ClienteService;

@SpringBootApplication
public class LivrariaParaibaApplication implements CommandLineRunner {

	private ClienteService clienteService;

	public LivrariaParaibaApplication(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public static void main(String[] args) {
		SpringApplication.run(LivrariaParaibaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Scanner leitor = new Scanner(System.in);
		System.out.println("Escolha uma opção" + "\n1 - Adicionar Usuário" + "\n2 - Consultar Usuário por Email"
				+ "\n3 - Cadastrar Autor" + "\n4 - Editar Autor" + "\n5 - Cadastar Livro" + "\n6 - Editar Livro"
				+ "\n7 - Excluir Livro" + "\n8 - Cadastrar livro no Estoque"
				+ "\n9 - Cosnultar os 5 Livros mais Baratos" + "\n10 - Consultar todos os livros"
				+ "\n11 - Adicionar Livro ao Carrinho");
		int n = leitor.nextInt();
		Cliente c = new Cliente();

		switch (n) {

		case 1:
			c.setNome("Bruno");
			c.setCpf("121.323.223-98");
			c.setBairro("São Vicente");
			c.setCidade("Monteiro");
			c.setEstado("Paraíba");
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date data = formato.parse("17/10/2000");
			c.setNascimento(data);
			c.setNumero(102);
			c.setRua("Rua Madalena");
			c.setUsername("bruno@gmail.com");
			clienteService.salvarCliente(c);
			break;
		case 2:
			c = clienteService.pesquisarPorEmail(c.getUsername());
			System.out.println(c.getNome());

			break;
		case 10:
			List<Cliente> lista = clienteService.pesquisarTodosClientes();
			for (Cliente a : lista) {
				System.out.println(a.getNome());
			}
			break;
		default:
			break;
		}

	}

}
