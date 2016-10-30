﻿package app;


import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.util.Locale;


public class sl_okr {

    private JButton b;
    private JPanel p;
    private JTable table;
    public JLabel l, l1, l2, l3;
	private JTextField text;
	String name_ad;

	
    public sl_okr(int row, String login){	

	JFrame f = new JFrame("Случки и окрол");
	f.setSize(700, 400);
    f.setLocationRelativeTo(null);
    f.getContentPane().setLayout(new FlowLayout());
    Font font = new Font("Segoe Script",  Font.BOLD,  12);
    Font font1 = new Font("Segoe Script",  Font.BOLD,  14);
    JLabel l = new JLabel("Случки и окрол");
	l.setBounds(90, 10, 50, 10);
	l.setFont(font1);
	f.getContentPane().add(l);

        Connection conn;
        
        try {

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://mysql80.1gb.ru/gb_x_lera99";
            String name = "gb_x_lera99";
            String password = "a77z3fc3qw";
            int id = row+1; 
            conn = DriverManager.getConnection(url, name, password);
            Statement st = conn.createStatement();
            KModel10 dbm = new KModel10(true);
            String in = "";
            try{    
           
              String query = "SELECT  `Идент. номер` FROM  `_g` WHERE  `id` =  '"+id+"'";
              ResultSet rs = dbm.connect().executeQuery(query);
              while (rs.next()) {
                  in = rs.getString(1);
              } 
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Ошибка подключения к БД!", JOptionPane.ERROR_MESSAGE);
                }
            String name_table = login + "_sl_okr"+ "_" + in;
            ResultSet rs = st.executeQuery("SELECT * FROM `"+name_table+"`");
            JTable table = new JTable(dbm);
            RowSorter<TableModel> sorter= new TableRowSorter<>(dbm);
            table.setRowSorter(sorter);
            JScrollPane sc = new JScrollPane(table);
            table.setPreferredScrollableViewportSize(new Dimension(680,250));
            f.getContentPane().add(sc);
            dbm.setDataSource(rs);
            
            
    		l1 = new JLabel("Идентификатор кролика:"); 
    		l1.setBounds(65, 90, 400, 25);
    		l1.setFont(font);
    		f.getContentPane().add(l1);
    		
    	    text = new  JTextField(10);
    	    text.setBounds(65, 120, 160, 20);
    	    text.setText(in);
    	    text.setEditable(false);
    	    f.getContentPane().add(text);
    	    
    	    
    	    b = new JButton("Добавление новой случки");
    	    b.setBounds(10, 220, 130, 20);
    	    b.setVisible(true);
    	    b.setFont(font);
    	    f.getContentPane().add(b);
    	    
    	    
    	    b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	new Add_sl_okr(row, name_table, login);
                	f.dispose();
                }
            });    
    	    
             f.setResizable(false);
  	         f.setVisible(true);   

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Вы не выбрали кролика!", "Ошибка подключения к БД!", JOptionPane.ERROR_MESSAGE);
        }
        
          
	}
	
    
	
	
}