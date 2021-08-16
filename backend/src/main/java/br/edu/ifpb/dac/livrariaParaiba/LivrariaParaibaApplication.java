package br.edu.ifpb.dac.livrariaParaiba;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.edu.ifpb.dac.livrariaParaiba.model.Autor;
import br.edu.ifpb.dac.livrariaParaiba.model.Cliente;
import br.edu.ifpb.dac.livrariaParaiba.model.Endereco;
import br.edu.ifpb.dac.livrariaParaiba.model.GenerosTipos;
import br.edu.ifpb.dac.livrariaParaiba.model.ItemCarrinho;
import br.edu.ifpb.dac.livrariaParaiba.model.Livro;
import br.edu.ifpb.dac.livrariaParaiba.model.Role;
import br.edu.ifpb.dac.livrariaParaiba.model.Usuario;
import br.edu.ifpb.dac.livrariaParaiba.service.AutorService;
import br.edu.ifpb.dac.livrariaParaiba.service.ClienteService;
import br.edu.ifpb.dac.livrariaParaiba.service.EnderecoService;
import br.edu.ifpb.dac.livrariaParaiba.service.ItemCarrinhoService;
import br.edu.ifpb.dac.livrariaParaiba.service.LivroService;

@SpringBootApplication
public class LivrariaParaibaApplication  {
	
	public static void main(String[] args) {
		SpringApplication.run(LivrariaParaibaApplication.class, args);
	}


}
