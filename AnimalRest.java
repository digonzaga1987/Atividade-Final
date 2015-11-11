package edu.tnt.exemplo.jsf.escolatnt.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.tnt.exemplo.jsf.escolatnt.business.GerenciadorDeAnimais;
import edu.tnt.exemplo.jsf.escolatnt.business.GerenciadorDeCursos;
import edu.tnt.exemplo.jsf.escolatnt.business.bo.GerenciadorDeAnimaisBO;
import edu.tnt.exemplo.jsf.escolatnt.business.bo.GerenciadorDeCursosBO;
import edu.tnt.exemplo.jsf.escolatnt.business.model.Animal;

@Path("/animais")
public class AnimalRest {
	
    public final static  String DRIVER = "org.postgresql.Driver";
    public final static  String URL = "jdbc:postgresql://localhost/postgres";
    public final static  String USER = "postgres";
    public final static  String PASSWD = "admin";
    
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String selectAll() throws Exception {
	List<Animal> animaisList = new ArrayList<Animal>();
    Connection conn = null;    
    conn = DriverManager.getConnection(URL,USER,PASSWD);
	GerenciadorDeAnimais bo = new GerenciadorDeAnimaisBO();
	animaisList = bo.listarAnimais(conn);

	String animais = "{\"data\":[";
	
	for (int i = 0; i < animaisList.size()  ; i++) { 
			animais = animais + "{\"nome\": \""+ animaisList.get(i).getNome() +"\","+
					             "\"nomecompleto\": \""+ animaisList.get(i).getNomeCompleto() +"\","+
					             "\"rgn\": \""+ animaisList.get(i).getRgn() +"\","+
					             "\"siglausual\": \""+ animaisList.get(i).getSiglaUsual() +"\","+
					             "\"seriealfa\": \""+ animaisList.get(i).getSerieAlfa() +"\","+
	                             "\"rgd\": \""+ animaisList.get(i).getRgd() +"\"},";
	}
	animais = animais.substring(0, animais.length() -1) + "]}";


	return animais;
}
}
