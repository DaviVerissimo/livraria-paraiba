package br.edu.ifpb.dac.livrariaParaiba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AutorController {

	@Autowired
	private AutorService autorService;

	/**
	 * 
	 * @param autor
	 * @return retorna a página "form" (formulário) do autor.
	 */
	@GetMapping("/autores/novo")
	public String autor(@ModelAttribute("autor") Autor autor) {
		return "autor/form";
	}

	/**
	 * 
	 * @param autor
	 * @param bindingResult
	 * @return cajo haja algum erro, o metodo retorna uma mensagem informando o
	 *         erro. Se não tiver erro, o metodo salva o autor e redireciona para a
	 *         pagina de autores.
	 */
	@PostMapping("/autor/salvar")
	public String salvarAutor(@Valid @ModelAttribute("autor") Autor autor, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "autor/form";
		}
		autorService.salvar(autor);
		return "redirect:/autores";
	}

	/**
	 * 
	 * @param model
	 * @return retorna a pagina index de autores.
	 */
	@GetMapping("/autores")
	public String autores(Model model, @RequestParam("page") Optional<Integer> pagina,
			@RequestParam("size") Optional<Integer> tamanho) {

		int paginaAtual = pagina.orElse(1) - 1;
		int tamanhoPagina = tamanho.orElse(4);

		PageRequest requisicao = PageRequest.of(paginaAtual, tamanhoPagina, Sort.by("ID"));
	/*	Page<Autor> listaPaginada = autorService.retornarListaDeAutoresPaginada(requisicao);
		
		model.addAttribute("listaAutores", listaPaginada);
		int totalPaginas = listaPaginada.getTotalPages();
		if (totalPaginas > 0) {
			List<Integer> numerosPaginas = IntStream.rangeClosed(1, totalPaginas).boxed().collect(Collectors.toList());
			model.addAttribute("numerosPaginas", numerosPaginas);
		} */
		return "autor/index";
	}

	/**
	 * 
	 * @param id
	 * @param model
	 * @return retorna o formulario para a edição do autor passado no parametro do
	 *         método.
	 */
	@GetMapping("/autor/{ID}")
	public String alterarAutor(@PathVariable("ID") long id, Model model) {
		Optional<Autor> autorOpt = Optional.ofNullable(autorService.pesquisarAutorPorID(id));
		if (autorOpt.isEmpty()) {
			throw new IllegalArgumentException("Autor inválido.");
		}
		model.addAttribute("autor", autorOpt.get());
		return "autor/form";
	}

	/**
	 * 
	 * @param id
	 * @return o autor passado como parametro é removido e retorna para a listagem
	 *         de autores.
	 */
	@GetMapping("/autor/excluir/{ID}")
	public String excluirAutor(@PathVariable("ID") long id) {
		Optional<Autor> autorOpt = Optional.ofNullable(autorService.pesquisarAutorPorID(id));
		if (autorOpt.isEmpty()) {
			throw new IllegalArgumentException("Autor inválido.");
		}

		autorService.remove(autorOpt.get().getID());
		return "redirect:/autores";
	}
}
