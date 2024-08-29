package co.uptc.edu.presentacion.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import co.uptc.edu.logica.control.OperacionesAditivo;

public class GUICrearAditivo extends JFrame implements ActionListener {

	private JPanel JPAreaFormulario;
	private JLabel JLTitulo;
	private JLabel JLNombre;
	private JTextField JTNombre;
	private JLabel JLPorcConcentracion;
	private JTextField JTPorcConcentracion;
	private JLabel JLLitrosPorKilo;
	private JTextField JTLitrosPorKilo;

	private JButton JBCrear;
	private JButton JBCancelar;

	public GUICrearAditivo() {
		JPAreaFormulario = new JPanel();

		JLTitulo = new JLabel("Crear Aditivo", SwingConstants.CENTER);

		JLNombre = new JLabel("Nombre");
		JTNombre = new JTextField();

		JLPorcConcentracion = new JLabel("Porcentaje de concentración %");
		JTPorcConcentracion = new JTextField();

		JLLitrosPorKilo = new JLabel("Litros por Kg");
		JTLitrosPorKilo = new JTextField();

		JBCrear = new JButton("Crear");
		JBCancelar = new JButton("Cancelar");

		setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(400, 500);
		setLocationRelativeTo(null);
		setTitle("Crear aditivo");

		setResizable(false);
	}

	public void CrearAditivo() {
		JPAreaFormulario.setBounds(0, 0, 375, 490);
		JPAreaFormulario.setBackground(new Color(242, 242, 242));

		JPAreaFormulario.setLayout(null);

		JLTitulo.setBounds(0, 15, 400, 40);
		JLTitulo.setFont(new Font("Arial", Font.BOLD, 40));
		JLTitulo.setForeground(new Color(31, 90, 166));

		JLNombre.setBounds(15, 90, 80, 20);
		JLNombre.setFont(new Font("Fredoka One", Font.BOLD, 20));
		JLNombre.setForeground(Color.black);
		JTNombre.setBounds(15, 120, 245, 30);

		JLPorcConcentracion.setBounds(15, 180, 350, 20);
		JLPorcConcentracion.setFont(new Font("Fredoka One", Font.BOLD, 20));
		JLPorcConcentracion.setForeground(Color.black);
		JTPorcConcentracion.setBounds(15, 210, 80, 30);

		JLLitrosPorKilo.setBounds(15, 270, 310, 25);
		JLLitrosPorKilo.setFont(new Font("Fredoka One", Font.BOLD, 20));
		JLLitrosPorKilo.setForeground(Color.black);
		JTLitrosPorKilo.setBounds(15, 305, 100, 30);

		JBCrear.setBounds(200, 390, 90, 40);
		JBCrear.setForeground(Color.white);
		JBCrear.setBackground(new Color(22, 85, 140));
		JBCrear.addActionListener(this);

		JBCancelar.setBounds(100, 390, 90, 40);
		JBCancelar.setForeground(Color.white);
		JBCancelar.setBackground(new Color(168, 191, 86));
		JBCancelar.addActionListener(this);

		add(JPAreaFormulario);
		JPAreaFormulario.add(JLTitulo);
		JPAreaFormulario.add(JLNombre);
		JPAreaFormulario.add(JTNombre);
		JPAreaFormulario.add(JLPorcConcentracion);
		JPAreaFormulario.add(JTPorcConcentracion);
		JPAreaFormulario.add(JLLitrosPorKilo);
		JPAreaFormulario.add(JTLitrosPorKilo);
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
			if (JTNombre.getText().length() != 0 && JTLitrosPorKilo.getText().length() != 0
					&& JTPorcConcentracion.getText().length() != 0) {

				String Nombre = JTNombre.getText();
				String LitroPorKilo = JTLitrosPorKilo.getText();
				String PorcConentracion = JTPorcConcentracion.getText();
				new OperacionesAditivo().AgregarAditivo(Nombre, LitroPorKilo, PorcConentracion);
				JOptionPane.showMessageDialog(null, "Aditivo Creado");
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
