package app;


import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.util.Locale;


public class SpravAdres {

    private JButton b;
    private JPanel p;
    private JTable table;
    public JLabel l, l1, l2, l3;
	private JTextField text;
	String name_ad;
	
    public SpravAdres(String login){
    String name_table= login + "_adres";	
    DefaultTableModel dm = new DefaultTableModel(); 

	JFrame f = new JFrame("Справочник адресов");
	f.setSize(380, 460);
    f.setLocationRelativeTo(null);
    f.getContentPane().setLayout(new FlowLayout());
    Font font = new Font("Segoe Script",  Font.BOLD,  12);
    JLabel l = new JLabel("Справочник адресов");
	l.setBounds(90, 10, 50, 10);
	l.setFont(font);
	f.getContentPane().add(l);

        Connection conn;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://mysql80.1gb.ru/gb_x_lera99";
            String name = "gb_x_lera99";
            String password = "a77z3fc3qw";
            
            conn = DriverManager.getConnection(url, name, password);
            Statement st = conn.createStatement();
            KModel8 dbm = new KModel8(true);
            ResultSet rs = st.executeQuery("SELECT * FROM `"+name_table+"`");
            JTable table = new JTable(dbm);
            RowSorter<TableModel> sorter= new TableRowSorter<>(dbm);
            table.setRowSorter(sorter);
            JScrollPane sc = new JScrollPane(table);
            table.setPreferredScrollableViewportSize(new Dimension(320,300));
            f.getContentPane().add(sc);
            dbm.setDataSource(rs);
            
            
    		l1 = new JLabel("Название:"); 
    		l1.setBounds(65, 90, 400, 25);
    		l1.setFont(font);
    		f.getContentPane().add(l1);
    		
    	    text = new  JTextField(20);
    	    text.setBounds(65, 120, 160, 20);
    	    f.getContentPane().add(text);
    	    
    		
            JButton b = new JButton("Добавить");
            b.setFont(font);
            b.addActionListener((ActionEvent evt) -> {
                
            	KModel8 m = new KModel8(true);
                name_ad = text.getText();
                m.add(name_ad);
                try{    
                    ResultSet rs1 = st.executeQuery("SELECT * FROM `"+name_table+"`");
                    dbm.setDataSource(rs1);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Ошибка подключения к БД!", JOptionPane.ERROR_MESSAGE);
                }
            });
            f.getContentPane().add(b); 
            
            
            JButton b1 = new JButton("Удалить");
            b1.setFont(font);
            b1.addActionListener((ActionEvent evt) -> {
                int row = table.getSelectedRow();
                if (row < 0) {
                } else {
                    int recNum = table.convertRowIndexToModel(row);
                    try {
                        ((KModel8) table.getModel()).removeRow(recNum);
                        table.clearSelection();
                        ((KModel8) table.getModel()).remove(row);   
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