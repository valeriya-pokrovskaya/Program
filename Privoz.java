package app;


import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.util.Locale;
import java.sql.Date;


public class Privoz {

    private JButton b;
    private JPanel p;
    private JTable table;
    public JLabel l, l1, l2, l3;
	private JTextField text, text1;
	String data="", kol="", date="";
	String s[]={"1","2","3","4","5", "6", "7", "8", "9"};

	
    public Privoz(String login){
    String name_table= login + "_privoz";	
    DefaultTableModel dm = new DefaultTableModel(); 

	JFrame f = new JFrame("���� ������� �����");
	f.setSize(380, 460);
    f.setLocationRelativeTo(null);
    f.getContentPane().setLayout(new FlowLayout());
    Font font = new Font("Segoe Script",  Font.BOLD,  12);
    JLabel l = new JLabel("���� ������� �����");
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
            KModel5 dbm = new KModel5(true);
            ResultSet rs = st.executeQuery("SELECT * FROM `"+name_table+"`");
            JTable table = new JTable(dbm);
            RowSorter<TableModel> sorter= new TableRowSorter<>(dbm);
            table.setRowSorter(sorter);
            JScrollPane sc = new JScrollPane(table);
            table.setPreferredScrollableViewportSize(new Dimension(320,300));
            f.getContentPane().add(sc);
            dbm.setDataSource(rs);
            
        
                    
            l1 = new JLabel("����:");
    		l1.setBounds(65, 40, 200, 20);
    		l1.setFont(font);
    		f.getContentPane().add(l1);
    		
    		text = new JTextField(10);
    		text.setBounds(65, 60, 160, 20);
    		f.getContentPane().add(text);
    	  
    		l2 = new JLabel("�����:"); 
    		l2.setBounds(65, 90, 300, 25);
    		l2.setFont(font);
    		f.getContentPane().add(l2);
    		
    	    text1 = new  JTextField(10);
    	    text1.setBounds(65, 120, 160, 20);
    	    f.getContentPane().add(text1);
    	    
    	    l3 = new JLabel("��"); 
    		l3.setBounds(65, 150, 150, 25);
    		l3.setFont(font);
    		f.getContentPane().add(l3);
            
    		text.addMouseListener(new java.awt.event.MouseAdapter() {
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
            	text.setText(data);
                }
            });
    		
            JButton b = new JButton("��������");
            b.setFont(font);
            b.addActionListener((ActionEvent evt) -> {
                
            	KModel5 m = new KModel5(true);
                date = text.getText();
                kol = text1.getText();
                m.add(data, kol);
            try{    
                ResultSet rs1 = st.executeQuery("SELECT * FROM `"+name_table+"`");
                dbm.setDataSource(rs1);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "������ ����������� � ��!", JOptionPane.ERROR_MESSAGE);
            }
            });
            f.getContentPane().add(b); 
            
            
            JButton b1 = new JButton("�������");
            b1.setFont(font);
            b1.addActionListener((ActionEvent evt) -> {
                int row = table.getSelectedRow();
                if (row < 0) {
                } else {
                    int recNum = table.convertRowIndexToModel(row);
                    try {
                        ((KModel5) table.getModel()).removeRow(recNum);
                        table.clearSelection();
                        ((KModel5) table.getModel()).remove(row);   
                    } catch (Exception ex) {
                    }       
                }
                try{    
                    ResultSet rs1 = st.executeQuery("SELECT * FROM `"+name_table+"`");
                    dbm.setDataSource(rs1);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "������ ����������� � ��!", JOptionPane.ERROR_MESSAGE);
                }
            });
            f.setResizable(false);
            f.getContentPane().add(b1); 
            
  	    f.setVisible(true);  
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "������ ����������� � ��!", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	
	
}