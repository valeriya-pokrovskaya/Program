package app;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class avtorizaciya extends JFrame{
	
	public JLabel l, l1, l2;
	private JTextField text;
	private JPasswordField text1;
	public JButton b, b1;
	public JPanel p;
	private KFDB baza;
	public static String login,password; 
	
	public avtorizaciya (boolean bo){
	super("Авторизация");
    
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
    
    b = new JButton("Войти");
    b.setBounds(20, 115, 130, 20);
    b.setVisible(true);
    b.setFont(f);
    p.add(b);
    
    b1 = new JButton("Регистрация");
    b1.setBounds(185, 115, 130, 20);
    b1.setVisible(true);
    b1.setFont(f);
    p.add(b1);
   
    l2 = new JLabel("Такой пользователь уже есть!"); 
	l2.setBounds(60, 140, 250, 25);
	l2.setFont(f);
       
    b.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	boolean f;
        	login = text.getText();
        	password = new String(text1.getPassword());
        	f = baza.vhod(login, password); 
        	if (f==true) dispose();	
        	baza.table_porod(login);
        }
    });
    
    b1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	registraciya r = new registraciya(); 	   
        }
    });
    getContentPane().add(p);
    setSize(350, 200);
    setResizable(false);
    setLocationRelativeTo(null);
    setVisible(true);
	}
	
	avtorizaciya(){}

	public String getLogin(){
		return login;
	}
	
	public static void main(String[] args)
    {
		avtorizaciya a  =   new avtorizaciya(true);
    }
}
