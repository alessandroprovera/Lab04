package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public List<String> getDatiStudenteDataMatricola(int matricola) {

		final String sql = "select nome,cognome "
				+ "from studente "
				+ "where matricola = ?";

		List<String> datiStudente = new ArrayList<String>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");

				System.out.println(matricola + " " + cognome + " " + nome);

				datiStudente.add(cognome);
				datiStudente.add(nome);
			}

			conn.close();
			
			return datiStudente;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public List<Studente> getStudentiDatoNomeCorso(String codins) {

		final String sql = "select s.matricola,s.nome,s.cognome,s.CDS "
				+ "from studente s, iscrizione i, corso c "
				+ "where s.matricola = i.matricola and i.codins = c.codins and c.nome = ?";

		List<Studente> studenti = new ArrayList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codins);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");

				System.out.println(matricola + " " + cognome + " " + nome + " " + CDS);

				studenti.add(new Studente(matricola,cognome,nome,CDS));
			}

			conn.close();
			
			return studenti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public void iscriviStudenteACorso(int matricola, String codins) {
		
		final String sql = "INSERT INTO `iscrizione` (`matricola`, `codins`) VALUES (?, ?) ";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);
			st.setString(2, codins);

			st.executeQuery();

			conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
	}
	

}
