package co.uptc.edu.presentacion.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUIMenuSuperior extends JFrame implements ActionListener {

	private JPanel JPMenu;
	private JFrame JFOrigen;
	private JLabel JLImagenLogo;
	private ImageIcon imageLogo;
	private ImageIcon imgClientes;
	private ImageIcon imgStock;
	private ImageIcon imgVentas;
	private ImageIcon imgSalir;


	private JButton JBClientes;
	private JButton JBStock;
	private JButton JBVentas;
	private JButton JBSalir;

	public GUIMenuSuperior(JFrame origen) {
		JPMenu = new JPanel();
		JFOrigen = origen;
		
		
		imgClientes= new ImageIcon("Recursos/iconos/MSclientes.jpg");
		imgStock= new ImageIcon("Recursos/iconos/MSstock.jpg");
		imgVentas= new ImageIcon("Recursos/iconos/MSventas.jpg");
		imgSalir= new ImageIcon("Recursos/iconos/MSsalir.jpg");

		JBClientes = new JButton(imgClientes);
		JBStock = new JButton(imgStock);
		JBVentas = new JButton(imgVentas);
		JBSalir = new JButton(imgSalir);
		// JBAgregarPostre.addActionListener(this);
		// JBMostrarPostres.addActionListener(this);
		//imageLogo = new ImageIcon("Recursos/iconos/logoCircular.jpg");
		//JLImagenLogo = new JLabel(imageLogo);
		

	}

	

	public JPanel MostrarMenu() {
		
		
		JPMenu.setBounds(5, 0, 1000, 70);		
		JPMenu.setBackground(new Color(242, 242, 242));
		JPMenu.setLayout(null);

		JBClientes.setBounds(100, 15, 150, 40);
		JBClientes.setBackground(new Color(22, 85, 140));
		JBClientes.setForeground(Color.white);
		JBClientes.setText("Clientes");
		JBClientes.addActionListener(this);

		JBStock.setBounds(250, 15, 150, 40);
		JBStock.setBackground(new Color(22, 85, 140));
		JBStock.setForeground(Color.white);
		JBStock.setText("Stock");
		JBStock.addActionListener(this);

		JBVentas.setBounds(400, 15, 150, 40);
		JBVentas.setBackground(new Color(22, 85, 140));
		JBVentas.setForeground(Color.white);
		JBVentas.setText("Ventas");
		JBVentas.addActionListener(this);

		JBSalir.setBounds(550, 15, 150, 40);
		JBSalir.setBackground(new Color(22, 85, 140));
		JBSalir.setForeground(Color.white);
		JBSalir.setText("Salir");
		JBSalir.addActionListener(this);
		
		add(JPMenu);
		//JPMenu.add(JLImagenLogo);
		JPMenu.add(JBClientes);
		JPMenu.add(JBStock);
		JPMenu.add(JBVentas);
		JPMenu.add(JBSalir);
		//JPMenu.add(JLImagenLogo);

		return JPMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(JBSalir)) {
			JOptionPane.showMessageDialog(null, "Adios");
			System.exit(0);
	
		}
		
		if (e.getSource().equals(JBStock)) {
			if (JFOrigen.getTitle().equals("Stock")) {
				JOptionPane.showMessageDialog(null, "Es la ventana actual");
			}else {
				JFOrigen.setVisible(false);
				GUITablaStock ts;
				try {
					ts = new GUITablaStock();
					ts.TablaStock();	
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
							
			}			
		}
		if (e.getSource().equals(JBClientes)) {
			if (JFOrigen.getTitle().equals("Clientes")) {
				JOptionPane.showMessageDialog(null, "Es la ventana actual");
			}else {
				JFOrigen.setVisible(false);
				try {
					GUIClientes c = new GUIClientes();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}			
		}
		if (e.getSource().equals(JBVentas)) {
			if (JFOrigen.getTitle().equals("Ventas")) {
				JOptionPane.showMessageDialog(null, "Es la ventana actual");
			}else {
				JFOrigen.setVisible(false);
				new GUIVenta();			
			}			
		}
		
	}
}