package it.polito.tdp.lab04.model;

import java.util.List;
import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	CorsoDAO corsoDAO = new CorsoDAO();
	StudenteDAO studenteDAO = new StudenteDAO();
	
	public List<Corso> getTuttiICorsi(){
		return this.corsoDAO.getTuttiICorsi();
	}
	
	public List<String> getDatiStudenteDataMatricola(int matricola){
		return this.studenteDAO.getDatiStudenteDataMatricola(matricola);
	}
	
	public List<Studente> getStudentiDatoNomeCorso(String codins){
		return studenteDAO.getStudentiDatoNomeCorso(codins);
	}
	
	public List<Corso> getCorsiByMatricola(int matricola){
		return corsoDAO.getCorsiByMatricola(matricola);
	}
	
	public boolean studenteIscrittoACorso(String codins, int matricola) {
		List<Studente> studenti = studenteDAO.getStudentiDatoNomeCorso(codins);
		for(Studente s: studenti) {
			if(s.getMatricola() == matricola)
				return true;
		}
		return false;
	}
	
	public void iscriviStudenteACorso(int matricola, String codins) {
		this.studenteDAO.iscriviStudenteACorso(matricola, codins);
	}
	
	public String getCodinsCorso(String nome) {
		List<Corso> corsi = corsoDAO.getTuttiICorsi();
		for(Corso c: corsi) {
			if(c.getNome().compareTo(nome) == 0)
				return c.getCodins();
		}
		return null;
	}

}
