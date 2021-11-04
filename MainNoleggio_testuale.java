
import java.util.Scanner;


public class MainNoleggio_testuale {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Creiamo un archivio: inserire il nome");
		
		//richiedo il nome del file contenente l'archivio
		String nomefile = input.nextLine();
		
		//dichiaro il tipo d'interfaccia che deve attivare il main
		String interfaccia ="testuale";
		
		// creare un archivio con il nome scelto in input
		ArchivioMezzi archivio = new ArchivioMezzi(nomefile, interfaccia);
		
		char scelta;
		do {
			System.out.println(); //riga bianca 
			System.out.println("OPERAZIONI DISPONIBILI");
			System.out.println("   [A]ggiungi mezzo all'archivio");
			System.out.println("   [R]imuovi mezzo dall'archivio");
			System.out.println("   [V]isualizza i mezzi a disposizione");
			System.out.println("   [M]ostra i mezzi di una certa categoria");
			System.out.println("   [P]renota un mezzo se disponibile");
			System.out.println("   [E]limina prenotazione");
			System.out.println("   [G]uarda storico prenotazioni di uno specifico mezzo");
			System.out.println("   [C]ontrolla prenotazioni effettive");
			System.out.println("   [S]alva l'archivio");
			System.out.println("   [U]scita");
			System.out.print("scelta: ");
			scelta = input.nextLine().charAt(0);
			
			System.out.println(); //riga bianca
			switch (scelta) {

			// caso "Aggiungi mezzo all'archivio"
	         	case 'A':
				archivio.Aggiungi_testuale();  //funzione diversa dal main grafico
				break;
			
			// caso "Rimuovi mezzo dall'archivio"
			case 'R': 
				archivio.rimuoviMezzo();
				break;  

		    // caso "Visualizza tutti i mezzi dell'archivio"
			case 'V': 
				archivio.visualizza();
				break;
			
			// caso "Prenota un mezzo"
			case 'P':
				archivio.prenota();
				break;
			
			case 'S':
				archivio.salva();
			    break;
			// caso "Mostra mezzi di una certa categoria disponibili, in base a una data scelta"
			case 'M':
				archivio.mostraMezziperCategoria();
				break;
				
	 		// caso "Elimina prenotazione"
			case 'E':
				archivio.eliminaPrenotazione();
				break;     
                       
			// caso "Guarda prenotazioni di uno specifico mezzo" (storico prenotazioni, cioè incluse cancellazioni)
			case 'G':
				archivio.ottieniStorico();
				break;
			// caso "Controlla prenotazioni di uno specifico mezzo" (passate/future, ESCLUSE cancellazioni)
			case 'C':
				archivio.visualizzaPrenotazioniGenerali(); 
				break;
			case 'U':
				if (archivio.daSalvare()) {
					archivio.salva();
					System.out.println("Modifiche salvate. Programma terminato");
				}
				else {
					System.out.println("Non ci sono modifiche da salvare. Programma terminato");
				}
				break;
			default : System.out.println("Errore, operazione non disponibile. Riprova");
			}

			System.out.println(); // riga vuota tra una operazione e l'altra  

		} while (scelta!='U');
	}		
}

