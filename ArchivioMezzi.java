import java.util.TimeZone;
import java.util.Vector;
import javax.swing.JOptionPane;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Calendar;
import java.util.Scanner;

//Classe che costruisce l'archivio dei mezzi dell'autonoleggio, con i metodi per la gestione dell'autonoleggio
public class ArchivioMezzi {
	
	private String nomeArchivio;
	public static Vector<Mezzi> archivioMezzi = new Vector<Mezzi>();
	boolean modificato=false;
	static Scanner input = new Scanner(System.in);
	public String interfaccia;
	
	//costruttore
	//crea l'archivio dei mezzi dell'autonoleggio
	public ArchivioMezzi(String nomeArchivio, String interfaccia) {
		
		//specifico se l'interfaccia è testuale o grafica
		this.interfaccia= interfaccia;
		
		//se il nome dell'archivio non è stato inserito o si esce dalla finestra di input
		if (nomeArchivio == null) {
			System.exit(0); //termina il programma
		} else { 
		// se il nome viene inserito l'input viene assegnato come nome all'oggetto archivioMezzi
			this.nomeArchivio=nomeArchivio;
			try {
				//se oggetto archivio già esistente leggo il file 
				ObjectInputStream file_input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(nomeArchivio)));
				// legge l'intero vettore da file
				archivioMezzi = (Vector<Mezzi>) file_input.readObject();
				file_input.close();
			} catch (FileNotFoundException e) {
				// gestisce il caso in cui il file non sia presente 
				if (interfaccia.equals("grafica")) {
					JOptionPane.showMessageDialog(null,"ATTENZIONE: Il file " + nomeArchivio + " non esiste. \n Sarà creato al primo salvataggio.");	
				} else {
					System.out.println("ATTENZIONE: Il file " + nomeArchivio + " non esiste. \n Sarà creato al primo salvataggio.");
				}
				
				
			} catch (ClassNotFoundException e) {
				// gestisce il caso in cui il file non contenga un oggetto
				if (interfaccia.equals("grafica")) {
					JOptionPane.showMessageDialog(null,"Errore di lettura: \n "+ e);
				} else {
					System.out.println("Errore di lettura: \n "+ e);
				}
				System.exit(0);
				
			} catch (IOException e) {
				// gestisce altri errori di input/output
				if (interfaccia.equals("grafica")) {
					JOptionPane.showMessageDialog(null,"ERRORE di I/O \n "+ e);
				} else {
					System.out.println("ERRORE di I/O \n "+ e);
				}
				
				System.exit(0);
			}
		}
		}
	
	
	//metodo per aggiungere un mezzo con interfaccia testuale
	public void Aggiungi_testuale() {
        Scanner input = new Scanner(System.in);
       
	 	//Data di default con cui inizializziamo lo storico delle prenotazioni di ogni mezzo.
	String dateDefault="01/01/70";
    	System.out.println("Inserisci il modello");
    	String mod= input.nextLine();
    	System.out.println("Inserisci la targa");
    	String tar= input.nextLine();
    	System.out.println("Inserisci il tipo del mezzo: [A]utovettura o [F]urgone");
    	char ti = input.nextLine().charAt(0);
    	String tipo="";
    	if (ti=='A') {
    		tipo="Autovettura";
    		System.out.println("Inserisci il numero di posti: 2/4/5");
    		String posti=input.nextLine();
    		System.out.println("Inserisci la tipologia: [B]erlina, [M]edia, [U]tilitaria");
    		char t = input.nextLine().charAt(0);
    		String tipologia="";
    		    if (t=='B') {
    			    tipologia= "Berlina";
    		    } else if (t=='M') {
    		    	 tipologia= "Media";
    		    } else if (t=='U') {
    		    	 tipologia= "Utilitaria";
    		    } else { 
    		    	System.out.println("Errore");
    		    	return;
    		    }
           
    		System.out.println("Inserisci l'alimentazione: [B]enzina, [D]iesel");
            char a = input.nextLine().charAt(0);
            String alimentazione="";
		    if (a=='B') {
			         alimentazione= "Benzina";
			} else if (a=='D') {
		    	     alimentazione= "Diesel";
			} else  {
				System.out.println("Errore comando");
				return;
			}
		    boolean trovato=false;
		    
		    Autovettura au= new Autovettura(tipo, mod, tar, posti, tipologia, alimentazione);
		    
		    
		    for (Mezzi m : archivioMezzi) {
				//controllo limitato sull' identificatore targa (due mezzi non possono avere la stessa targa )
				if (m.targa.equals(au.targa)) {
					 	trovato=true;
				}
			}
			
			//trovato mezzo già presente nell'archivio
			if (trovato) {
				System.out.println( "Mezzo già presente nell'archivio");
				
			} else {
				//se mezzo non presente nell'archivio viene aggiunto all'archivio dei mezzi e creata la sua lista delle prenotazioni
				    archivioMezzi.add(au);
				    au.aggiungiPrenotazione(dateDefault, "null");
				    modificato=true;	
				    System.out.println( "Mezzo aggiunto all'archivio");
			}
		    
		    
		} else if (ti=='F') {
        	tipo="Furgone";
    		System.out.println("Inserisci la tipologia: [G]rande, [P]iccolo");
    		char t = input.nextLine().charAt(0);
    		String tipologiaF="";
    		if (t=='G') {
    			    tipologiaF= "Grande";
    		} else if (t=='P') {
    		    	 tipologiaF= "Piccolo";
    		} else {
    			System.out.println("Errore comando");
    			return;
    		}
    		
    		System.out.println("Inserisci tipo patente: [B], [C]");
            char p = input.nextLine().charAt(0);
            String patente="";
    		if (p=='B') {
    			 patente= "B";
    		} else if (p=='C') {
    		    patente= "C";
    		} else {
    			System.out.println("Errore comando");
    			return;
    		}
    	    
    		boolean trovato=false;
    		Furgone a=new Furgone(tipo, mod, tar, patente, tipologiaF);
    		 for (Mezzi m : archivioMezzi) {
  				//controllo limitato sull' identificatore targa (due mezzi non possono avere la stessa targa )
  				if (m.targa.equals(a.targa)) {
  					 	trovato=true;
  				}
  			}
  			
  			//trovato mezzo già presente nell'archivio
  			if (trovato) {
  				System.out.println( "Mezzo già presente nell'archivio");
  				
  			} else {
  				//se mezzo non presente nell'archivio viene aggiunto all'archivio dei mezzi e creata la sua lista delle prenotazioni
  					archivioMezzi.add(a);
  				    a.aggiungiPrenotazione(dateDefault, "null");
  				    modificato=true;	
  				    System.out.println( "Mezzo aggiunto all'archivio");
  			}
  		    
    	} else {
    		System.out.println("Errore comando");
    	}
 }
 
  
	public static Vector<Mezzi> mezziRimuovibili = new Vector<Mezzi>();
  
  //Metodo che rimuove un mezzo dall'archivio se NON ha prenotazioni 
  //o se NON ha prenotazioni per il giorno corrente e giorni successivi al giorno corrente
  public void rimuoviMezzo() {
	  
	  if (interfaccia.equals("grafica")) {
	  	 //calcolo della data di oggi
	  	 Date oggi = ottieniDataOdierna();
	  	 
	  	 
		 Vector<Mezzi> mezziRimuovibili = new Vector<Mezzi>();
		
		 int n=1;
		 String s = ""; //Stringa per visualizzare i mezzi rimuovibili nella finestra di pop up
		 
		 //per ogni mezzo dell'archivio
		 for (Mezzi m : archivioMezzi) {
			 // vettore con le date delle prenotazioni del mezzo corrente
			 Vector<Date> listaPrenotazioniSingoloMezzo=m.ottieniDatePrenotazione(m.ottieniPrenotazioni(),interfaccia);
			 
			 //se nel vettore ho solo la data di prenotazione di default considero il mezzo rimuovibile e lo visualizzo
			 if (listaPrenotazioniSingoloMezzo.size() == 1) {
				 s =s+ n +"."+ m.visualizzaM()+ "\n";
				 mezziRimuovibili.add(m);			
		 	     n++;
			 } else { // se ci sono altre prenotazioni oltre a quella di default
				 
				 //Basta che ci sia una data di prenotazione uguale o successiva alla data odierna 
				 //per non visualizzare il mezzo tra quelli rimuovibili.
				 //inizialmente considero il mezzo rimuovibile 
				 boolean trovato=true;
				 //confronto le date a partire dalla seconda data di prenotazione (non considero la data di default)
				 for (int i =1; i <listaPrenotazioniSingoloMezzo.size(); i++ ) {
					 //se la data di prenotazione è successiva o uguale alla data di oggi non posso rimuovere il mezzo
					 if (listaPrenotazioniSingoloMezzo.get(i).compareTo(oggi)>=0){
						 trovato=false; 
					 }
				 }	
				 
				 //trovato un mezzo rimuovibile lo aggiungo al vettore dei mezzi rimuovibili
				 if (trovato) {
				 s= s+ n+ "."+ m.visualizzaM()+ "\n";
				 mezziRimuovibili.add(m);
		 	     n++;
				 }
			 }	
		 }
		 
		 //caso in cui ci sono prenotazioni oltre a quella relativa alla data di default:
		 //ma boolean trovato rimasto false , cioè tutti i mezzi considerati
		 //hanno delle prenotazioni uguali o successive alla data odierna (come dire mezziRimuovibili.empty()==true )
		 if (n==1) {
			 JOptionPane.showMessageDialog(null,"Nessun mezzo rimovibile. Mezzi prenotati in date successive a quella odierna");
			 MainNoleggio.FinestraMain.setVisible(true); //torno al menù principale
		 } else { // se ci sono mezzi rimuovibili 
			 
			 //Finestra di pop up che mostra i mezzi rimuovibili e chiede all'utente d'inserire il numero del mezzo che
			 //desidera rimuovere
			 String numMezzo = JOptionPane.showInputDialog("Digita il numero del mezzo che vuoi rimuovere:"+ "\n"+s);
			 if (numMezzo != null) {
				 //il numero inserito dall'utente è una stringa , vogliamo trasformarlo in intero per
				 //accedere al mezzo scelto nel vettore dei mezzi rimuovibili e rimuovere quel mezzo dall'archivio dei mezzi totali
				 try {
				 int  num = Integer.parseInt(numMezzo);
				 archivioMezzi.remove(mezziRimuovibili.get(num-1));
				 modificato=true;	// segnalo la modifica per eventuale salvataggio 
				 MainNoleggio.FinestraMain.setVisible(true); // torno al menù principale
				 } catch (Exception e) { 
				//gestione eccezione causata da errore di conversione: comunicazione di errore e richiamo alla funzione corrente
				//col fine di consentire all'utente di riprovare a rimuovere il mezzo
				 JOptionPane.showMessageDialog(null, "Format error, riprova");
				 rimuoviMezzo();
				 }
			}
			else { //se il pop up viene chiuso torno al menù principale
				MainNoleggio.FinestraMain.setVisible(true);	
			}
		 }
		 
	 } else { //interfaccia testuale
		 Date oggi = ottieniDataOdierna();
		 Vector<Mezzi> mezziRimuovibili = new Vector<Mezzi>();
		 System.out.println("Digita il numero del mezzo che vuoi rimuovere:");
		 int n=1;
		 for (Mezzi m : archivioMezzi) {
			 Vector<Date> listaPrenotazioniSingoloMezzo=m.ottieniDatePrenotazione(m.ottieniPrenotazioni(),interfaccia);
			 if (listaPrenotazioniSingoloMezzo.size() ==1) {
				 System.out.print(" "+ n + ". ");
				 System.out.println(m.visualizzaM());
				 mezziRimuovibili.add(m);
				 System.out.println();
		 	     n++;
			 } else {
				 //Basta che ci sia una data di prenotazione uguale o successiva alla data odierna 
				 //per non visualizzare il mezzo tra quelli rimuovibili.
				 boolean trovato=true;
				 for (int i =1; i <listaPrenotazioniSingoloMezzo.size(); i++ ) {
					 if (listaPrenotazioniSingoloMezzo.get(i).compareTo(oggi)>=0){
						 trovato=false;
					 }
					 }	
				 if (trovato) {
				 System.out.print(" "+ n + ". ");
				 m.visualizzaM();
				 mezziRimuovibili.add(m);
				 System.out.println();
		 	     n++;
				 }
			 }	
		 }
		 if (n==1) {
			 System.out.println("Nessun mezzo rimovibile. Mezzi prenotati in date successive a quella odierna");
			 
		 }
		 else {
		 int numMezzo= input.nextInt();
		 input.nextLine();
		 archivioMezzi.remove(mezziRimuovibili.get(numMezzo-1));
		 modificato=true;
		 System.out.println("Mezzo rimosso correttamente");
		 }
	 }
}


// metodo per aggiungere un mezzo all'archivio dei mezzi se non già presente 
 //e creare la lista di prenotazione relativa a quel mezzo inizializzata con un'unica
  //data di default (usando interfaccia grafica)
public void aggiungi(Mezzi f) {
	
	String dateDefault="01/01/70"; //data di default necessaria per specificare la disponibilità immediata del mezzo
	//nel momento in cui viene aggiunto all'archivio (cioè non ha prenotazioni)
	
	//aggiunta del mezzo all'archivio solo se non già presente 
	boolean trovato=false;
	for (Mezzi m : archivioMezzi) {
		//controllo limitato sull' identificatore targa (due mezzi non possono avere la stessa targa )
		if (m.targa.equals(f.targa)) {
			 	trovato=true;
		}
	}
	
	//trovato mezzo già presente nell'archivio
	if (trovato) {
		JOptionPane.showMessageDialog(null, "Mezzo già presente nell'archivio");
		 90MainNoleggio.FinestraMain.setVisible(true);
	} else {
		//se mezzo non presente nell'archivio viene aggiunto all'archivio dei mezzi e creata la sua lista delle prenotazioni
			archivioMezzi.add(f);
		    f.aggiungiPrenotazione(dateDefault, "null");
		    modificato=true;	
		}	
}


//Metodo per visualizzare tutti i mezzi dell'archivio con le loro caratteristiche
public void visualizza() {
	
	String s="";	
	for (Mezzi m : archivioMezzi) {
	     s= s+ "-"+ m.visualizzaM() +"\n";
	}
	//se interfaccia grafica
	if (interfaccia.equals("grafica")) {
		 JOptionPane.showMessageDialog(null, s); //visualizzo tramite un pop up
		 MainNoleggio.FinestraMain.setVisible(true);
	
	} else { //se interfaccia testuale		
		System.out.println("I mezzi in archivio sono: \n" + s);
	}	
}

//Metodo per far inserire all'utente una data in input come stringa
//e resistuirla come oggetto di tipo Date
public static Date inserisciData(String interfaccia) {
	boolean ok=true;
	Date d=null;
	String data;
	 do {
		  ok= true;
		  
			  if (interfaccia.equals("grafica")) {
				   try {
					  data=JOptionPane.showInputDialog("Per quale data vuoi visualizzare le disponibilità? (es. 01/01/20 )");
					   if (data!=null) {
					   DateFormat df = new SimpleDateFormat ("dd/MM/yy");
					   d = df.parse (data);
					   }
				   } catch (ParseException e) {	  
					   ok = false;
				   }
			   
			  } else {	//se testuale	
				   System.out.println("Inserisci la data nel seguente formato 01/01/20: ");	  
				   DateFormat df = new SimpleDateFormat ("dd/MM/yy");
				   try {
					data = input.nextLine();  
					d = df.parse (data);
				   } catch (ParseException e) {	
					   ok=false;
				   }  			   
			  }
				   
	 } while(!ok); //fin tanto che la data non viene inserita nel formato corretto viene richiesta all'utente

	 return d;
	}



//Metodo che prende in input una data di tipo Date e la resistuisce in formato String
public String OttieniDataStringa(Date d) {
	 SimpleDateFormat dateFormat =  new SimpleDateFormat("dd/MM/yy");
	 return dateFormat.format(d);
	}
	
	
//Metodo che permette all'utente di prenotare un mezzo SE disponibile per una certa data
public void prenota() {
		if (interfaccia.equals("grafica")) {
			  Vector<Mezzi> mezziPrenotabili = new Vector<Mezzi>();
		
			  //pop up che richiede all'utente il nome con cui vuole prenotare
			  String nomeCliente= JOptionPane.showInputDialog("Inserisci identificativo");
			  
			  //se pop up viene chiuso o nome cliente non inserito
			  if (nomeCliente== null) {
				  MainNoleggio.FinestraMain.setVisible(true);
			  }
			  //se nome cliente inserito
			  else {
				  //viene invocato il metodo per inserire come input la data
				  Date d = inserisciData(interfaccia);
				  //se esco dal pop up evocato da inserisciData() l'input è null quindi voglio tornare al menù principale
				  if (d== null) {
					  MainNoleggio.FinestraMain.setVisible(true);
				  } else { 
			 		    //richiedo la data in formato stringa per poterla confrontare con le date di prenotazione 
					    String data = OttieniDataStringa(d);
						//	Controlla se la data inserita dall'utente è precedente alla data corrente.
						if(ottieniDataOdierna().compareTo(d)!=0 && ottieniDataOdierna().compareTo(d)>0) {
						  JOptionPane.showMessageDialog(null, "Data precedente a quella odierna");
						  MainNoleggio.FinestraMain.setVisible(true);
						
						} else {  
							 String s=""; //Utile a far visualizzare al cliente i mezzi a disposizione tramite pop up
							 int n=1;
							 for (Mezzi m : archivioMezzi) {			
					      
								 //Controllo se la data inserita dall'utente è già presente nel vettore delle prenotazioni del mezzo
								 boolean trovato=false;
								 Vector<Date> listaDate=m.ottieniDatePrenotazione(m.ottieniPrenotazioni(),interfaccia);
								 for (Date controllaDate : listaDate) {	 
									 if ((controllaDate.compareTo(d)==0)) {
										 trovato=true;
									 }
								 }
								 //Ho trovato un mezzo già prenotato per quella data, controllo il mezzo successivo
								 if (trovato) {
									 continue; 
								 }
								 //Viene riempita la stringa per visualizzare tramite pop up i mezzi disponibili, che vengono aggiunti alla lista mezziPrenotabili
								 else  {				
									 s= s+ n + "." + m.visualizzaM() + "\n";
							 	     mezziPrenotabili.add(m);
							 	     n++;
						 	     } 
							 } 
							
							 //pop up che mostra all'utente i mezzi disponibili e che richiede come input il numero di posizione del mezzo da prenotare
							 String numMezzo= JOptionPane.showInputDialog("Digita il numero del mezzo che vuoi prenotare:" + "\n"+ s);
							 //L'utente sceglie quale tra i mezzi disponibili (mezziPrenotabili)
							 //e viene aggiunta la prenotazione allo storico prenotazioni del mezzo selezionato.
							 if (numMezzo != null) {
								try {
									//conversione numero inserito dall'utente (di tipo String) in numero di tipo int per accedere
									//al mezzo prenotabile scelto e aggiungere la prenotazione nella lista delle prenotazioni di quel mezzo
									 int  num = Integer.parseInt(numMezzo);
									 mezziPrenotabili.get(num-1).aggiungiPrenotazione(data, nomeCliente); // come dire mezzo.aggiungiPrenotazione(data,nomeCliente)
									 MainNoleggio.FinestraMain.setVisible(true); //terminata la prenotazione torno al menù principale
									 modificato=true;	 
								 } catch (Exception e) { //gestione dell'errore di conversione da stringa a intero
									 JOptionPane.showMessageDialog(null, "Format error, riprova");
									 prenota(); //possibilità all'utente di reinserire il numero del mezzo da prenotare
							 	 }
							//se viene chiuso il pop up	 
							 } else {
									 MainNoleggio.FinestraMain.setVisible(true);
							 }
					
						}
				  }
			  }
		 } else { //testuale		  
			  Vector<Mezzi> mezziPrenotabili = new Vector<Mezzi>();
			  System.out.println("Prenotazione mezzo:");
			  System.out.println("Inserisci identificativo:");
			  //L'identificativo cliente coincide con la prima parola inserita
			  String nomeCliente= input.nextLine();
			 
			  //se l'utente digita 2 o più nomi come identificativo scatta l'errore dentro InserisciData()
			  // perchè le parole successive vengono interpretate come date di prenotazione . Viene dunque
			  //richiesto di reinserire la data nel formato corretto.
			 
			  Date d = inserisciData(interfaccia);
			  String data = OttieniDataStringa(d);
				//	Controlla se la data inserita dall'utente è precedente alla data corrente.
				if(ottieniDataOdierna().compareTo(d)!=0 && ottieniDataOdierna().compareTo(d)>0) {
					System.out.println("Data precedente a quella odierna");
				} else {
				 //Utile a far visualizzare al cliente i mezzi a disposizione.
				 System.out.println("Quale mezzo vuoi prenotare, tra i seguenti? Digita il numero corrispondente");
				 int n=1;
				 for (Mezzi m : archivioMezzi) {			
		             //Se la data dell'ultima prenotazione è successiva o uguale alla data inserita dall'utente, non visualizzo 
					 //il mezzo perchè non prenotabile
					 boolean trovato=false;
					 Vector<Date> listaDate=m.ottieniDatePrenotazione(m.ottieniPrenotazioni(), interfaccia);
					 for (Date controllaDate : listaDate) {	 
						 if ((controllaDate.compareTo(d)==0)) {
							 trovato=true;
						
						 }
					 }
					 
					 if (trovato) {
						 continue; 
					 }
					 //Vengono mostrati solo i mezzi disponibili all'interno dell'archivio e aggiunti alla lista mezziPrenotabili
					 else  {
						 System.out.print(" "+ n + ". ");
				 	     System.out.println(m.visualizzaM());
				 	     mezziPrenotabili.add(m);
				 	     System.out.println();
				 	     n++;
			 	     } 
				} 
				 //L'utente sceglie quale tra i mezzi disponibili (mezziPrenotabili)
				 //e viene aggiunta la prenotazione allo storico prenotazioni del mezzo selezionato.
				 int numMezzo= input.nextInt();
				 input.nextLine();
				 mezziPrenotabili.get(numMezzo-1).aggiungiPrenotazione(data, nomeCliente);
				 System.out.println("Mezzo disponibile, prenotato!"); 
				 modificato=true;
				}
		}	
}
		


//Metodo che consente di rimuovere una prenotazione sulla base del nomeCliente,  targa del mezzo prenotato e data di prenotazione
	public void eliminaPrenotazione() {
		
		if (interfaccia.equals("grafica")) {
			 //pop up che richiede il nome cliente
			 String nomePrenotazione= JOptionPane.showInputDialog("Prenotazione a nome di:");
			 //se viene chiuso il pop up
			 if (nomePrenotazione== null) {
				  MainNoleggio.FinestraMain.setVisible(true);
			 }
			 else {
				 //metodo che tramite pop up richiede d'inserire la data 
				  Date dataPrenotazione = inserisciData(interfaccia);
				  //se esco dal pop up l'input è null quindi voglio tornare al menù principale
				  if (dataPrenotazione== null) {
					  MainNoleggio.FinestraMain.setVisible(true);
				  }
				  //tratto la data di input come data di prenotazione, la converto in stringa per confrontarla con le prenotazioni del mezzo
			      else {
					  String data = OttieniDataStringa(dataPrenotazione);
					  
					  //pop up per inserire la targa
					  String targaMezzoPrenotato= JOptionPane.showInputDialog("Inserisci targa:");
					  //se viene chiuso il pop up
					  if (targaMezzoPrenotato==null) {
						  MainNoleggio.FinestraMain.setVisible(true);
					  }
					  else {
						 
						 boolean prenotazioneRimossa = false;
						 for (Mezzi m : archivioMezzi) {
							 // filtro sulla targa per evitare di eliminare prenotazioni 
							 //fatte dalla stessa persona per la stessa data per mezzi diversi
							 if (m.targa.equals(targaMezzoPrenotato)) {
								 //(int i parte da 1 per non confrontare la data inserita con la data della prenotazione di default)
								 for (int i=1; i < m.ottieniPrenotazioni().size();i++) {
									//confronto le date di prenotazione del mezzo con la targa desiderata con la data inserita dall'utente
									 //e il nome del cliente che ha prenotato con il nomeCliente inserito dall'utente 
									if(data.equals( m.ottieniPrenotazioni().get(i)[0]) && nomePrenotazione.equals( m.ottieniPrenotazioni().get(i)[1])) {
										//invoco il metodo RimuoviPrenotazione sul mezzo passando come parametro la posizione della data da rimuovere
										//nella lista delle prenotazioni generali del mezzo (non dallo storico delle prenotazioni del mezzo)
										m.RimuoviPrenotazione(i); 
										prenotazioneRimossa=true; // booleano per segnalare che la prenotazione da rimuovere è stata trovata (una volta rimossa rimane a true)
									} 
								 }
								 
								 // se prenotazione rimossa voglio poter salvare la mia modifica
								 if (prenotazioneRimossa) {
									 modificato=true; 
								 } 
								 
							 } else {
								 //se targa digitata diversa da quella del mezzo corrente considerato
								 //prendo in esame il mezzo successivo
								 continue;
							 }
						 }
						
						 //se non ho trovato la prenotazione richiesta quindi non è stata rimossa nessuna prenotazione
						 if (!prenotazioneRimossa) {
							 JOptionPane.showMessageDialog(null,"Rimozione prenotazione NON andata a buon fine");
							 MainNoleggio.FinestraMain.setVisible(true); //torno al menù principale
						 }else {
							 JOptionPane.showMessageDialog(null, "Prenotazione rimossa correttamente");
							 MainNoleggio.FinestraMain.setVisible(true); //finita l'operazione torno al menù principale
							
						 }
				 
					  }
			      }	
			 }
		} else { //testuale
			 System.out.println("---RIMUOVI PRENOTAZIONE----");
			 System.out.println("Prenotazione a nome di: ");
			 String nomePrenotazione = input.nextLine();
				 
			 Date d = inserisciData(interfaccia);
			 String dataPrenotazione = OttieniDataStringa(d);
			
			 System.out.println("Targa del mezzo: ");
			 String targaMezzoPrenotato= input.nextLine();
			 
			 boolean prenotazioneRimossa = false;
			 Vector<String[]> PrenotazioniGenerali = new Vector<String[]>();
			 for (Mezzi m : archivioMezzi) {
				 // filtro sulla targa per evitare di eliminare prenotazioni 
				 //fatte dalla stessa persona per la stessa data per mezzi diversi
				 if (m.targa.equals(targaMezzoPrenotato)) {
					 //int i parte da 1 per non confrontare la data inserita con la data della prenotazione di default
					 for (int i=1; i < m.ottieniPrenotazioni().size();i++) {
						if(dataPrenotazione.equals( m.ottieniPrenotazioni().get(i)[0]) && nomePrenotazione.equals( m.ottieniPrenotazioni().get(i)[1])) {
							m.RimuoviPrenotazione(i);
							prenotazioneRimossa=true;
						} 
					 }
					 if (prenotazioneRimossa) {
						 System.out.println("Prenotazione rimossa correttamente");
						 modificato=true;
					 } 
				 } else {
					 //se targa digitata diversa a quella del mezzo corrente considerato
					 //prendo in esame il mezzo successivo
					 continue;
				 }
			 }
			 
			 //se ho digitato male la targa 
			 if (!prenotazioneRimossa) {
				 System.out.println("Rimozione prenotazione NON andata a buon fine");
			 }	 
			
		}
	 }	
	
	
	
//Ottieni storico prenotazioni per mezzo specifico
	 public void ottieniStorico() {
		 
		 if (interfaccia.equals("grafica")) {
			 int n=1;		 
			 String s="";
			 for (Mezzi m : archivioMezzi) {
				 s= s + n + "." + m.visualizzaM() + "\n";
		 	     n++;
			 }
			 
			 //pop up che permette di scegliere il mezzo di cui vedere lo storico delle prenotazioni
			 String numMezzo= JOptionPane.showInputDialog("Digita il numero del mezzo di cui vuoi vedere lo storico prenotazioni:" + "\n"+ s);
			
			 //se il pop up non viene chiuso
			 if (numMezzo != null) {
				 try {
					 //conversione del numero inserito in input in intero
					 int  num = Integer.parseInt(numMezzo);
					 String r =archivioMezzi.get(num-1).visualizzaStorico();
					 if (r=="Nessuna Prenotazione") {
						  JOptionPane.showMessageDialog(null, r); //pop up con messaggio
					 } else  { 
						 // pop up con la lista delle prenotazioni del mezzo selezionato
						 JOptionPane.showMessageDialog(null, "---STORICO PRENOTAZIONI MEZZO CON TARGA "+ archivioMezzi.get(num-1).targa + "---" + "\n" + r);
						 MainNoleggio.FinestraMain.setVisible(true); // terminata l'operazione torno al menù principale
					 }	 
				 } catch (Exception e) {
				 JOptionPane.showMessageDialog(null, "Format error, riprova");
				 ottieniStorico(); // se numero inserito non valido possibilità di reinserire il numero
				 }
			 }
			else {
				MainNoleggio.FinestraMain.setVisible(true);
			}
	
		} else { //testuale
			 int n=1;
			 System.out.println("Digita il numero del mezzo di cui vuoi vedere lo storico prenotazioni:");
			 for (Mezzi m : archivioMezzi) {
				 System.out.print(" "+ n + ". ");
				 System.out.println(m.visualizzaM());
				 System.out.println();
		 	     n++;
			 }
			 int numMezzo= input.nextInt();
			 input.nextLine();
		
			 String r=archivioMezzi.get(numMezzo-1).visualizzaStorico();
			
			 if (r=="Nessuna Prenotazione") {
				 System.out.println(r); 
			} else  { 
				System.out.println("---STORICO PRENOTAZIONI MEZZO CON TARGA "+ archivioMezzi.get(numMezzo-1).targa + "--- \n" + r );
			}	 
		}
	}
	 

	 //Ottieni prenotazioni generali per mezzo specifico
	 public void visualizzaPrenotazioniGenerali() {
			
			 if (interfaccia.equals("grafica")) {
			 
				 int n=1;		 
				 String s="";
				 for (Mezzi m : archivioMezzi) {
					 s= s + n + "." + m.visualizzaM() + "\n";
			 	     n++;
				 }
				 
				 //pop up che permette di scegliere il mezzo di cui vedere le prenotazioni effettive
				 String numMezzo= JOptionPane.showInputDialog("Digita il numero del mezzo di cui vuoi vedere le prenotazioni correnti:" + "\n"+ s);
				
				 //se il pop up non viene chiuso
				 if (numMezzo != null) {
					 try {
						 int  num = Integer.parseInt(numMezzo);
						 String r =archivioMezzi.get(num-1).visualizzaPrenotazioni();
						 if (r=="Nessuna Prenotazione") {
							  JOptionPane.showMessageDialog(null, r); //pop up con messaggio
						 } else  { 
							 // pop up con la lista delle prenotazioni del mezzo selezionato
							 JOptionPane.showMessageDialog(null, "---PRENOTAZIONI CORRENTI MEZZO CON TARGA "+ archivioMezzi.get(num-1).targa + "---" + "\n" + r);
							 MainNoleggio.FinestraMain.setVisible(true); // terminata l'operazione torno al menù principale
						 }	 
					 } catch (Exception e) {
					 JOptionPane.showMessageDialog(null, "Format error, riprova");
					 visualizzaPrenotazioniGenerali(); // se numero inserito non valido possibilità di reinserire il numero
					 }
				 }
				else {
					MainNoleggio.FinestraMain.setVisible(true);
				}
		
		} else { //testuale
			 int n=1;		 
			 String s="";
			 for (Mezzi m : archivioMezzi) {
				 s= s + n + "." + m.visualizzaM() + "\n";
		 	     n++;
			 }
			 
			 //pop up che permette di scegliere il mezzo di cui vedere le prenotazioni effettive
			 System.out.println("Digita il numero del mezzo di cui vuoi vedere le prenotazioni correnti:" + "\n"+ s);
			 int numMezzo= input.nextInt();
			 input.nextLine();
			
			 String r =archivioMezzi.get(numMezzo-1).visualizzaPrenotazioni();
			 if (r=="Nessuna Prenotazione") {
						System.out.println("---PRENOTAZIONI CORRENTI MEZZO CON TARGA--- \n" +r); 
			 } else  { 				
				System.out.println( "---PRENOTAZIONI CORRENTI MEZZO CON TARGA "+ archivioMezzi.get(numMezzo-1).targa + "---" + "\n" + r);
		
			}	 				 
		} 		
}
	 
	 
public void mostraMezziperCategoria() {
	  if (interfaccia.equals("grafica")) {
		  //Chiediamo la data per la quale controllare le disponibilità
		  Date d = inserisciData(interfaccia); 
		  //se esco dal pop up, l'input è null quindi voglio tornare al menù principale
		  if (d == null) {
			  MainNoleggio.FinestraMain.setVisible(true);  
		  }
		  else {
			 String data = OttieniDataStringa(d);
			 //Controllo per vedere se la data inserita è anteriore o meno a quella odierna
			 if(ottieniDataOdierna().compareTo(d)!=0 && ottieniDataOdierna().compareTo(d)>0) {
				JOptionPane.showMessageDialog(null,"Data precedente a quella odierna");
				mostraMezziperCategoria();	// viene richiamato il metodo per dare la possibilità di reinserire la data
			 } 
			 else {
			    //Chiediamo la categoria del mezzo
				String categoria=JOptionPane.showInputDialog("Di quale categoria vuoi visualizzare la disponibilità? [A]utovetture o [F]urgoni?");
				//si considera solo il primo carattere della stringa inserita dall'utente
				char c = categoria.charAt(0);
				
				//se il pop up viene chiuso
				if (categoria ==null) {
					MainNoleggio.FinestraMain.setVisible(true);
				} else {
				
					String s="";
					//Se la categoria è una autovettura
					if (c =='A'){
						//Salvo nel vettore listaAutovetture il vettore restituito dalla funzione restituisciListaCategoria, passandogli come 
						//parametro il carattere relativo alla categoria inserita dall'utente.
						Vector<Mezzi> listaAutovetture = restituisciListaCategoria(c);
							
						int n=0; //contatore dei risultati mostrati 
						
						//Inizializziamo il booleano trovato per indicare quando trovo una data nella lista 
						//di prenotazioni uguale a quella richiesta
						boolean trovato=false;
					
						//per mostrare le autovetture disponibili 
						for (Mezzi m : listaAutovetture) {
							 trovato=false; //a inizio ciclo dev'essere sempre false (disponibile)
							 //Vengono memorizzate le date di tutte le prenotazioni per ogni singola auto
							 Vector<Date> listaDateAutovetture=m.ottieniDatePrenotazione(m.ottieniPrenotazioni(),interfaccia);
							 
							 //Vengono controllate le date delle prenotazioni nella listaDateAutovetture con la data inserita dall'utente
							 for (Date controllaDate : listaDateAutovetture) {	 
								 if ((controllaDate.compareTo(d)==0)) {
									 //Ho trovato una prenotazione per questa data 
									 trovato=true;
								 }
							 }
							 //Non voglio stampare il mezzo perchè non disponibile, perciò continuo il ciclo su un altro mezzo
							 if (trovato) {
								 continue; 
							 }
							 //Vengono stampati solo i mezzi disponibili all'interno della lista Autovetture
							 //Il contatore n serve a contare i mezzi disponibili
							 else  {
						 	     s= s+"-"+ m.visualizzaM()+ "\n";
						 	     //Contatore stampe dei mezzi disponibili
						 	     n++;
					 	     } 
						} 
						//pop up che mostra le autovetture disponibili per la data richiesta
						JOptionPane.showMessageDialog(null,"Le autovetture disponibili per la data " + data + " sono: \n"+ s);
						
						
						//Se il contatore è zero significa che non sono state trovate autovetture disponibili
						if (n==0) {
							JOptionPane.showMessageDialog(null,"Nessuna autovettura disponibile per questa data");
							
						}
						
						
						MainNoleggio.FinestraMain.setVisible(true);
						
					}
					//Se la categoria è un furgone
					else  if (c =='F') {
						//memorizzo in un vettore il vettore con tutti i furgoni dell'archivio
						Vector<Mezzi> listaFurgoni= restituisciListaCategoria(c);
						int n=0;
						boolean trovato=false;
			
						for (Mezzi m : listaFurgoni) {
							 trovato=false;
							 //memorizzo in un vettore le date di prenotazione del furgone corrente
							 Vector<Date> listaDateFurgoni=m.ottieniDatePrenotazione(m.ottieniPrenotazioni(),interfaccia);
							 //controllo se la data inserita dall'utente è presente tra le date di prenotazione
							 for (Date controllaDate : listaDateFurgoni) {	 
								 if ((controllaDate.compareTo(d)==0)) {
									 trovato=true;
								 }
							 }
							//se trovo la data continuo a cercare un furgone disponibile tra quelli in archivio
							 if (trovato) {
								 continue; 
							 }
							 //Vengono mostrati solo i furgoni disponibili 
							 else  {
						 	     s= s+ "-"+ m.visualizzaM()+"\n";
						 	     n++;
					 	     } 
						}
						
						JOptionPane.showMessageDialog(null,"I furgoni disponibili per la data " + data + " sono: \n"+ s);
						
						if (n==0) {
							JOptionPane.showMessageDialog(null,"Nessun furgone disponibile per questa data");
						}
						MainNoleggio.FinestraMain.setVisible(true);
						
				   } else {
						JOptionPane.showMessageDialog(null, "Errore nel selezionare la categoria");
						MainNoleggio.FinestraMain.setVisible(true);
				   }
				
				}
			 }
		}
	} else { //testuale
		//Chiediamo la data per la quale controllare le disponibilità
		System.out.println("Per quale data vuoi visualizzare le disponibilità?");
		  Date d = inserisciData(interfaccia);
		  String data = OttieniDataStringa(d);
		 //Controllo per vedere se la data inserita è anteriore o meno a quella odierna
		 if(ottieniDataOdierna().compareTo(d)!=0 && ottieniDataOdierna().compareTo(d)>0) {
			System.out.println("Data precedente a quella odierna");
		 } else {
		    //Chiediamo la categoria del mezzo
			System.out.println("Di quale categoria vuoi visualizzare la disponibilità? [A]utovetture o [F]urgoni?");
			char categoria=input.nextLine().charAt(0);
			
			//Se la categoria è una autovettura
			if (categoria=='A'){
				//Salvo nel vettore listaAutovetture la lista restituita dalla funzione restituisciListaCategoria, passandogli come 
				//parametro la stringa inserita dall'utente.
				//memorizzo la lista dei mezzi di categoria autovettura
				Vector<Mezzi> listaAutovetture = restituisciListaCategoria(categoria);
					
				int n=0;
				//Inizializziamo il booleano trovato per indicare quando trovo una data nella lista 
				//di prenotazioni uguale a quella richiesta
				boolean trovato=false;
				System.out.println("Le autovetture disponibili per la data " + data + " sono:");
				for (Mezzi m : listaAutovetture) {
					 trovato=false;
					 //Memorizzo le date di tutte le prenotazioni per ogni singola auto
					 Vector<Date> listaDateAutovetture=m.ottieniDatePrenotazione(m.ottieniPrenotazioni(),interfaccia);
					
					 for (Date controllaDate : listaDateAutovetture) {	 
						 if ((controllaDate.compareTo(d)==0)) {
							 //Ho trovato una prenotazione per questa data 
							 trovato=true;
						 }
					 }
					 //Non voglio stampare il mezzo non disponibile, perciò continuo il ciclo su un altro mezzo
					 if (trovato) {
						 continue; 
					}
					 //Vengono stampati solo i mezzi disponibili all'interno della lista Autovetture
					 //Il contatore n serve a contare i mezzi disponibili
					 else  {
						 System.out.print(" - ");
				 	     System.out.println(m.visualizzaM());
				 	     //Contatore stampe dei mezzi disponibili
				 	     n++;
			 	     } 
					
				} 
				//Se il contatore è zero significa che non trovo mezzi disponibili
				if (n==0) {
						System.out.println("Nessuna auto disponibile per questa data");
				}
				
			}
			//Se la categoria è un furgone
			else  if (categoria=='F') {
				Vector<Mezzi> listaFurgoni= restituisciListaCategoria(categoria);
				int n=0;
				boolean trovato=false;
			    System.out.println("I furgoni disponibili per la data " + data + " sono:");
				for (Mezzi m : listaFurgoni) {
					 trovato=false;
					 Vector<Date> listaDateFurgoni=m.ottieniDatePrenotazione(m.ottieniPrenotazioni(),interfaccia);
					 for (Date controllaDate : listaDateFurgoni) {	 
						 if ((controllaDate.compareTo(d)==0)) {
							 trovato=true;
						 }
					 }
					
					 if (trovato) {
						 continue; 
					}
					 //Vengono mostrati solo i mezzi disponibili all'interno della lista dei furgoni
					 else  {
						 System.out.print(" - ");
				 	     System.out.println(m.visualizzaM());
				 	     n++;
			 	     } 
				}
				if (n==0) {
					System.out.println("Nessun furgone disponibile per questa data");
				}
			} else {
				System.out.println("Errore nel selezionare la categoria");
			}
		}
		
	}	
}


//metodo usato in mostraMezziperCategoria() che restituisce i vettori delle autovetture o dei furgoni
public Vector<Mezzi> restituisciListaCategoria(char a) {
	   Vector<Mezzi> listaAutovetture = new Vector<Mezzi>();
	   Vector<Mezzi> listaFurgoni= new Vector<Mezzi>();
	   for (Mezzi m : archivioMezzi) {
		  String categoria= m.tipo;
		  if (categoria.equals("Autovettura")) {
			  listaAutovetture.add(m);
		  } else {
			  listaFurgoni.add(m);
		  }
	   }
	  
	   if (a =='A') {
		   return listaAutovetture;
	   }  else {
		   return listaFurgoni;
	   }
}

//Metodo per ottenere la data di oggi
//date1.compareTo(date2); //date1 < date2 otterremo come risultato un valore minore di 0
//date2.compareTo(date1); //date2 > date1 otterremo come risultato un valore maggiore di 0
//date1.compareTo(date3); //date1 = date3 otterremo come risultato esattamente 0
public static Date ottieniDataOdierna() {
	 Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY);
	 Date today = calendar.getTime();
	 int format = DateFormat.SHORT;
	 DateFormat dateFormat = DateFormat.getDateInstance(format, Locale.ITALY);
	 String oggi=dateFormat.format(today);
	 Date d=null;
	 try {
		 DateFormat df = new SimpleDateFormat ("dd/MM/yy");
		 d=df.parse(oggi);	 
	 } catch (ParseException e) {
		 JOptionPane.showMessageDialog(null, "Format error, riprova");
	 }
	 return d;
}

//verifica se ci sono modifiche non salvate
public boolean daSalvare() {
		return modificato;
}

//salva il registro nel file
//restituisce true se il salvataggio è andato a buon fine
public boolean salva() {
	
	if (interfaccia.equals("grafica")) {
	// salva solo se necessario (se ci sono modifiche)
	if (daSalvare()) { 
		try {
			ObjectOutputStream file_output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomeArchivio)));
			// salva l'intero oggetto (vettore) nel file
			file_output.writeObject(archivioMezzi);
			file_output.close();
			modificato = false; // le modifiche sono state salvate
			JOptionPane.showMessageDialog(null,"Salvataggio correttamente eseguito");
			MainNoleggio.FinestraMain.setVisible(true);
			return true;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"ERRORE di I/O");	
			return false;
		}		
	} else {
			JOptionPane.showMessageDialog(null,"Nessuna modifica da salvare");
			MainNoleggio.FinestraMain.setVisible(true);
			return true;
	}
	} else { //testuale
		if (daSalvare()) { 
			try {
				ObjectOutputStream file_output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomeArchivio)));
				// salva l'intero oggetto (vettore) nel file
				file_output.writeObject(archivioMezzi);
				file_output.close();
				modificato = false; // le modifiche sono state salvate
				System.out.println("Salvataggio correttamente eseguito");
				
				return true;
			} catch (IOException e) {
				System.out.println("ERRORE di I/O");	
				return false;
			}		
		} else {
				System.out.println("Nessuna modifica da salvare");	
				return true;
		}
	
	}
	}
	


}
