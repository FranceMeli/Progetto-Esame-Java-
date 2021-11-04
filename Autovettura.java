//Classe per costruire un'autovettura, sottoclasse della classe Mezzi
public class Autovettura extends Mezzi{
	
	private String posti; 
	private String tipologia;
	private String alimentazione; 
	
	//costruttore autovettura
	public Autovettura( String tipo, String modello , String targa, String posti, String tipologia, String alimentazione ) {
		// chiama il costruttore di Mezzi
		super ( tipo, modello , targa );
		this.posti=posti;
		this.tipologia=tipologia;
		this.alimentazione=alimentazione;
	}
	
	//metodo per generare una stringa che permette di visualizzare i dettagli dell'autovettura con un pop up
	public String visualizzaM(){ 
		   String c= " Categoria: "+ tipo + "\t";
		   String m= " Modello: "+ modello+ "\t";
		   String tar= " Targa: "+ targa +"\t";
		   String p= " Posti: "+ posti +"\t";
		   String t= " Tipologia: "+ tipologia +"\t";
		   String a= " Alimentazione: "+ alimentazione +"\t";
		   String v= c+m+tar+p+t+a;
		   return v;  
	}
	
	

}
