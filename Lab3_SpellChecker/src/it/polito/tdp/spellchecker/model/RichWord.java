package it.polito.tdp.spellchecker.model;

public class RichWord {
	String parola; 
	boolean corretto;
	public RichWord(String parola, boolean corretto) {
		super();
		this.parola = parola;
		this.corretto = corretto;
	}
	/**
	 * @return the parola
	 */
	public String getParola() {
		return parola;
	}
	/**
	 * @param parola the parola to set
	 */
	public void setParola(String parola) {
		this.parola = parola;
	}
	/**
	 * @return the corretto
	 */
	public boolean isCorretto() {
		return corretto;
	}
	/**
	 * @param corretto the corretto to set
	 */
	public void setCorretto(boolean corretto) {
		this.corretto = corretto;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RichWord [parola=" + parola + ", corretto=" + corretto + "]";
	}
	
}
