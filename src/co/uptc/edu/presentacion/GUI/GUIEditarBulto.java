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
import co.uptc.edu.logica.modelo.Bulto;
import co.uptc.edu.persistencia.DAOBulto;

public class GUIEditarBulto extends JFrame implements ActionListener {

	private JPanel JPAreaFormulario;
	private JLabel JLTitulo;
	private JLabel JLVariacionPeso;
	private JTextField JTVariacionPeso;
	private JLabel JLPrecioUnitario;
	private JTextField JTPrecioUnitario;

	private JButton JBCrear;
	private JButton JBCancelar;
	
	private String VariacionPeso, PrecioUnitario;
	private int Indice;

	public GUIEditarBulto(int Indice) {
		
		ArrayList<Bulto> datos = new DAOBulto().ObtenerDatosBultos();
		
		this.VariacionPeso = Integer.toString(datos.get(Indice).getVariacionPeso());
		this.PrecioUnitario = Double.toString(datos.get(Indice).getPrecioUnitario());
		this.Indice= Indice;
		
		
		JPAreaFormulario = new JPanel();

		JLTitulo = new JLabel("Editar Bulto", SwingConstants.CENTER);

		JLVariacionPeso = new JLabel("Variación de peso");
		JTVariacionPeso = new JTextField(this.VariacionPeso);

		JLPrecioUnitario = new JLabel("Precio Unitario $");
		JTPrecioUnitario = new JTextField(this.PrecioUnitario);

		JBCrear = new JButton("Guardar");
		JBCancelar = new JButton("Cancelar");

		setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(400, 500);
		setLocationRelativeTo(null);
		setTitle("Editar bulto");

		setResizable(false);
	}

	public void EditarBulto() {
		JPAreaFormulario.setBounds(0, 0, 375, 490);
		JPAreaFormulario.setBackground(new Color(242, 242, 242));

		JPAreaFormulario.setLayout(null);

		JLTitulo.setBounds(0, 15, 400, 40);
		JLTitulo.setFont(new Font("Arial", Font.BOLD, 40));
		JLTitulo.setForeground(new Color(31, 90, 166));

		JLVariacionPeso.setBounds(15, 90, 300, 20);
		JLVariacionPeso.setFont(new Font("Fredoka One", Font.BOLD, 20));
		JLVariacionPeso.setForeground(Color.black);
		JTVariacionPeso.setBounds(15, 120, 245, 30);

		JLPrecioUnitario.setBounds(15, 180, 400, 20);
		JLPrecioUnitario.setFont(new Font("Fredoka One", Font.BOLD, 20));
		JLPrecioUnitario.setForeground(Color.black);
		JTPrecioUnitario.setBounds(15, 210, 80, 30);

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
		JPAreaFormulario.add(JLVariacionPeso);
		JPAreaFormulario.add(JTVariacionPeso);
		JPAreaFormulario.add(JLPrecioUnitario);
		JPAreaFormulario.add(JTPrecioUnitario);
		;
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
			if (JTPrecioUnitario.getText().length() != 0 && JTVariacionPeso.getText().length() != 0) {

				this.PrecioUnitario = JTPrecioUnitario.getText();
				this.VariacionPeso = JTVariacionPeso.getText();
				new OperacionesBulto().ModificarBulto(this.PrecioUnitario, this.VariacionPeso,Indice);
				JOptionPane.showMessageDialog(null, "Bulto Modificado");
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
