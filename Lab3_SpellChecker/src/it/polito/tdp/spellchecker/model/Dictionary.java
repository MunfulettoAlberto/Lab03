package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	List <String> dictionary = new ArrayList<String>();
	List <String> dicItalian = new ArrayList<String>();
	
	public Dictionary() {
		super();
	}
	
	public void loadDictionary(String lingua){
		try {
			FileReader fr = new FileReader("rsc/"+lingua+".txt");
			BufferedReader br = new BufferedReader(fr);
			String word1;
			while ((word1 = br.readLine()) != null) {
			// Aggiungere parola alla struttura dati
				dictionary.add(word1);
			}
			br.close();
			} catch (IOException e){
			System.out.println("Errore nella lettura del file");
		}
		
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> richlist = new ArrayList <RichWord>();
		RichWord richword = null;
			for(String temp : inputTextList){
				boolean presente = false;
				for(String s : dictionary){
					if(temp.compareTo(s)==0){
						presente = true;
						richword = new RichWord(temp, presente);
					}
				}
				if(!presente){
					richword = new RichWord(temp, presente);
				}
				richlist.add(richword);
			}

		return richlist;
	}
}
