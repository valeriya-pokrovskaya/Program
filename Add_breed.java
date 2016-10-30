package app;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Add_breed extends JFrame{


	public JLabel l, l1, l2, l3;
	private JTextField text, text1, text2;
	private JButton b, b1;
	public JPanel p;
	String name="", color="", sokr="";
	
	Add_breed(String login){
		
		super("Добавление новой породы");
		setSize(305, 300);
	    setLocationRelativeTo(null);
	    setVisible(true);
	    String name_table = login +"_porod";
		Font f = new Font("Segoe Script",  Font.BOLD,  12);
		Font f1 = new Font("Segoe Script",  Font.BOLD,  13);
	    p = new JPanel();
	    p.setLayout(null);
	     
	    l = new JLabel("Добавление новой породы");
		l.setBounds(40, 10, 350, 20);
		l.setFont(f1);
		p.add(l);
	    
		l1 = new JLabel("Название породы");
		l1.setBounds(65, 40, 200, 20);
		l1.setFont(f);
		p.add(l1);
		
		text = new JTextField();
		text.setBounds(65, 60, 160, 20);
	    p.add(text);
	  
		l2 = new JLabel("Сокращение породы"); 
		l2.setBounds(65, 90, 300, 25);
		l2.setFont(f);
		p.add(l2);
		
	    text1 = new  JTextField();
	    text1.setBounds(65, 120, 160, 20);
	    p.add(text1);
	    
	    l3 = new JLabel("Цвет"); 
		l3.setBounds(65, 150, 150, 25);
		l3.setFont(f);
		p.add(l3);
		
		text2 = new  JTextField();
	    text2.setBounds(65, 180, 160, 20);
	    p.add(text2);
	    
	    b = new JButton("Добавить");
	    b.setBounds(10, 220, 130, 20);
	    b.setVisible(true);
	    b.setFont(f);
	    p.add(b);
	    
	    b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                KModel3 m = new KModel3(true);
                name = text.getText();
                sokr = text1.getText();
                color = text2.getText();
                m.add(name, sokr, color);
                new SpravBreed(login);
                dispose();
            }
            
        });
	    
	    b1 = new JButton("Выйти");
	    b1.setBounds(150, 220, 130, 20);
	    b1.setVisible(true);
	    b1.setFont(f);
	    p.add(b1);
	    
	    b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
            
        });
	    
	    getContentPane().add(p);
	    setResizable(false);
	}
}
