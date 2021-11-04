import java.util.Vector;
import javax.swing.JOptionPane;
import java.util.Date;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//Classe per la creazione di un mezzo e metodi applicabili ad esso
public class Mezzi implements Serializable{
	
	static final long serialVersionUID = 1;
   
	Vector<String[]> StoricoPrenotazioneMezzi = new Vector<String[]>(); 
	Vector<String[]> listaPrenotazioneMezzi = new Vector<String[]>(); 
	   
	public String tipo;
	public String modello; 
	public String targa; 
	
	//costruttore con le caratteristiche generali del mezzo
	public Mezzi(String tipo, String modello, String targa) {
		this.tipo=tipo;
		this.modello=modello;
		this.targa=targa;	
	}
		
	 //metodo per generare una stringa che permetta di visualizzare i dettagli del mezzo con un pop up
	 public String visualizzaM(){ 
		   String c=  "Categoria: "+ tipo + "\t";
		   String m = " Modello: "+ modello+ "\t";
		   String t =" Targa: "+ targa +"\t";
		   String v = c+m+t;
		   return v;
	  }
	
	  //metodo che restituisce il vettore delle date di prenotazione di un mezzo
	  public Vector<Date> ottieniDatePrenotazione(Vector<String[]> list, String interfaccia) {
		   Vector<Date> vettoreDate = new Vector<Date>(); 
		   for (String[] elem : list) {
			 Date d=null;
			 try {
				 DateFormat df = new SimpleDateFormat ("dd/MM/yy");
				 d=df.parse(elem[0]);
				 
			 } catch (ParseException e) {		
				 if (interfaccia.equals("grafica")) {
					 JOptionPane.showMessageDialog(null,"Error Format Date");
				 }else {
					 System.out.println("Error Format Date");
				 }
			 }
			 vettoreDate.add(d);
			 
		  } 
		   return vettoreDate;
	   }
	  
	   
	  //metodo che restituisce la lista delle prenotazioni storiche di un mezzo (comprese le prenotazioni rimosse)
	   public Vector<String[]> StoricoPrenotazioni () {
		   return StoricoPrenotazioneMezzi;
	   }
	   
	   //metodo che restituisce la lista generale delle prenotazioni  (senza le prenotazioni rimosse , non è lo storico delle prenotazioni)
	   public Vector<String[]> ottieniPrenotazioni () {
		   return listaPrenotazioneMezzi;
	  }
	   
	   //metodo che serve a rimuovere una prenotazione dalla lista delle prenotazioni generali (non dallo storico delle prenotazioni)
	   public void RimuoviPrenotazione(int i) {
		   listaPrenotazioneMezzi.remove(i);
	   }
	    
	  //Aggiunge la singola prenotazione sia all'archivio storico delle prenotazioni per ogni mezzo che all'archivio generale delle prenotazioni
	   public void aggiungiPrenotazione(String data, String nome) {  
		   String[] prenotazioneSingola = { data, nome };
		   StoricoPrenotazioneMezzi.add(prenotazioneSingola);  
		   listaPrenotazioneMezzi.add(prenotazioneSingola);
	   }
	   
	  
	 //Restituisce una stringa contenente l'archivio storico delle prenotazioni per ogni mezzo (tipo String perchè usiamo pop up per la visualizzazione)
	   public String visualizzaStorico(){
		   String r="";
		  //se ho un'unica prenotazione è quella di default che indica la disponibilità immediata del mezzo
		   if (StoricoPrenotazioneMezzi.size() ==1) {
			   r= "Nessuna prenotazione";
		   } else {
			   
				//altrimenti ho delle prenotazioni reali da stampare 
				//parto dalla prima prenotazione per NON stampare la prenotazione di default   
				for (int j=1; j < StoricoPrenotazioneMezzi.size(); j++) {
					String c= " Nome cliente: "+ StoricoPrenotazioneMezzi.get(j)[1] + "\t\t" ;
					String d= " Data prenotazione : "+ StoricoPrenotazioneMezzi.get(j)[0] ;
					String v= c+d;
				    r= r+v + "\n";
				 }
			
		   } return r;
	  }
		   
	   
	 //Restituisce una stringa contenente la lista delle prenotazioni per ogni mezzo (tipo String perchè usiamo pop up per la visualizzazione)
	  public String visualizzaPrenotazioni(){
	    			String s="";
				  //se ho un'unica prenotazione è quella di default che indica la disponibilità immediata del mezzo
				   if (listaPrenotazioneMezzi.size() ==1) {
					   s="Nessuna prenotazione";
				   } else {
					   
						//altrimenti ho delle prenotazioni reali da stampare 
						//parto dalla prima prenotazione per NON stampare la prenotazione di default
						for (int j=1; j < listaPrenotazioneMezzi.size(); j++) {
							String c= " Nome cliente: "+ listaPrenotazioneMezzi.get(j)[1] + "\t\t" ;
							String d= " Data prenotazione : "+ listaPrenotazioneMezzi.get(j)[0] ;
							String v= c+d;
						    s= s+v + "\n";
						}
				   }
				   return s;
	   
	   } 
	   
	   
}