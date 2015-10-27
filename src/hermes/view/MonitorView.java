package hermes.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.Box;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

public class MonitorView extends JFrame {

	private JPanel contentPane;
	private JTextField txtCrearEtiqueta;
	private JTextField txtNuevoNombre;
	private final JPanel panelFiltros = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

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

	/**
	 * Create the frame.
	 */
	public MonitorView() {
		setTitle("Hermes Monitor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1108, 629);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panelFiltros.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFiltros.setBounds(19, 12, 528, 286);
		contentPane.add(panelFiltros);
		panelFiltros.setLayout(null);
		
		JLabel label = new JLabel("Contenido");
		label.setBounds(26, 35, 73, 15);
		panelFiltros.add(label);
		
		JLabel label_1 = new JLabel("Contexto");
		label_1.setBounds(26, 62, 70, 29);
		panelFiltros.add(label_1);
		
		JComboBox cboContenido = new JComboBox();
		cboContenido.setBounds(120, 30, 152, 24);
		panelFiltros.add(cboContenido);
		
		JComboBox cboContexto = new JComboBox();
		cboContexto.setBounds(120, 64, 152, 24);
		panelFiltros.add(cboContexto);
		
		JLabel label_2 = new JLabel("Niñ@");
		label_2.setBounds(26, 103, 70, 15);
		panelFiltros.add(label_2);
		
		JComboBox cboNiño = new JComboBox();
		cboNiño.setBounds(120, 100, 152, 24);
		panelFiltros.add(cboNiño);
		
		JLabel label_3 = new JLabel("Fecha/Hora");
		label_3.setBounds(26, 131, 95, 15);
		panelFiltros.add(label_3);
		
		JLabel label_4 = new JLabel("desde");
		label_4.setBounds(120, 153, 70, 15);
		panelFiltros.add(label_4);
		
		JLabel label_5 = new JLabel("hasta");
		label_5.setBounds(287, 151, 70, 15);
		panelFiltros.add(label_5);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(118, 177, 154, 26);
		panelFiltros.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(285, 177, 154, 26);
		panelFiltros.add(textField_1);
		
		JComboBox cboEtiqueta = new JComboBox();
		cboEtiqueta.setBounds(120, 211, 152, 26);
		panelFiltros.add(cboEtiqueta);
		
		JLabel label_6 = new JLabel("Etiqueta");
		label_6.setBounds(26, 217, 70, 15);
		panelFiltros.add(label_6);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(118, 249, 154, 25);
		panelFiltros.add(btnFiltrar);
		
		JPanel panelEtiquetas = new JPanel();
		panelEtiquetas.setBorder(new TitledBorder(null, "Etiquetas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEtiquetas.setBounds(574, 12, 516, 286);
		contentPane.add(panelEtiquetas);
		panelEtiquetas.setLayout(null);
		
		JLabel label_7 = new JLabel("Crear etiqueta");
		label_7.setBounds(23, 40, 104, 15);
		panelEtiquetas.add(label_7);
		
		txtCrearEtiqueta = new JTextField();
		txtCrearEtiqueta.setBounds(184, 36, 154, 24);
		panelEtiquetas.add(txtCrearEtiqueta);
		txtCrearEtiqueta.setColumns(10);
		
		JLabel label_8 = new JLabel("Eliminar etiqueta");
		label_8.setBounds(23, 77, 127, 15);
		panelEtiquetas.add(label_8);
		
		JComboBox cboEliminarEtiqueta = new JComboBox();
		cboEliminarEtiqueta.setBounds(184, 72, 154, 24);
		panelEtiquetas.add(cboEliminarEtiqueta);
		
		JLabel label_9 = new JLabel("Asignar etiqueta");
		label_9.setBounds(23, 113, 127, 15);
		panelEtiquetas.add(label_9);
		
		JComboBox cboAsignarEtiqueta = new JComboBox();
		cboAsignarEtiqueta.setBounds(184, 108, 154, 24);
		panelEtiquetas.add(cboAsignarEtiqueta);
		
		JLabel label_10 = new JLabel("Renombrar etiqueta");
		label_10.setBounds(23, 149, 143, 15);
		panelEtiquetas.add(label_10);
		
		JComboBox cboRenombrarEtiqueta = new JComboBox();
		cboRenombrarEtiqueta.setBounds(184, 144, 154, 24);
		panelEtiquetas.add(cboRenombrarEtiqueta);
		
		JLabel label_11 = new JLabel("Nuevo nombre");
		label_11.setBounds(23, 184, 117, 15);
		panelEtiquetas.add(label_11);
		
		txtNuevoNombre = new JTextField();
		txtNuevoNombre.setBounds(184, 180, 154, 24);
		panelEtiquetas.add(txtNuevoNombre);
		txtNuevoNombre.setColumns(10);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(350, 35, 154, 25);
		panelEtiquetas.add(btnCrear);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(350, 72, 154, 25);
		panelEtiquetas.add(btnEliminar);
		
		JButton btnAsignardesasig = new JButton("Asignar/Desasig.");
		btnAsignardesasig.setBounds(350, 108, 154, 25);
		panelEtiquetas.add(btnAsignardesasig);
		
		JButton btnRenombrar = new JButton("Renombrar");
		btnRenombrar.setBounds(350, 179, 154, 25);
		panelEtiquetas.add(btnRenombrar);
		
		JPanel panelNotificaciones = new JPanel();
		panelNotificaciones.setBorder(new TitledBorder(null, "Notificaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelNotificaciones.setBounds(19, 324, 1071, 268);
		contentPane.add(panelNotificaciones);
	}
}
