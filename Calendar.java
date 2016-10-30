package app;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calendar {

	    int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
	    int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);;
	    JLabel l = new JLabel("", JLabel.CENTER);
	    String day = "";
	    JDialog d;
	    JButton[] button = new JButton[49];
	 
	    public Calendar(JFrame parent) {
	        d = new JDialog();
	        d.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	        d.setModal(true);
	        String[] header = { "Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб" };
	        JPanel p1 = new JPanel(new GridLayout(7, 7));
	        p1.setPreferredSize(new Dimension(360, 120));
	 
	         
	        for (int i = 0; i < header.length; i++) {
	            button[i] = new JButton();
	            if(i==0||i==6){
	                button[i].setForeground(Color.red);
	            }
	            else{
	                button[i].setForeground(Color.blue);
	            }
	            button[i].setText(header[i]);
	            button[i].setBackground(Color.LIGHT_GRAY);
	            p1.add(button[i]);
	        }
	        for (int x = 7; x < button.length; x++) {
	            final int selection = x;
	            button[x] = new JButton();
	            button[x].setFocusPainted(false);
	            button[x].setBackground(Color.white);
	                button[x].addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent ae) {
	                        day = button[selection].getActionCommand();
	                        
	                        if(!day.trim().equals("")){
	                            d.dispose();
	                        }    
	                   // System.out.println(day);
	                   // System.out.println(month);
	                  //  System.out.println(year);
	                    }
	                });
	            if(x==7||x==13||x==14||x==20||x==21||x==27||x==28||x==34||x==35||x==41||x==42||x==48){
	                button[x].setBackground(new Color(255, 200, 200));
	            }
	            p1.add(button[x]);
	        }
	        JPanel p2 = new JPanel(new GridLayout(1, 3));
	 
	        // Предыдущий месяц
	        JButton previous = new JButton("<< Назад");
	        previous.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {
	                month--;
	                displayDate();
	            }
	        });
	        p2.add(previous);
	 
	        // Текущий месяц
	        p2.add(l);
	 
	        // Следующий месяц
	        JButton next = new JButton("Вперед >>");
	        next.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {
	                month++;
	                displayDate();
	            }
	        });
	        p2.add(next);
	 
	        d.add(p1, BorderLayout.CENTER);
	        d.add(p2, BorderLayout.SOUTH);
	        d.pack();
	        //parent.setUndecorated(true);
	        d.setLocationRelativeTo(parent);
	        displayDate();
	        //d.setUndecorated(true);
	        d.setVisible(true);
	    }
	 
	    public void displayDate() {
	        for (int x = 7; x < button.length; x++) {
	            button[x].setText("");
	        }
	 
	        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
	                "MMMM yyyy");
	        java.util.Calendar cal = java.util.Calendar.getInstance();
	        cal.set(year, month, 1);
	        int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
	        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
	 
	        for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++) {
	            button[x].setText("" + day);
	        }
	        l.setText(sdf.format(cal.getTime()));
	        d.setTitle("Выбор даты");
	    }
	 
	    public String setPickedDate() {
	        if (day.equals("")) {
	            return day;
	        }
	        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
	                "yyyy-MM-dd");
	 
	        java.util.Calendar cal = java.util.Calendar.getInstance();
	        cal.set(year, month, Integer.parseInt(day));
	       return sdf.format(cal.getTime());
	    }
	}	
