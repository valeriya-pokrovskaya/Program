package app;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class Hel extends JFrame{
	
	public Hel()
	{
		super("Справка");
		setSize(500, 250);
		
        
        JTabbedPane jtp = new JTabbedPane();
        JPanel p = new JPanel(); 
        p.setLayout(null);
        JTextArea t = new JTextArea("");
        t.setBounds(5, 20, 300, 150);
        p.add(t);
        jtp.addTab("Форма Спавочник пород", p);

        JPanel p1 = new JPanel(); 
        p.setLayout(null);
        JTextArea t1 = new JTextArea();
        t1.setBounds(5, 20, 300, 150);
        p1.add(t1);
        jtp.addTab("Форма Спавочник прививок", p1);
        
        JPanel p2 = new JPanel(); 
        p.setLayout(null);
        JTextArea t2 = new JTextArea();
        t2.setBounds(5, 20, 300, 150);
        p2.add(t2);
        jtp.addTab("Форма Спавочник адресов", p2);
        
        
       getContentPane().add(jtp); 
       setVisible(true); 
	}

}
