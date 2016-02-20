package hermes.view;

import hermes.dao.*;
import hermes.db.LecturaJSON;
import hermes.http.MonitorServer;
import hermes.model.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class MonitorView extends JFrame {

	private JPanel contentPane;
	private JTextField txtCrearEtiqueta;
	private JTextField txtNuevoNombre;
	private JTable table;
	private JComboBox<Categoria> cboCategoria;
	private JComboBox<Contenido> cboContenido;
	private JComboBox<Contexto> cboContexto;
	private JComboBox<Nino> cboNino;
	private JComboBox<Etiqueta> cboEtiqueta;
	private JComboBox<Etiqueta> cboRenombrarEtiqueta;
	private JComboBox<Etiqueta> cboEliminarEtiqueta;
	private JComboBox<Etiqueta> cboAsignarEtiqueta;
	private DefaultTableModel modeloTabla;
	private JButton btnFiltrar;
	private static JLabel lblNotificaciones;
	
	private  JDateChooser dcFechaDesde=new JDateChooser();
	private JDateChooser dcFechaHasta=new JDateChooser();
	
	private Contenido contenido = null;
	private Contexto contexto = null;
	private Nino nino = null;
	private Categoria categoria = null;
	private Etiqueta etiqueta = null;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					LecturaJSON lector = new LecturaJSON();
//					lector.cargarNotificaciones("notificaciones.txt");
					MonitorServer servidor = new MonitorServer();
					servidor.run();
					
					MonitorView frame = new MonitorView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void actualizarVista() {
		lblNotificaciones.setText("Hay nuevas notificaciones, presione Mostrar Todo para verlas");
		lblNotificaciones.setVisible(true);
	}
	
	public void inicializarComboBoxCategoria(JComboBox<Categoria> combo) {
		ICategoriaDAO categoriaDAO = FactoriaDAO.getCategoriaDAO();
		List<Categoria> lista = categoriaDAO.listarCategorias();
		DefaultComboBoxModel<Categoria> modelo = new DefaultComboBoxModel<Categoria>();
		Categoria categoria = new Categoria(0, "«Todos»");
		modelo.addElement(categoria);
		for (Categoria c: lista)
			modelo.addElement(c);
		combo.setModel(modelo);
	}
	
	public void inicializarComboBoxContenido(JComboBox<Contenido> combo) {
		IContenidoDAO contenidoDAO = FactoriaDAO.getContenidoDAO();
		List<Contenido> lista = contenidoDAO.listarContenidos();
		DefaultComboBoxModel<Contenido> modelo = new DefaultComboBoxModel<Contenido>();
		Contenido contenido = new Contenido(0, "«Todos»");
		modelo.addElement(contenido);
		for (Contenido c: lista)
			modelo.addElement(c);
		combo.setModel(modelo);
	}
	
	public void inicializarComboBoxContexto(JComboBox<Contexto> combo) {
		IContextoDAO contextoDAO = FactoriaDAO.getContextoDAO();
		List<Contexto> lista = contextoDAO.listarContextos();
		DefaultComboBoxModel<Contexto> modelo = new DefaultComboBoxModel<Contexto>();
		Contexto contexto = new Contexto(0, "«Todos»");
		modelo.addElement(contexto);
		for (Contexto c: lista)
			modelo.addElement(c);
		combo.setModel(modelo);
	}
	
	public void inicializarComboBoxNino(JComboBox<Nino> combo) {
		INinoDAO ninoDAO = FactoriaDAO.getNinoDAO();
		List<Nino> lista = ninoDAO.listarNinos();
		DefaultComboBoxModel<Nino> modelo = new DefaultComboBoxModel<Nino>();
		Nino nino = new Nino(0, "«Todos»", "");
		modelo.addElement(nino);
		for (Nino n: lista)
			modelo.addElement(n);
		combo.setModel(modelo);
	}
	
	/**
	 * Obtiene todas las etiquetas de la base de datos y las agrega al JComboBox
	 * @param combo el JComboBox que va a ser rellenado
	 */
	private void inicializarComboBoxEtiquetaParaFiltro(JComboBox<Etiqueta> combo) {
		IEtiquetaDAO etiquetaDAO = FactoriaDAO.getEtiquetaDAO();
		List<Etiqueta> lista = etiquetaDAO.listarEtiquetas();
		DefaultComboBoxModel<Etiqueta> modelo = new DefaultComboBoxModel<Etiqueta>();
		Etiqueta etiqueta = new Etiqueta(0, "«Todos»");
		modelo.addElement(etiqueta);
		for (Etiqueta e: lista)
			modelo.addElement(e);
		combo.setModel(modelo);
	}
	
	private void inicializarComboBoxEtiqueta(JComboBox<Etiqueta> combo) {
		IEtiquetaDAO etiquetaDAO = FactoriaDAO.getEtiquetaDAO();
		List<Etiqueta> lista = etiquetaDAO.listarEtiquetas();
		DefaultComboBoxModel<Etiqueta> modelo = new DefaultComboBoxModel<Etiqueta>();
		for (Etiqueta e: lista)
			modelo.addElement(e);
		combo.setModel(modelo);
	}
	
	private void refrescarComboBoxEtiqueta() {
		inicializarComboBoxEtiqueta(cboRenombrarEtiqueta);
		inicializarComboBoxEtiquetaParaFiltro(cboEtiqueta);
		inicializarComboBoxEtiqueta(cboEliminarEtiqueta);
		inicializarComboBoxEtiqueta(cboAsignarEtiqueta);
	}

	public void rellenarTabla(List<Notificacion> lista) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
	    for (Notificacion n: lista) {
	    	Object[] fila = new Object[7];
	    	
	    	fila[0] = n.getId();
	    	fila[1] = formatter.format(n.getFecha_envio());
	    	fila[2] = n.getContenido();
	    	fila[3] = n.getContexto();
	    	fila[4] = n.getCategoria();
	    	fila[5] = n.getNino();
	    	fila[6] = n.getEtiquetasComoString();
		
	    	modeloTabla.addRow(fila);
	    }
	}
	
/*	private static void requestMonitor(String notificacion){
		try{
			URL url = new URL("http://localhost");
			HttpURLConnection coneccion = (HttpURLConnection) url.openConnection();
			coneccion.setReadTimeout(5000);
			coneccion.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
			coneccion.addRequestProperty("data", notificacion);
			coneccion.connect();
			System.out.println("Request URL ... " + coneccion.getResponseCode());
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
	}*/
	
	/**
	 * Create the frame.
	 */
	public MonitorView() {
		setTitle("Hermes Monitor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1085, 704);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane); 
		contentPane.setLayout(null);
		JPanel panelFiltros = new JPanel();
		panelFiltros.setBounds(5, 5, 551, 256);
		contentPane.add(panelFiltros);
		panelFiltros.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		JLabel label = new JLabel("Contenido");
		label.setBounds(26, 35, 73, 15);
		
		JLabel label_1 = new JLabel("Contexto");
		label_1.setBounds(26, 60, 70, 29);
		
		cboEtiqueta = new JComboBox<Etiqueta>();
		cboEtiqueta.setBounds(383, 62, 152, 24);
		
		JDateChooser dcFechaDesde = new JDateChooser();
		dcFechaDesde.setBounds(120, 183, 152, 26);

		JDateChooser dcFechaHasta = new JDateChooser();
		dcFechaHasta.setBounds(282, 183, 152, 26);
		
		try {
			dcFechaDesde.setDate(formatter.parse("01/01/2015 00:01"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		                        
	    Calendar c = Calendar.getInstance();        
	    c.add(Calendar.DATE, 1);  
	    Date tomorrow = c.getTime();

		dcFechaHasta.setDate(tomorrow);
		
		panelFiltros.add(dcFechaDesde);
		panelFiltros.add(dcFechaHasta);
		
		btnFiltrar = new JButton("Filtrar");
		
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date fecha_desde = new Date();
				Date fecha_hasta = new Date();
				
				fecha_desde = dcFechaDesde.getDate();
				fecha_hasta = dcFechaHasta.getDate();
				
				contenido = (Contenido)cboContenido.getModel().getSelectedItem();
				contexto = (Contexto)cboContexto.getModel().getSelectedItem();
				categoria = (Categoria)cboCategoria.getModel().getSelectedItem();
				nino = (Nino)cboNino.getModel().getSelectedItem();
				etiqueta = (Etiqueta)cboEtiqueta.getModel().getSelectedItem();
				
				INotificacionDAO notificacionDAO = FactoriaDAO.getNotificacionDAO();
				List<Notificacion> lista = notificacionDAO.filtrarNotificaciones(fecha_desde, fecha_hasta, contenido, 
						contexto, categoria, nino, etiqueta);
				
				modeloTabla.setRowCount(0);
				rellenarTabla(lista);
				
			}
		});
		btnFiltrar.setBounds(120, 220, 152, 25);
		
		JLabel label_2 = new JLabel("Nin@");
		label_2.setBounds(26, 100, 70, 15);
		
		JLabel label_3 = new JLabel("Fecha/Hora");
		label_3.setBounds(26, 140, 95, 15);
		
		JLabel label_4 = new JLabel("desde");
		label_4.setBounds(120, 160, 70, 15);
		
		JLabel label_5 = new JLabel("hasta");
		label_5.setBounds(285, 160, 70, 15);
		
		cboContenido = new JComboBox<Contenido>();	
		cboContenido.setBounds(120, 30, 152, 24);
		
		cboContexto = new JComboBox<Contexto>();
		cboContexto.setBounds(120, 62, 152, 24);
		
		cboNino = new JComboBox<Nino>();	
		cboNino.setBounds(120, 95, 152, 24);
		
		JLabel label_6 = new JLabel("Etiqueta");
		label_6.setBounds(303, 67, 70, 15);
		
		JLabel lblNewLabel = new JLabel("Categoria");
		lblNewLabel.setBounds(303, 35, 73, 14);
		
		cboCategoria = new JComboBox<Categoria>();
		cboCategoria.setBounds(383, 30, 152, 24);
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
		panelFiltros.add(label_6);
		panelFiltros.add(cboEtiqueta);
		panelFiltros.add(btnFiltrar);
		
		JButton btnMostrarTodo = new JButton("Mostrar Todo");
		btnMostrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modeloTabla.setRowCount(0);
	            INotificacionDAO notificacionDAO = FactoriaDAO.getNotificacionDAO();
	            List<Notificacion> lista = notificacionDAO.listarNotificaciones();
	            rellenarTabla(lista);
	            lblNotificaciones.setVisible(false);
			}
		});
		btnMostrarTodo.setBounds(383, 220, 152, 25);
		panelFiltros.add(btnMostrarTodo);
		

		
		JPanel panelEtiquetas = new JPanel();
		panelEtiquetas.setBounds(567, 5, 495, 256);
		contentPane.add(panelEtiquetas);
		panelEtiquetas.setBorder(new TitledBorder(null, "Etiquetas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel label_7 = new JLabel("Crear etiqueta");
		label_7.setBounds(23, 34, 104, 15);
		
		txtCrearEtiqueta = new JTextField();
		txtCrearEtiqueta.setBounds(160, 29, 152, 24);
		txtCrearEtiqueta.setColumns(10);
		
		JLabel label_8 = new JLabel("Eliminar etiqueta");
		label_8.setBounds(23, 70, 127, 15);
		
		cboEliminarEtiqueta = new JComboBox<Etiqueta>();
		cboEliminarEtiqueta.setBounds(160, 65, 152, 24);
		
		JLabel label_9 = new JLabel("Asignar etiqueta");
		label_9.setBounds(23, 106, 127, 15);
		
		cboAsignarEtiqueta = new JComboBox<Etiqueta>();
		cboAsignarEtiqueta.setBounds(160, 101, 152, 24);
		
		JLabel label_10 = new JLabel("Renombrar etiqueta");
		label_10.setBounds(23, 142, 127, 15);
		
		cboRenombrarEtiqueta = new JComboBox<Etiqueta>();
		cboRenombrarEtiqueta.setBounds(160, 137, 152, 24);
		cboRenombrarEtiqueta.addActionListener(new ComboEditarEtiquetaListener());
		
		JLabel label_11 = new JLabel("Nuevo nombre");
		label_11.setBounds(23, 178, 117, 15);
		
		txtNuevoNombre = new JTextField();
		txtNuevoNombre.setBounds(160, 173, 152, 24);
		txtNuevoNombre.setColumns(10);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(326, 28, 152, 25);
		btnCrear.addActionListener(new BotonCrearEtiquetaListener());
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(326, 65, 152, 25);
		btnEliminar.addActionListener(new BotonEliminarEtiquetaListener());
		
		JButton btnAsignardesasig = new JButton("Asignar/Desasig.");
		btnAsignardesasig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				
				etiqueta = (Etiqueta)cboAsignarEtiqueta.getModel().getSelectedItem();
				int filaseleccionada = table.getSelectedRow();
			         
				if (filaseleccionada == -1) {
			             JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
			    } else {
			             DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			             String etiquetas = (String) modelo.getValueAt(filaseleccionada, 6);
		            	 Integer id_notificacion = (Integer) modelo.getValueAt(filaseleccionada, 0);
		            	 IEtiquetaDAO etiquetaDAO = FactoriaDAO.getEtiquetaDAO();
			             if (!etiquetas.contains(etiqueta.getTexto())) {		         	 
			            	 etiquetaDAO.asignarEtiqueta(id_notificacion, etiqueta.getId());
			             } else {
			            	 etiquetaDAO.desasignarEtiqueta(id_notificacion, etiqueta.getId());
			             }
			             btnFiltrar.doClick();
			    }
			}
		});
		btnAsignardesasig.setBounds(326, 101, 152, 25);
		
		JButton btnRenombrar = new JButton("Renombrar");
		btnRenombrar.setBounds(326, 172, 152, 25);
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
		panelNotificaciones.setBounds(5, 307, 1057, 297);
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
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
		);
		
		table = new JTable();
		modeloTabla = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"#", "Fecha/Hora envio", "Contenido", "Contexto", "Categoria", "Nin@", "Etiquetas", 
			}
		);
		table.setModel(modeloTabla);
		
		table.getColumnModel().getColumn(0).setMaxWidth(46);
		table.getColumnModel().getColumn(0).setPreferredWidth(29);
		
		table.getColumnModel().getColumn(3).setPreferredWidth(28);
		
		INotificacionDAO notificacionDAO = FactoriaDAO.getNotificacionDAO();
		List<Notificacion> lista = notificacionDAO.listarNotificaciones();
		rellenarTabla(lista);
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(modeloTabla);
		table.setRowSorter(sorter);
		scrollPane.setViewportView(table);
		panelNotificaciones.setLayout(gl_panelNotificaciones);
		
		inicializarComboBoxCategoria(cboCategoria);
		inicializarComboBoxContenido(cboContenido);
		inicializarComboBoxContexto(cboContexto);
		inicializarComboBoxNino(cboNino);
		
		refrescarComboBoxEtiqueta();
		
//		LecturaJSON lector = new LecturaJSON();
//		lector.cargarNotificaciones("notificaciones.txt");
		
		txtNuevoNombre.setText(cboRenombrarEtiqueta.getSelectedItem().toString());
		
		lblNotificaciones = new JLabel("");
		lblNotificaciones.setForeground(Color.RED);
		lblNotificaciones.setBounds(15, 272, 439, 24);
		contentPane.add(lblNotificaciones);
	}

	private class BotonCrearEtiquetaListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String texto = txtCrearEtiqueta.getText();
			if (!texto.equals("")){
				IEtiquetaDAO etiquetaDAO = FactoriaDAO.getEtiquetaDAO();
				Etiqueta etiqueta = new Etiqueta(texto);
				etiquetaDAO.guardarEtiqueta(etiqueta);
				refrescarComboBoxEtiqueta();
				txtCrearEtiqueta.setText("");
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
				IEtiquetaDAO etiquetaDAO = FactoriaDAO.getEtiquetaDAO();
				Etiqueta etiquetaNueva = new Etiqueta(texto);
				Etiqueta etiquetaOriginal = (Etiqueta) cboRenombrarEtiqueta.getSelectedItem();
				etiquetaDAO.renombrarEtiqueta(etiquetaOriginal, etiquetaNueva);
				refrescarComboBoxEtiqueta();
				txtNuevoNombre.setText(cboRenombrarEtiqueta.getSelectedItem().toString());
				
				btnFiltrar.doClick(); //para que se refresque la tabla usando los filtros
			}	
		}
	}
	
	private class BotonEliminarEtiquetaListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int respuesta = JOptionPane.showConfirmDialog(null, "¿Eliminar etiqueta? Esta acción es definitiva");
			
			if (respuesta==JOptionPane.YES_OPTION){
				Etiqueta etiqueta = (Etiqueta) cboEliminarEtiqueta.getSelectedItem();
				FactoriaDAO.getEtiquetaDAO().eliminarEtiqueta(etiqueta);
				refrescarComboBoxEtiqueta();
				txtNuevoNombre.setText(cboRenombrarEtiqueta.getSelectedItem().toString());
			}	
		}		
	}
}
