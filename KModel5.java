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


public class KModel5 extends AbstractTableModel
{
	public static Connection conn;
	public String tableName;
    public ResultSetMetaData metaData = null;
	// здесь названи€ столбцов
    public ArrayList columnNames = new ArrayList();
    // список типов столбцов
    public ArrayList columnTypes = new ArrayList();
    // хранилище дл€ полученных данных из базы данных
    public ArrayList data = new ArrayList();
    String login = (new avtorizaciya()).getLogin();
    String name_table = login+"_privoz";
    // конструктор позвол€ет задать возможность редактировани€
    public KModel5(boolean editable) {
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
    
    // возможность редактировани€
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
    	if(column==0){
    		return "є";
    	}
    	else{
        return (String) columnNames.get(column);}
    }
    // данные в €чейке

    @Override
    public Object getValueAt(int row, int column) {
        synchronized (data) {
            return ((ArrayList) data.get(row)).get(column);
        }
    }

    // замена значени€ €чейки
    
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
        // удал€ем прежние данные
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
        // сообщаем об изменени€х в структуре данных
        fireTableStructureChanged();
        // получаем данные
        while (rs.next()) {
            // здесь будем хранить €чейки одной строки
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
            Logger.getLogger(KModel5.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void remove(int rowIndex) {
    	try {
            int row = rowIndex + 1;
    		String query = "DELETE FROM `gb_x_lera99`.`"+name_table+"` WHERE `"+name_table+"`.`id`="+ (row);
            System.out.println(row);
            connect().executeUpdate(query);
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(KModel5.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(KModel5.class.getName()).log(Level.SEVERE, null, ex);
        }
    	try {
            int row = rowIndex + 1;
            int rowCount = getRowCount();
    		String query = "ALTER TABLE `gb_x_lera99`.`"+name_table+"` MODIFY `"+name_table+"`.`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT = "+ (rowCount+1);
            connect().executeUpdate(query);
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(KModel5.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void add(String data, String kol){
    	int count=0;
    	try {
		String query = "select count(*) from `gb_x_lera99`.`"+name_table+"`";
		ResultSet rs = connect().executeQuery(query);
		while (rs.next()) {
            count = rs.getInt(1);
        }  
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(KModel5.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    	try {
    		int id = count + 1;
    		String query = "INSERT INTO  `gb_x_lera99`.`"+name_table+"` (`id` ,`ƒата` ,`ќбъем`) VALUES ('"+id+"',  '"+data+"',  '"+kol+"');";
            System.out.println(id);
            connect().executeUpdate(query);
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(KModel5.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    
}
    

