package br.edu.ifpb.dac.livrariaParaiba.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifpb.dac.livrariaParaiba.event.RecursoCriadoEvent;

/**
 * 
 * @author andré
 *
 */
@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

	@Override
	public void onApplicationEvent(RecursoCriadoEvent event) {
		HttpServletResponse response = event.getResponse();
		Long id = event.getId();
		adicionarHeaderLocation(response, id); 

	}

	private void adicionarHeaderLocation(HttpServletResponse response, Long id) {

		//Pega a partir da uri da requisição atual, adiciona o id e adiciona esse id na
		// uri
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

}
