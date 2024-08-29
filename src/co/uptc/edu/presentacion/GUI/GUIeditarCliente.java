package co.uptc.edu.presentacion.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import co.uptc.edu.logica.control.OperacionesCliente;

public class GUIeditarCliente extends JFrame implements ActionListener{
	
	private JPanel JPareaFormulario;
	private JLabel JLtitulo, JLnombre, JLtelefono;
	private JTextField JTnombre, JTtelefono;
	private JButton JBguardar, JBcancelar;
	private String nombre, telefono;
	private int indice;
	
	public GUIeditarCliente(String nombre, String telefono, int indice) {
		super("Editar Cliente");
		
		this.nombre = nombre;
		this.telefono = telefono;
		this.indice = indice;
		
		JPareaFormulario = new JPanel();
		JLtitulo = new JLabel("Editar cliente");
		JLnombre = new JLabel("Nombre:");
		JLtelefono = new JLabel("Teléfono:");
        JTnombre = new JTextField(nombre);
        JTtelefono = new JTextField(telefono);
        JBguardar = new JButton("Modificar");
        JBcancelar = new JButton("Cancelar");
		
        setSize(400, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBackground(new Color(242, 242, 242));
        setLocationRelativeTo(null); 
        initComponents();
        
	}
	
	private void initComponents() {
		GridLayout layout = new GridLayout(8, 1);
		layout.setHgap(10); // espacio horizontal entre columnas
		layout.setVgap(10); // espacio vertical entre filas
		
        JPareaFormulario.setLayout(layout);

		JLtitulo.setFont(new Font("Arial", Font.BOLD, 40));
		JLtitulo.setForeground(new Color(22, 85, 140));
		JLtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        
		Font font = new Font("Fedoka One", Font.BOLD, 20);
		Color color = Color.black;
		
        JLnombre.setFont(font);
        JLtelefono.setFont(font);
        
        JPareaFormulario.add(JLtitulo);
        JPareaFormulario.add(new JLabel(""));
        JPareaFormulario.add(JLnombre);
        JPareaFormulario.add(JTnombre);
        JPareaFormulario.add(JLtelefono);
        JPareaFormulario.add(JTtelefono);
        
        JBcancelar.setBackground(new Color(168, 191, 86));
        JBcancelar.setForeground(Color.white); 
        JBcancelar.addActionListener(this);
        
        JBguardar.setBackground(new Color(22, 85, 140));
        JBguardar.setForeground(Color.white);
        JBguardar.addActionListener(this);
        
        int margenbotones = 30;
        Border bordebotones = BorderFactory.createEmptyBorder(margenbotones, margenbotones, margenbotones, margenbotones);
        
        GridLayout layoutBotones = new GridLayout(1, 2);
		layoutBotones.setHgap(20);
        JPanel botones = new JPanel();
        botones.setLayout(layoutBotones);
        botones.setBorder(bordebotones);
        botones.setBorder(null);
        botones.add(JBcancelar);
        botones.add(JBguardar);
        
        JPareaFormulario.add(new JLabel(""));
        JPareaFormulario.add(botones);
        
        int margen = 50;
        Border borde = BorderFactory.createEmptyBorder(margen, margen, margen, margen);
        JPareaFormulario.setBorder(borde);
        
        add(JPareaFormulario);
        
        setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if((JButton)e.getSource() == JBcancelar){
			setVisible(false);
			try {
				new GUIClientes();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else if((JButton)e.getSource() == JBguardar) {
			
			if(JTnombre.getText().length() != 0 && JTtelefono.getText().length() != 0) {
				this.nombre = JTnombre.getText();
				this.telefono = JTtelefono.getText();
				new OperacionesCliente().editarCliente(nombre, telefono, indice);
				JOptionPane.showMessageDialog(null, "Cliente editado");
				setVisible(false);
				try {
					new GUIClientes();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else {
				JOptionPane.showMessageDialog(null, "Digite todos los campos");
			}
			
		}
		
		
	}

}