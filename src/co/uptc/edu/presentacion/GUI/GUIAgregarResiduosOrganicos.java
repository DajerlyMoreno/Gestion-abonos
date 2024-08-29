package co.uptc.edu.presentacion.GUI;

import java.awt.Color;
import java.awt.Dimension;
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

import co.uptc.edu.logica.control.OperacionesResiduosOrganicos;

public class GUIAgregarResiduosOrganicos extends JFrame implements ActionListener {

	private JPanel JPAreaFormulario;
	private JLabel JLTitulo;
	private JLabel JLTitulo2;
	private JLabel JLCantidadEntrante;
	private JTextField JTCantidadEntrante;
	private JLabel JLResiduosDespreciables;
	private JTextField JTResiduosDespreciables;

	private JButton JBGuardar;
	private JButton JBCancelar;

	public GUIAgregarResiduosOrganicos() {
		JPAreaFormulario = new JPanel();

		JLTitulo = new JLabel("Agregar materia", SwingConstants.CENTER);
		JLTitulo2 = new JLabel("orgánica", SwingConstants.CENTER);
		// JLTitulo.setPreferredSize(new Dimension(100, 50));

		JLCantidadEntrante = new JLabel("Cantidad entrante (Kg)");
		JTCantidadEntrante = new JTextField();

		JLResiduosDespreciables = new JLabel("Kg despreciables");
		JTResiduosDespreciables = new JTextField();

		JBGuardar = new JButton("Guardar");
		JBCancelar = new JButton("Cancelar");

		setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(400, 500);
		setLocationRelativeTo(null);
		setTitle("Agregar materia orgánica");

		setResizable(false);
	}

	public void AgregarMateriaOrganica() {
		JPAreaFormulario.setBounds(0, 0, 375, 490);
		JPAreaFormulario.setBackground(new Color(242, 242, 242));

		JPAreaFormulario.setLayout(null);

		JLTitulo.setBounds(0, 15, 400, 50);
		JLTitulo.setFont(new Font("Arial", Font.BOLD, 40));
		JLTitulo.setForeground(new Color(31, 90, 166));
		JLTitulo2.setBounds(0, 60, 400, 50);
		JLTitulo2.setFont(new Font("Arial", Font.BOLD, 40));
		JLTitulo2.setForeground(new Color(31, 90, 166));

		JLCantidadEntrante.setBounds(15, 145, 220, 30);
		JLCantidadEntrante.setFont(new Font("Fredoka One", Font.BOLD, 20));
		JLCantidadEntrante.setForeground(Color.black);
		JTCantidadEntrante.setBounds(15, 180, 245, 30);

		JLResiduosDespreciables.setBounds(15, 235, 350, 30);
		JLResiduosDespreciables.setFont(new Font("Fredoka One", Font.BOLD, 20));
		JLResiduosDespreciables.setForeground(Color.black);
		JTResiduosDespreciables.setBounds(15, 270, 80, 30);

		JBGuardar.setBounds(200, 390, 90, 40);
		JBGuardar.setForeground(Color.white);
		JBGuardar.setBackground(new Color(22, 85, 140));
		JBGuardar.addActionListener(this);

		JBCancelar.setBounds(100, 390, 90, 40);
		JBCancelar.setForeground(Color.white);
		JBCancelar.setBackground(new Color(168, 191, 86));
		JBCancelar.addActionListener(this);

		add(JPAreaFormulario);
		JPAreaFormulario.add(JLTitulo);
		JPAreaFormulario.add(JLTitulo2);
		JPAreaFormulario.add(JLCantidadEntrante);
		JPAreaFormulario.add(JTCantidadEntrante);
		JPAreaFormulario.add(JLResiduosDespreciables);
		JPAreaFormulario.add(JTResiduosDespreciables);
		JPAreaFormulario.add(JBGuardar);
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
		if (o.equals(JBGuardar)) {
			if (JTCantidadEntrante.getText().length() != 0 && JTResiduosDespreciables.getText().length() != 0) {
				Double CantidadEntrante = Double.parseDouble(JTCantidadEntrante.getText());
				int ResiduosDesprciables = Integer.parseInt(JTResiduosDespreciables.getText());
				if (new OperacionesResiduosOrganicos().ValidarDatos(CantidadEntrante,ResiduosDesprciables)) {
					CantidadEntrante = CantidadEntrante-ResiduosDesprciables;
					new OperacionesResiduosOrganicos().AgregarMateriaOrganica(CantidadEntrante,ResiduosDesprciables);
					JOptionPane.showMessageDialog(null, "Materia orgánica agregada");
					setVisible(false);
					try {
						GUITablaStock c = new GUITablaStock();
						c.TablaStock();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null, "La cantidad de residuos despreciables no debe ser mayor a la cantidad entrante");
				}
			}else {
				JOptionPane.showMessageDialog(null, "Digite todo los datos ");
			}
		}
	}

}
