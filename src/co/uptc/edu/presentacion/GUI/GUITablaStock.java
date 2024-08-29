package co.uptc.edu.presentacion.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import co.uptc.edu.logica.control.OperacionesAditivo;
import co.uptc.edu.logica.control.OperacionesBulto;
import co.uptc.edu.logica.modelo.Aditivo;
import co.uptc.edu.logica.modelo.Bulto;
import co.uptc.edu.persistencia.DAOAbono;
import co.uptc.edu.persistencia.DAOAditivos;
import co.uptc.edu.persistencia.DAOBulto;
import co.uptc.edu.persistencia.DAOResiduosOrganicos;

public class GUITablaStock extends JFrame implements ActionListener {
	private JPanel JPAreaFondo;
	private JPanel JPMenuSuperior;// Este debe ser el menu de la parte de arriba que debe permitir navegar
	private JPanel JPMenuBotonesTabla;
	private JButton JBModificarAditivo;
	private JButton JBAgregarAditivo;
	private JButton JBBorrarAditivo;
	private JButton JBCrearAditivo;
	private JLabel JLTablaStock;
	private JTable JTStock;
	private JScrollPane JSPTabla;
	private JScrollPane JSPGeneral;
	private JPanel JPMenuBotonesTabla2;
	private JButton JBModificarBulto;
	private JButton JBAgregarBulto;
	private JButton JBBorrarBulto;
	private JButton JBCrearBulto;
	private JLabel JLTablaBulto;
	private JTable JTBulto;
	private JScrollPane JSPTabla2;

	private JPanel JPEspacioInfoAbono;
	private JLabel JLInfoAbono;
	private JLabel JLInfoMateriaOrganica;
	private JButton JBAgregarMateriaOrganica;
	private JButton JBAgregarAbono;

	private DefaultTableCellRenderer centerRenderer;

	public GUITablaStock() throws IOException {
		JPAreaFondo = new JPanel();
		JPMenuSuperior = new GUIMenuSuperior(this).MostrarMenu();

		JPEspacioInfoAbono = new JPanel();
		JLInfoAbono = new JLabel("Total Abono " + new DAOAbono().ObtenerDatosAbono().get(0).getCantidad() + "Kg");
		JLInfoMateriaOrganica = new JLabel("Materia orgánica "
				+ new DAOResiduosOrganicos().ObtenerResiduosOrganicos().get(0).getCantidad() + "Kg");

		JBAgregarAbono = new JButton();
		JBAgregarMateriaOrganica = new JButton();

		File FileAgregarAbono = new File("Recursos/iconos/Agregar2.png");
		Image ImgAgregarAbono = ImageIO.read(FileAgregarAbono);
		JBAgregarAbono.setIcon(new ImageIcon(ImgAgregarAbono));
		JBAgregarAbono.setBackground(Color.white);
		JBAgregarAbono.setBorder(null);
		
		File FileAgregarMatOrg= new File("Recursos/iconos/Agregar2.png");
		Image ImgAgregarMatOrg = ImageIO.read(FileAgregarMatOrg);
		JBAgregarMateriaOrganica.setIcon(new ImageIcon(ImgAgregarMatOrg));
		JBAgregarMateriaOrganica.setBackground(Color.white);
		JBAgregarMateriaOrganica.setBorder(null);

		JPMenuBotonesTabla = new JPanel();
		JBCrearAditivo = new JButton("Crear aditivo");

		JBAgregarAditivo = new JButton("");
		JBModificarAditivo = new JButton("");
		JBBorrarAditivo = new JButton("");

		File FileEditarAditivo = new File("Recursos/iconos/editar.png");
		Image ImgEditarAditivo = ImageIO.read(FileEditarAditivo);
		JBModificarAditivo.setIcon(new ImageIcon(ImgEditarAditivo));
		JBModificarAditivo.setBackground(Color.white);
		JBModificarAditivo.setBorder(null);

		File FileEliminarAditivo = new File("Recursos/iconos/eliminar.png");
		Image ImgEliminarAditivo = ImageIO.read(FileEliminarAditivo);
		JBBorrarAditivo.setIcon(new ImageIcon(ImgEliminarAditivo));
		JBBorrarAditivo.setBackground(Color.white);
		JBBorrarAditivo.setBorder(null);

		File FileAgregarAditivo = new File("Recursos/iconos/Agregar.png");
		Image ImgAgregarAditivo = ImageIO.read(FileAgregarAditivo);
		JBAgregarAditivo.setIcon(new ImageIcon(ImgAgregarAditivo));
		JBAgregarAditivo.setBackground(Color.white);
		JBAgregarAditivo.setBorder(null);

		JLTablaStock = new JLabel("Stock");
		JTStock = new JTable();
		JSPTabla = new JScrollPane();
		JSPGeneral = new JScrollPane();
		centerRenderer = new DefaultTableCellRenderer();

		// Botones para bultos
		JBCrearBulto = new JButton("Crear bulto");

		JBModificarBulto = new JButton("");
		JBAgregarBulto = new JButton("");
		JBBorrarBulto = new JButton("");

		File FileEditarBulto = new File("Recursos/iconos/editar.png");
		Image ImgEditarBulto = ImageIO.read(FileEditarBulto);
		JBModificarBulto.setIcon(new ImageIcon(ImgEditarBulto));
		JBModificarBulto.setBackground(Color.white);
		JBModificarBulto.setBorder(null);

		File FileEliminarBulto = new File("Recursos/iconos/eliminar.png");
		Image ImgEliminarBulto = ImageIO.read(FileEliminarBulto);
		JBBorrarBulto.setIcon(new ImageIcon(ImgEliminarBulto));
		JBBorrarBulto.setBackground(Color.white);
		JBBorrarBulto.setBorder(null);

		File FileAgregarBulto = new File("Recursos/iconos/Agregar.png");
		Image ImgAgregarBulto = ImageIO.read(FileAgregarBulto);
		JBAgregarBulto.setIcon(new ImageIcon(ImgAgregarBulto));
		JBAgregarBulto.setBackground(Color.white);
		JBAgregarBulto.setBorder(null);

		JPMenuBotonesTabla2 = new JPanel();

		JLTablaBulto = new JLabel("Bultos");
		JTBulto = new JTable();
		JSPTabla2 = new JScrollPane();

		setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		setSize(800, 700);
		setLocationRelativeTo(null);
		setTitle("Stock");

		setResizable(false);
	}

	public void TablaStock() {

		JPAreaFondo.setBounds(0, 70, 1000, 930);
		JPAreaFondo.setBackground(Color.white);
		JPAreaFondo.setLayout(null);

		JPEspacioInfoAbono.setBounds(256, 20, 443, 50);
		JPEspacioInfoAbono.setBackground(new Color(242, 242, 242));
		JPEspacioInfoAbono.setLayout(null);

		JLInfoAbono.setBounds(10, 0, 160, 50);
		JLInfoAbono.setFont(new Font("Fredoka One", Font.BOLD, 15));
		JLInfoAbono.setForeground(Color.black);
		JLInfoAbono.setVerticalAlignment(SwingConstants.CENTER);

		JBAgregarAbono.setBounds(160, 5, 40, 40);
		JBAgregarAbono.addActionListener(this);
		
		JLInfoMateriaOrganica.setBounds(210, 0, 180, 50);
		JLInfoMateriaOrganica.setFont(new Font("Fredoka One", Font.BOLD, 15));
		JLInfoMateriaOrganica.setForeground(Color.black);
		JLInfoMateriaOrganica.setVerticalAlignment(SwingConstants.CENTER);
		
		JBAgregarMateriaOrganica.setBounds(395, 5, 40, 40);
		JBAgregarMateriaOrganica.addActionListener(this);

		// Tabla stock
		JLTablaStock.setBounds(100, 20, 130, 40);
		JLTablaStock.setFont(new Font("Arial", Font.BOLD, 40));
		JLTablaStock.setForeground(new Color(31, 90, 166));

		// Tabla bulto
		JLTablaBulto.setBounds(100, 305, 130, 40);
		JLTablaBulto.setFont(new Font("Arial", Font.BOLD, 40));
		JLTablaBulto.setForeground(new Color(31, 90, 166));

		// Tabla Stock
		JBCrearAditivo.setBounds(588, 255, 110, 40);
		JBCrearAditivo.setBackground(Color.white);
		JBCrearAditivo.setBorder(BorderFactory.createLineBorder(new Color(31, 90, 166), 2));
		JBCrearAditivo.setForeground(new Color(31, 90, 166));
		JBCrearAditivo.addActionListener(this);

		JPMenuBotonesTabla.setBackground(Color.white);

		FlowLayout LayourBotonesTablaAditivo = new FlowLayout();
		LayourBotonesTablaAditivo.setHgap(5);
		JPMenuBotonesTabla.setLayout(LayourBotonesTablaAditivo);
		JPMenuBotonesTabla.setBackground(Color.white);

		// Tabla bulto
		JBCrearBulto.setBounds(588, 540, 110, 40);
		JBCrearBulto.setBackground(Color.white);
		JBCrearBulto.setBorder(BorderFactory.createLineBorder(new Color(31, 90, 166), 2));
		JBCrearBulto.setForeground(new Color(31, 90, 166));
		JBCrearBulto.addActionListener(this);

		FlowLayout LayourBotonesTablaBulto = new FlowLayout();
		LayourBotonesTablaBulto.setHgap(5);
		JPMenuBotonesTabla2.setLayout(LayourBotonesTablaBulto);
		JPMenuBotonesTabla2.setBackground(Color.white);

		// JBAgregarAditivo.setBounds(10,10,30,30);

		JTStock.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int columna = JTStock.getColumnModel().getColumnIndexAtX(e.getX()); // Columna en la que se hizo clic
				int fila = e.getY() / JTStock.getRowHeight(); // Fila en la que se hizo clic

				if (columna == 3 && fila < JTStock.getRowCount()) {

					if (e.getX() >= JTStock.getCellRect(fila, columna, true).x + 20 && e
							.getX() <= JTStock.getCellRect(fila, columna, true).x + 20 + JBAgregarAditivo.getWidth()) {
						setVisible(false);
						GUIEditarAditivo ed = new GUIEditarAditivo(fila);
						ed.EditarAditivo();
					} else if (e.getX() >= JTStock.getCellRect(fila, columna, true).x + 26
							+ JBModificarAditivo.getWidth()
							&& e.getX() <= JTStock.getCellRect(fila, columna, true).x + 26
									+ JBModificarAditivo.getWidth() * 2) {
						setVisible(false);
						GUIAgregarAditivo ed = new GUIAgregarAditivo(fila);
						ed.AgregarAditivo();

					} else if (e.getX() >= JTStock.getCellRect(fila, columna, true).x + 32
							+ JBBorrarAditivo.getWidth() * 2
							&& e.getX() <= JTStock.getCellRect(fila, columna, true).x + 32
									+ JBBorrarAditivo.getWidth() * 3) {
						setVisible(false);
						new OperacionesAditivo().EliminarAditivo(fila);
						GUITablaStock tb;
						try {
							tb = new GUITablaStock();
							tb.TablaStock();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
			}
		});

		JTBulto.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int columna = JTBulto.getColumnModel().getColumnIndexAtX(e.getX()); // Columna en la que se hizo clic
				int fila = e.getY() / JTBulto.getRowHeight(); // Fila en la que se hizo clic

				if (columna == 3 && fila < JTBulto.getRowCount()) {

					if (e.getX() >= JTBulto.getCellRect(fila, columna, true).x + 20 && e
							.getX() <= JTBulto.getCellRect(fila, columna, true).x + 20 + JBAgregarBulto.getWidth()) {
						setVisible(false);
						GUIEditarBulto ed = new GUIEditarBulto(fila);
						ed.EditarBulto();
					} else if (e.getX() >= JTBulto.getCellRect(fila, columna, true).x + 26 + JBModificarBulto.getWidth()
							&& e.getX() <= JTBulto.getCellRect(fila, columna, true).x + 26
									+ JBModificarBulto.getWidth() * 2) {
						setVisible(false);
						GUIAgregarBulto a = new GUIAgregarBulto(fila);
						a.AgregarBulto();

					} else if (e.getX() >= JTBulto.getCellRect(fila, columna, true).x + 32
							+ JBBorrarBulto.getWidth() * 2
							&& e.getX() <= JTBulto.getCellRect(fila, columna, true).x + 32
									+ JBBorrarBulto.getWidth() * 3) {
						setVisible(false);
						new OperacionesBulto().EliminarBulto(fila);
						GUITablaStock tb;
						try {
							tb = new GUITablaStock();
							tb.TablaStock();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
			}
		});
		JSPGeneral.setBounds(0, 70, 1000, 930);
		JSPGeneral.setViewportView(JPAreaFondo);

		// Tabla stock
		JSPTabla.setBounds(100, 70, 600, 180);
		JSPTabla.setViewportView(JTStock);

		// Tabla bulto
		JSPTabla2.setBounds(100, 355, 600, 180);
		JSPTabla2.setViewportView(JTBulto);

		// Tabla stock
		JTStock.setModel(modeloTabla1());
		JTStock.setDefaultRenderer(Object.class, new RenderTable());
		JTStock.setRowHeight(50);

		JTableHeader HeaderAditivo = JTStock.getTableHeader();
		HeaderAditivo.setFont(new Font("Arial", Font.BOLD, 20));
		HeaderAditivo.setBackground(new Color(184, 217, 65));
		HeaderAditivo.setForeground(Color.black);

		JTStock.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		JTStock.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);// Para centrar los elementos dentro de
																				// las columnas
		JTStock.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

		TableColumn columna1 = this.JTStock.getColumn("Producto");
		TableColumn columna2 = this.JTStock.getColumn("Cantidad");
		TableColumn columna3 = this.JTStock.getColumn("Litros por Kg");
		TableColumn columna4 = this.JTStock.getColumn("Opciones");

		columna1.setPreferredWidth(60);
		columna2.setPreferredWidth(50);
		columna3.setPreferredWidth(80);
		columna4.setPreferredWidth(100);

		// Tabla bulto
		JTBulto.setModel(modeloTabla2());
		JTBulto.setDefaultRenderer(Object.class, new RenderTable());
		JTBulto.setRowHeight(50);

		JTableHeader HeaderBulto = JTBulto.getTableHeader();
		HeaderBulto.setFont(new Font("Arial", Font.BOLD, 20));
		HeaderBulto.setBackground(new Color(184, 217, 65));
		HeaderBulto.setForeground(Color.black);

		JTBulto.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		JTBulto.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);// Para centrar los elementos dentro de
																				// las columnas
		JTBulto.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

		TableColumn columna1Tabla2 = this.JTBulto.getColumn("Variación");
		TableColumn columna2Tabla2 = this.JTBulto.getColumn("Cantidad");
		TableColumn columna3Tabla2 = this.JTBulto.getColumn("Precio Unitario");
		TableColumn columna4Tabla2 = this.JTBulto.getColumn("Opciones");

		columna1Tabla2.setPreferredWidth(110);
		columna2Tabla2.setPreferredWidth(110);
		columna3Tabla2.setPreferredWidth(150);
		columna4Tabla2.setPreferredWidth(160);

		JPAreaFondo.add(JPEspacioInfoAbono);
		JPEspacioInfoAbono.add(JLInfoAbono);
		JPEspacioInfoAbono.add(JBAgregarAbono);
		JPEspacioInfoAbono.add(JLInfoMateriaOrganica);
		JPEspacioInfoAbono.add(JBAgregarMateriaOrganica);
		JPAreaFondo.add(JSPTabla);
		JPAreaFondo.add(JBCrearAditivo);
		JPAreaFondo.add(JLTablaStock);
		JPMenuBotonesTabla.add(JBModificarAditivo);
		JPMenuBotonesTabla.add(JBAgregarAditivo);
		JPMenuBotonesTabla.add(JBBorrarAditivo);
		JPAreaFondo.add(JSPTabla2);
		JPAreaFondo.add(JBCrearBulto);
		JPAreaFondo.add(JLTablaBulto);
		JPMenuBotonesTabla2.add(JBModificarBulto);
		JPMenuBotonesTabla2.add(JBAgregarBulto);
		JPMenuBotonesTabla2.add(JBBorrarBulto);
		add(JPMenuSuperior);
		add(JPAreaFondo);
		add(JSPGeneral);

		setVisible(true);
	}

	private TableModel modeloTabla1() {
		DefaultTableModel modelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String encabezados[] = { "Producto", "Cantidad", "Litros por Kg", "Opciones" };
		modelo.setColumnIdentifiers(encabezados);
		Object[] fila;

		for (Aditivo a : new DAOAditivos().ObtenerDatosAditivos()) {

			fila = new Object[4];
			fila[0] = a.getNombre();
			fila[1] = a.getCantidad()+" "+a.getVariacion();
			fila[2] = a.getNumLitrosPorKg();
			fila[3] = JPMenuBotonesTabla;
			modelo.addRow(fila);

		}
		return modelo;
	}

	private TableModel modeloTabla2() {
		DefaultTableModel modelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String encabezados[] = { "Variación", "Cantidad", "Precio Unitario", "Opciones" };
		modelo.setColumnIdentifiers(encabezados);
		Object[] fila;

		for (Bulto b : new DAOBulto().ObtenerDatosBultos()) {

			fila = new Object[4];
			fila[0] = b.getVariacionPeso();
			fila[1] = b.getStock();
			fila[2] = "$" + b.getPrecioUnitario();
			fila[3] = JPMenuBotonesTabla2;
			modelo.addRow(fila);

		}
		return modelo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(JBCrearAditivo)) {
			this.setVisible(false);
			GUICrearAditivo c = new GUICrearAditivo();
			c.CrearAditivo();
		}
		if (o.equals(JBCrearBulto)) {
			this.setVisible(false);
			GUICrearBulto c = new GUICrearBulto();
			c.CrearBulto();
		}
		if (o.equals(JBAgregarAbono)) {
			this.setVisible(false);
			GUIProducirAbono c;
			try {
				c = new GUIProducirAbono();
				c.ProducirAbono();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		if (o.equals(JBAgregarMateriaOrganica)) {
			this.setVisible(false);
			GUIAgregarResiduosOrganicos c = new GUIAgregarResiduosOrganicos();
			c.AgregarMateriaOrganica();
		}
	}
}
