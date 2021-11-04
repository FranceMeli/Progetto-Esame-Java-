import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//Classe per creazione finestra che dipende dal click sul pulsante aggiungiMezzo della finestra mostrata dal main
public class FinestraMezzi extends JFrame {

	JPanel panel= new JPanel();
    JLabel s=new JLabel("Scegli la categoria del mezzo che vuoi aggiungere");
    JButton bottone = new JButton("Autovettura");
    JButton bottone1 = new JButton("Furgone");
    Listener evento1 = new Listener();
    
    //costruttore
	public FinestraMezzi () {
		
		//dimensioni finestra
        this.setSize(400,100);
        
        //la chiusura della finestra non provoca nessun evento 
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        //aggiunta degli elementi creati al pannello
        panel.add(s);
        panel.add(bottone);
        panel.add(bottone1);
        
        //collegamento elementi all'evento
        bottone.addActionListener(evento1);
        bottone1.addActionListener(evento1);
        
        //aggiunta del pannello alla finestra
        this.add(panel);
        
        //Visibilità finestra mezzi
        this.setVisible(true);
        
	}
	
	
	//dichiarazione delle finestre di tipo FinestraAutovettura o di tipo FinestraFurgone
	FinestraAutovettura FinestraAutovettura;
	FinestraFurgone FinestraFurgone;
	
	class Listener implements ActionListener{
			
		public void actionPerformed(ActionEvent e) {
			//elemento che subisce l'evento
			Object src= e.getSource();	
			//se viene cliccato il pulsante autovettura si passa alla finestra autovettura per creare l'autovettura
			if (src==bottone) {
			    FinestraAutovettura = new FinestraAutovettura();
			    setVisible(false); 
			} else {
				//se viene cliccato il pulsante furgone si passa alla finestra furgone per creare il furgone
				FinestraFurgone= new FinestraFurgone(); 
				setVisible(false);
			}
				 
		}
	}
}
