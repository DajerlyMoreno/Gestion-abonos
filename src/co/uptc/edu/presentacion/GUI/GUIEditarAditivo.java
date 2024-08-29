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
import co.uptc.edu.logica.modelo.Aditivo;
import co.uptc.edu.persistencia.DAOAditivos;

public class GUIEditarAditivo extends JFrame implements ActionListener{
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
	
	private String LitroXKilo,Nombre, PorcConc;
	private int  Indice;

	public GUIEditarAditivo(int Indice) {
		
		ArrayList<Aditivo> datos = new DAOAditivos().ObtenerDatosAditivos();
		
		this.Nombre = datos.get(Indice).getNombre();
		this.PorcConc = Integer.toString(datos.get(Indice).getPorcentajeConcentracion()) ;
		this.LitroXKilo = Integer.toString(datos.get(Indice).getNumLitrosPorKg());
		this.Indice = Indice;
		
		JPAreaFormulario = new JPanel();

		JLTitulo = new JLabel("Editar Aditivo", SwingConstants.CENTER);

		JLNombre = new JLabel("Nombre");
		JTNombre = new JTextField(this.Nombre);

		JLPorcConcentracion = new JLabel("Porcentaje de concentración %");
		JTPorcConcentracion = new JTextField(this.PorcConc);

		JLLitrosPorKilo = new JLabel("Litros por Kg");
		JTLitrosPorKilo = new JTextField(this.LitroXKilo);

		JBCrear = new JButton("Guardar");
		JBCancelar = new JButton("Cancelar");

		setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(400, 500);
		setLocationRelativeTo(null);
		setTitle("Editar aditivo");

		setResizable(false);
	}

	public void EditarAditivo() {
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

				this.Nombre = JTNombre.getText();
				this.LitroXKilo = JTLitrosPorKilo.getText();
				this.PorcConc = JTPorcConcentracion.getText();
				new OperacionesAditivo().ModificarAditivo(Nombre, LitroXKilo, PorcConc, Indice);
				JOptionPane.showMessageDialog(null, "Aditivo Modificado");
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
