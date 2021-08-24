package br.edu.ifpb.dac.livrariaParaiba.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifpb.dac.livrariaParaiba.model.Autor;
import br.edu.ifpb.dac.livrariaParaiba.model.Livro;
import br.edu.ifpb.dac.livrariaParaiba.service.AutorService;
import br.edu.ifpb.dac.livrariaParaiba.service.LivroService;

@Controller
@RequestMapping("/adm")
public class LivroController {
	@Autowired
	private LivroService servico;
	@Autowired
	private AutorService servicoAutor;

	@GetMapping("/livros")
	public String livroLista(Model modelo) {
		modelo.addAttribute("listaLivros", servico.recuperarTodosOsLivros());
		return "livro/livrosIndex";
	}

	@GetMapping("/livros/novo")
	public String livroCadastro(Model model) {
		model.addAttribute("livro", new Livro());
		model.addAttribute("fieldToFocus", "nome");
		return "livro/editarLivro";
	}

	@GetMapping("/livros/alterar/{id}")
	public String alterarLivro(@PathVariable("id") long id, Model model) {
		Livro livroAlterado = servico.recuperarLivro(id)
				.orElseThrow(() -> new IllegalArgumentException("Livro inválido"));
		livroAlterado.setId(id);
		model.addAttribute("livro", livroAlterado);
		return "livro/editarLivro";
	}

	@GetMapping("/livros/excluir/{id}")
	public String excluirLivro(@PathVariable("id") long id, Model model) {

		Optional<Livro> livroOpt = servico.recuperarLivro(id);
		if (livroOpt.isEmpty()) {
			throw new IllegalArgumentException("Livro inválido.");
		}

		servico.removePorId(livroOpt.get().getId());
		return "redirect:/adm/livros";
	}

	@PostMapping("/livros/salvar")
	public String salvarLivro(@Valid @ModelAttribute("livro") Livro livro, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "livro/editarLivro";
		}
		List<Autor> autoresLista = livro.getAutores();
		livro.setAutores(new ArrayList<>());
		for (int i = 0; i < autoresLista.size(); i++) {
			Autor autorNumeravel = servicoAutor.pesquisarAutorPorNome(autoresLista.get(i).getNome());
			if (autorNumeravel != null) {
				livro.addAutor(autorNumeravel);
			}
		}
		servico.cadastrarLivro(livro);
		return "redirect:/adm/livros";
	}

	@RequestMapping(value = "/livros/salvar", params = { "addAutor" })
	public String addAutor(Livro livro, Model model, BindingResult bindingResult) {
		livro.addAutor(new Autor());
		String fieldId = "autores" + (livro.getAutores().size() - 1) + ".nome";
		model.addAttribute("fieldToFocus", fieldId);
		return "livro/editarLivro";
	}

	@RequestMapping(value = "/livros/salvar", params = { "removeAutor" })
	public String removeAutor(Livro livro, BindingResult bindingResult, HttpServletRequest req) {
		final Integer autorIndex = Integer.valueOf(req.getParameter("removeAutor"));
		livro.removerAutor(autorIndex);
		return "livro/editarLivro";
	}

	@PostMapping("**/pesquisarlivro")
	public String pesquisar(@RequestParam("pesquisar") String nomeLivro, Model model) {
		model.addAttribute("listaLivros", servico.retornarLivrosPeloNome(nomeLivro));
		return "livro/livrosIndex";
	}
}
