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

public class Add_w_g {


	Weight_g wg;
	public JLabel l, l1, l2, l3;
	private JTextField text, text1;
	private JButton b, b1;
	public JPanel p;
	String name="", weight="", data="";
	Choice ch, ch1;
	
	
	
	Add_w_g(int row, String name_table, String login){
 
	    JFrame fr = new JFrame("Добавление нового веса");
		fr.setSize(305, 250);
	    fr.setLocationRelativeTo(null);
	    fr.setVisible(true);
		Font f = new Font("Segoe Script",  Font.BOLD,  12);
		Font f1 = new Font("Segoe Script",  Font.BOLD,  13);
	    p = new JPanel();
	    p.setLayout(null);
	     
	    l = new JLabel("Добавление нового веса");
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
	    
		l2 = new JLabel("Вес"); 
		l2.setBounds(65, 90, 150, 25);
		l2.setFont(f);
		p.add(l2);
		
		text1 = new JTextField();
		text1.setBounds(65, 120, 160, 20);
		p.add(text1);
	    
	    
	    b = new JButton("Добавить");
	    b.setBounds(10, 170, 130, 20);
	    b.setVisible(true);
	    b.setFont(f);
	    p.add(b);
	    
	    b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                KModel2 m = new KModel2(true);
                data = text.getText();
                weight = text1.getText();;
                int count=0;
            	try {
         		String query = "select count(*) from `gb_x_lera99`.`"+name_table+"`";
         		ResultSet rs = m.connect().executeQuery(query);
         		while (rs.next()) {
                    count = rs.getInt(1);
                }  
                } catch (SQLException | ClassNotFoundException | IOException ex) {
                    Logger.getLogger(KModel2.class.getName()).log(Level.SEVERE, null, ex);
                }
            	
            	try {
            		int id = count + 1;
            		String query = "INSERT INTO  `gb_x_lera99`.`"+name_table+"` (`id`, `Дата`, `Вес(граммы)`) VALUES ('"+id+"', '"+data+"', '"+weight+"');";
                    System.out.println(id);
                    m.connect().executeUpdate(query);
                } catch (SQLException | ClassNotFoundException | IOException ex) {
                    Logger.getLogger(KModel2.class.getName()).log(Level.SEVERE, null, ex);
            }   
            	new Weight_g(row, login);
                fr.dispose();
            }
            
        });
	    
	    b1 = new JButton("Выйти");
	    b1.setBounds(150, 170, 130, 20);
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

