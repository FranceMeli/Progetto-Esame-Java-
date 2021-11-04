//Classe per costruire un furgone, sottoclasse di Mezzi
public class Furgone extends Mezzi{

	private String patente; 
	private String tipologia; 
	
	//costruttore furgone
	public Furgone( String tipo, String modello , String targa, String patente, String tipologia) {
		// chiama il costruttore di Mezzi
		super (tipo, modello , targa );
		//costruzione parametri specifici
		this.patente=patente;
		this.tipologia=tipologia;		
	}
	
	//metodo per generare una stringa che permette di visualizzare i dettagli del furgone con un pop up
	public String visualizzaM(){ 
		   String c=  " Categoria: "+ tipo + "\t\t";
		   String m = " Modello: "+ modello+ "\t\t";
		   String t =" Targa: "+ targa +"\t\t";
		   String p= " Patente: "+ patente +"\t\t";
		   String d= " Dimensione: "+ tipologia +"\t\t";
		   String v = c+m+t+p+d;
		   return v;
	}

}