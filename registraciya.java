package app;

import java.awt.Font;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

public class registraciya extends JFrame{

	public JLabel l, l1, l2, l3;
	private JTextField text;
	private JPasswordField text1, text2;
	private JButton b, b1, b2;
	public JPanel p;
	private KFDB baza;
	static String login,password, password1; 
	
	registraciya(){
	super("Регистрация");
		
	    
	baza = new KFDB("com.mysql.jdbc.Driver", "jdbc:mysql://mysql80.1gb.ru/gb_x_lera99", "gb_x_lera99", "a77z3fc3qw"); 
	Font f = new Font("Segoe Script",  Font.BOLD,  12);
	
	p = new JPanel();
    p.setLayout(null);
	
    l = new JLabel("Логин");
	l.setBounds(90, 10, 50, 10);
	l.setFont(f);
	p.add(l);
	
	text = new JTextField();
	text.setBounds(90, 30, 160, 20);
    p.add(text);
  
	l1 = new JLabel("Пароль"); 
	l1.setBounds(90, 55, 50, 25);
	l1.setFont(f);
	p.add(l1);
	
	text1 = new  JPasswordField();
    text1.setBounds(90, 80, 160, 20);
    p.add(text1);
    
    l2 = new JLabel("Повторите пароль"); 
	l2.setBounds(90, 105, 150, 25);
	l2.setFont(f);
	p.add(l2);
	
	text2 = new  JPasswordField();
    text2.setBounds(90, 135, 160, 20);
    p.add(text2);
    
	b = new JButton("Регистрация");
    b.setBounds(20, 170, 130, 20);
    b.setVisible(true);
    b.setFont(f);
    p.add(b);
    
    b1 = new JButton("Выход");
    b1.setBounds(185, 170, 130, 20);
    b1.setVisible(true);
    b1.setFont(f);
    p.add(b1);
    
    
	
    
	b.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	char[] password1 = text1.getPassword();
            char[] password2 = text2.getPassword();
            if (Arrays.equals(password1, password2)) {
        	login = text.getText();
        	password = new String(text1.getPassword());
        	baza.regictraciya(login, password);	
        	}
            else{
        	 JOptionPane.showMessageDialog(null, "Пароли не совпадают!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
        	}
            
            if(text.getText()=="") JOptionPane.showMessageDialog(null, "Не введен логин!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
            if(password1==null && password1==null) JOptionPane.showMessageDialog(null, "Не введен пароль!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
            baza.create_table_m(login);
            baza.create_table_g(login);
            baza.create_table_priv(login);
            baza.create_table_porod(login);
            baza.create_table_privoz(login);
            baza.create_table_rashod(login);
            baza.create_table_zooplan(login);
            baza.create_table_adres(login);
        	JOptionPane.showMessageDialog(null, "Регистрация прошла успешно!", "Успех", JOptionPane.INFORMATION_MESSAGE);
        }
    });
	
	b1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	dispose();  
        }
    });
	
	
	getContentPane().add(p);
    setSize(350, 250);
    setResizable(false);
    setLocationRelativeTo(null);
    setVisible(true);
	}		
}
