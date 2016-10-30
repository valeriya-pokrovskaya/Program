package app;

import java.awt.Checkbox;
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

public class Add_sl_okr {

	public JLabel l, l1, l2, l3, l4;
	private JTextField text, text1, text2, text3;
	private JButton b, b1;
	public JPanel p;
	String name="", srok="", data1="", data2="", kol_r="", kol_m="", i="";
	Choice ch;
	Checkbox cb1;
	
	
	
	Add_sl_okr(int row, String name_table, String login){
		KModel m = new KModel(true); 
		ArrayList<String> a = new ArrayList<String>();
	     a = m.getPar(); 
	    int count = m.getCount(); 
	    JFrame fr = new JFrame("Добавление новой случки");
		fr.setSize(305, 450);
	    fr.setLocationRelativeTo(null);
	    fr.setVisible(true);
		Font f = new Font("Segoe Script",  Font.BOLD,  12);
		Font f1 = new Font("Segoe Script",  Font.BOLD,  13);
	    p = new JPanel();
	    p.setLayout(null);
	     
	    l = new JLabel("Добавление новой случки");
		l.setBounds(40, 10, 350, 20);
		l.setFont(f1);
		p.add(l);
	    
		l1 = new JLabel("Дата соития");
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
        	data1 = da + "." + m + "." + y;	
        	text.setText(data1);
            }
        });
	    
		l2 = new JLabel("Партнер"); 
		l2.setBounds(65, 90, 150, 25);
		l2.setFont(f);
		p.add(l2);
		
		ch = new Choice();
		ch.setBounds(65, 120, 160, 20);
		for(int i = 0; i < count; i++){ ch.add(a.get(i));}
		p.add(ch);
	    
	    l3 = new JLabel("Дата окрола"); 
		l3.setBounds(65, 150, 150, 25);
		l3.setFont(f);
		p.add(l3);
		
		text1 = new JTextField();
		text1.setBounds(65, 180, 160, 20);
		p.add(text1);
	    
		text1.addMouseListener(new java.awt.event.MouseAdapter() {
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
        	data2 = da + "." + m + "." + y;	
        	text1.setText(data2);
            }
        });
		
		l3 = new JLabel("Рождено"); 
		l3.setBounds(65, 210, 150, 25);
		l3.setFont(f);
		p.add(l3);
		
		text2 = new JTextField();
		text2.setBounds(65, 240, 160, 20);
		p.add(text2);
		
		l4 = new JLabel("Мертворожденные"); 
		l4.setBounds(65, 270, 150, 25);
		l4.setFont(f);
		p.add(l4);
		
		text3 = new JTextField();
		text3.setBounds(65, 300, 160, 20);
		p.add(text3);
		
		cb1 = new Checkbox("Инбридинг"); 
	    cb1.setBounds(65, 330, 160, 30); 
	    cb1.setFont(f);
	    p.add(cb1); 
		
	    b = new JButton("Добавить");
	    b.setBounds(10, 370, 130, 20);
	    b.setVisible(true);
	    b.setFont(f);
	    p.add(b);
	    
	    b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                KModel m = new KModel(true);
                data1 = text.getText();
                name = ch.getSelectedItem();
                data2 = text1.getText();
                kol_r = text2.getText();
                kol_m = text3.getText();
                if (cb1.getState()==true){ i="Да";}
                else{i="Нет";}
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
            		String query = "INSERT INTO  `gb_x_lera99`.`"+name_table+"` (`id`, `Дата соития`, `Партнер`, `Дата окрола`, `Рождено`, `Мертворожденные`, `Инбридинг`) VALUES ('"+id+"', '"+data1+"', '"+name+"', '"+data2+"', '"+kol_r+"', '"+kol_m+"', '"+i+"');";
                    System.out.println(id);
                    m.connect().executeUpdate(query);
                } catch (SQLException | ClassNotFoundException | IOException ex) {
                    Logger.getLogger(KModel.class.getName()).log(Level.SEVERE, null, ex);
            }   
            	new sl_okr(row, login);
                fr.dispose();
            }
            
        });
	    
	    b1 = new JButton("Выйти");
	    b1.setBounds(150, 370, 130, 20);
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

