import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//Classe MainNoleggio che contiene il main per far partire l'esecuzione del programma
public class MainNoleggio extends JFrame {

		 public static MainNoleggio FinestraMain;
		
		 //creazione della finestra che conterrà il menù principale con tutte le operazioni per la gesione del noleggio
		 public static void main(String[] args) {
			 FinestraMain = new MainNoleggio("Gestione Noleggio");
			 FinestraMain.setTitle("Menù principale");			
		 }
	
		
		 //finestra di input che chiede il nome dell'archivio
	     static String nomefile = JOptionPane.showInputDialog("Accedi/Crea Archivio","Inserisci nome archivio");
	     
		 //dichiaro il tipo d'interfaccia che deve attivare il main
	     static String interfaccia ="grafica";
	     
		 // creazione archivio con il nome scelto in input
		 static ArchivioMezzi archivio = new ArchivioMezzi(nomefile,interfaccia);
			 
		 
		 //creazione pannello finestra e bottoni menù principale
		 JPanel panel= new JPanel();
	     JButton bottoneA = new JButton("Aggiungi mezzo");
	     JButton bottoneR = new JButton("Rimuovi mezzo");
	     JButton bottoneV = new JButton("Visualizza la lista completa dei mezzi");
	     JButton bottoneM = new JButton("Mostra mezzi per categoria");
	     JButton bottoneP = new JButton("Prenota mezzo");
	     JButton bottoneE = new JButton("Elimina prenotazione");
	     JButton bottoneG = new JButton("Guarda storico prenotazioni mezzo ");
	     JButton bottoneVP= new JButton("Controlla prenotazioni effettive");
	     JButton bottoneS = new JButton("Salva archivio");
	     JButton bottoneU = new JButton("Uscita");
	 

	     //creazione dell'ascoltatore
	     Listener evento = new Listener();  
	     
	     
	     //costruttore
	     public MainNoleggio(String titolo) {      
					super(titolo);
			        this.setSize(400,280); //dimensioni finestra
			        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //la chiusura del menù termina il programma
			        
			        //aggiunta dei pulsanti del menù al pannello della finestra
			        panel.add(bottoneA);
			        panel.add(bottoneR);
			        panel.add(bottoneV);
			        panel.add(bottoneM);
			        panel.add(bottoneP);
			        panel.add(bottoneE);
			        panel.add(bottoneG);
			        panel.add(bottoneVP);
			        panel.add(bottoneS);
			        panel.add(bottoneU);
			 
			        
			        //collegamento dei pulsanti all'evento
			        bottoneA.addActionListener(evento);
			        bottoneR.addActionListener(evento);
			        bottoneV.addActionListener(evento);
			        bottoneM.addActionListener(evento);
			        bottoneP.addActionListener(evento);
			        bottoneE.addActionListener(evento);
			        bottoneG.addActionListener(evento);
			        bottoneS.addActionListener(evento);
			        bottoneU.addActionListener(evento);
			        bottoneVP.addActionListener(evento);
			        
			        //aggiunta del pannello alla finestra
			        this.add(panel);
			        //visibilità della finestra
			        this.setVisible(true);
       
			}
	  
	   
	     
		public static FinestraMezzi FinestraMezzi;
		String v="";	
		//classe ascoltatrice degli eventi 
		class Listener implements ActionListener{
				public void actionPerformed(ActionEvent e) {
						Object src= e.getSource(); //oggetto che subisce l'evento
						if (src==bottoneA) {		
							 setVisible(false); // la finestra del menù principale scompare ogni volta che si preme su uno dei pulsanti
							 FinestraMezzi = new FinestraMezzi();	// creazione della finestra per aggiungere un mezzo all'archivio 
                        }
						else if (src==bottoneR) {
							setVisible(false);
							archivio.rimuoviMezzo(); // elimina un mezzo dall'archivio se non ci sono prenotazioni per il giorno corrente o una data successiva						
						}
						else if (src==bottoneV) {
							setVisible(false);
							archivio.visualizza();
						}
						else if (src==bottoneM) {
							setVisible(false);
							archivio.mostraMezziperCategoria();	//mostra i mezzi disponibili per una certa data a seconda della categoria scelta
						} else if (src==bottoneP) {
							setVisible(false);
							archivio.prenota(); // prenotazione di un mezzo se disponibile
						} else if (src==bottoneE) {
							setVisible(false);
							archivio.eliminaPrenotazione(); //elimina una prenotazione (indipendentemente dalla data corrente)
						} else if (src==bottoneG) {
							setVisible(false);
							archivio.ottieniStorico(); //mostra tutte le prenotazioni (passate/future, comprese cancellazioni) di un mezzo specifico
						} else if (src==bottoneVP) {
								setVisible(false);
								archivio.visualizzaPrenotazioniGenerali(); //mostra tutte le prenotazioni (passate/future, SENZA cancellazioni) di un mezzo specifico	
						} else if (src==bottoneS) {
							setVisible(false);
							archivio.salva();	//salvataggio dell'archivio
						} else if (src==bottoneU) {  //uscita dal menù
							setVisible(false);
							if (archivio.daSalvare()) { //se ci sono modifiche da salvare 
								int risposta;
								do {
									//pop up che chiede conferma per il salvataggio
									risposta = JOptionPane.showConfirmDialog(null,  "Vuoi salvare le modifiche effettuate?",
											" Scelta " , JOptionPane.YES_NO_OPTION );
									switch (risposta) {
									case JOptionPane.YES_OPTION : archivio.salva(); System.exit(0); 
									case JOptionPane.NO_OPTION : System.exit(0); 
									case JOptionPane.CLOSED_OPTION : System.exit(0); 
									}
								} while ((risposta!=JOptionPane.YES_OPTION) || (risposta!=JOptionPane.NO_OPTION));
							}
							else { 
								JOptionPane.showMessageDialog(null, "Non ci sono modifiche da salvare. Programma terminato");
								System.exit(0); //termina programma 
							}
						}
						
						}
								
		}
		
		
		//metodo che costruisce il mezzo recuperando i parametri inseriti dall'utente nelle finestre dei mezzi 
		public static void costruisciMezzo(String s) {
		
			//se la categoria è furgone controllo che i parametri della FinestraFurgone siano stati compilati dall'utente
			if (s.equals("Furgone")) {
				//non è necessario controllare il tipo di mezzo perchè è sempre specificato a priori
				if ( (FinestraFurgone.m.equals("")) ||  (FinestraFurgone.t.equals("") )||(FinestraFurgone.patente.equals("")) ||  (FinestraFurgone.dimensione.equals("") )) {
					JOptionPane.showMessageDialog(null, "Caratteristiche mezzo incomplete: mezzo NON aggiunto.");
				} else {
					Furgone a = new Furgone (FinestraFurgone.tipo, FinestraFurgone.m,FinestraFurgone.t,FinestraFurgone.patente, FinestraFurgone.dimensione);
					archivio.aggiungi(a);	
				}
			}
			//se la categoria è autovettura controllo che i parametri della FinestraAutovettura siano stati compilati dall'utente
			else {			
				if ( (FinestraAutovettura.m.equals("")) || (FinestraAutovettura.t.equals("")) || (FinestraAutovettura.posti.equals("")) || (FinestraAutovettura.tipologia.equals("")) || (FinestraAutovettura.alimentazione.equals("")) ) {
					JOptionPane.showMessageDialog(null, "Caratteristiche mezzo incomplete: mezzo NON aggiunto.");
				} else {
					Autovettura a = new Autovettura (FinestraAutovettura.tipo, FinestraAutovettura.m, FinestraAutovettura.t, FinestraAutovettura.posti, FinestraAutovettura.tipologia, FinestraAutovettura.alimentazione);
					archivio.aggiungi(a);
					
				}
			}
			FinestraMain.setVisible(true); //costruito il mezzo o meno torno comunque al menù principale
		}
		

}
			
		
