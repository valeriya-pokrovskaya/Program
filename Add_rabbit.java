package app;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Add_rabbit extends JFrame{
	
	JRadioButton r1, r2;
	JPanel p;
	
	public Add_rabbit(){
		super("Добавление кролика");
		Font font = new Font("Segoe Script",  Font.BOLD,  14);
	    JLabel l = new JLabel("Выберете какого пола добавить кролика:");
		l.setBounds(90, 10, 50, 10);
		l.setFont(font);
		JPanel p = new JPanel();
		p.add(l);
		ButtonGroup bg = new ButtonGroup();
        r1 = new JRadioButton("Самец");
        r2 = new JRadioButton("Самка");
        bg.add(r1);
        bg.add(r2);
        
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        p.add(r1);
        p.add(r2);
        this.add(p);
        
        r1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new Add_m();
             dispose();
            }
        });

        r2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new Add_g();
             dispose();
            }
        });
         
        setSize(500, 100);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane();
	}
}
