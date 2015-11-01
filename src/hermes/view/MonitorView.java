package hermes.view;

import hermes.dao.*;
import hermes.model.*;

import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class MonitorView extends JFrame {

	private JPanel contentPane;
	private JTextField txtCrearEtiqueta;
	private JTextField txtNuevoNombre;
	private final JPanel panelFiltros = new JPanel();
	private JTextField txtDesde;
	private JTextField txtHasta;
	private JTable table;
	private JComboBox cboCategoria;
	private JComboBox cboContenido;
	private JComboBox cboContexto;
	private JComboBox cboNino;
	private JComboBox cboEtiqueta;
	private JComboBox cboRenombrarEtiqueta;
	private JComboBox cboEliminarEtiqueta;
	private JComboBox cboAsignarEtiqueta;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonitorView frame = new MonitorView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void inicializarComboBoxCategoria() {
		ICategoriaDAO categoriaDAO = FactoriaDAO.getCategoriaDAO();
		List<Categoria> lista;
		lista = categoriaDAO.listarCategorias();
		for (Categoria c: lista)
			cboCategoria.addItem(c.getTexto());
	}
	
	public void inicializarComboBoxContenido() {
		IContenidoDAO contenidoDAO = FactoriaDAO.getContenidoDAO();
		List<Contenido> lista;
		lista = contenidoDAO.listarContenidos();
		for (Contenido c: lista)
			cboContenido.addItem(c.getTexto());
	}
	
	public void inicializarComboBoxContexto() {
		IContextoDAO contextoDAO = FactoriaDAO.getContextoDAO();
		List<Contexto> lista;
		lista = contextoDAO.listarContextos();
		for (Contexto c: lista)
			cboContexto.addItem(c.getTexto());
	}
	
	public void inicializarComboBoxNino() {
		INinoDAO ninoDAO = FactoriaDAO.getNinoDAO();
		List<Nino> lista;
		lista = ninoDAO.listarNinos();
		for (Nino c: lista)
			cboNino.addItem(c.getNombre() + " " + c.getApellido());
	}
	
	/**
	 * Obtiene todas las etiquetas de la base de datos y las agrega al JComboBox
	 * @param combo el JComboBox que va a ser rellenado
	 */
	private void inicializarComboBoxEtiqueta(JComboBox<Etiqueta> combo) {
		IEtiquetaDAO etiquetaDAO = FactoriaDAO.getEtiquetaDAO();
		List<Etiqueta> lista = etiquetaDAO.listarEtiquetas();
		for (Etiqueta e: lista)
			combo.addItem(e);
	}


	/**
	 * Create the frame.
	 */
	public MonitorView() {
		setTitle("Hermes Monitor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1077, 654);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane); 
		contentPane.setLayout(null);
		panelFiltros.setBounds(5, 5, 551, 285);
		contentPane.add(panelFiltros);
		panelFiltros.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel label = new JLabel("Contenido");
		label.setBounds(26, 35, 73, 15);
		
		JLabel label_1 = new JLabel("Contexto");
		label_1.setBounds(26, 60, 70, 29);
		
		txtDesde = new JTextField();
		txtDesde.setEnabled(false);
		txtDesde.setBounds(118, 186, 154, 26);
		txtDesde.setColumns(10);
		
		txtHasta = new JTextField();
		txtHasta.setEnabled(false);
		txtHasta.setBounds(285, 186, 154, 26);
		txtHasta.setColumns(10);
		
		cboEtiqueta = new JComboBox();
		cboEtiqueta.setBounds(120, 220, 152, 26);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(118, 253, 154, 25);
		
		JLabel label_2 = new JLabel("Nin@");
		label_2.setBounds(26, 100, 70, 15);
		
		JLabel label_3 = new JLabel("Fecha/Hora");
		label_3.setBounds(26, 140, 95, 15);
		
		JLabel label_4 = new JLabel("desde");
		label_4.setBounds(118, 160, 70, 15);
		
		JLabel label_5 = new JLabel("hasta");
		label_5.setBounds(285, 160, 70, 15);
		
		cboContenido = new JComboBox();
		cboContenido.setBounds(120, 30, 152, 24);
		
		cboContexto = new JComboBox();
		cboContexto.setBounds(120, 62, 152, 24);
		
		cboNino = new JComboBox();
		cboNino.setBounds(120, 95, 152, 24);
		
		JLabel label_6 = new JLabel("Etiqueta");
		label_6.setBounds(26, 226, 70, 15);
		
		JLabel lblNewLabel = new JLabel("Categoria");
		lblNewLabel.setBounds(301, 67, 73, 14);
		
		cboCategoria = new JComboBox();
		cboCategoria.setBounds(381, 62, 152, 24);
		panelFiltros.setLayout(null);
		panelFiltros.add(label);
		panelFiltros.add(cboContenido);
		panelFiltros.add(label_1);
		panelFiltros.add(cboContexto);
		panelFiltros.add(lblNewLabel);
		panelFiltros.add(cboCategoria);
		panelFiltros.add(label_2);
		panelFiltros.add(cboNino);
		panelFiltros.add(label_3);
		panelFiltros.add(label_4);
		panelFiltros.add(label_5);
		panelFiltros.add(txtDesde);
		panelFiltros.add(txtHasta);
		panelFiltros.add(label_6);
		panelFiltros.add(cboEtiqueta);
		panelFiltros.add(btnFiltrar);
		
		JPanel panelEtiquetas = new JPanel();
		panelEtiquetas.setBounds(567, 5, 495, 285);
		contentPane.add(panelEtiquetas);
		panelEtiquetas.setBorder(new TitledBorder(null, "Etiquetas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel label_7 = new JLabel("Crear etiqueta");
		label_7.setBounds(23, 34, 104, 15);
		
		txtCrearEtiqueta = new JTextField();
		txtCrearEtiqueta.setBounds(160, 29, 154, 24);
		txtCrearEtiqueta.setColumns(10);
		
		JLabel label_8 = new JLabel("Eliminar etiqueta");
		label_8.setBounds(23, 70, 127, 15);
		
		cboEliminarEtiqueta = new JComboBox();
		cboEliminarEtiqueta.setBounds(160, 65, 154, 24);
		
		JLabel label_9 = new JLabel("Asignar etiqueta");
		label_9.setBounds(23, 106, 127, 15);
		
		cboAsignarEtiqueta = new JComboBox();
		cboAsignarEtiqueta.setBounds(160, 101, 154, 24);
		
		JLabel label_10 = new JLabel("Renombrar etiqueta");
		label_10.setBounds(23, 142, 127, 15);
		
		cboRenombrarEtiqueta = new JComboBox();
		cboRenombrarEtiqueta.setBounds(160, 137, 154, 24);
		cboRenombrarEtiqueta.addActionListener(new ComboEditarEtiquetaListener());
		
		JLabel label_11 = new JLabel("Nuevo nombre");
		label_11.setBounds(23, 178, 117, 15);
		
		txtNuevoNombre = new JTextField();
		txtNuevoNombre.setBounds(160, 173, 154, 24);
		txtNuevoNombre.setColumns(10);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(326, 28, 154, 25);
		btnCrear.addActionListener(new BotonCrearEtiquetaListener());
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(326, 65, 154, 25);
		btnEliminar.addActionListener(new BotonEliminarEtiquetaListener());
		
		JButton btnAsignardesasig = new JButton("Asignar/Desasig.");
		btnAsignardesasig.setBounds(326, 101, 154, 25);
		
		JButton btnRenombrar = new JButton("Renombrar");
		btnRenombrar.setBounds(326, 172, 154, 25);
		btnRenombrar.addActionListener(new BotonEditarEtiquetaListener());
		
		panelEtiquetas.setLayout(null);
		panelEtiquetas.add(label_7);
		panelEtiquetas.add(txtCrearEtiqueta);
		panelEtiquetas.add(btnCrear);
		panelEtiquetas.add(label_8);
		panelEtiquetas.add(cboEliminarEtiqueta);
		panelEtiquetas.add(btnEliminar);
		panelEtiquetas.add(label_9);
		panelEtiquetas.add(cboAsignarEtiqueta);
		panelEtiquetas.add(btnAsignardesasig);
		panelEtiquetas.add(label_10);
		panelEtiquetas.add(cboRenombrarEtiqueta);
		panelEtiquetas.add(label_11);
		panelEtiquetas.add(txtNuevoNombre);
		panelEtiquetas.add(btnRenombrar);
		
		JPanel panelNotificaciones = new JPanel();
		panelNotificaciones.setBounds(5, 290, 1057, 314);
		contentPane.add(panelNotificaciones);
		panelNotificaciones.setBorder(new TitledBorder(null, "Notificaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panelNotificaciones = new GroupLayout(panelNotificaciones);
		gl_panelNotificaciones.setHorizontalGroup(
			gl_panelNotificaciones.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNotificaciones.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1025, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelNotificaciones.setVerticalGroup(
			gl_panelNotificaciones.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNotificaciones.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Fecha/Hora envio", "Contenido", "Contexto", "Categoria", "Nin@", "Etiqueta"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(98);
		scrollPane.setViewportView(table);
		panelNotificaciones.setLayout(gl_panelNotificaciones);
		
		inicializarComboBoxCategoria();
		inicializarComboBoxContenido();
		inicializarComboBoxContexto();
		inicializarComboBoxNino();
		inicializarComboBoxEtiqueta(cboRenombrarEtiqueta);
		inicializarComboBoxEtiqueta(cboEtiqueta);
		inicializarComboBoxEtiqueta(cboEliminarEtiqueta);
		inicializarComboBoxEtiqueta(cboAsignarEtiqueta);
		
	}

	private class BotonCrearEtiquetaListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String texto = txtCrearEtiqueta.getText();
			if (!texto.equals("")){
				System.out.println("click en crear etiqueta con texto not null");
				IEtiquetaDAO etiquetaDAO = FactoriaDAO.getEtiquetaDAO();
				Etiqueta etiqueta = new Etiqueta(texto);
				etiquetaDAO.guardarEtiqueta(etiqueta);
			}	
		}
	}
	
	private class ComboEditarEtiquetaListener implements ActionListener{	
		@Override
		public void actionPerformed(ActionEvent e) {
			txtNuevoNombre.setText(cboRenombrarEtiqueta.getSelectedItem().toString());
		}
	}
	
	private class BotonEditarEtiquetaListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String texto = txtNuevoNombre.getText();
			if (!texto.equals("")){
				System.out.println("click en editar etiqueta con texto not null");
				IEtiquetaDAO etiquetaDAO = FactoriaDAO.getEtiquetaDAO();
				Etiqueta etiquetaNueva = new Etiqueta(texto);
				Etiqueta etiquetaOriginal = (Etiqueta) cboRenombrarEtiqueta.getSelectedItem();
				etiquetaDAO.renombrarEtiqueta(etiquetaOriginal, etiquetaNueva);
			}	
		}
	}
	
	private class BotonEliminarEtiquetaListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("click en eliminar etiqueta");
			int respuesta = JOptionPane.showConfirmDialog(null, "¿Eliminar etiqueta? Esta acción es definitiva");
			
			if (respuesta==JOptionPane.YES_OPTION){
				Etiqueta etiqueta = (Etiqueta) cboEliminarEtiqueta.getSelectedItem();
				FactoriaDAO.getEtiquetaDAO().eliminarEtiqueta(etiqueta);
			}
			
		}
		
	}
	
}
