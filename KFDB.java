package app;

import java.sql.*;
import java.util.Vector;
import javax.swing.*;


public class KFDB 
{
    private static Connection con = null;

    public KFDB(String driver, String url, String login, String pass)
    {
        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, login, pass);
        } catch (ClassNotFoundException ex)
        {
            System.err.println("KFDB.Cannot find this db driver classes.");
            ex.printStackTrace();
        } catch (SQLException e)
        {
            System.err.println("KFDB.Cannot connect to this db.");
            e.printStackTrace();
        }         
    }
    
    public void create_table_m(String login)
    {
    	String name_table = "";
    	try{
    		Statement st = con.createStatement();
    		name_table = login + "_m";
    		String table =
    		"CREATE TABLE `gb_x_lera99`.`"+name_table+"` ( `id` INT NOT NULL AUTO_INCREMENT , `�����. �����` VARCHAR(255) NOT NULL , `������` VARCHAR(255) NOT NULL , `���� ��������` VARCHAR(255) NOT NULL,  `�������` VARCHAR(255) NOT NULL , `������` VARCHAR(255) NOT NULL , `���` VARCHAR(255) NOT NULL , `�������` VARCHAR(255) NOT NULL , `������������` VARCHAR(255) NOT NULL ,  `���-�� �������` VARCHAR(255) NOT NULL , `���-�� ����������` VARCHAR(255) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
    		st.executeUpdate(table);
    		System.out.println("Table creation process successfully!");
    		String query =
    	    		"UPDATE  `gb_x_lera99`.`users` SET  `name_m` =  '"+name_table+"' WHERE  `users`.`login` ='"+login+"';";
    	    		st.executeUpdate(query);
    		}
    		catch(SQLException s){
    		System.out.println("Table all ready exists!");
    		}
    }

    public void create_table_g(String login)
    {
    	String name_table="";
    	try{
    		Statement st = con.createStatement();
    		name_table = login + "_g";
    		String table =
    				"CREATE TABLE `gb_x_lera99`.`"+name_table+"` ( `id` INT NOT NULL AUTO_INCREMENT , `�����. �����` VARCHAR(255) NOT NULL , `������` VARCHAR(255) NOT NULL , `���� ��������` VARCHAR(255) NOT NULL,  `�������` VARCHAR(255) NOT NULL , `������` VARCHAR(255) NOT NULL , `���` VARCHAR(255) NOT NULL , `�������` VARCHAR(255) NOT NULL , `������������` VARCHAR(255) NOT NULL ,  `���-�� �������` VARCHAR(255) NOT NULL , `���-�� ����������` VARCHAR(255) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
    		st.executeUpdate(table);
    		System.out.println("Table creation process successfully!");
    		String query =
    	    		"UPDATE  `gb_x_lera99`.`users` SET  `name_g` =  '"+name_table+"' WHERE  `users`.`login` ='"+login+"';";
    	    		st.executeUpdate(query);
    		}
    		catch(SQLException s){
    		System.out.println("Table all ready exists!");
    		}
    }
    
    public void create_table_priv(String login)
    {
    	String name_table="";
    	try{
    		Statement st = con.createStatement();
    		name_table = login + "_priv";
    		String table =
    		"CREATE TABLE `gb_x_lera99`.`"+name_table+"` ( `id` INT NOT NULL AUTO_INCREMENT , `��������` VARCHAR(255) NOT NULL , `��������� � ... ����` INT(50) NOT NULL ,   `���� ��������(����)` INT(50) NOT NULL ,  PRIMARY KEY (`id`)) ENGINE = InnoDB;";
    		st.executeUpdate(table);
    		System.out.println("Table creation process successfully!");
    		String query =
    	    		"UPDATE  `gb_x_lera99`.`users` SET  `name_priv` =  '"+name_table+"' WHERE  `users`.`login` ='"+login+"';";
    	    		st.executeUpdate(query);
    		}
    		catch(SQLException s){
    		System.out.println("Table all ready exists!");
    		}
    }
    
    public void create_table_porod(String login)
    {
    	String name_table = "";
    	try{
    		Statement st = con.createStatement();
    		name_table = login + "_porod";
    		String table =
    		"CREATE TABLE `gb_x_lera99`.`"+name_table+"` ( `id` INT NOT NULL AUTO_INCREMENT , `��������` VARCHAR(255) NOT NULL , `����������` VARCHAR(255) NOT NULL ,   `����` VARCHAR(255) NOT NULL ,  PRIMARY KEY (`id`)) ENGINE = InnoDB;";
    		st.executeUpdate(table);
    		System.out.println("Table creation process successfully!");
    		String query =
    	    		"UPDATE  `gb_x_lera99`.`users` SET  `name_porod` =  '"+name_table+"' WHERE  `users`.`login` ='"+login+"';";
    	    		st.executeUpdate(query);
    	    String query1 =
    	     	     "INSERT INTO `gb_x_lera99`.`"+name_table+"`(`id`, `��������`, `����������`, `����`) VALUES (1,'������','������','�����'), (2,'�������','��','������-�������'), (3,'�����������','���','���������'), (4,'�����-�����','���','������'),(5,'��������� �������','���','���������'),(6,'����� �������','���','�����'),(7,'����������� ������','���','�����'),(8,'������� �������','���','�����'),(9,'�������������� �����','���','������-�����'),(10,'����� �������','���','������-�����'),(11,'������ ��� ���','�2','�����'),(12,'������ ��� ���','�3','�����'),(13,'������ ��� ���','�4','�����'),(14,'������ ��� ���','�5','�����'),(15,'������ ��� ���.','�6','�����'),(16,'�����','��','�����'),(17,'����-�����������','����','�����-�����'),(18,'������ ��� ���','�7','�����'),(19,'���������','���','�����'),(20,'�����-����','�-�','�����'),(21,'�������������� �����','���','�����'),(22,'�����������','���','�����-���������');";
    	     	    st.executeUpdate(query1);	
    		}
    		catch(SQLException s){
    		System.out.println("Table all ready exists!");
    		}
    }
    public void create_table_privoz(String login)
    {
    	String name_table = "";
    	try{
    		Statement st = con.createStatement();
    		name_table = login + "_privoz";
    		String table =
    		"CREATE TABLE `gb_x_lera99`.`"+name_table+"` ( `id` INT NOT NULL AUTO_INCREMENT , `����` VARCHAR(255) NOT NULL , `�����` INT(50) NOT NULL  ,  PRIMARY KEY (`id`)) ENGINE = InnoDB;";
    		st.executeUpdate(table);
    		System.out.println("Table creation process successfully!");
    		String query =
    	    		"UPDATE  `gb_x_lera99`.`users` SET  `name_privoz` =  '"+name_table+"' WHERE  `users`.`login` ='"+login+"';";
    	    		st.executeUpdate(query);
    		}
    		catch(SQLException s){
    		System.out.println("Table all ready exists!");
    		}
    }
    
    public void create_table_rashod(String login)
    {
    	String name_table = "";
    	try{
    		Statement st = con.createStatement();
    		name_table = login + "_rashod";
    		String table =
    		"CREATE TABLE `gb_x_lera99`.`"+name_table+"` ( `id` INT NOT NULL AUTO_INCREMENT , `����` VARCHAR(255) NOT NULL , `�����` INT(50) NOT NULL  ,  PRIMARY KEY (`id`)) ENGINE = InnoDB;";
    		st.executeUpdate(table);
    		System.out.println("Table creation process successfully!");
    		String query =
    	    		"UPDATE  `gb_x_lera99`.`users` SET  `name_rashod` =  '"+name_table+"' WHERE  `users`.`login` ='"+login+"';";
    	    		st.executeUpdate(query);
    		}
    		catch(SQLException s){
    		System.out.println("Table all ready exists!");
    		}
    }
    public void create_table_zooplan(String login)
    {
    	String name_table = "";
    	try{
    		Statement st = con.createStatement();
    		name_table = login + "_zooplan";
    		String table =
    		"CREATE TABLE `gb_x_lera99`.`"+name_table+"` ( `id` INT NOT NULL AUTO_INCREMENT , `�������� ������` VARCHAR(255) NOT NULL , `�����` VARCHAR(255) NOT NULL  , `���. ����������` VARCHAR(255) NOT NULL  , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
    		st.executeUpdate(table);
    		System.out.println("Table creation process successfully!");
    		String query =
    	    		"UPDATE  `gb_x_lera99`.`users` SET  `name_zooplan` =  '"+name_table+"' WHERE  `users`.`login` ='"+login+"';";
    	    		st.executeUpdate(query);
    		}
    		catch(SQLException s){
    		System.out.println("Table all ready exists!");
    		}
    }
    
    public void create_table_adres(String login)
    {
    	String name_table = "";
    	try{
    		Statement st = con.createStatement();
    		name_table = login + "_adres";
    		String table =
    		"CREATE TABLE `gb_x_lera99`.`"+name_table+"` ( `id` INT NOT NULL AUTO_INCREMENT , `��������` VARCHAR(255) NOT NULL ,   PRIMARY KEY (`id`)) ENGINE = InnoDB;";
    		st.executeUpdate(table);
    		System.out.println("Table creation process successfully!");
    		String query =
    	    		"UPDATE  `gb_x_lera99`.`users` SET  `name_adres` =  '"+name_table+"' WHERE  `users`.`login` ='"+login+"';";
    	    		st.executeUpdate(query);
    		}
    		catch(SQLException s){
    		System.out.println("Table all ready exists!");
    		}
    }
    public Vector<Vector<Object>> getNomen(String query)
    {
        Vector<Vector<Object>> retVector = new Vector<Vector<Object>>();
        try
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
                        while (rs.next())
                        {
                                Vector<Object> newRow = new Vector<Object>();
                                for (int i = 2; i <= cols; i++)
                                {
                                        newRow.add(rs.getObject(i));
                                }
                                retVector.add(newRow);
                        }
                        rs.close();
            st.close();
         } catch (SQLException e)
         {
            System.err.println("KFDB.There are problems with the query " + query);
            e.printStackTrace();
         }    	
        return retVector;
    }
    
    public boolean vhod(String login, String password)
    {
    	boolean f=false;
    	try{
    		String pas="";
    		Statement st = con.createStatement();
    	    String query = "SELECT * FROM  `users` WHERE  `login` =  '"+login+"' AND  `password` =  '"+password+"'";
    		ResultSet rs = st.executeQuery(query);
    	    while (rs.next()) {
    	    pas = rs.getString(1);
    	    System.out.print(pas);
    	    }   	  
    	if(pas == "")
	    {   
	    	f=false;
	    	JOptionPane.showMessageDialog(null, "������������ ������!", "������ �����", JOptionPane.ERROR_MESSAGE);
	    }
	    else
	    {
	    	f=true;
	    	new KApplication();
	    }
    	
    	}
    		catch(SQLException s){
    		System.out.println("not!");
    		}	
    	 
	    
    	return f;
    }
    
    public void regictraciya(String login, String password)
    {
    	
    	try{
    		String logins="";
    		Statement st = con.createStatement();
    	    String query3 = "SELECT * FROM `users` WHERE `login` = '" + login + "'";
    		ResultSet rs = st.executeQuery(query3);
    	    while (rs.next()) {
    	    logins = rs.getString(1);
    	    System.out.print(logins);
    	    }
    	    if(logins != ""){ JOptionPane.showMessageDialog(null, "����� ������������ ��� ����!", "������ �����", JOptionPane.ERROR_MESSAGE);}
    	    else{
    	    	    int count=0;
    	    		String query1 = "select count(*) from `users`";
    	    		ResultSet rs1 = st.executeQuery(query1);
    	    		while (rs1.next()) {
    	                count = rs1.getInt(1);
    	            }
    	    		count = count + 1;
    	    	    Statement st1 = con.createStatement();
    	    	    String query2 = "INSERT INTO `users`(`id`, `login`, `password`, `name_m`, `name_g`, `name_priv`, `name_porod`, `name_privoz`, `name_rashod`, `name_zooplan`, `name_adres`) VALUES (NULL, '"+login+"', '"+password+"', '', '', '', '', '', '', '', '');";
    	    	    st1.executeUpdate(query2);
    	    		System.out.println("successfully!");
    	    	    }
    	}
    		catch(SQLException s){
    		System.out.println("not!");
    		}	
    	//catch (Exception e) { JOptionPane.showMessageDialog(null, "����� ������ ��������� ������ ��������� ����� � �����", "������ �����", JOptionPane.ERROR_MESSAGE);}
    }
    
    public String table_porod(String login)
    {
    	return login;
    }
    
}

