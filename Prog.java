package app;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class Prog extends JFrame{

private BufferedImage bufImg = null;
private BufferedImage image;
 
 
	public Prog()
	{
		super("О программе");
		setSize(520, 250);
		JPanel p = new JPanel();
        p.setLayout(null);
        //setResizable(false);
        
        JLabel  l = new JLabel("О программе"); 
        l.setBounds(5, 5, 85, 20);	
        l.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        p.add(l);
        
        JLabel l1 = new JLabel("Программа 'Учет кроликов' предназначена для хранения информации о кроликах");   
        l1.setBounds(5, 30, 500, 20);	
        p.add(l1);
        	
        JLabel l2 = new JLabel("", new ImageIcon("prog.jpg"),JLabel.CENTER);    
        l2.setBounds(70, 60, 350, 100);	
        p.add(l2);
        
        JLabel l3 = new JLabel("Версия 1.0", JLabel.CENTER);    
        l3.setBounds(200, 170, 100, 20);	
        p.add(l3);
       
    getContentPane().add(p);    
	setVisible(true);
	}
}
