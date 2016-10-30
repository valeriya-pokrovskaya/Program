package app;
/*import java.io.FileOutputStream;
import java.io.IOException;
 
import java.awt.Component;
 
import javax.swing.JFileChooser;
import javax.swing.JTable;
 
import java.sql.Date;
import java.sql.Timestamp;
 
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
 
public class export {
	// поля класа
	private Component parent;
	private JTable table;
	private String nameFile;
	private String address;
	private Workbook workbook = new HSSFWorkbook();
	private Sheet sheet = workbook.createSheet("Двигуни");
	private Row row = sheet.createRow(0);
	private Cell cell;
	private Font fontTitle = workbook.createFont();
	private CellStyle styleTitle = workbook.createCellStyle();
	private CellStyle styleFloat = workbook.createCellStyle();
	private CellStyle styleDate = workbook.createCellStyle();
	private CellStyle styleTimestamp = workbook.createCellStyle();
	private CellStyle styleNull = workbook.createCellStyle();
	private DataFormat formatFloat = workbook.createDataFormat();
	private DataFormat formatDate = workbook.createDataFormat();
	private DataFormat formatTimestamp = workbook.createDataFormat();
 
	// конструктор класа (компонент, таблиця та назва файла задається)
	public export(Component parent, JTable table, String nameFile) {
		this.parent = parent;
		this.table = table;
		this.nameFile = nameFile;
		if (isAddress()) {
			setVariables();
			setTitleTable();
			setDataTable();
			writeTable();
		}
	}
 
	// метод вибору директорії для запису файла
	private boolean isAddress() {
		MainFrame.frame.msg(parent,
				"Виберіть папку, в котру буде записаний файл: " + nameFile);
		JFileChooser dir = new JFileChooser(System.getProperty("user.home"));
		dir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		dir.setAcceptAllFileFilterUsed(false);
		int choose = dir.showSaveDialog(parent);
		if (choose == JFileChooser.APPROVE_OPTION
				&& dir.getSelectedFile().exists()) {
			address = dir.getSelectedFile().getPath();
			return true;
		}
		return false;
	}
 
	// налаштування полів класа (шрифт, стиль, формат даних)
	public void setVariables() {
		fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);
		fontTitle.setColor(HSSFColor.AQUA.index);
		styleTitle.setAlignment(CellStyle.ALIGN_CENTER);
		styleTitle.setFont(fontTitle);
		styleFloat.setDataFormat(formatFloat.getFormat("0.0##"));
		styleDate.setDataFormat(formatDate.getFormat("yyyy р."));
		styleTimestamp.setDataFormat(formatTimestamp
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				.getFormat("d/mm/yyyy h:mm:ss"));
		styleNull.setAlignment(CellStyle.ALIGN_RIGHT);
	}
 
	
	public void setBorder(CellStyle style) {
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderBottom(CellStyle.BORDER_THIN);
	}
 
	// метод прописує назву стовпців таблиці
	public void setTitleTable() {
		for (int i = 0; i < table.getModel().getColumnCount(); i++) {
			cell = row.createCell(i);
			cell.setCellValue(table.getModel().getColumnName(i));
			cell.setCellStyle(styleTitle);
		}
	}
 
	// метод прописує дані в таблицю
	public void setDataTable() {
		for (int i = 0; i < table.getModel().getRowCount(); i++) {
			row = sheet.createRow(i + 1);
			for (int j = 0; j < table.getModel().getColumnCount(); j++) {
				String type = table.getModel().getColumnClass(j).getName();
				cell = row.createCell(j);
				if (table.getModel().getValueAt(i, j) != null) {
					switch (type) {
					case "java.lang.Integer":
						cell.setCellValue(Integer.valueOf(table.getModel()
								.getValueAt(i, j).toString()));
						break;
					case "java.lang.Float":
						cell.setCellValue(Float.valueOf(table.getModel()
								.getValueAt(i, j).toString()));
						cell.setCellStyle(styleFloat);
						break;
					case "java.sql.Date":
						cell.setCellValue(Date.valueOf(table.getModel()
								.getValueAt(i, j).toString()));
						cell.setCellStyle(styleDate);
						break;
					case "java.sql.Timestamp":
						cell.setCellValue(Timestamp.valueOf(table.getModel()
								.getValueAt(i, j).toString()));
						cell.setCellStyle(styleTimestamp);
						break;
					default:
						cell.setCellValue(table.getModel().getValueAt(i, j)
								.toString());
						break;
					}
				} else {
					cell.setCellValue("невідомо");
					cell.setCellStyle(styleNull);
				}
				setBorder(cell.getCellStyle());
				sheet.autoSizeColumn(j);
			}
		}
	}
 
	// метод запису таблиці в файл формату xls
	public void writeTable() {
		try (FileOutputStream fileExcel = new FileOutputStream(address + "\\"
				+ nameFile)) {
			workbook.write(fileExcel);
			MainFrame.frame.msg(parent, "Таблиця успішно записана в файл.");
		} catch (IOException e) {
			MainFrame.frame.msg(parent,
					"Неможливо виконати експорт таблиці в Excel.");
		}
	}
}*/