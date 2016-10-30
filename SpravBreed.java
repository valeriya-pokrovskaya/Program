package app;


import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class SpravBreed {

    private KModel3 model;
    private KFDB baza;
    private JButton b, b1;
    private JPanel p;
    private JTable table;
    
    public SpravBreed(String login){	
    String name_table=login + "_porod";	
    DefaultTableModel dm = new DefaultTableModel(); 
	JFrame f = new JFrame("Справочник пород");
	f.setSize(380, 440);
    f.setLocationRelativeTo(null);
    f.getContentPane().setLayout(new FlowLayout());
    Font font = new Font("Segoe Script",  Font.BOLD,  12);
    Font fo = new Font("Segoe Script",  Font.BOLD,  14);
    JLabel l = new JLabel("Справочник пород");
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
            KModel3 dbm = new KModel3(true);
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
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
            	 new Add_breed(login);
                 f.dispose(); 
                }
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
                        ((KModel3) table.getModel()).removeRow(recNum);
                        table.clearSelection();
                        ((KModel3) table.getModel()).remove(row);   
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