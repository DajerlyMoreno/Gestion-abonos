package co.uptc.edu.presentacion.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import co.uptc.edu.logica.control.OperacionesAbono;
import co.uptc.edu.logica.control.OperacionesAditivo;
import co.uptc.edu.logica.modelo.Aditivo;
import co.uptc.edu.logica.modelo.Bulto;
import co.uptc.edu.persistencia.DAOAditivos;
import co.uptc.edu.persistencia.DAOBulto;
import co.uptc.edu.persistencia.DAOResiduosOrganicos;

public class GUIProducirAbono extends JFrame implements ActionListener {
	private JPanel JPAreaFormulario;
	private JPanel JPAreaInfo;
	private JLabel JLTitulo;
	private JLabel JLMatOrgDisponible;
	private JLabel JLDatosAditivo;
	private JLabel JLDatosStockAditivo;
	private JLabel JLCalculoNumAbonoPosible;
	private JLabel JLBuscarAditivo;
	private JTextField JTBuscarAditivo;
	private JTextField JTAbonoACrear;

	private JButton JBBuscarAditivo;
	private JButton JBAgregar;
	private JButton JBCancelar;
	private Aditivo AditivoATrabajar;
	private int NumAbonoPosible;

	public GUIProducirAbono() throws IOException {
		JPAreaFormulario = new JPanel();
		JPAreaInfo = new JPanel();

		JLTitulo = new JLabel("Producir Abono", SwingConstants.CENTER);

		JLMatOrgDisponible = new JLabel();
		JLDatosAditivo = new JLabel();
		JLDatosStockAditivo = new JLabel();

		JLBuscarAditivo = new JLabel("Buscar aditivo por nombre");
		JTBuscarAditivo = new JTextField();
		JBBuscarAditivo = new JButton();

		File FileBuscarAditivo = new File("Recursos/iconos/buscarAdit.png");
		Image ImgBuscarAditivo = ImageIO.read(FileBuscarAditivo);
		JBBuscarAditivo.setIcon(new ImageIcon(ImgBuscarAditivo));
		JBBuscarAditivo.setBackground(Color.white);
		JBBuscarAditivo.setBorder(null);

		JLCalculoNumAbonoPosible = new JLabel();
		JTAbonoACrear = new JTextField();

		JBAgregar = new JButton("Crear");
		JBCancelar = new JButton("Cancelar");

		ArrayList<Bulto> datos = new DAOBulto().ObtenerDatosBultos();

		setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(400, 500);
		setLocationRelativeTo(null);
		setTitle("Producirabono");

		setResizable(false);
	}

	public void ProducirAbono() {
		JPAreaFormulario.setBounds(0, 0, 375, 490);
		JPAreaFormulario.setBackground(new Color(242, 242, 242));

		JPAreaFormulario.setLayout(null);

		JLTitulo.setBounds(0, 15, 400, 55);
		JLTitulo.setFont(new Font("Arial", Font.BOLD, 40));
		JLTitulo.setForeground(new Color(31, 90, 166));

		JPAreaInfo.setBounds(20, 80, 345, 140);
		JPAreaInfo.setBackground(Color.white);

		JPAreaInfo.setLayout(null);

		JLBuscarAditivo.setBounds(30, 20, 345, 20);
		JLBuscarAditivo.setFont(new Font("Fredoka One", Font.BOLD, 15));
		JLBuscarAditivo.setForeground(Color.black);
		JTBuscarAditivo.setBounds(30, 50, 230, 30);
		JBBuscarAditivo.setBounds(270, 40, 40, 42);
		JBBuscarAditivo.addActionListener(this);

		JLDatosAditivo.setBounds(30, 100, 345, 20);
		JLDatosAditivo.setFont(new Font("Fredoka One", Font.BOLD, 15));
		JLDatosAditivo.setForeground(Color.black);
		JLDatosStockAditivo.setBounds(30, 120, 345, 20);
		JLDatosStockAditivo.setFont(new Font("Fredoka One", Font.BOLD, 15));
		JLDatosStockAditivo.setForeground(Color.black);

		JLMatOrgDisponible.setBounds(30, 160, 345, 20);
		JLMatOrgDisponible.setFont(new Font("Fredoka One", Font.BOLD, 15));
		JLMatOrgDisponible.setForeground(Color.black);
		JLMatOrgDisponible.setText("Materia orgánica disponible: "
				+ new DAOResiduosOrganicos().ObtenerResiduosOrganicos().get(0).getCantidad() + " Kg");

		JPAreaInfo.setBounds(20, 75, 345, 200);
		JPAreaInfo.setBackground(Color.white);

		JLCalculoNumAbonoPosible.setBounds(18, 300, 300, 20);
		JLCalculoNumAbonoPosible.setFont(new Font("Fredoka One", Font.BOLD, 15));
		JLCalculoNumAbonoPosible.setForeground(Color.black);
		JLCalculoNumAbonoPosible.setText("Puede producir "
				+ ((this.AditivoATrabajar == null) ? 0
						: new OperacionesAbono().CalcularKiloPosibles(this.AditivoATrabajar,
								new DAOResiduosOrganicos().ObtenerResiduosOrganicos().get(0).getCantidad()))
				+ " Kg de abono");
		JTAbonoACrear.setBounds(18, 330, 245, 30);

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
		JPAreaInfo.add(JLBuscarAditivo);
		JPAreaInfo.add(JTBuscarAditivo);
		JPAreaInfo.add(JBBuscarAditivo);
		JPAreaInfo.add(JLDatosAditivo);
		JPAreaInfo.add(JLDatosStockAditivo);
		JPAreaInfo.add(JLMatOrgDisponible);
		JPAreaFormulario.add(JLCalculoNumAbonoPosible);
		JPAreaFormulario.add(JTAbonoACrear);
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
		if (o.equals(JBBuscarAditivo)) {
			if (JTBuscarAditivo.getText().length() != 0) {
				String NombreAditivo = JTBuscarAditivo.getText();
				if (new OperacionesAditivo().BuscarAditivo(NombreAditivo)) {
					AditivoATrabajar = new Aditivo();
					this.AditivoATrabajar = new OperacionesAditivo().TraerAditivo(NombreAditivo);
					JLCalculoNumAbonoPosible.setText("Puede producir "
							+ ((this.AditivoATrabajar == null) ? 0
									: (int) new OperacionesAbono().CalcularKiloPosibles(this.AditivoATrabajar,
											new DAOResiduosOrganicos().ObtenerResiduosOrganicos().get(0).getCantidad()))
							+ " Kg de abono");
					this.NumAbonoPosible = (int) new OperacionesAbono().CalcularKiloPosibles(this.AditivoATrabajar,
							new DAOResiduosOrganicos().ObtenerResiduosOrganicos().get(0).getCantidad());
					JLDatosAditivo.setText(this.AditivoATrabajar.getNombre() + " con margen de "
							+ (int) this.AditivoATrabajar.getNumLitrosPorKg() + "L por Kg");
					JLDatosStockAditivo.setText("con un stock de: "+this.AditivoATrabajar.getCantidad()+"L");
				} else {
					JOptionPane.showMessageDialog(null, "Ese aditivo no existe");
					JLDatosAditivo.setText("");
					JLDatosStockAditivo.setText("");
					this.AditivoATrabajar=null;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Digite todo los datos ");
			}
		}
		if (o.equals(JBAgregar)) {
			if (JTAbonoACrear.getText().length() != 0) {
				int AbonoACrear = Integer.parseInt(JTAbonoACrear.getText());
				if (this.AditivoATrabajar != null) {
					if (new OperacionesAbono().ValidarCantidad(AbonoACrear, NumAbonoPosible)) {

						new OperacionesAbono().AgregarAbono(AbonoACrear, this.AditivoATrabajar);
						JOptionPane.showMessageDialog(null, "Abono producido");
						setVisible(false);
						try {
							GUITablaStock c = new GUITablaStock();
							c.TablaStock();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "La cantidad es invalida");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Primero debe escoger un aditivo");
				}

			} else {
				JOptionPane.showMessageDialog(null, "Digite todo los datos ");
			}
		}
	}
}
