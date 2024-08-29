package co.uptc.edu.presentacion.GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.lang.System.Logger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import co.uptc.edu.logica.control.OperacionesBulto;
import co.uptc.edu.logica.control.OperacionesCliente;
import co.uptc.edu.logica.control.OperacionesVenta;
import co.uptc.edu.logica.modelo.Bulto;
import co.uptc.edu.logica.modelo.Cliente;
import co.uptc.edu.logica.modelo.Factura;
import co.uptc.edu.persistencia.DAOfactura;

// test



public class GUIFormVentas extends JFrame{
	
	private JComboBox<String> comboBox;
    private JTextField textField;
    private ArrayList<String> opciones;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
	
	private JTextField campoTexto;
    
    private ArrayList<String> listaDatos;
    private JLabel JLTitulo;
    private JPanel JPArea;
    private JLabel JLBuscarCliente;
    private Bulto bulto1;
    private ArrayList<Bulto> bultosArr;
    
    OperacionesCliente op = new OperacionesCliente();
    
    
    public GUIFormVentas(ArrayList<String> datos, ArrayList<String> opciones) {
    	bultosArr = new ArrayList<Bulto>();
    	this.listaDatos = datos;
    	setTitle("Agregar Venta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

        setLayout(null);
        setResizable(false);
        
        JPArea= new JPanel();
        JPArea.setBounds(0,0,500,700);
        JPArea.setBackground(Color.white);
        JPArea.setLayout(null);
        add(JPArea);
        
        JLBuscarCliente= new JLabel("Busque numero de telefono");
        JLBuscarCliente.setBounds(10, 70, 300, 70);
        JLBuscarCliente.setFont(new Font("Fredoka One", Font.BOLD, 20));
		JLBuscarCliente.setForeground(Color.black);		
        JPArea.add(JLBuscarCliente);
        
        
        JLTitulo= new JLabel();
        JLTitulo.setText("AGREGAR VENTA");
        JLTitulo.setBounds(10, 10, 800, 70);
		Font font = new Font("Arial", Font.BOLD, 40);
		Color color = new Color(22, 85, 140);
		JLTitulo.setFont(font);
		JLTitulo.setForeground(color);
		JPArea.add(JLTitulo);
		
		
		JButton botonBuscar = new JButton("Buscar");
        botonBuscar.setBounds(220, 150, 80, 30);
        botonBuscar.setForeground(Color.white);
        botonBuscar.setBackground(new Color(22, 85, 140));
        botonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        JPArea.add(botonBuscar);
        // Configurar el campo de texto
        campoTexto = new JTextField();
        campoTexto.setBounds(10, 150, 200, 30);
        JPArea.add(campoTexto);
        
        
        this.opciones = opciones;

       

        // Configurar el JComboBox
        comboBox = new JComboBox<>(opciones.toArray(new String[0]));
        comboBox.setBounds(10,200, 200, 30);
        comboBox.setEnabled(false);
        
        JPArea.add(comboBox);

        // Configurar el JTextField
        textField = new JTextField();
        textField.setBounds(220, 200, 80, 30);
        textField.setEnabled(false);
        JPArea.add(textField);

        // Agregar el listener para el JComboBox
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (comboBox.getSelectedIndex() != 0) {
                    textField.setEnabled(true);
                    textField.requestFocus();
                } else {
                    textField.setEnabled(false);
                }
            }
        });

        // Configurar la tabla y su modelo
        String[] columnas = {"Cliente", "Variacion elegida", "Cantidad ingresado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(10, 250, 410, 200);
        JPArea.add(scrollPane);

        // Agregar el botón para agregar filas a la tabla
        JButton botonAgregar = new JButton("Agregar");
        botonAgregar.setBounds(220, 460, 200, 30);
        botonAgregar.setForeground(Color.white);
        botonAgregar.setBackground(new Color(168, 191, 86));
        JPArea.add(botonAgregar);

        // Agregar el listener para el botón de agregar filas
        botonAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String resultado = "No se encontró nada";
                if (textField.isEnabled()) {
                	
                	
                	resultado= op.obtenerCliente(campoTexto.getText()).getNombre();
                    //resultado = campoTexto.getText();
                }
                String opcion = (String) comboBox.getSelectedItem();
                
                
                
                bulto1 = new OperacionesBulto().obtenerBulto(opcion);
                bulto1.setStock(Integer.parseInt(textField.getText()));
                bultosArr.add(bulto1);
                Object[] fila = {resultado, opcion, textField.getText()};
                
                String texto= textField.getText();
                if(texto.length()>0) {
                	modeloTabla.addRow(fila);
                	
                }else {
                	JOptionPane.showMessageDialog(null, "Hay un campo vacío");
                }
                
            }
        });
        
        // Agregar el botón para eliminar filas de la tabla
        JButton botonEliminar = new JButton("Eliminar");
        botonEliminar.setBounds(10, 460, 200, 30);
        botonEliminar.setForeground(Color.white);
        botonEliminar.setBackground(new Color(255, 92, 89));
        JPArea.add(botonEliminar);

        // Agregar el listener para el botón de eliminar filas
        botonEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tabla.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    modeloTabla.removeRow(filaSeleccionada);
                }}});
        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.setBounds(10, 530, 200, 30);
        botonCancelar.setForeground(Color.white);
        botonCancelar.setBackground(new Color(255, 92, 89));
        JPArea.add(botonCancelar);

        // Agregar el listener para el botón de eliminar filas
        botonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new GUIVenta();
                }});
        
        
        JButton botonAgregarVenta = new JButton("Agregar venta");
        botonAgregarVenta.setBounds(220, 530, 200, 30);
        botonAgregarVenta.setForeground(Color.white);
        botonAgregarVenta.setBackground(new Color(22, 85, 140));
        JPArea.add(botonAgregarVenta);
        
        //boton agregar venta
        botonAgregarVenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	
            	
            	new OperacionesVenta().AgregarVenta(op.obtenerCliente(campoTexto.getText()), bultosArr);
            	/*
            	ArrayList<Bulto> bultoss = new ArrayList<>();
            	fact.setBultosAsociados(bultoss);
            	fact.setId(1);
            	fact.setValorTotal(1500);
                System.out.println(fact.toString());
                */
            }
        });

        // Mostrar el JFrame
        setSize(500, 700);
        setVisible(true);
    }
   
    private void buscarActionPerformed(ActionEvent evt) {
        String textoBuscado = campoTexto.getText();
        if (listaDatos.contains(textoBuscado)) {
            comboBox.setEnabled(true);
            campoTexto.setEnabled(false);
        } else {
            comboBox.setEnabled(false);
        }
    }

   


}