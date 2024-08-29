package co.uptc.edu.presentacion.GUI;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import co.uptc.edu.logica.modelo.Aditivo;
import co.uptc.edu.persistencia.DAOAditivos;
import co.uptc.edu.logica.modelo.Factura;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GUIFactura extends JFrame implements ActionListener {
	private JPanel JPAreaFactura;
	private JLabel JLNumFactura;
	private JLabel JLCliente;	
	private JLabel JLFechaEntrega;
	private JLabel JLLinea;
	private JLabel JLLinea2;
	private JLabel JLLinea3;
	private JLabel JLValorTotal;
	private JTable JTFactura;
	private DefaultTableCellRenderer centerRenderer;
	private JScrollPane JScroll;

	private JButton JBOk;
	
	
	public  GUIFactura(String id,String fecha ,String nomCli, String valor) {
		// TODO Auto-generated constructor stub
		JPAreaFactura=new JPanel();
		JLNumFactura = new JLabel("------------Factura #:"+id+"---------------");
		JLCliente= new JLabel("Cliente:               "+nomCli);
		JLFechaEntrega= new JLabel("Fecha entrega:      "+fecha);
		JLLinea= new JLabel("-----------------------------------------");
		JLLinea2= new JLabel("-----------------------------------------");
		JLLinea3= new JLabel("-----------------------------------------");
		
		JLValorTotal= new JLabel("Valor total:                "+valor);
		JTFactura= new JTable();
		JScroll= new JScrollPane();
		centerRenderer = new DefaultTableCellRenderer();
		JBOk= new JButton("OK");
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		setSize(400, 500);
		setLocationRelativeTo(null);
		setTitle("FACTURA");
		setResizable(false);
		//initComponents();
		
	}
	public void Factura() {
		JPAreaFactura.setBounds(0, 0, 375, 490);
		JPAreaFactura.setBackground(new Color(242, 242, 242));
		JPAreaFactura.setLayout(null);
		
		JLNumFactura.setBounds(10, 10, 370, 20);
		JLNumFactura.setFont(new Font("Verdana", Font.PLAIN, 20));
		JLNumFactura.setForeground(Color.black);
		
		JLCliente.setBounds(15, 30, 370, 20);
		JLCliente.setFont(new Font("Verdana", Font.PLAIN, 18));
		JLCliente.setForeground(Color.black);
		
		JLFechaEntrega.setBounds(15, 60, 370, 20);
		JLFechaEntrega.setFont(new Font("Verdana", Font.PLAIN, 18));
		JLFechaEntrega.setForeground(Color.black);
		
		JLLinea.setBounds(15, 75, 370, 20);
		JLLinea.setFont(new Font("Verdana", Font.PLAIN, 18));
		JLLinea.setForeground(Color.black);
		
		JLLinea2.setBounds(15, 330, 370, 20);
		JLLinea2.setFont(new Font("Verdana", Font.PLAIN, 18));
		JLLinea2.setForeground(Color.black);
		
		JLValorTotal.setBounds(15, 350, 370, 20);
		JLValorTotal.setFont(new Font("Verdana", Font.PLAIN, 18));
		JLValorTotal.setForeground(Color.black);
		
		JLLinea3.setBounds(15, 370, 370, 20);
		JLLinea3.setFont(new Font("Verdana", Font.PLAIN, 18));
		JLLinea3.setForeground(Color.black);
		
		JBOk.setBounds(150, 390, 90, 40);
		JBOk.setForeground(Color.white);
		JBOk.setBackground(new Color(168, 191, 86));
		JBOk.addActionListener(this);
		
		JScroll.setBounds(0, 100, 390, 143);
		JScroll.setViewportView(JTFactura);
		
		JTFactura.setModel(modeloTabla());
		JTFactura.setDefaultRenderer(Object.class, new RenderTable());
		JTFactura.setRowHeight(40);

		JTFactura.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		JTFactura.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);// Para centrar los elementos dentro de
																				// las columnas
		JTFactura.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

		TableColumn columna1 = this.JTFactura.getColumn("Variacion");
		TableColumn columna2 = this.JTFactura.getColumn("Cantidad");
		TableColumn columna3 = this.JTFactura.getColumn("Precio Unitario");

		columna1.setPreferredWidth(50);
		columna2.setPreferredWidth(50);
		columna3.setPreferredWidth(50);
		
		
		
		JPAreaFactura.add(JLNumFactura);
		JPAreaFactura.add(JLCliente);
		JPAreaFactura.add(JLFechaEntrega);
		JPAreaFactura.add(JLLinea);
		JPAreaFactura.add(JLLinea2);
		JPAreaFactura.add(JLLinea3);
		//JPAreaFactura.add(JTFactura);
		JPAreaFactura.add(JScroll);
		JPAreaFactura.add(JBOk);
		JPAreaFactura.add(JLValorTotal);
		add(JPAreaFactura);
		
		setVisible(true);
	}
	
	private TableModel modeloTabla() {
		DefaultTableModel modelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String encabezados[] = { "Variacion", "Cantidad", "Precio Unitario" };
		modelo.setColumnIdentifiers(encabezados);
		Object[] fila;

		//for (Factura f : ) {

			fila = new Object[3];
			fila[0] = "30kg";
			fila[1] = "5";
			fila[2] = "$"+"30000";
			modelo.addRow(fila);

		//}
		return modelo;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(JBOk)) {
			this.setVisible(false);
			new GUIVenta();
		}
	}
	
	
}