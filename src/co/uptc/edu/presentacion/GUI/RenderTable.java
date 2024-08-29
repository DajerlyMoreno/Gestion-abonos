package co.uptc.edu.presentacion.GUI;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderTable extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (value instanceof JPanel) {
			// Si el valor de la celda es un JPanel, devolver el JPanel tal cual
			return (JPanel) value;
		}

		if (value instanceof List) {
			// Si el valor de la celda es una lista de Strings, crear un JPanel con tres
			// botones
			JPanel panel = new JPanel(new GridLayout(1, 3)); // GridLayout de 1 fila y 3 columnas
			List<String> strings = (List<String>) value;
			for (String s : strings) {
				JButton button = new JButton(s);
				panel.add(button);
			}
			return panel;
		}

		// Si el valor de la celda no es un JPanel ni una lista de Strings, usar el
		// renderizador predeterminado
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}
}
