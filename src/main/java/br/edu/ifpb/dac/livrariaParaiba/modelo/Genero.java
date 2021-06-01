package br.edu.ifpb.dac.livrariaParaiba.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name = "genero")
public class Genero implements Serializable{
	
private static final long serialVersionUID = 1L;

public List<String> getAllGeneros(){
	List<String> listaGenerosLiterarios = new ArrayList<String>();
	listaGenerosLiterarios.add("ficção");
    return listaGenerosLiterarios ;
}

}
