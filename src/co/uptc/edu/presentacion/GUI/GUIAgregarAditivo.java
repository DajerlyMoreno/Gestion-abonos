package co.uptc.edu.presentacion.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import co.uptc.edu.logica.control.OperacionesAditivo;
import co.uptc.edu.logica.control.OperacionesBulto;
import co.uptc.edu.logica.modelo.Aditivo;
import co.uptc.edu.persistencia.DAOAditivos;
import co.uptc.edu.persistencia.DAOBulto;

public class GUIAgregarAditivo extends JFrame implements ActionListener{
	private JPanel JPAreaFormulario;
	private JLabel JLTitulo;
	private JLabel JLCantidadEntrante;
	private JTextField JTCantidadEntrante;

	private JButton JBCrear;
	private JButton JBCancelar;
	
	private String CantidadEntrante;
	private int Indice;

	public GUIAgregarAditivo(int Indice) {
		
		this.Indice= Indice;
		
		JPAreaFormulario = new JPanel();

		JLTitulo = new JLabel("Agregar aditivo", SwingConstants.CENTER);

		JLCantidadEntrante = new JLabel("Cantidad entrante (L)");
		JTCantidadEntrante = new JTextField();

		JBCrear = new JButton("Agregar");
		JBCancelar = new JButton("Cancelar");

		setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(400, 300);
		setLocationRelativeTo(null);
		setTitle("Agregar aditivo");

		setResizable(false);
	}

	public void AgregarAditivo() {
		JPAreaFormulario.setBounds(0, 0, 375, 490);
		JPAreaFormulario.setBackground(new Color(242, 242, 242));

		JPAreaFormulario.setLayout(null);

		JLTitulo.setBounds(0, 15, 400, 50);
		JLTitulo.setFont(new Font("Arial", Font.BOLD, 40));
		JLTitulo.setForeground(new Color(31, 90, 166));

		JLCantidadEntrante.setBounds(15, 90, 300, 25);
		JLCantidadEntrante.setFont(new Font("Fredoka One", Font.BOLD, 20));
		JLCantidadEntrante.setForeground(Color.black);
		JTCantidadEntrante.setBounds(15, 120, 245, 30);

		JBCrear.setBounds(200, 180, 90, 40);
		JBCrear.setForeground(Color.white);
		JBCrear.setBackground(new Color(22, 85, 140));
		JBCrear.addActionListener(this);

		JBCancelar.setBounds(100, 180, 90, 40);
		JBCancelar.setForeground(Color.white);
		JBCancelar.setBackground(new Color(168, 191, 86));
		JBCancelar.addActionListener(this);

		add(JPAreaFormulario);
		JPAreaFormulario.add(JLTitulo);
		JPAreaFormulario.add(JLCantidadEntrante);
		JPAreaFormulario.add(JTCantidadEntrante);
		JPAreaFormulario.add(JBCrear);
		JPAreaFormulario.add(JBCancelar);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(JBCancelar)) {
			this.setVisible(false);
			GUITablaStock c;
			try {
				c = new GUITablaStock();
				c.TablaStock();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else if (o.equals(JBCrear)) {
		
			if (JTCantidadEntrante.getText().length()!=0) {

				Double CantidadEntrante = Double.parseDouble(JTCantidadEntrante.getText());
				new OperacionesAditivo().AnadirAditivo(CantidadEntrante, Indice);
				JOptionPane.showMessageDialog(null, "Listros añadidos");
				setVisible(false);
				try {
					GUITablaStock c = new GUITablaStock();
					c.TablaStock();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {

				JOptionPane.showMessageDialog(null, "Digite todo los datos ");
			}

		}

	}
}
