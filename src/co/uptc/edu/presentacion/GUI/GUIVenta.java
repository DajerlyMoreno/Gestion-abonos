package co.uptc.edu.presentacion.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import co.uptc.edu.logica.control.OperacionesVenta;
import co.uptc.edu.logica.modelo.Cliente;
import co.uptc.edu.persistencia.DAOclientes;
import co.uptc.edu.persistencia.utilidades.Archivo;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GUIVenta extends JFrame implements ActionListener{
	
	private JPanel JPAreaMenu;
	private JPanel JPAreaVentas;
	private JPanel JPbotonTabla;
	private JLabel JLTitulo;
	private JButton JBVerFactura;
	private JButton JBAgregarVenta;
	private JTable JTDatosVentas;
	private JScrollPane JSPTabla;
	private DefaultTableCellRenderer centerRenderer;
	private ImageIcon imgVerFactura;
	
	
	public GUIVenta()  {
		imgVerFactura=new ImageIcon("Recursos/iconos/lupa.png");
		JPAreaMenu = new GUIMenuSuperior(this).MostrarMenu();;
		JPAreaVentas = new JPanel();
		JPbotonTabla = new JPanel();
		JLTitulo = new JLabel();
		JBAgregarVenta = new JButton();
		JBVerFactura = new JButton(imgVerFactura);
		JTDatosVentas = new JTable();
		JSPTabla = new JScrollPane();
		centerRenderer = new DefaultTableCellRenderer();
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		setSize(800, 700);
		setLocationRelativeTo(null);
		setTitle("VENTAS");
		setResizable(false);
		initComponents();
	}
	
	private void initComponents()  {
		JPAreaMenu.setBackground(new Color(242, 242, 242));
		JPAreaMenu.setBounds(0, 70, 1000, 930);
		JPanel menu = new GUIMenuSuperior(this).MostrarMenu();
		menu.setLayout(new GridLayout(1,4));
		int margenMenu = 10;
		Border bordeMenu = BorderFactory.createEmptyBorder(margenMenu+5, margenMenu*10, margenMenu+5, margenMenu*10);
		menu.setPreferredSize(new Dimension(menu.getWidth(), 70));
        menu.setBorder(bordeMenu);
		JPAreaMenu = menu;
		JPAreaVentas.setBackground(Color.white);
		
		JLTitulo.setText("Ventas");
		Font font = new Font("Arial", Font.BOLD, 40);
		Color color = new Color(22, 85, 140);
		JLTitulo.setFont(font);
		JLTitulo.setForeground(color);
		
		JSPTabla.setViewportView(JTDatosVentas);
		
		JPAreaVentas.setLayout(new BoxLayout(JPAreaVentas, BoxLayout.Y_AXIS));
		JPAreaVentas.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio en blanco encima del JLabel
		JPAreaVentas.add(JLTitulo);
		JPAreaVentas.add(Box.createRigidArea(new Dimension(0, 20)));
		//////////////////////////////////////////////////////
		JTDatosVentas.addMouseListener(new MouseListener() {
			
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
				int columna = JTDatosVentas.getColumnModel().getColumnIndexAtX(e.getX()); // Columna en la que se hizo clic
			    int fila = e.getY() / JTDatosVentas.getRowHeight(); // Fila en la que se hizo clic
			    if (columna==4) {
			    	int rowIndex= JTDatosVentas.getSelectedRow();
			    	if(rowIndex!= -1) {
			    		String id=   modeloTabla().getValueAt(rowIndex, 0).toString();
			    		String fecha= (String) modeloTabla().getValueAt(rowIndex, 1);
			    		String nomCli= (String) modeloTabla().getValueAt(rowIndex, 2);
			    		String valor= (String) modeloTabla().getValueAt(rowIndex, 3);
			    		GUIFactura f= new GUIFactura(id,fecha, nomCli, valor);
				    	f.Factura();
				    	setVisible(false);
			    	}
			    }
			    
			    //if (columna == 4 && fila < JTDatosVentas.getRowCount()) {
			    	//GUIFactura f= new GUIFactura(id, nomCli, valor);
			    	//f.Factura();
			    	//JOptionPane.showMessageDialog(null,"l");
			        
			    //}
			}
		});
		
		//////////////////////////////////////////////////////
		
		JTDatosVentas.setModel(modeloTabla());
		JTDatosVentas.setRowHeight(50);
		JTDatosVentas.setDefaultRenderer(Object.class, new RenderTable());
		
		JTableHeader header = JTDatosVentas.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 20));
		header.setBackground(new Color(184,217,65));
		header.setForeground(Color.black);
		
		JTDatosVentas.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		JTDatosVentas.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		JTDatosVentas.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		
		TableColumn columna1 = this.JTDatosVentas.getColumn("#");
		TableColumn columna2 = this.JTDatosVentas.getColumn("Fecha entrega");
		TableColumn columna3 = this.JTDatosVentas.getColumn("Nombre cliente");
		TableColumn columna4 = this.JTDatosVentas.getColumn("Valor");
		TableColumn columna5 = this.JTDatosVentas.getColumn("Opciones");
		
		
		columna1.setPreferredWidth(50);
		columna2.setPreferredWidth(100);
		columna3.setPreferredWidth(100);
		columna4.setPreferredWidth(50);
		columna5.setPreferredWidth(70);
		
		JPAreaVentas.add(JSPTabla);
		
		
		
		JBVerFactura.setBackground(Color.white);
		JBVerFactura.setBounds(0, 0, 70, 50);
		JBVerFactura.setBorder(null);
		JBVerFactura.setLayout(null);
		JBVerFactura.addActionListener(this);
        
        
		
        FlowLayout layourbotones = new FlowLayout();
        layourbotones.setHgap(20);
        JPbotonTabla.setLayout(layourbotones);
        JPbotonTabla.setBackground(Color.white);        
		JPbotonTabla.add(JBVerFactura);
		
		JBAgregarVenta.setText("Nueva Venta");
		JBAgregarVenta.setBackground(Color.white);
		JBAgregarVenta.setBorder(BorderFactory.createLineBorder(new Color(31, 90, 166),2));
		JBAgregarVenta.setForeground(new Color(31, 90, 166));
		JBAgregarVenta.setPreferredSize(new Dimension(100,50));
		JBAgregarVenta.addActionListener(this);
		
		JPanel boton = new JPanel();
		boton.setLayout(new BorderLayout());
		boton.setBackground(Color.white);
		boton.add(JBAgregarVenta, BorderLayout.EAST);
		
		JPAreaVentas.add(boton);
		int margen = 110;
		Border borde = BorderFactory.createEmptyBorder(margen, 0, margen, 0);
        JPAreaVentas.setBorder(borde);
		
		JPanel JPEspacioIzq = new JPanel();
	    JPEspacioIzq.setPreferredSize(new Dimension(50, JPAreaVentas.getHeight()));
	    JPEspacioIzq.setBackground(Color.white);
	    
	    JPanel JPEspacioDer = new JPanel();
	    JPEspacioDer.setPreferredSize(new Dimension(50, JPAreaVentas.getHeight()));
	    JPEspacioDer.setBackground(Color.white);

	    
		
		add(JPAreaMenu, BorderLayout.NORTH);
	    add(JPEspacioIzq, BorderLayout.WEST);
		add(JPAreaVentas, BorderLayout.CENTER);
		add(JPEspacioDer, BorderLayout.EAST);
		//add(JPEspacioInf, BorderLayout.SOUTH);

		setVisible(true);
	
	}
	
	private TableModel modeloTabla() {
		DefaultTableModel modelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {//Este metodo es para evitar que el usuario edite las celdas de la tabla
				return false;
			}
		};
		
		String[] encabezados = { "#", "Fecha entrega", "Nombre cliente", "Valor","Opciones"};
		modelo.setColumnIdentifiers(encabezados);
		
		Object[]fila;
		
		for(Cliente cli: new DAOclientes().ObtenerClientes()) {
			fila = new Object[5];
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date fecha = new Date();
			String fechaFormateada = sdf.format(fecha);
			fila[0] = cli.getNumeroVentas();
			fila[1] = fechaFormateada;
			fila[2] = cli.getNombre();
			fila[3] = "$$$$$";
			fila[4] = JPbotonTabla;
			
			modelo.addRow(fila);
		}
		return modelo;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == JBAgregarVenta) {
			setVisible(false);
			//necesita telefonos y bultos
			
			new GUIFormVentas(new OperacionesVenta().obtenerNumTelefonicos(), new OperacionesVenta().ObtenerVariacion());
			
		}
		if(e.getSource() == JBVerFactura) {
			JOptionPane.showMessageDialog(null, "VER FACTURA");
		}
		
	}
}