/**
 * Sample Skeleton for 'SpellChecker.fxml' Controller Class
 */

package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


public class SpellCheckerController {

	Dictionary model;
    /**
	 * @param model the model to set
	 */
	public void setDictionary(Dictionary model) {
		this.model = model;
	}
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cbxLingua"
    private ComboBox<String> cbxLingua; // Value injected by FXMLLoader

    @FXML // fx:id="txtInput"
    private TextArea txtInput; // Value injected by FXMLLoader

    @FXML // fx:id="btnSpellCheck"
    private Button btnSpellCheck; // Value injected by FXMLLoader

    @FXML // fx:id="txtWrongWords"
    private TextArea txtWrongWords; // Value injected by FXMLLoader

    @FXML // fx:id="txtContatore"
    private Label txtContatore; // Value injected by FXMLLoader

    @FXML // fx:id="btnClear"
    private Button btnClear; // Value injected by FXMLLoader

    @FXML // fx:id="txtTime"
    private Label txtTime; // Value injected by FXMLLoader

    @FXML
    void doClearText(ActionEvent event) {
    	txtInput.clear();
    	txtWrongWords.clear();
    	txtContatore.setText("");
    	txtTime.setText("");
    	cbxLingua.setDisable(false);
    	txtInput.setDisable(false);
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	//Carico il dizionario con la lingua selezionata
    	btnClear.setDisable(false);
    	txtInput.setDisable(true);
    	cbxLingua.setDisable(true);
    	model.loadDictionary(cbxLingua.getValue());
    	
    	String s = txtInput.getText().toLowerCase().replaceAll("[\\p{Punct}]", "");
    	String arrayword[] = s.split(" ");
    	List<String> wordlist = new ArrayList <String>();
    	for(int i=0; i < arrayword.length; i++){
    		wordlist.add(arrayword[i]);
    	}
    	
    	List <RichWord> result = model.spellCheckText(wordlist);
    	int cont = 0;
    	long l1 = System.nanoTime();
    	for(RichWord temp : result){
    		if(!temp.isCorretto()){
    			txtWrongWords.appendText(temp.getParola()+" \n");
    			cont++;
    		}
    	}
    	long l2 = System.nanoTime();
    	txtTime.setText("Control errors time: "+(l2-l1));
    	if(cont==1)
    		txtContatore.setText(cont+" errore");
    	if(cont>1)
    		txtContatore.setText(cont+" errori");
    	if(cont==0)
    		txtContatore.setText("Nessun errore");
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cbxLingua != null : "fx:id=\"cbxLingua\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtWrongWords != null : "fx:id=\"txtWrongWords\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtContatore != null : "fx:id=\"txtContatore\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";
       
        cbxLingua.getItems().addAll("English", "Italian");
        
        if(cbxLingua.getItems().size()!=0)
        	cbxLingua.setValue(cbxLingua.getItems().get(0));
        

    }
}
