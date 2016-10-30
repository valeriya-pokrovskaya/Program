package app;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.*;
import java.beans.*;
import java.sql.Statement;
import java.sql.*;

public class KApplication extends JFrame 
{
        private static final long serialVersionUID = 1L;
        private JTable table, table1;
        private KModel model;
        private KModel2 model2;
        private KFDB baza;
        private JMenuBar menu;
        private JMenu file, help, info, actions, report, statistics;
        private JMenuItem prog, hel,  exit, add_rabbit, add_rabota, pol, breed, vakcin, priv, ras, loc;
        private String name_table;
        private KModel3 model3;
        private JButton b, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15;
        JPanel p, p1, p2;

        public KApplication()
        {
                super("Учет кроликов");
                
                Connection conn, conn1;
                
                String login = (new avtorizaciya()).getLogin();
                String name_m = login+"_m";
                String name_g = login+"_g";
                String name_zooplan = login+"_zooplan";
                menu = new JMenuBar();
                setJMenuBar(menu);
                file = new JMenu("Главная");
                menu.add(file);
                pol = new JMenuItem("Сменить пользователя");
                file.add(pol);
                exit = new JMenuItem("Выход");
                file.add(exit);
                info = new JMenu("Информация");
                menu.add(info);
                breed = new JMenuItem("Справочник пород");
                info.add(breed);
                vakcin = new JMenuItem("Справочник вакцин");
                info.add(vakcin);
                loc = new JMenuItem("Справочник адресов");
                info.add(loc);
                priv = new JMenuItem("Учет привоза корма");
                info.add(priv);
                ras = new JMenuItem("Учет расхода корма");
                info.add(ras);
                report = new JMenu("Отчеты");
                menu.add(report);
                help = new JMenu("Справка");
                menu.add(help);
                prog = new JMenuItem("О программе");
                help.add(prog);
                hel = new JMenuItem("Помощь");
                help.add(hel); 
                JTabbedPane jtp = new JTabbedPane();
                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://mysql80.1gb.ru/gb_x_lera99";
                    String name = "gb_x_lera99";
                    String password = "a77z3fc3qw";
                    
                    conn = DriverManager.getConnection(url, name, password);
                    Statement st = conn.createStatement();
                    KModel dbm = new KModel(true);
                    ResultSet rs = ((java.sql.Statement) st).executeQuery("SELECT * FROM `"+name_m+"`");
                    JTable table = new JTable(dbm);
                    RowSorter<TableModel> sorter= new TableRowSorter<>(dbm);
                    table.setRowSorter(sorter);
                    JScrollPane sc = new JScrollPane(table);
                    table.setPreferredScrollableViewportSize(new Dimension(880,480));
                    getContentPane().add(jtp);
                    JPanel p = new JPanel();
                    p.add(sc);
                    ImageIcon icon5 = new ImageIcon("dob.png"); 
    	            b13=new JButton(icon5); 
                    b13.setPreferredSize(new Dimension(30,30));
                    b13.setToolTipText("Добавление нового кролика");
    	            b13.setVisible(true);
    	            p.add(b13);
                    ImageIcon icon1 = new ImageIcon("ob.gif"); 
    	            b=new JButton(icon1); 
                    b.setPreferredSize(new Dimension(30,30));
                    b.setToolTipText("Обновление таблицы");
    	            b.setVisible(true);
    	            p.add(b);
                    ImageIcon icon = new ImageIcon("s.jpg"); 
    	            b1=new JButton(icon); 
                    b1.setPreferredSize(new Dimension(30,30));
                    b1.setToolTipText("Родовод кролика");
    	            b1.setVisible(true);
    	            p.add(b1);
    	            ImageIcon icon2 = new ImageIcon("ves.png"); 
    	            b2=new JButton(icon2); 
                    b2.setPreferredSize(new Dimension(30,30));
                    b2.setToolTipText("Вес кролика");
    	            b2.setVisible(true);
    	            p.add(b2);
                    ImageIcon icon3 = new ImageIcon("priv.gif"); 
    	            b3=new JButton(icon3); 
                    b3.setPreferredSize(new Dimension(30,30));
                    b3.setToolTipText("Прививки кролика");
    	            b3.setVisible(true);
    	            p.add(b3);
    	            ImageIcon icon4 = new ImageIcon("del.gif"); 
    	            b10=new JButton(icon4); 
                    b10.setPreferredSize(new Dimension(30,30));
                    b10.setToolTipText("Удаление записи");
    	            b10.setVisible(true);
    	            p.add(b10);
    	            jtp.addTab("Самцы", p);
                    b.addActionListener((ActionEvent evt) -> {
                    	try{
                    		ResultSet rs1 =  st.executeQuery("SELECT * FROM `"+name_m+"`");
                            dbm.setDataSource(rs1);
                    	}
                    	catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Ошибка подключения к БД!", JOptionPane.ERROR_MESSAGE);
                        }  
                        
                    });
                    
                    b13.addActionListener((ActionEvent evt) -> {
                    	new Add_m();
                    });
                    
                    b1.addActionListener((ActionEvent evt) -> {
                    	
                    	int row = table.getSelectedRow();                   
                    		new Rod_m(row, login);
                    });
                    
                    b2.addActionListener((ActionEvent evt) -> {
                    	int row = table.getSelectedRow();
                    	new Weight_m(row, login);
                    });
                    
                    b3.addActionListener((ActionEvent evt) -> {
                    	int row = table.getSelectedRow();
                    	new Priv_m(row, login);
                    });
                    
                    b10.addActionListener((ActionEvent evt) -> {
                    	int row = 0;
                    	row = table.getSelectedRow();
                        if (row < 0) {
                        } else {
                            int recNum = table.convertRowIndexToModel(row);
                            try {
                                ((KModel) table.getModel()).removeRow(recNum);
                                table.clearSelection();
                                ((KModel) table.getModel()).remove(row);  
                            } catch (Exception ex) {
                            }       
                        }
                    
                });
                    
                    dbm.setDataSource(rs); 
                 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Ошибка подключения к БД!", JOptionPane.ERROR_MESSAGE);
            }     
             
            try{    
            	
            	Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://mysql80.1gb.ru/gb_x_lera99";
                String name = "gb_x_lera99";
                String password = "a77z3fc3qw";
                conn = DriverManager.getConnection(url, name, password);
                Statement st = conn.createStatement(); 
                
                KModel2 dbm = new KModel2(true);
                ResultSet rs = ((java.sql.Statement) st).executeQuery("SELECT * FROM `"+name_g+"`");
                JTable table = new JTable(dbm);
                RowSorter<TableModel> sorter= new TableRowSorter<>(dbm);
                table.setRowSorter(sorter);
                JScrollPane sc = new JScrollPane(table);
                table.setPreferredScrollableViewportSize(new Dimension(880,480));
                JPanel p1 = new JPanel();
                p1.add(sc);
                ImageIcon icon6 = new ImageIcon("dob.png"); 
	            b14=new JButton(icon6); 
                b14.setPreferredSize(new Dimension(30,30));
                b14.setToolTipText("Добавление нового кролика");
	            b14.setVisible(true);
	            p1.add(b14);
                ImageIcon icon1 = new ImageIcon("ob.gif"); 
	            b4=new JButton(icon1); 
                b4.setPreferredSize(new Dimension(30,30));
                b4.setToolTipText("Обновление таблицы");
	            b4.setVisible(true);
	            p1.add(b4);
                ImageIcon icon = new ImageIcon("s.jpg"); 
	            b5=new JButton(icon); 
                b5.setPreferredSize(new Dimension(30,30));
                b5.setToolTipText("Родовод кролика");
	            b5.setVisible(true);
	            p1.add(b5);
	            ImageIcon icon2 = new ImageIcon("ves.png"); 
	            b6=new JButton(icon2); 
                b6.setPreferredSize(new Dimension(30,30));
                b6.setToolTipText("Вес кролика");
	            b6.setVisible(true);
	            p1.add(b6);
                ImageIcon icon3 = new ImageIcon("priv.gif"); 
	            b7=new JButton(icon3); 
                b7.setPreferredSize(new Dimension(30,30));
                b7.setToolTipText("Прививки кролика");
	            b7.setVisible(true);
	            p1.add(b7);
	            ImageIcon icon5 = new ImageIcon("ok.jpg"); 
	            b12=new JButton(icon5); 
                b12.setPreferredSize(new Dimension(30,30));
                b12.setToolTipText("Случки и окрол");
	            b12.setVisible(true);
	            p1.add(b12);
	            ImageIcon icon4 = new ImageIcon("del.gif"); 
	            b11=new JButton(icon4); 
                b11.setPreferredSize(new Dimension(30,30));
                b11.setToolTipText("Удаление записи");
	            b11.setVisible(true);
	            p1.add(b11);
                jtp.addTab("Самки", p1);
                
                b14.addActionListener((ActionEvent evt) -> {
                	new Add_g();
                });
                
                b4.addActionListener((ActionEvent evt) -> {
                	try{
                		ResultSet rs1 =  st.executeQuery("SELECT * FROM `"+name_g+"`");
                        dbm.setDataSource(rs1);
                	}
                	catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Ошибка подключения к БД!", JOptionPane.ERROR_MESSAGE);
                    }  
                    
                });
                
                b5.addActionListener((ActionEvent evt) -> {
                	int row = table.getSelectedRow();
                	new Rod_g(row, login);
                });
                
                b6.addActionListener((ActionEvent evt) -> {
                	int row = table.getSelectedRow();
                	new Weight_g(row, login); 
                });
                
                b7.addActionListener((ActionEvent evt) -> {
                	int row = table.getSelectedRow();
                	new Priv_g(row, login);	
                });   
                
                b12.addActionListener((ActionEvent evt) -> {
                	int row = table.getSelectedRow();
                	new sl_okr(row, login);	
                }); 
                
                b11.addActionListener((ActionEvent evt) -> {
                	int row = table.getSelectedRow();
                    if (row < 0) {
                    } else {
                        int recNum = table.convertRowIndexToModel(row);
                        try {
                            ((KModel2) table.getModel()).removeRow(recNum);
                            table.clearSelection();
                            ((KModel2) table.getModel()).remove(row);  
                        } catch (Exception ex) {
                        }       
                    }
            });   
                dbm.setDataSource(rs); 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Ошибка подключения к БД!", JOptionPane.ERROR_MESSAGE);
            }    
            
            try{
            	Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://mysql80.1gb.ru/gb_x_lera99";
                String name = "gb_x_lera99";
                String password = "a77z3fc3qw";
                conn = DriverManager.getConnection(url, name, password);
                Statement st = conn.createStatement();
                KModel7 dbm = new KModel7(true);
                ResultSet rs =  st.executeQuery("SELECT * FROM `"+name_zooplan+"`");
                JTable table = new JTable(dbm);
                RowSorter<TableModel> sorter= new TableRowSorter<>(dbm);
                table.setRowSorter(sorter);
                JScrollPane sc = new JScrollPane(table);
                table.setPreferredScrollableViewportSize(new Dimension(880,480));
                JPanel p2 = new JPanel();
                p2.add(sc);
                ImageIcon icon3 = new ImageIcon("dob.png"); 
	            b15=new JButton(icon3); 
                b15.setPreferredSize(new Dimension(30,30));
                b15.setToolTipText("Добавление нового кролика");
	            b15.setVisible(true);
	            p2.add(b15);
                ImageIcon icon1 = new ImageIcon("ob.gif"); 
	            b8=new JButton(icon1); 
                b8.setPreferredSize(new Dimension(30,30));
                b8.setToolTipText("Обновление таблицы");
	            b8.setVisible(true);
	            p2.add(b8);
                ImageIcon icon = new ImageIcon("del.gif"); 
	            b9=new JButton(icon); 
                b9.setPreferredSize(new Dimension(30,30));
                b9.setToolTipText("Удаление записи");
	            b9.setVisible(true);
	            p2.add(b9);
                jtp.addTab("Зооплан", p2);
                dbm.setDataSource(rs);
                
                b15.addActionListener((ActionEvent evt) -> {
                	new Add_rabota();
                });
                
                b8.addActionListener((ActionEvent evt) -> {
                	try{
                		ResultSet rs1 =  st.executeQuery("SELECT * FROM `"+name_zooplan+"`");
                        dbm.setDataSource(rs1);
                	}
                	catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Ошибка подключения к БД!", JOptionPane.ERROR_MESSAGE);
                    }  
                    
                });
                
                b9.addActionListener((ActionEvent evt) -> {
                    	int row = table.getSelectedRow();
                        if (row < 0) {
                        } else {
                            int recNum = table.convertRowIndexToModel(row);
                            try {
                                ((KModel7) table.getModel()).removeRow(recNum);
                                table.clearSelection();
                                ((KModel7) table.getModel()).remove(row);   
                            } catch (Exception ex) {
                            }       
                        }
                });
                    
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Ошибка подключения к БД!", JOptionPane.ERROR_MESSAGE);
        }    

            
         
                       
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
            
        });
        prog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               new Prog();
            }
        });
        hel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              new Hel();
            }
        });
        pol.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               new avtorizaciya(true);
              dispose();
            }
        });
        
        breed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 new SpravBreed(login);
            	
            }
        });  
        vakcin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 new SpravVakcin(login); 
            }
        });
        
        loc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 new SpravAdres(login); 
            }
        });
        
        priv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 new Privoz(login); 
            }
        });
        ras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 new Rashod(login); 
            }
        });
         setVisible(true);
         setSize(900, 640);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
         setResizable(false);}   
}