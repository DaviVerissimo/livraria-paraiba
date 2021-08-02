package br.edu.ifpb.dac.livrariaParaiba.controller;

import java.util.ArrayList;
import java.util.List;

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

import br.edu.ifpb.dac.livrariaParaiba.model.Autor;
import br.edu.ifpb.dac.livrariaParaiba.model.Livro;
import br.edu.ifpb.dac.livrariaParaiba.service.AutorService;
import br.edu.ifpb.dac.livrariaParaiba.service.LivroService;

@Controller
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
		ArrayList<String> nomeAutores = new ArrayList<>();
		for (Autor autorIt : servicoAutor.retornarListaDeAutores()) {
			nomeAutores.add(autorIt.getNome());
		}
		model.addAttribute("autoresNome", nomeAutores);
		model.addAttribute("livro", new Livro());
		model.addAttribute("fieldToFocus", "nome");

		return "livro/editarLivro";
	}

	@GetMapping("/livros/alterar/{id}")
	public String alterarLivro(@PathVariable("id") long id, Model model) {
		Livro livroAlterado = servico.recuperarLivro(id)
				.orElseThrow(() -> new IllegalArgumentException("Livro inválido"));

		model.addAttribute("livro", livroAlterado);
		return "livro/editarLivro";
	}

	@PostMapping("/livros/excluir/{id}")
	public String excluirLivro(@PathVariable("id") long id, Model model) {
		Livro livroExcluir = servico.recuperarLivro(id)
				.orElseThrow(() -> new IllegalArgumentException("Livro inválido"));

		servico.removerLivro(livroExcluir);

		return "redirect:/livros";
	}

	@PostMapping("/livros/salvar")
	public String salvarLivro(@Valid @ModelAttribute("livro") Livro livro, @PathVariable("autores") ArrayList<Autor> autores,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "livro/editarLivro";
		}
		for (int i = 0; i < autores.size(); i++) {
			if (autores.get(i) != null) {
				if (servicoAutor.pesquisarAutorPorNome(autores.get(i).getNome()) == null) {
					Autor a = new Autor(livro.getAutores().get(i).getNome());
					a.addLivro(livro);
					servicoAutor.salvar(a);
					livro.addAutor(a);
				} else {
					servicoAutor.pesquisarAutorPorNome(autores.get(i).getNome()).addLivro(livro);
					livro.addAutor(servicoAutor.pesquisarAutorPorNome(autores.get(i).getNome()));
				}
			}
		}
		servico.cadastrarLivro(livro);

		return "redirect:/livros";
	}

	@RequestMapping(value = "/livros/salvar", params = { "addAutor" })
	public String addLivro(Livro livro, Model model, BindingResult bindingResult) {
		livro.addAutor(new Autor());
		String fieldId = "autores" + (livro.getAutores().size() - 1) + ".nome";
		model.addAttribute("fieldToFocus", fieldId);
		return "livro/editarLivro";
	}

	@RequestMapping(value = "/livros/salvar", params = { "removeAutor" })
	public String removeLivro(Livro livro, BindingResult bindingResult, HttpServletRequest req) {
		final Integer autorIndex = Integer.valueOf(req.getParameter("removeAutor"));
		livro.removerAutor(autorIndex);
		return "livro/editarLivro";
	}
}
