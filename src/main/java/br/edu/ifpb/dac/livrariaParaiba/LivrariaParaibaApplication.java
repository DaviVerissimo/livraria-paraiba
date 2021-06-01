package br.edu.ifpb.dac.livrariaParaiba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import java.util.List;
import java.util.Optional;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifpb.dac.livrariaParaiba.model.Autor;
import br.edu.ifpb.dac.livrariaParaiba.model.Cliente;
import br.edu.ifpb.dac.livrariaParaiba.model.Endereco;
import br.edu.ifpb.dac.livrariaParaiba.model.ItemCarrinho;
import br.edu.ifpb.dac.livrariaParaiba.model.Livro;
import br.edu.ifpb.dac.livrariaParaiba.service.AutorService;
import br.edu.ifpb.dac.livrariaParaiba.service.ClienteService;
import br.edu.ifpb.dac.livrariaParaiba.service.EnderecoService;
import br.edu.ifpb.dac.livrariaParaiba.service.ItemCarrinhoService;
import br.edu.ifpb.dac.livrariaParaiba.service.LivroService;

@SpringBootApplication
public class LivrariaParaibaApplication implements CommandLineRunner {

	private ClienteService clienteService;
	private EnderecoService enderecoService;
	private ItemCarrinhoService carrinhoService;
	private LivroService livroService;
	private AutorService autorService;

	public LivrariaParaibaApplication(ClienteService clienteService, EnderecoService enderecoService,
			ItemCarrinhoService carrinhoService, LivroService livroService, AutorService autorService) {
		this.clienteService = clienteService;
		this.enderecoService = enderecoService;
		this.carrinhoService = carrinhoService;
		this.livroService = livroService;
		this.autorService = autorService;

	}

	public static void main(String[] args) {
		SpringApplication.run(LivrariaParaibaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		boolean aux = true;
		Scanner leitor = new Scanner(System.in);
		while (aux) {
			System.out.println("Escolha uma opção" + "\n1 - Adicionar Usuário" + "\n2 - Consultar Usuário por Email"
					+ "\n3 - Cadastrar Autor" + "\n4 - Editar Autor" + "\n5 - Cadastar Livro" + "\n6 - Editar Livro"
					+ "\n7 - Excluir Livro" + "\n8 - Cadastrar livro no Estoque"
					+ "\n9 - Cosnultar os 5 Livros mais Baratos" + "\n10 - Consultar todos os livros"
					+ "\n11 - Adicionar Livro ao Carrinho");
			int n = leitor.nextInt();
			Cliente c = new Cliente();

			switch (n) {

			case 1:

				Endereco endereco = new Endereco();
				System.out.println("Digite seus dados");
				String cond = leitor.nextLine();
				System.out.println("Digite seu CPF: ");
				String cpf = leitor.nextLine();
				System.out.println("Digite seu nome: ");
				String nome = leitor.nextLine();
				System.out.println("Digite o dia, mês e ano em que nasceu (dd/mm/aaaa): ");
				String nascimento = leitor.nextLine();
				System.out.println("Digite seu email: ");
				String email = leitor.nextLine();
				System.out.println("Digite sua senha: ");
				String senha = leitor.nextLine();
				System.out.println("Digite o estado onde reside: ");
				String estado = leitor.nextLine();
				System.out.println("Digite a cidade: ");
				String cidade = leitor.nextLine();
				System.out.println("Digite seu bairro: ");
				String bairro = leitor.nextLine();
				System.out.println("Digite sua rua: ");
				String rua = leitor.nextLine();
				System.out.println("Digite o numero da residência: ");
				int numero = leitor.nextInt();

				// set dos atributos
				c.setCpf(cpf);
				c.setNome(nome);
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				Date data = formato.parse(nascimento);
				c.setNascimento(data);
				c.setUsername(email);
				c.setSenha(senha);
				endereco.setBairro(bairro);
				endereco.setCidade(cidade);
				endereco.setEstado(estado);
				endereco.setNumero(numero);
				endereco.setRua(rua);
				c.setEndereco(endereco);
				endereco.setCliente(c);
				clienteService.salvarCliente(c);
				enderecoService.salvarEndereco(endereco);
				break;
			case 2:
				System.out.println("Digite seus dados");
				cond = leitor.nextLine();
				System.out.println("Digite o email: ");
				email = leitor.nextLine();
				c = clienteService.pesquisarPorEmail(email);
				System.out.println(c.getNome());

				break;

			case 3:
				Autor autorNovo = new Autor();
//				List<String> generos = autorNovo.getGeneros();
				System.out.println("Digite seus dados");
				cond = leitor.nextLine();
				System.out.print("Nome do autor: ");
				String nomeAutor = leitor.nextLine();
//				System.out.println("Generos: separe por ',' se houver mais de um");
//				String genero = leitor.nextLine();
//				generos = Arrays.asList(genero.split(","));
				autorNovo.setNome(nomeAutor);
				autorService.salvar(autorNovo);
				System.out.println("Autor cadastrado");
				break;

			case 10:
				List<Cliente> lista = clienteService.pesquisarTodosClientes();
				for (Cliente a : lista) {
					System.out.println(a.getNome());
				}
				break;
			case 11:
				c = clienteService.pesquisarPorId(1);
				List<Livro> catalogo = livroService.recuperarTodosOsLivros();
				for (Livro livro : catalogo) {
					System.out.println("ID: " + livro.getId() + "\nNome: " + livro.getNome() + "\nPreço: "
							+ livro.getValor() + "\nQuantidade disponível: " + livro.getQuantidade()
							+ "\n---------------------------------------------");
				}
				System.out.println("Digite o id do livro: ");
				long id = leitor.nextLong();
				System.out.println("Digite a quantidade: ");
				int qtd = leitor.nextInt();
				Optional<Livro> livro = livroService.recuperarLivro(id);
				if (livro.get().getQuantidade() >= qtd) {
					ItemCarrinho item = new ItemCarrinho();
					item.setCliente(c);
					item.setLivro(livro.get());
					item.setQtd(qtd);
					item.setStatus("Pendente");
					carrinhoService.salvarItem(item);
				} else {
					System.out.println("Quantidade indisponível");
				}
				break;
			default:
				leitor.close();
				aux = false;
				break;
			}
		}
	}

}
