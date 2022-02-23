//import javax.swing.JFrame;
//import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class TableRenderer implements TableCellRenderer{
	//TableRenderer Class: used for setting table characteristics
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int column){
		//Purpose: overrides method that determines table characteristics and how types of data are inputted into a table cell
		//Params: current table, the value that has to be inputted, the row, column, etc.
		//Returns: Component 
		Font tableTitle = new Font("TimesRoman", Font.ITALIC, 20), tableText = new Font("Helvetica", Font.PLAIN, 15), buyText = new Font("Helvetica", Font.BOLD, 30);
		JLabel tempField = new JLabel(), output = new JLabel();
		if (value instanceof JList) {
			JList tempList = new JList();
			tempList = (JList<String>)value;
			return tempList;
		}else if (value instanceof Image){
			output.setIcon(new ImageIcon((Image)value));
			return output;
		}else if (value instanceof Integer || value instanceof Double){
			output.setText("" + value);
			return output;
		}else if (value instanceof JButton){
			((JButton) value).setSize(new Dimension(table.getColumnModel().getColumn(column).getWidth(), table.getRowHeight(row)));
			((JButton) value).setLocation(0,0);
			return (Component) value;
		}else  if (row==0) {
			tempField.setFont(tableTitle);
			tempField.setHorizontalAlignment(SwingConstants.CENTER);
			tempField.setText((String)value);
		}else{ 
			tempField.setFont(tableText);
			tempField.setHorizontalAlignment(SwingConstants.LEFT);
			tempField.setText((String)value);
		}
		return tempField;
	}
}