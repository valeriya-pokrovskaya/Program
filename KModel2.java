package app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.AbstractTableModel;


public class KModel2 extends AbstractTableModel
{
	public static Connection conn;
	public String tableName;
    public ResultSetMetaData metaData = null;
	// здесь названия столбцов
    public ArrayList columnNames = new ArrayList();
    // список типов столбцов
    public ArrayList columnTypes = new ArrayList();
    // хранилище для полученных данных из базы данных
    public ArrayList data = new ArrayList();
    String login = (new avtorizaciya()).getLogin();
    String name_table = login+"_g";
    // конструктор позволяет задать возможность редактирования
    public KModel2(boolean editable) {
        this.editable = editable;
    }
    private boolean editable;

    // количество строк
    @Override
    public int getRowCount() {
        synchronized (data) {
            return data.size();
        }
    }
    
    // возможность редактирования
    @Override
    public boolean isCellEditable(int row, int column) {
    	if(column==0){return false;}
    	else{return true;}  
    }
    
    // количество столбцов
    @Override
    public int getColumnCount() {
        return columnNames.size();
    }
    // тип данных столбца

    @Override
    public Class getColumnClass(int column) {
        return (Class) columnTypes.get(column);
    }
    // название столбца

    @Override
    public String getColumnName(int column) {
    	switch(column){
    	case(0):
    		return "№";
    	default:
            return (String) columnNames.get(column);
    	}
    }
    // данные в ячейке

    @Override
    public Object getValueAt(int row, int column) {
        synchronized (data) {
            return ((ArrayList) data.get(row)).get(column);
        }
    }

    // замена значения ячейки
    
    public void getValueAt( Object value, int row, int column) {
        synchronized (data) {
            ((ArrayList) data.get(row)).set(column, value);
        }
    }
    public void removeRow(int rowIndex) {

        data.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    // получение данных из объекта ResultSet

    public void setDataSource(ResultSet rs) throws Exception {
        // удаляем прежние данные
        data.clear();
        columnNames.clear();
        columnTypes.clear();
        // получаем вспомогательную информацию о столбцах
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            // название столбца
            columnNames.add(rsmd.getColumnName(i + 1));
            // тип столбца
            Class type= Class.forName(rsmd.getColumnClassName(i + 1));
            columnTypes.add(type);
        }
        // сообщаем об изменениях в структуре данных
        fireTableStructureChanged();
        // получаем данные
        while (rs.next()) {
            // здесь будем хранить ячейки одной строки
            ArrayList row = new ArrayList();
            for (int i = 0; i < columnCount; i++) {
                if (columnTypes.get(i) == String.class) {
                    row.add(rs.getString(i + 1));
                } else {
                    row.add(rs.getObject(i + 1));
                }
            }
            synchronized (data) {
                data.add(row);
                // сообщаем о прибавлении строки
                fireTableRowsInserted(
                        data.size() - 1, data.size() - 1);

            }
        }
}
    public static Statement connect() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
	      // Properties props = new Properties();
	        //FileInputStream in = new FileInputStream(fileName);
	        //props.load(in);
	        //in.close();
	    	Class.forName("com.mysql.jdbc.Driver");
          String url = "jdbc:mysql://mysql80.1gb.ru/gb_x_lera99";
          String name = "gb_x_lera99";
          String password = "a77z3fc3qw";
          conn = DriverManager.getConnection(url, name, password);
          Statement st = conn.createStatement();
	        return st;
	    }
    public void addRow() {
        ArrayList row = new ArrayList();
        row.add(getRowCount() + 1);
        data.add(row);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);

    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        synchronized (data) {
            ((ArrayList) data.get(row)).set(column, value);
        }
        try {
            String columnName = getColumnName(column);
            String query
                    = "UPDATE  `gb_x_lera99`.`"+name_table+"` SET `"+ columnName + "`="+ "'"+value+"'"+ "WHERE"+  "`"+name_table+"`.`id`="+"'"+getValueAt(row, 0)+"'";

            System.out.println(value);
            System.out.println(columnName);

            connect().executeUpdate(query);
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(KModel6.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void remove(int rowIndex) {
    	try {
            int row = rowIndex + 1;
    		String query = "DELETE FROM `gb_x_lera99`.`"+name_table+"` WHERE `"+name_table+"`.`id`="+ (row);
            System.out.println(row);
            connect().executeUpdate(query);
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(KModel6.class.getName()).log(Level.SEVERE, null, ex);
        }
    	try {
    		int rowCount = getRowCount();
    		int row = rowIndex + 1;
    		for(int i=row; i <= rowCount; i++){
    		String query = "UPDATE  `gb_x_lera99`.`"+name_table+"` SET `"+name_table+"`.`id`="+ "'"+i+"'" + "WHERE"+  "`"+name_table+"`.`id`="+(i+1);
    		connect().executeUpdate(query);
    		}
    		System.out.println(rowCount);
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(KModel6.class.getName()).log(Level.SEVERE, null, ex);
        }
    	try {
            int row = rowIndex + 1;
            int rowCount = getRowCount();
    		String query = "ALTER TABLE `gb_x_lera99`.`"+name_table+"` MODIFY `"+name_table+"`.`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT = "+ (rowCount+1);
            connect().executeUpdate(query);
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(KModel6.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void add(String number, String name, String data, String age, String breed, /*String weight,*/ String info, String location, String kol1, String kol2){
    	int count=0;
    	try {
		String query = "select count(*) from `gb_x_lera99`.`"+name_table+"`";
		ResultSet rs = connect().executeQuery(query);
		while (rs.next()) {
            count = rs.getInt(1);
        }  
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(KModel6.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    	try {
    		int id = count + 1;
    		String query = "INSERT INTO  `gb_x_lera99`.`"+name_table+"` (`id` ,`Идент. номер` ,`Кличка`, `Дата рождения`,`Возраст`,`Порода`,`Вес`,`Пометки`,`Расположение`,`Кол-во соседей`, `Кол-во родившихся`) VALUES ('"+id+"', '"+number+"', '"+name+"', '"+data+"', '"+age+"', '"+breed+"', '', '"+info+"', '"+location+"', '"+kol1+"', '"+kol2+"');";
            System.out.println(id);
            connect().executeUpdate(query);
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(KModel6.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    public void create_table_rod(String number){
 	   
 	   String name_table = login + "_rod"+ "_g"+ "_"+ number;
 	   
 	   try{
    		String query =
    				"CREATE TABLE `gb_x_lera99`.`"+name_table+"` ( `id` INT NOT NULL AUTO_INCREMENT , `Кличка матери` VARCHAR(255) NOT NULL , `Кличка бабушки(мать)` VARCHAR(255) NOT NULL , `Кличка дедушки(мать)` VARCHAR(255) NOT NULL,  `Кличка отца` VARCHAR(255) NOT NULL , `Кличка бабушки(отец)` VARCHAR(255) NOT NULL , `Кличка дедушки(отец)` VARCHAR(255) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
    		connect().executeUpdate(query);
    		System.out.println("Table creation process successfully!");
 	   } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(KModel6.class.getName()).log(Level.SEVERE, null, ex);
    }   
 	   
    }
    
 public void create_table_w(String number){
 	   
 	   String name_table = login + "_w"+ "_g"+"_"+ number;
 	   
 	   try{
    		String query =
    				"CREATE TABLE `gb_x_lera99`.`"+name_table+"` ( `id` INT NOT NULL AUTO_INCREMENT , `Дата` VARCHAR(255) NOT NULL , `Вес(граммы)` VARCHAR(255) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
    		connect().executeUpdate(query);
    		System.out.println("Table creation process successfully!");
 	   } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(KModel6.class.getName()).log(Level.SEVERE, null, ex);
    }   
 	   
    }

     public void create_table_p(String number){
 	   
 	   String name_table = login + "_p"+ "_g"+ "_"+ number;
 	   
 	   try{
 		String query =
 				"CREATE TABLE `gb_x_lera99`.`"+name_table+"` ( `id` INT NOT NULL AUTO_INCREMENT , `Дата проведения` VARCHAR(255) NOT NULL , `Название` VARCHAR(255) NOT NULL , `Срок действия` VARCHAR(255) NOT NULL,  PRIMARY KEY (`id`)) ENGINE = InnoDB;";
 		connect().executeUpdate(query);
 		System.out.println("Table creation process successfully!");
 	   } catch (SQLException | ClassNotFoundException | IOException ex) {
         Logger.getLogger(KModel6.class.getName()).log(Level.SEVERE, null, ex);
         }   
      }
     
     
     public void create_table_sl_okr(String number){
   	   
   	   String name_table = login + "_sl_okr"+ "_"+ number;
   	   
   	   try{
   		String query =
   				"CREATE TABLE `gb_x_lera99`.`"+name_table+"` ( `id` INT NOT NULL AUTO_INCREMENT , `Дата соития` VARCHAR(255) NOT NULL , `Партнер` VARCHAR(255) NOT NULL , `Дата окрола` VARCHAR(255) NOT NULL , `Рождено` VARCHAR(255) NOT NULL, `Мертворожденные` VARCHAR(255) NOT NULL, `Инбридинг` VARCHAR(255) NOT NULL,  PRIMARY KEY (`id`)) ENGINE = InnoDB;";
   		connect().executeUpdate(query);
   		System.out.println("Table creation process successfully!");
   	   } catch (SQLException | ClassNotFoundException | IOException ex) {
           Logger.getLogger(KModel6.class.getName()).log(Level.SEVERE, null, ex);
           }   
        }
     
     public void insertRod(String n, String name_m, String name_mb, String name_md, String name_p, String name_pb, String name_pd){ 
   	   int count=0;
   	   String name_table = login + "_rod"+ "_g"+ "_" + n;
      	try {
   		String query = "select count(*) from `gb_x_lera99`.`"+name_table+"`";
   		ResultSet rs = connect().executeQuery(query);
   		while (rs.next()) {
              count = rs.getInt(1);
          }  
          } catch (SQLException | ClassNotFoundException | IOException ex) {
              Logger.getLogger(KModel6.class.getName()).log(Level.SEVERE, null, ex);
          }
      	
      	try {
      		int id = count + 1;
      		String query = "INSERT INTO  `gb_x_lera99`.`"+name_table+"` (`id`, `Кличка матери`, `Кличка бабушки(мать)`, `Кличка дедушки(мать)`,  `Кличка отца`, `Кличка бабушки(отец)`, `Кличка дедушки(отец)`) VALUES ('"+id+"', '"+name_m+"', '"+name_mb+"', '"+name_md+"', '"+name_p+"', '"+name_pb+"', '"+name_pd+"');";
              System.out.println(id);
              connect().executeUpdate(query);
          } catch (SQLException | ClassNotFoundException | IOException ex) {
              Logger.getLogger(KModel6.class.getName()).log(Level.SEVERE, null, ex);
      }
   	   
   	   
      }
}
    

