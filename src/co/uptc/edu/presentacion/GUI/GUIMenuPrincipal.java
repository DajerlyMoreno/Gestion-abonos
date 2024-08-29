package co.uptc.edu.presentacion.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUIMenuPrincipal extends JFrame implements ActionListener{
	
	private JButton JBClientes;
	private JButton JBStock;
	private JButton JBVentas;
	private JButton JBSalir;
	private JPanel JPLogo;
	private JPanel JPAreaBotones;
	
	private ImageIcon imagen;
	private JLabel label;
	
	private ImageIcon ImgCliente;
	private ImageIcon ImgStock;
	private ImageIcon ImgVentas;
	
	public static final Color azulBotones= new Color(22, 85, 140);
	public static final Color verdeBotonSalir= new Color(184,217,65);

	
	
	
	
	public GUIMenuPrincipal() {
		 
		
		
		ImgCliente= new ImageIcon("Recursos/iconos/cliente.png");
		ImgStock= new ImageIcon("Recursos/iconos/stock.png");
		ImgVentas= new ImageIcon("Recursos/iconos/ventas.png");
		
		
		JBClientes= new JButton(ImgCliente);
		JBStock= new JButton(ImgStock);
		JBVentas= new JButton(ImgVentas);
		JBSalir= new JButton("salir");
		JPLogo= new JPanel();
		JPAreaBotones= new JPanel();
		
		imagen= new ImageIcon("Recursos/iconos/logo.jpg");
		label = new JLabel(imagen);

		
		
		setLayout (null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(800,700);
		
		setLocationRelativeTo(null);
		setTitle("Menu Principal");
		setResizable(false);
		
	}
	
	public void Menu() {
		
		
		
		JPLogo.setBounds(0, 0, 800, 180);
		JPAreaBotones.setBounds(0, 180, 1000, 1000-JPLogo.getHeight());
		JPLogo.setBackground(Color.white);
		JPAreaBotones.setBackground(Color.white);
		JPAreaBotones.setLayout(null);
		
		JBClientes.setBounds(20, 100, 200, 200);
		JBClientes.setBackground(azulBotones);
		JBClientes.setToolTipText("Clientes");
		JBClientes.addActionListener(this);
		
		
		JBStock.setBounds(290, 100, 200, 200);		
		JBStock.setBackground(azulBotones);
		JBStock.setToolTipText("Stock");
		JBStock.addActionListener(this);
		
		JBVentas.setBounds(560, 100, 200, 200);
		JBVentas.setBackground(azulBotones);
		JBVentas.setToolTipText("Ventas");
		JBVentas.addActionListener(this);
		
		JBSalir.setBounds(290, 350, 200, 60);
		JBSalir.setBackground(verdeBotonSalir);
		JBSalir.setFont(new Font("Arial", Font.PLAIN, 20));
		JBSalir.setForeground(Color.white);
		JBSalir.addActionListener(this);
		
		Image image= imagen.getImage();
		
		int anchoOriginal = image.getWidth(null);
		int altoOriginal = image.getHeight(null);
		int altoEscalado = 200; // Establece el ancho deseado para la imagen
		int anchoEscalado = (anchoOriginal * altoEscalado) / altoOriginal; // Calcula el ancho proporcional
		
		Image nuevaImagen=image.getScaledInstance(anchoEscalado, altoEscalado, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imagenRedimensionada = new ImageIcon(nuevaImagen);
		label= new JLabel(imagenRedimensionada);
		
		

		
		add(JPLogo);
		JPLogo.add(label);
		add(JPAreaBotones);
		JPAreaBotones.add(JBClientes);
		JPAreaBotones.add(JBStock);
		JPAreaBotones.add(JBVentas);
		
		JPAreaBotones.add(JBSalir);
		
		setLocationRelativeTo(null);
        setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(JBSalir)) {
			JOptionPane.showMessageDialog(null, "Adios");
			System.exit(0);
	
		}		
		if (e.getSource().equals(JBStock)) {			
				this.setVisible(false);
				GUITablaStock ts;
				try {
					ts = new GUITablaStock();
					ts.TablaStock();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
							
		}
		if (e.getSource().equals(JBClientes)) {			
			this.setVisible(false);
			try {
				GUIClientes c = new GUIClientes();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		}
		if (e.getSource().equals(JBVentas)) {			
			this.setVisible(false);
			
			new GUIVenta();
		}
		
	}
	
	

}