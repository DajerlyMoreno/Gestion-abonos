package co.uptc.edu.presentacion.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import co.uptc.edu.logica.control.OperacionesCliente;
import co.uptc.edu.logica.control.OperacionesVenta;
import co.uptc.edu.logica.modelo.Cliente;
import co.uptc.edu.persistencia.DAOclientes;

public class GUIClientes extends JFrame implements ActionListener {
	
	private JPanel JPAreaMenu;
	private JPanel JPAreaClientes;
	private JPanel JPbotonesTabla;
	private JLabel JLTitulo;
	private JButton JBAgregarCliente;
	private JButton JBEditarCliente;
	private JButton JBBorrarCliente;
	private JButton JBAgregarVenta;
	private JTable JTDatosCliente;
	private JScrollPane JSPTabla;
	private DefaultTableCellRenderer centerRenderer;
	
	public GUIClientes() throws IOException {
		JPAreaMenu = new JPanel();
		JPAreaClientes = new JPanel();
		JPbotonesTabla = new JPanel();
		JLTitulo = new JLabel();
		JBAgregarCliente = new JButton();
		JBEditarCliente = new JButton("");
		JBBorrarCliente = new JButton("");
		JBAgregarVenta = new JButton("");
		JTDatosCliente = new JTable();
		JSPTabla = new JScrollPane();
		centerRenderer = new DefaultTableCellRenderer();
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		setSize(800, 700);
		setLocationRelativeTo(null);
		setTitle("CLIENTES");
		setResizable(true);
		initComponents();
	}

	private void initComponents() throws IOException {
		JPanel menu = new GUIMenuSuperior(this).MostrarMenu();
		menu.setLayout(new GridLayout(1,4));
		int margenMenu = 10;
		Border bordeMenu = BorderFactory.createEmptyBorder(margenMenu+5, margenMenu*10, margenMenu+5, margenMenu*10);
		menu.setPreferredSize(new Dimension(menu.getWidth(), 70));
        menu.setBorder(bordeMenu);
		JPAreaMenu = menu;
		JPAreaClientes.setBackground(Color.white);
		
		JLTitulo.setText("Clientes");
		Font font = new Font("Arial", Font.BOLD, 40);
		Color color = new Color(22, 85, 140);
		JLTitulo.setFont(font);
		JLTitulo.setForeground(color);
		
		JSPTabla.setViewportView(JTDatosCliente);
		
		JTDatosCliente.addMouseListener(new MouseListener() {
			
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
				int columna = JTDatosCliente.getColumnModel().getColumnIndexAtX(e.getX()); // Columna en la que se hizo clic
			    int fila = e.getY() / JTDatosCliente.getRowHeight(); // Fila en la que se hizo clic
			    
			    if (columna == 3 && fila < JTDatosCliente.getRowCount()) {
			        if (e.getX() >= JTDatosCliente.getCellRect(fila, columna, true).x + 30
			                && e.getX() <= JTDatosCliente.getCellRect(fila, columna, true).x + 30 + JBEditarCliente.getWidth()) {
			        	setVisible(false);
			        	new GUIeditarCliente(JTDatosCliente.getValueAt(fila, 0) + "", JTDatosCliente.getValueAt(fila, 1) + "", fila);
			        
			        } else if (e.getX() >= JTDatosCliente.getCellRect(fila, columna, true).x + 50 + JBEditarCliente.getWidth()
			                && e.getX() <= JTDatosCliente.getCellRect(fila, columna, true).x + 50 + JBEditarCliente.getWidth()*2) {
			        	setVisible(false);
						//necesita telefonos y bultos
						
						new GUIFormVentas(new OperacionesVenta().obtenerNumTelefonicos(), new OperacionesVenta().ObtenerVariacion());
			        
			        } else if (e.getX() >= JTDatosCliente.getCellRect(fila, columna, true).x + 70 + JBEditarCliente.getWidth()*2
			                && e.getX() <= JTDatosCliente.getCellRect(fila, columna, true).x + 70 + JBEditarCliente.getWidth()*3) {
			        	setVisible(false);
			        	new OperacionesCliente().eliminarCliente(fila);
			        	try {
							new GUIClientes();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			        }
			    }
			   
			}
		});
		
		JTDatosCliente.setModel(modeloTabla());
		JTDatosCliente.setRowHeight(50);
		JTDatosCliente.setDefaultRenderer(Object.class, new RenderTable());
		
		JTableHeader header = JTDatosCliente.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 20));
		header.setBackground(new Color(184,217,65));
		header.setForeground(Color.black);
		
		JTDatosCliente.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		JTDatosCliente.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		JTDatosCliente.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		
		TableColumn columna1 = this.JTDatosCliente.getColumn("Nombre");
		TableColumn columna2 = this.JTDatosCliente.getColumn("Telefono");
		TableColumn columna3 = this.JTDatosCliente.getColumn("Compras");
		TableColumn columna4 = this.JTDatosCliente.getColumn("Opciones");
	
		columna1.setPreferredWidth(150);
		columna2.setPreferredWidth(150);
		columna3.setPreferredWidth(100);
		columna4.setPreferredWidth(200);
		
		File fileneditar = new File("Recursos/iconos/editar.png");
        Image imgeditar = ImageIO.read(fileneditar);
        JBEditarCliente.setIcon(new ImageIcon(imgeditar));
        JBEditarCliente.setBackground(Color.white);
        JBEditarCliente.setBorder(null);
        
        File fileneliminar = new File("Recursos/iconos/eliminar.png");
        Image imgeliminar = ImageIO.read(fileneliminar);
        JBBorrarCliente.setIcon(new ImageIcon(imgeliminar));
        JBBorrarCliente.setBackground(Color.white);
        JBBorrarCliente.setBorder(null);
        
        File filenventa = new File("Recursos/iconos/venta.png");
        Image imgeventa = ImageIO.read(filenventa);
        JBAgregarVenta.setIcon(new ImageIcon(imgeventa));
        JBAgregarVenta.setBackground(Color.white);
        JBAgregarVenta.setBorder(null);
		
        FlowLayout layourbotones = new FlowLayout();
        layourbotones.setHgap(20);
	    JPbotonesTabla.setLayout(layourbotones);
	    JPbotonesTabla.setBackground(Color.white);
		JPbotonesTabla.add(JBEditarCliente);
		JPbotonesTabla.add(JBAgregarVenta);
		JPbotonesTabla.add(JBBorrarCliente);
		
		JPanel JPtitulo = new JPanel();
		JPtitulo.setLayout(new BorderLayout());
		JPtitulo.setBackground(Color.white);
		JPtitulo.add(JLTitulo, BorderLayout.WEST);
		
		Component com = Box.createRigidArea(new Dimension(0, 20));
		JPAreaClientes.setLayout(new BoxLayout(JPAreaClientes, BoxLayout.Y_AXIS));
		JPAreaClientes.setAlignmentX(Component.LEFT_ALIGNMENT);
		JPAreaClientes.add(com); 
		JPAreaClientes.add(JPtitulo);
		JPAreaClientes.add(com);
		JPAreaClientes.add(JSPTabla);
		JPAreaClientes.add(Box.createRigidArea(new Dimension(0, 5))); 
		
		JBAgregarCliente.setText("Nuevo Cliente");
		JBAgregarCliente.setBackground(Color.white);
		JBAgregarCliente.setBorder(BorderFactory.createLineBorder(new Color(31, 90, 166),2));
		JBAgregarCliente.setForeground(new Color(31, 90, 166));
		JBAgregarCliente.setPreferredSize(new Dimension(100,50));
		JBAgregarCliente.addActionListener(this);
		
		JPanel boton = new JPanel();
		boton.setLayout(new BorderLayout());
		boton.setBackground(Color.white);
		boton.add(JBAgregarCliente, BorderLayout.EAST);
		
		JPAreaClientes.add(boton);
		
		int margen = 110;
		Border borde = BorderFactory.createEmptyBorder(margen, 0, margen, 0);
        JPAreaClientes.setBorder(borde);
		
		JPanel JPEspacioIzq = new JPanel();
	    JPEspacioIzq.setPreferredSize(new Dimension(50, JPAreaClientes.getHeight()));
	    JPEspacioIzq.setBackground(Color.white);
	    
	    JPanel JPEspacioDer = new JPanel();
	    JPEspacioDer.setPreferredSize(new Dimension(50, JPAreaClientes.getHeight()));
	    JPEspacioDer.setBackground(Color.white);
		
		add(JPAreaMenu, BorderLayout.NORTH);
	    add(JPEspacioIzq, BorderLayout.WEST);
		add(JPAreaClientes, BorderLayout.CENTER);
		add(JPEspacioDer, BorderLayout.EAST);

		setVisible(true);
	}

	private TableModel modeloTabla() {
		DefaultTableModel modelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {//Este metodo es para evitar que el usuario edite las celdas de la tabla
				return false;
			}
		};
		
		String[] encabezados = {"Nombre", "Telefono", "Compras", "Opciones"};
		modelo.setColumnIdentifiers(encabezados);
		
		Object[]fila;
		
		for(Cliente cli: new DAOclientes().ObtenerClientes()) {
			fila = new Object[4];
			fila[0] = cli.getNombre();
			fila[1] = cli.getTelefono();
			fila[2] = cli.getNumeroVentas();
			fila[3] = JPbotonesTabla;
			
			modelo.addRow(fila);
		}
		return modelo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == JBAgregarCliente) {
			new GUIcrearCliente();
			this.setVisible(false);
		}
		
	}
	
}