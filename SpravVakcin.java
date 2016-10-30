package app;


import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class SpravVakcin {

    private KModel4 model;
    private KFDB baza;
    private JButton b, b1;
    private JPanel p;
    private JTable table;
    
    public SpravVakcin(String login){
    	
    DefaultTableModel dm = new DefaultTableModel(); 
    String name_table= login + "_priv";	
	JFrame f = new JFrame("Справочник прививок");
	f.setSize(350, 440);
    f.setLocationRelativeTo(null);
    f.getContentPane().setLayout(new FlowLayout());
    Font font = new Font("Segoe Script",  Font.BOLD,  12);
    Font fo = new Font("Segoe Script",  Font.BOLD,  14);
    JLabel l = new JLabel("Справочник прививок");
	l.setBounds(90, 10, 50, 10);
	l.setFont(fo);
	f.getContentPane().add(l);

        Connection conn;
        try {

            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://mysql80.1gb.ru/gb_x_lera99";
            String name = "gb_x_lera99";
            String password = "a77z3fc3qw";
            
            conn = DriverManager.getConnection(url, name, password);
            Statement st = conn.createStatement();
            KModel4 dbm = new KModel4(true);
            ResultSet rs = st.executeQuery("SELECT * FROM `"+name_table+"`");
            JTable table = new JTable(dbm);
            RowSorter<TableModel> sorter= new TableRowSorter<>(dbm);
            table.setRowSorter(sorter);
            JScrollPane sc = new JScrollPane(table);
            table.setPreferredScrollableViewportSize(new Dimension(320,300));
            f.getContentPane().add(sc);
            dbm.setDataSource(rs);
            
            JButton b = new JButton("Добавить запись");
            b.setFont(font);
            b.addActionListener((ActionEvent evt) -> {
                new Add_vakcin(login);
                f.dispose();     
            });
            f.getContentPane().add(b); 
            
            JButton b1 = new JButton("Удалить запись");
            b1.setFont(font);
            b1.addActionListener((ActionEvent evt) -> {
                int row = table.getSelectedRow();
                if (row < 0) {
                } else {
                    int recNum = table.convertRowIndexToModel(row);
                    try {
                        ((KModel4) table.getModel()).removeRow(recNum);
                        table.clearSelection();
                        ((KModel4) table.getModel()).remove(row);   
                    } catch (Exception ex) {
                    }       
                }
                try{    
                    ResultSet rs1 = st.executeQuery("SELECT * FROM `"+name_table+"`");
                    dbm.setDataSource(rs1);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Ошибка подключения к БД!", JOptionPane.ERROR_MESSAGE);
                }
            });
            f.getContentPane().add(b1); 
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Ошибка подключения к БД!", JOptionPane.ERROR_MESSAGE);
        }
        
        f.setResizable(false);
  	    f.setVisible(true);  
	}
	
	
	
}