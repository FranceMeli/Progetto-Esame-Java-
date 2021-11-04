import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//Classe per creazione finestra per inserire le caratteristiche del furgone che si vuole aggiungere all'archivio.
//Dipende dal click sul pulsante furgone della finestraMezzi
public class FinestraFurgone extends JFrame {

		JPanel panel1= new JPanel();
		
		//intestazione finestra
		JLabel s=new JLabel("INSERISCI INFORMAZIONI FURGONE");   
		//falsa etichetta per avere uno spazio tra intestazione e parametri
	    JLabel GAP =new JLabel("    "); 
	    
	    //Parametri e descrizione dei parametri richiesti
	    JLabel s1=new JLabel("Modello:");
	    JTextField Jmodello= new JTextField(10);
	    
	    JLabel s2=new JLabel("Targa");
	    JTextField Jtarga= new JTextField(10);
	    
	    JLabel s3=new JLabel("Patente:");
	    JRadioButton pat1= new JRadioButton("B");
	    JRadioButton pat2= new JRadioButton("C");
	    ButtonGroup gruppoPatente = new ButtonGroup();
	    
	    JLabel s4=new JLabel("Dimensione:");
	    JRadioButton dim1= new JRadioButton("Grande");
	    JRadioButton dim2= new JRadioButton("Piccolo");
	    ButtonGroup gruppoDimensioni = new ButtonGroup();
	
	    //pulsante salvataggio
	    JButton bottoneSalva = new JButton("Salva");
	    //pulsante annulla
	    JButton bottoneAnnulla = new JButton("Annulla");
	    
	    //listener eventi
	    Listener evento2= new Listener();
	    
	    //layout della finestra
	    GroupLayout layout =new GroupLayout(panel1);

	 
public FinestraFurgone() {
		
		//dimensioni finestra: larghezza, altezza	
        this.setSize(500,250);
        
        //la chiusura della finestra non provoca nessun evento
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
       
       
        //descrizione finestra
        panel1.add(s);
        
        //label vuota per lasciare uno spazio tra la descrizione della finestra e i parametri 
        panel1.add(GAP);
        
        //targa e modello
        panel1.add(s1);
        panel1.add(Jmodello);   
        panel1.add(s2);
        panel1.add(Jtarga);
        
        //tipo di patente
        panel1.add(s3);
        panel1.add(pat1);
        panel1.add(pat2);   
        
        //dimensioni
        panel1.add(s4);
        panel1.add(dim1);
        panel1.add(dim2);
        panel1.add(bottoneSalva);
        panel1.add(bottoneAnnulla);
        
        //aggiunta del pannello alla finestra
        this.add(panel1);
        
        //aggiunta dei pulsanti ai vari gruppi
        gruppoDimensioni.add(dim1);
        gruppoDimensioni.add(dim2);
        gruppoPatente.add(pat1);
        gruppoPatente.add(pat2);
        
        //elementi finestra collegati all'evento
        bottoneSalva.addActionListener(evento2);
        bottoneAnnulla.addActionListener(evento2);
        Jtarga.addActionListener(evento2); 
        Jmodello.addActionListener(evento2);
     
        //visibilità finestraFurgone
        this.setVisible(true);
        
        //assegnamento del layout al pannello
        panel1.setLayout(layout);
        
        //spazi di deafult
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
     
        
        //visualizzazione per colonne 
        layout.setHorizontalGroup(
        		  layout.createSequentialGroup()
        		  
        		  //prima colonna
        		  .addGroup(layout.createParallelGroup()
        				  .addComponent(GAP)
        				  .addComponent(s)
        				  .addComponent(GAP)
        				  .addComponent(s1)
        				  .addComponent(s2)
        				  .addComponent(s3)
        				  .addComponent(s4)
        				  )
        				  
        		  //seconda colonna
        		  .addGroup(layout.createParallelGroup()
        				  .addComponent(Jmodello)
        				  .addComponent(Jtarga)
        				  .addComponent(pat1)
        				  .addComponent(dim1)
        				  .addComponent(bottoneAnnulla)
        				
        				  )
        		  //terza colonna  
        		  .addGroup(layout.createParallelGroup()
		        		  .addComponent(pat2)
		        		  .addComponent(dim2)
		        		  .addComponent(bottoneSalva)
        				  )
        				  
        		);
        
        
        //visualizzazione righe
        layout.setVerticalGroup(
        		
        		 layout.createSequentialGroup()
        		 //riga zero
         		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
               			 .addComponent(GAP)
               			 )
        		 //prima riga
        		 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				 .addComponent(s)
        				 )
        		 //seconda riga
        		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
              			 .addComponent(GAP)
              			 )
           		 	
        		//terza riga
        		 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				 .addComponent(s1)
        				 .addComponent(Jmodello)
        				 )
        		//quarta riga	 
        		 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				  .addComponent(s2)
        				  .addComponent(Jtarga)
        				  )
        		//quinta riga
        		 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		        		 .addComponent(s3)
		        		 .addComponent(pat1)
		        		 .addComponent(pat2)
        				 )
        		//sesta riga 
        		 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                		 .addComponent(s4)
                		 .addComponent(dim1)
                		 .addComponent(dim2)
                		 )
        		 //settima riga
        		 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				 .addComponent(bottoneAnnulla)
        				 .addComponent(bottoneSalva)
        				 
        				 )
        		 
        		);  

        
	}
	
	
	public static String tipo;
	public static String m;
	public static String t;
	public static String patente;
	public static String dimensione;
	
	
class Listener implements ActionListener{
	public void actionPerformed(ActionEvent e) { 
		
		tipo="Furgone";
		//Recupero la stringa inserita dall'utente nei campi JTextField
		m= Jmodello.getText();
		t= Jtarga.getText(); 
		
		//primo gruppo pulsanti
		if (pat1.isSelected())
				patente="B";
		else if (pat2.isSelected())
				patente="C";
		else patente="";
		
		
		//secondo gruppo pulsanti
		if (dim1.isSelected())
				dimensione="Grande";
		else if (dim2.isSelected())
				dimensione="Piccolo";
		else dimensione=""; 
		
		
		//viene chiamata la funzione costruisciMezzo del main passando come parametro la categoria del mezzo
		Object src= e.getSource();	
		if (src==bottoneSalva) {
			MainNoleggio.costruisciMezzo(tipo);
			setVisible(false);
		} else if (src==bottoneAnnulla) {
			setVisible(false);
			MainNoleggio.FinestraMain.setVisible(true);
		}		
	       
	}
}		
 



}
