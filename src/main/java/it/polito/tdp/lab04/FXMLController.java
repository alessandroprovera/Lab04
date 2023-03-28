package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.ArrayList;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private Button btnIscriviti;

    @FXML
    private Button btnReset;

    @FXML
    private ComboBox<String> cmbCorsi;

    @FXML
    private TextField txtCognome;
    
    @FXML
    private Button btnCompletamento;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextArea txtRisultato;
    
    public void setModel(Model model) {
		this.model = model;
		List<Corso> temp = new ArrayList<Corso>();
		temp = this.model.getTuttiICorsi();
        for(Corso c: temp) {
        	this.cmbCorsi.getItems().add(c.getNome());
        }
        this.cmbCorsi.getItems().add(" ");
	}
    
    @FXML
    void doCompletamento(ActionEvent event) {
    	List<String> datiStudente = this.model.getDatiStudenteDataMatricola(Integer.parseInt(this.txtMatricola.getText()));
    	this.txtCognome.setText(datiStudente.get(0));
    	this.txtNome.setText(datiStudente.get(1));
    	
    }

	@FXML
    void doCercaCorsi(ActionEvent event) {
		if(this.model.getDatiStudenteDataMatricola(Integer.parseInt(this.txtMatricola.getText())).isEmpty())
			this.txtRisultato.setText("Matricola non esistente");
		else {
			List<Corso> daStampare = this.model.getCorsiByMatricola(Integer.parseInt(this.txtMatricola.getText()));
			for(Corso c: daStampare) {
				this.txtRisultato.appendText(c.toString());
			}
		}
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	if(this.cmbCorsi.getValue().compareTo(" ") == 0)
    		this.txtRisultato.setText("Errore selezione corso");
    	{
    		List<Studente> daStampare = this.model.getStudentiDatoNomeCorso(this.cmbCorsi.getValue());
        	for(Studente s: daStampare) {
        		this.txtRisultato.appendText(s.toString());
        	}
    	}
    }

    @FXML
    void doIscriviti(ActionEvent event) {
    	if(this.model.studenteIscrittoACorso(this.cmbCorsi.getValue(), Integer.parseInt(this.txtMatricola.getText())))
    		this.txtRisultato.setText("Studente gia iscritto a corso");
    	else
    		this.model.iscriviStudenteACorso(Integer.parseInt(this.txtMatricola.getText()), this.model.getCodinsCorso(this.cmbCorsi.getValue()));    		
    }

    @FXML
    void doReset(ActionEvent event) {
    	this.txtMatricola.clear();
    	this.txtCognome.clear();
    	this.txtNome.clear();
    	this.txtRisultato.clear();
    }

    @FXML
    void initialize() {
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscriviti != null : "fx:id=\"btnIscriviti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }

}
