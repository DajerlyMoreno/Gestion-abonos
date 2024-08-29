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

import co.uptc.edu.logica.control.OperacionesAbono;
import co.uptc.edu.logica.control.OperacionesBulto;
import co.uptc.edu.logica.modelo.Bulto;
import co.uptc.edu.persistencia.DAOAbono;
import co.uptc.edu.persistencia.DAOBulto;

public class GUIAgregarBulto extends JFrame implements ActionListener{

	private JPanel JPAreaFormulario;
	private JPanel JPAreaInfo;
	private JLabel JLTitulo;
	private JLabel JLInfoAbonoDisponible;
	private JLabel JLCalculoNumBultosPosibles;
	private JLabel JLCantidadBultos;
	private JTextField JTCantidadBultos;

	private JButton JBAgregar;
	private JButton JBCancelar;
	
	private Bulto BultoATrabajar;
	private int Indice;
	private Double BultosPosibles;

	public GUIAgregarBulto(int indice) {
		
		this.Indice = indice;
		ArrayList<Bulto> bultos = new DAOBulto().ObtenerDatosBultos();
		BultoATrabajar = bultos.get(indice);
		this.BultosPosibles = new OperacionesBulto().CalcularBultosPosibles(BultoATrabajar);
		
		JPAreaFormulario = new JPanel();
		JPAreaInfo = new JPanel();

		JLTitulo = new JLabel("Agregar Bulto", SwingConstants.CENTER);

		JLInfoAbonoDisponible = new JLabel("Hay "+new DAOAbono().ObtenerDatosAbono().get(0).getCantidad()+" Kg de abono", SwingConstants.CENTER);
		JLCalculoNumBultosPosibles = new JLabel("Alcanza para "+this.BultosPosibles+" bultos de "+BultoATrabajar.getVariacionPeso()+" Kg", SwingConstants.CENTER);
		
		JLCantidadBultos = new JLabel("Cantidad de bultos");
		JTCantidadBultos = new JTextField();

		JBAgregar = new JButton("Agregar");
		JBCancelar = new JButton("Cancelar");
		
		ArrayList<Bulto> datos = new DAOBulto().ObtenerDatosBultos();

		setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(400, 500);
		setLocationRelativeTo(null);
		setTitle("Agregar bulto");

		setResizable(false);
	}
	
	public void AgregarBulto() {
		JPAreaFormulario.setBounds(0, 0, 375, 490);
		JPAreaFormulario.setBackground(new Color(242, 242, 242));

		JPAreaFormulario.setLayout(null);

		JLTitulo.setBounds(0, 15, 400, 55);
		JLTitulo.setFont(new Font("Arial", Font.BOLD, 40));
		JLTitulo.setForeground(new Color(31, 90, 166));
		
		JPAreaInfo.setBounds(20,80,345,140);
		JPAreaInfo.setBackground(Color.white);
		
		JPAreaInfo.setLayout(null);
		
		JLInfoAbonoDisponible.setBounds(0, 40, 345, 23);
		JLInfoAbonoDisponible.setFont(new Font("Fredoka One", Font.BOLD, 18));
		JLInfoAbonoDisponible.setForeground(Color.black);
		
		JLCalculoNumBultosPosibles.setBounds(0, 100, 345, 23);
		JLCalculoNumBultosPosibles.setFont(new Font("Fredoka One", Font.BOLD, 18));
		JLCalculoNumBultosPosibles.setForeground(Color.black);
		
		JPAreaInfo.setBounds(20,75,345,200);
		JPAreaInfo.setBackground(Color.white);

		JLCantidadBultos.setBounds(15, 300, 300, 20);
		JLCantidadBultos.setFont(new Font("Fredoka One", Font.BOLD, 20));
		JLCantidadBultos.setForeground(Color.black);
		JTCantidadBultos.setBounds(15, 330, 245, 30);

		JBAgregar.setBounds(200, 390, 90, 40);
		JBAgregar.setForeground(Color.white);
		JBAgregar.setBackground(new Color(22, 85, 140));
		JBAgregar.addActionListener(this);

		JBCancelar.setBounds(100, 390, 90, 40);
		JBCancelar.setForeground(Color.white);
		JBCancelar.setBackground(new Color(168, 191, 86));
		JBCancelar.addActionListener(this);

		add(JPAreaFormulario);
		JPAreaFormulario.add(JLTitulo);
		JPAreaFormulario.add(JPAreaInfo);
		JPAreaInfo.add(JLInfoAbonoDisponible);
		JPAreaInfo.add(JLCalculoNumBultosPosibles);
		JPAreaFormulario.add(JLCantidadBultos);
		JPAreaFormulario.add(JTCantidadBultos);
		JPAreaFormulario.add(JBAgregar);
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
			
		}
		if (o.equals(JBAgregar)) {
			if (JTCantidadBultos.getText().length() != 0) {
				if (Double.parseDouble(JTCantidadBultos.getText())<=BultosPosibles) {
					int stockAAgregar = Integer.parseInt(JTCantidadBultos.getText());
					new OperacionesBulto().ModificarBulto(stockAAgregar, this.Indice);
					new OperacionesAbono().QuitarAbono(BultoATrabajar,stockAAgregar);
					JOptionPane.showMessageDialog(null, "Bultos agregados");
					setVisible(false);
					try {
						GUITablaStock c = new GUITablaStock();
						c.TablaStock();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null, "Cantidad invalida");
				}
			}else {
				JOptionPane.showMessageDialog(null, "Digite todo los datos ");
			}
		}

	}

}
