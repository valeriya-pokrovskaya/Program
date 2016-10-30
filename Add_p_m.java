package app;

import java.awt.Choice;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Add_p_m {

	public JLabel l, l1, l2, l3;
	private JTextField text, text1, text2;
	private JButton b, b1;
	public JPanel p;
	String name="", srok="", data="";
	Choice ch, ch1;
	
	
	
	Add_p_m(int row, String name_table, String login){
		KModel4 m = new KModel4(true); 
		ArrayList<String> a = new ArrayList<String>();
	     a = m.getPriv(); 
	    ArrayList<String> a1 = new ArrayList<String>();
	    a1 = m.getSrok(); 
	    int count = m.getCount(); 
	    JFrame fr = new JFrame("Добавление новой прививки");
		fr.setSize(305, 300);
	    fr.setLocationRelativeTo(null);
	    fr.setVisible(true);
		Font f = new Font("Segoe Script",  Font.BOLD,  12);
		Font f1 = new Font("Segoe Script",  Font.BOLD,  13);
	    p = new JPanel();
	    p.setLayout(null);
	     
	    l = new JLabel("Добавление новой прививки");
		l.setBounds(40, 10, 350, 20);
		l.setFont(f1);
		p.add(l);
	    
		l1 = new JLabel("Дата");
		l1.setBounds(65, 40, 100, 20);
		l1.setFont(f);
		p.add(l1);
		
		text = new JTextField();
		text.setBounds(65, 60, 160, 20);
	    p.add(text);
	  
		text.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {            	
        	Calendar cr = new Calendar(fr);
        	String m="", da="";
        	int mon = cr.month+1;
        	int d = Integer.parseInt(cr.day);
        	if(mon>=1 && mon<=9){
        	m ="0"+ Integer.toString(mon);	
        	}
        	else{
        		m = Integer.toString(cr.month+1);	
        	}
        	if(d>=1 && d<=9){
        	da = "0"+ cr.day;
        	}
        	else{
        		da =cr.day;	
        	}
        	String y = Integer.toString(cr.year);
        	data = da + "." + m + "." + y;	
        	text.setText(data);
            }
        });
	    
		l2 = new JLabel("Название"); 
		l2.setBounds(65, 90, 150, 25);
		l2.setFont(f);
		p.add(l2);
		
		ch = new Choice();
		ch.setBounds(65, 120, 160, 20);
		for(int i = 0; i < count; i++){ ch.add(a.get(i));}
		p.add(ch);
	    
	    l3 = new JLabel("Срок действия"); 
		l3.setBounds(65, 150, 150, 25);
		l3.setFont(f);
		p.add(l3);
		
		ch1 = new Choice();
		ch1.setBounds(65, 180, 160, 20);
		for(int i = 0; i < count; i++) {ch1.add(a1.get(i));}
		p.add(ch1);
	    
	    b = new JButton("Добавить");
	    b.setBounds(10, 220, 130, 20);
	    b.setVisible(true);
	    b.setFont(f);
	    p.add(b);
	    
	    b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                KModel m = new KModel(true);
                data = text.getText();
                name = ch.getSelectedItem();;
                srok = ch1.getSelectedItem();;
                int count=0;
            	try {
         		String query = "select count(*) from `gb_x_lera99`.`"+name_table+"`";
         		ResultSet rs = m.connect().executeQuery(query);
         		while (rs.next()) {
                    count = rs.getInt(1);
                }  
                } catch (SQLException | ClassNotFoundException | IOException ex) {
                    Logger.getLogger(KModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            	
            	try {
            		int id = count + 1;
            		String query = "INSERT INTO  `gb_x_lera99`.`"+name_table+"` (`id`, `Дата проведения`, `Название`, `Срок действия`) VALUES ('"+id+"', '"+data+"', '"+name+"', '"+srok+"');";
                    System.out.println(id);
                    m.connect().executeUpdate(query);
                } catch (SQLException | ClassNotFoundException | IOException ex) {
                    Logger.getLogger(KModel.class.getName()).log(Level.SEVERE, null, ex);
            }   
            	new Priv_m(row, login);
                fr.dispose();
            }
            
        });
	    
	    b1 = new JButton("Выйти");
	    b1.setBounds(150, 220, 130, 20);
	    b1.setVisible(true);
	    b1.setFont(f);
	    p.add(b1);
	    
	    b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               fr.dispose();
            }
            
        });
	    
	    fr.setResizable(false);
	    fr.getContentPane().add(p);
	}
}

