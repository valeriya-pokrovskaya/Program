package app;


import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class Add_g extends JFrame{

	JLabel l, l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, l17, l18, l19, l20;
	JTextField text,text1, text2, text3, text4, text5, text6, text7, text8, text9, text10, text11, text12;
	Choice ch1, ch;
	Checkbox cb1, cb2, cb3;
	JButton b, b1, b2;
	String number="", name="", data="", age="", breed="", weight="", info="", location="", kol1="", kol2="", i="", i1="", i2="";
	String name_m="", name_p="", name_mb="", name_md="", name_pb="", name_pd="", n="";
	
	public Add_g(){
		JFrame f = new JFrame("Добавление нового кролика женского пола");
		JTabbedPane jtp = new JTabbedPane();
		f.getContentPane().add(jtp);
		
		Font font = new Font("Segoe Script",  Font.BOLD,  12);
		Font font1 = new Font("Segoe Script",  Font.BOLD,  14);
		KModel3 m = new KModel3(true); 
		ArrayList<String> a = new ArrayList<String>();
		a = m.getBreeds();
		int count = m.getCount();
		KModel8 m1= new KModel8(true); 
		ArrayList<String> a1 = new ArrayList<String>();
		a1 = m1.getAdres();
		int count1 = m1.getCount();
		
   //вкладка Основная информация
		
        JPanel p = new JPanel();
        p.setLayout(null);
        
        l = new JLabel("Главная информация:");
		l.setBounds(10, 10, 160, 20);
		l.setFont(font);
		l.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		p.add(l);
        
        l1 = new JLabel("Идент. номер: *");
		l1.setBounds(10, 35, 200, 20);
		l1.setFont(font);
		p.add(l1);
		
		text = new JTextField(10);
		text.setBounds(10, 60, 160, 20);
		p.add(text);
		
		l2 = new JLabel("Кличка:");
		l2.setBounds(190, 35, 200, 20);
		l2.setFont(font);
		p.add(l2);
		
		text1 = new JTextField(10);
		text1.setBounds(190, 60, 160, 20);
		p.add(text1);
		
		l3 = new JLabel("Дата рождения:");
		l3.setBounds(370, 35, 200, 20);
		l3.setFont(font);
		p.add(l3);
		
		text2 = new JTextField(10);
		text2.setBounds(370, 60, 160, 20);
		p.add(text2);
		
		l4 = new JLabel("Возраст:");
		l4.setBounds(10, 85, 200, 20);
		l4.setFont(font);
		p.add(l4);
		
		text3 = new JTextField(10);
		text3.setBounds(10, 110, 160, 20);
		p.add(text3);
		
		l5 = new JLabel("Порода:");
		l5.setBounds(190, 85, 200, 20);
		l5.setFont(font);
		p.add(l5);
		
		ch = new Choice();
		ch.setBounds(190, 110, 160, 20);
		for(int i = 0; i < count; i++){
		ch.add(a.get(i));
		}
		p.add(ch);
		
		l6 = new JLabel("Расположение:");
		l6.setBounds(370, 85, 200, 20);
		l6.setFont(font);
		p.add(l6);
		
		ch1 = new Choice();
		ch1.setBounds(370, 110, 160, 20);
		for(int i = 0; i < count1; i++){
		ch1.add(a1.get(i));
		}
		p.add(ch1);
		
		l8 = new JLabel("Соседство и гены:");
		l8.setBounds(10, 145, 160, 20);
		l8.setFont(font);
		l8.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		p.add(l8);
		
		l7 = new JLabel("Кол-во соседий:");
		l7.setBounds(10, 170, 200, 20);
		l7.setFont(font);
		p.add(l7);
		
		text4 = new JTextField(10);
		text4.setBounds(10, 195, 160, 20);
		p.add(text4);
		
		l8 = new JLabel("Кол-во родящихся:");
		l8.setBounds(190, 170, 200, 20);
		l8.setFont(font);
		p.add(l8);
		
		text5 = new JTextField(10);
		text5.setBounds(190, 195, 160, 20);
		p.add(text5);
		
		l9 = new JLabel("Пометки:");
		l9.setBounds(10, 230, 160, 20);
		l9.setFont(font);
		l9.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		p.add(l9);
		
		cb1 = new Checkbox("Инбридинг"); 
	    cb1.setBounds(10, 260, 160, 30); 
	    cb1.setFont(font);
	    p.add(cb1); 
	    cb2 = new Checkbox("Готовая продукция"); 
	    cb2.setBounds(190, 260, 160, 30); 
	    cb2.setFont(font);
	    p.add(cb2); 
	    cb3 = new Checkbox("Готов к реализации"); 
	    cb3.setBounds(370, 260, 160, 30);
	    cb3.setFont(font);
	    p.add(cb3);
	    
	    b = new JButton("Добавить");
	    b.setBounds(120, 330, 160, 30);
	    b.setVisible(false);
	    b.setFont(font);
	    p.add(b);
	    
	    text2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {            	
        	Calendar cr = new Calendar(f);
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
        	text2.setText(data);
            }
        });
	    
	    text.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) { 
            	b.setVisible(true);
            }
        });
	    
	    b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                KModel2 km = new KModel2(true);
                number = text.getText();;
                name = text1.getText(); 
                data =  text2.getText();
                age = text3.getText();
                breed = ch.getSelectedItem();  
                location = ch1.getSelectedItem();
                kol1 =  text4.getText();
                kol2 = text5.getText();
                text12.setText(number);

                if (cb1.getState()==true){ i="И";}
                if (cb2.getState()==true){ i1="ГП";}
                if (cb3.getState()==true){ i2="ГкР";}
                if (i==""){ info =  i1 + "," + i2; System.out.println(info);}
                else if(i1==""){info =  i + "," + i2; System.out.println(info);}
                else if(i2==""){ info =  i + "," + i1; System.out.println(info);}
                else if(i=="" & i1=="") {info = i2; System.out.println(info);}
                else if(i=="" & i2=="") {info = i1; System.out.println(info);}
                else if(i1=="" & i2=="") {info = i; System.out.println(info);}
                else if(i=="" & i1=="" & i2==""){info = ""; System.out.println(info);}
                else{info = i + "," + i1 + "," + i2; System.out.println(info);}
                km.add(number, name, data, age, breed, info, location, kol1, kol2);
                jtp.setEnabledAt(1, true);
            	km.create_table_rod(number);
            	km.create_table_w(number);
            	km.create_table_p(number);
            	km.create_table_sl_okr(number);
            }
        });
	    
	    b1 = new JButton("Выйти");
	    b1.setBounds(300, 330, 160, 30);
	    b1.setVisible(true);
	    b1.setFont(font);
	    p.add(b1);
	    
	    b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            f.dispose();
            }
            
        });
        jtp.addTab("Основное", p);
        
      //вкладка Родовод      
        
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        
        l10 = new JLabel("Информация о родственниках:");
		l10.setBounds(10, 10, 270, 20);
		l10.setFont(font1);
		l10.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		p1.add(l10);
		
		l11 = new JLabel("Кличка матери:");
		l11.setBounds(10, 45, 200, 20);
		l11.setFont(font);
		p1.add(l11);
		
		text6 = new JTextField(10);
		text6.setBounds(10, 70, 160, 20);
		p1.add(text6);
		
		l12 = new JLabel("Кличка бабушки(мать):");
		l12.setBounds(190, 45, 200, 20);
		l12.setFont(font);
		p1.add(l12);
		
		text7 = new JTextField(10);
		text7.setBounds(190, 70, 160, 20);
		p1.add(text7);
		
		l13 = new JLabel("Кличка дедушки(мать):");
		l13.setBounds(370, 45, 200, 20);
		l13.setFont(font);
		p1.add(l13);
		
		text8 = new JTextField(10);
		text8.setBounds(370, 70, 160, 20);
		p1.add(text8);
        
		l14 = new JLabel("Кличка отца:");
		l14.setBounds(10, 110, 200, 20);
		l14.setFont(font);
		p1.add(l14);
		
		text9 = new JTextField(10);
		text9.setBounds(10, 135, 160, 20);
		p1.add(text9);
		
		l15 = new JLabel("Кличка бабушки(отец):");
		l15.setBounds(190, 110, 200, 20);
		l15.setFont(font);
		p1.add(l15);
		
		text10 = new JTextField(10);
		text10.setBounds(190, 135, 160, 20);
		p1.add(text10);
		
		l16 = new JLabel("Кличка дедушки(отец):");
		l16.setBounds(370, 110, 200, 20);
		l16.setFont(font);
		p1.add(l16);
		
		text11 = new JTextField(10);
		text11.setBounds(370, 135, 160, 20);
		p1.add(text11);
		
		text12 = new JTextField(10);
		text12.setBounds(10, 200, 160, 20);
		text12.setVisible(false);
		p1.add(text12);
		
		b2 = new JButton("Добавить");
	    b2.setBounds(220, 330, 160, 30);
	    b2.setVisible(true);
	    b2.setFont(font);
	    p1.add(b2);
		
	    b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	KModel2 km1 = new KModel2(true);
                name_m = text6.getText(); 
            	name_mb = text7.getText(); 
            	name_md = text8.getText();
            	name_p = text9.getText(); 
            	name_pb = text10.getText(); 
            	name_pd = text11.getText(); 
                n = text12.getText();
            	km1.insertRod(n, name_m, name_mb, name_md, name_p, name_pb, name_p);
            }
            
        });
		
        jtp.addTab("Родовод", p1);
        jtp.setEnabledAt(1, false);
        
        f.setSize(600, 450);
        f.setVisible(true);
        setResizable(false);
        f.setLocationRelativeTo(null);
	}
}
