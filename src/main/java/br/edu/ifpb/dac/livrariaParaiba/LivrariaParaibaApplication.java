package br.edu.ifpb.dac.livrariaParaiba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifpb.dac.livrariaParaiba.modelo.Genero;
import br.edu.ifpb.dac.livrariaParaiba.service.AutorService;

@SpringBootApplication
public class LivrariaParaibaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrariaParaibaApplication.class, args);
		System.out.println("Colega");
		AutorService a = new AutorService();
		//a.salvar("Jessica", "Romance");
		//Genero g = new Genero();
		//g.getAllGeneros();
	}

}
