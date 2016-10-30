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
    		"CREATE TABLE `gb_x_lera99`.`"+name_table+"` ( `id` INT NOT NULL AUTO_INCREMENT , `Идент. номер` VARCHAR(255) NOT NULL , `Кличка` VARCHAR(255) NOT NULL , `Дата рождения` VARCHAR(255) NOT NULL,  `Возраст` VARCHAR(255) NOT NULL , `Порода` VARCHAR(255) NOT NULL , `Вес` VARCHAR(255) NOT NULL , `Пометки` VARCHAR(255) NOT NULL , `Расположение` VARCHAR(255) NOT NULL ,  `Кол-во соседей` VARCHAR(255) NOT NULL , `Кол-во родившихся` VARCHAR(255) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
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
    				"CREATE TABLE `gb_x_lera99`.`"+name_table+"` ( `id` INT NOT NULL AUTO_INCREMENT , `Идент. номер` VARCHAR(255) NOT NULL , `Кличка` VARCHAR(255) NOT NULL , `Дата рождения` VARCHAR(255) NOT NULL,  `Возраст` VARCHAR(255) NOT NULL , `Порода` VARCHAR(255) NOT NULL , `Вес` VARCHAR(255) NOT NULL , `Пометки` VARCHAR(255) NOT NULL , `Расположение` VARCHAR(255) NOT NULL ,  `Кол-во соседей` VARCHAR(255) NOT NULL , `Кол-во родившихся` VARCHAR(255) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
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
    		"CREATE TABLE `gb_x_lera99`.`"+name_table+"` ( `id` INT NOT NULL AUTO_INCREMENT , `Название` VARCHAR(255) NOT NULL , `Назначить с ... дней` INT(50) NOT NULL ,   `Срок действия(дней)` INT(50) NOT NULL ,  PRIMARY KEY (`id`)) ENGINE = InnoDB;";
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
    		"CREATE TABLE `gb_x_lera99`.`"+name_table+"` ( `id` INT NOT NULL AUTO_INCREMENT , `Название` VARCHAR(255) NOT NULL , `Сокращение` VARCHAR(255) NOT NULL ,   `Цвет` VARCHAR(255) NOT NULL ,  PRIMARY KEY (`id`)) ENGINE = InnoDB;";
    		st.executeUpdate(table);
    		System.out.println("Table creation process successfully!");
    		String query =
    	    		"UPDATE  `gb_x_lera99`.`users` SET  `name_porod` =  '"+name_table+"' WHERE  `users`.`login` ='"+login+"';";
    	    		st.executeUpdate(query);
    	    String query1 =
    	     	     "INSERT INTO `gb_x_lera99`.`"+name_table+"`(`id`, `Название`, `Сокращение`, `Цвет`) VALUES (1,'Гибрид','Гибрид','Серый'), (2,'Бабочка','ББ','Светло-розовый'), (3,'Серебристый','СрБ','Сиреневый'), (4,'Черно-Бурый','ЧрБ','Черный'),(5,'Советская Шиншила','СоШ','Бирюзовый'),(6,'Белая Чернова','БеЧ','Белый'),(7,'Бельгийский Фландр','БеФ','Белый'),(8,'Венский Голубой','ВеГ','Синий'),(9,'Калифорниискии Белый','БеВ','Светло-серый'),(10,'Серыи Великан','СрВ','Тускло-серый'),(11,'Гибрид Срб БеВ','Г2','Белый'),(12,'Гибрид СрВ Срб','Г3','Белый'),(13,'Гибрид БеВ КфБ','Г4','Белый'),(14,'Гибрид Кфб БеВ','Г5','Белый'),(15,'Гибрид СрВ БеВ.','Г6','Белый'),(16,'Белка','Бл','Белый'),(17,'Серо-серебристый','ССрб','Темно-серый'),(18,'Гибрид Срб СрВ','Г7','Белый'),(19,'Акселерат','АкС','Белый'),(20,'Белка-Сиам','Б-С','Белый'),(21,'Новозеландский Белый','НзБ','Белый'),(22,'Черепаховый','ЧрП','Темно-оранжевый');";
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
    		"CREATE TABLE `gb_x_lera99`.`"+name_table+"` ( `id` INT NOT NULL AUTO_INCREMENT , `Дата` VARCHAR(255) NOT NULL , `Объем` INT(50) NOT NULL  ,  PRIMARY KEY (`id`)) ENGINE = InnoDB;";
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
    		"CREATE TABLE `gb_x_lera99`.`"+name_table+"` ( `id` INT NOT NULL AUTO_INCREMENT , `Дата` VARCHAR(255) NOT NULL , `Объем` INT(50) NOT NULL  ,  PRIMARY KEY (`id`)) ENGINE = InnoDB;";
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
    		"CREATE TABLE `gb_x_lera99`.`"+name_table+"` ( `id` INT NOT NULL AUTO_INCREMENT , `Название работы` VARCHAR(255) NOT NULL , `Адрес` VARCHAR(255) NOT NULL  , `Доп. информация` VARCHAR(255) NOT NULL  , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
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
    		"CREATE TABLE `gb_x_lera99`.`"+name_table+"` ( `id` INT NOT NULL AUTO_INCREMENT , `Название` VARCHAR(255) NOT NULL ,   PRIMARY KEY (`id`)) ENGINE = InnoDB;";
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
	    	JOptionPane.showMessageDialog(null, "Неправильный пароль!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
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
    	    if(logins != ""){ JOptionPane.showMessageDialog(null, "Такой пользователь уже есть!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);}
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
    	//catch (Exception e) { JOptionPane.showMessageDialog(null, "Логин должен содержать только латинские буквы и цифры", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);}
    }
    
    public String table_porod(String login)
    {
    	return login;
    }
    
}

