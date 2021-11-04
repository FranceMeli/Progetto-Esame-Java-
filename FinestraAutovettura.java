import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//Classe per creazione finestra per inserire le caratteristiche dell'auto che si vuole 
//aggiungere all'archivio. Dipende dal click sul pulsante "Autovettura" della finestraMezzi
public class FinestraAutovettura extends JFrame implements ActionListener {
	
	JPanel panel1= new JPanel();
    JLabel s=new JLabel("INSERISCI INFORMAZIONI AUTOVETTURA");
    
    //falsa etichetta per avere uno spazio tra intestazione e parametri
    JLabel GAP =new JLabel("    ");
    
    //Parametri e descrizione dei parametri richiesti
    JLabel s1=new JLabel("Modello:");
    JTextField Jmodello= new JTextField(10);
   
    JLabel s2=new JLabel("Targa:");
    JTextField Jtarga= new JTextField(10);
    
    JLabel s3=new JLabel("Posti:");
    JRadioButton posti0 = new JRadioButton("2");
    JRadioButton posti1 = new JRadioButton("4");
    JRadioButton posti2 = new JRadioButton("5");
    ButtonGroup gruppoPosti = new ButtonGroup();
    
    JLabel s4=new JLabel("Tipologia:");
    JRadioButton tipo0 = new JRadioButton("Utilitaria");
    JRadioButton tipo1= new JRadioButton("Media");
    JRadioButton tipo2 = new JRadioButton("Berlina");
    ButtonGroup gruppoTipologia= new ButtonGroup();
   
    JLabel s5=new JLabel("Alimentazione:");
    JRadioButton Alim0 = new JRadioButton("Benzina");
    JRadioButton Alim1 = new JRadioButton("Diesel");
    ButtonGroup gruppoAlimentazione = new ButtonGroup();
    
    JButton bottoneSalva = new JButton("Salva");
    JButton bottoneAnnulla = new JButton("Annulla");
    
    GroupLayout layout =new GroupLayout(panel1);
    

    public FinestraAutovettura() {
    	
    	//dimensioni finestra: larghezza, altezza
    	this.setSize(600,280);
        
        //la chiusura della finestra autovettura non provoca nulla
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
        
        //numero posti
        panel1.add(s3);
        panel1.add(posti0);
        panel1.add(posti1);
        panel1.add(posti2);
        
        //tipologia di auto
        panel1.add(s4);
        panel1.add(tipo0);
        panel1.add(tipo1);
        panel1.add(tipo2);
        
        //alimentazione
        panel1.add(s5);
        panel1.add(Alim0);
        panel1.add(Alim1);
        
        gruppoPosti.add(posti0);
        gruppoPosti.add(posti1);
        gruppoPosti.add(posti2);
        
        gruppoTipologia.add(tipo0);
        gruppoTipologia.add(tipo1);
        gruppoTipologia.add(tipo2);
        
        gruppoAlimentazione.add(Alim0);
        gruppoAlimentazione.add(Alim1);
        
        panel1.add(bottoneSalva);
        panel1.add(bottoneAnnulla);
        
        
        bottoneSalva.addActionListener(this);
        bottoneAnnulla.addActionListener(this);
        Jtarga.addActionListener(this); 
        Jmodello.addActionListener(this);
       
        this.add(panel1);
        this.setVisible(true);
        
        panel1.setLayout(layout);
        
        //spazi di default
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        
        //visualizzazione per colonne 
        layout.setHorizontalGroup(
      		  layout.createSequentialGroup()
      		  		.addGroup(layout.createParallelGroup()
      		  			.addComponent(GAP)
      		  			.addComponent(s)
      		  			.addComponent(GAP)
      		  			.addComponent(s1)
      		  			.addComponent(s2)
      		  			.addComponent(s3)
      		  			.addComponent(s4)
      		  			.addComponent(s5)      		  				
      		  		)
      		  		
      		  		.addGroup(layout.createParallelGroup()     		  		
		      		   	.addComponent(Jmodello)
		    		 	.addComponent(Jtarga)
		    		 	.addComponent(Alim0)
		    		 	.addComponent(posti0)
		    		 	.addComponent(tipo0) 	
      		  		)
      		  		
      		  		
      		  		.addGroup(layout.createParallelGroup() 
      		  			.addComponent(Alim1)
      		  			.addComponent(posti1)	
      		  			.addComponent(tipo1)
      		  			.addComponent(bottoneAnnulla)    		  			
      		  		)
      		  			
      		  			
      		  		.addGroup(layout.createParallelGroup() 
      		  			.addComponent(posti2)
      		  			.addComponent(tipo2)
      		  			.addComponent(bottoneSalva)
      		  			
      		  		)
      		  	);
        
        
        //visualizzazione righe
        layout.setVerticalGroup(
        		 
        		layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        					 .addComponent(GAP)
        					 )
        		 	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        		 			 .addComponent(s)
        		 			 )
        			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
              				 .addComponent(GAP)
        				   	 )       		 	
        		 	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	        				 .addComponent(s1)
	        				 .addComponent(Jmodello)
	        		 		 )        		 	
        		 	 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	           				  .addComponent(s2)
	           				  .addComponent(Jtarga)
	        		 		  )       		 	
        		 	 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	        		 		  .addComponent(s5)
	        		 		  .addComponent(Alim0)
	        		 		  .addComponent(Alim1)   		 		
	        		 		  )
        		 	 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	        		 		  .addComponent(s3)
	        		 		  .addComponent(posti0)
	        		 		  .addComponent(posti1)
	        		 		  .addComponent(posti2)
	        		 		  )
        			 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	             		 	  .addComponent(s4)
	             		 	  .addComponent(tipo0)
	             		 	  .addComponent(tipo1)
	             		 	  .addComponent(tipo2)
	        				  )
        			 
	 
        			 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        					 .addComponent(bottoneAnnulla)
        					 .addComponent(bottoneSalva)
        					 )
        		
        		
        		);
	}
    
    
    public static String tipo;
	public static String m;
	public static String t;
	public static String posti;
	public static String tipologia;
	public static String alimentazione;

	public void actionPerformed(ActionEvent e) { 
			tipo="Autovettura";
			 
			//Recupero la stringa inserita dall'utente nei campi JTextField
			m= Jmodello.getText();
			t= Jtarga.getText();
			
			if (posti0.isSelected())
				posti= "2";
			else if (posti1.isSelected())
				posti="4";
			else if  (posti2.isSelected())
				posti="5";
			else posti="";
			
			
			if (tipo0.isSelected())
				tipologia="Utilitaria";
			else if (tipo1.isSelected())
				tipologia="Media";
			else if (tipo2.isSelected())
				tipologia="Berlina";
			else tipologia="";
			
			
			if (Alim0.isSelected())
				alimentazione="Benzina";
			else if (Alim1.isSelected())
				alimentazione="Diesel";
			else alimentazione="";
			
			//viene chiamata la funzione costruisciMezzo del main passando come parametro la categoria del mezzo
			Object src= e.getSource();	
			if (src==bottoneSalva) {
				MainNoleggio.costruisciMezzo(tipo);
				setVisible(false);	
			} else if (src==bottoneAnnulla){
				setVisible(false);
				MainNoleggio.FinestraMain.setVisible(true);
			}
			
	
	}
}

