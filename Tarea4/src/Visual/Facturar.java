package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import Logico.CilindroHueco;
import Logico.Cliente;
import Logico.Esfera;
import Logico.Fabrica;
import Logico.Factura;
import Logico.Queso;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import java.awt.Toolkit;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.List;

public class Facturar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtid;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private static DefaultListModel<String> model1;
	private static DefaultListModel<String> model2;
	private JTextField txttotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Facturar dialog = new Facturar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model1 = new DefaultListModel<String>();
		model2 = new DefaultListModel<String>();
	}

	/**
	 * Create the dialog.
	 * @throws ParseException 
	 */
	public Facturar() throws ParseException {
		setTitle("Facturar");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Facturar.class.getResource("/Auxiliares/receipt.png")));
		setBounds(100, 100, 546, 407);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Buscar Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(4, 2, 521, 123);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(12, 20, 46, 14);
		panel.add(lblCodigo);
		
		MaskFormatter mask1 = new MaskFormatter("C-#");
		JFormattedTextField txtid = new JFormattedTextField(mask1);
		txtid.setToolTipText("C-#");
		txtid.setBounds(75, 17, 129, 20);
		panel.add(txtid);
		txtid.setColumns(10);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String codigo = txtid.getText();
				Cliente aux = Fabrica.getInstance().buscarClienteByID(codigo);
				if(aux != null) {
					txtNombre.setText(aux.getNombre().toString()); 
					txtNombre.setEditable(false);
					txtDireccion.setText(aux.getDireccion().toString());
					txtDireccion.setEditable(false);
					txtTelefono.setText(aux.getTelefono().toString());
					txtTelefono.setEditable(false);
					JOptionPane.showMessageDialog(null, "Cliente existente", "Notificación", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					txtNombre.setEnabled(true);
					txtDireccion.setEnabled(true);
					txtNombre.setText("");
					txtDireccion.setText("");
					JOptionPane.showMessageDialog(null, "Cliente no existe.\n Por favor ingrese los datos", "Notificación", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setIcon(new ImageIcon(Facturar.class.getResource("/Auxiliares/magnifying-glass.png")));
		btnNewButton.setBounds(214, 16, 46, 23);
		panel.add(btnNewButton);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 54, 56, 14);
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setBounds(75, 51, 185, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(270, 54, 67, 14);
		panel.add(lblTelfono);
			
		MaskFormatter mask = new MaskFormatter("(###) ###-####");
		JFormattedTextField txtTelefono = new JFormattedTextField(mask);
		txtTelefono.setBounds(324, 51, 191, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(12, 88, 67, 14);
		panel.add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.setEnabled(false);
		txtDireccion.setBounds(75, 85, 440, 20);
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Factura", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(4, 128, 521, 196);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblQuesosDisponibles = new JLabel("Quesos disponibles:");
		lblQuesosDisponibles.setBounds(86, 12, 118, 14);
		panel_1.add(lblQuesosDisponibles);
		
		JLabel lblCarrito = new JLabel("Carrito:");
		lblCarrito.setBounds(356, 12, 46, 14);
		panel_1.add(lblCarrito);
		

		List listQueso = new List();
		listQueso.setBounds(57, 38, 162, 114);
		panel_1.add(listQueso);
		for (Queso aux : Fabrica.getInstance().getMisQuesos()) {
			String tipo;
			if(aux instanceof Esfera) {
				tipo = "Esfera";
			}else {
			if(aux instanceof CilindroHueco) {
				tipo = "Cilindro Hueco";
				}else {
					tipo = "Cilindro";
				}
			}
			String aux2 = aux.getId().toString() +" - "+ tipo + " - "+ aux.PrecioReal();
			listQueso.add(aux2);
		}
		
		List listCarrito = new List();
		listCarrito.setBounds(301, 38, 162, 114);
		JButton button = new JButton(">");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected = listQueso.getSelectedItem().toString();
				listCarrito.add(selected);
				listQueso.remove(listQueso.getSelectedItem());
				float total = 0;
				
				for (int i = 0; i < listCarrito.getItemCount(); i++) {
					int pos = listCarrito.getItem(i).indexOf(" -");
					String cod = listCarrito.getItem(i).substring(0, pos);
					Queso aux = Fabrica.getInstance().buscarQuesoByID(cod);
					total += aux.PrecioReal();
				}
				
				txttotal.setText(Float.toString(total));
			}
		});
		button.setBounds(240, 63, 44, 23);
		panel_1.add(button);
		panel_1.add(listCarrito);
		
		JButton button_1 = new JButton("<");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected = listCarrito.getSelectedItem().toString();
				listQueso.add(selected);
				listCarrito.remove(listCarrito.getSelectedItem());
				float total = 0;
				for (int i = 0; i < listCarrito.getItemCount(); i++) {
					int pos = listCarrito.getItem(i).indexOf(" -");
					String cod = listCarrito.getItem(i).substring(0, pos);
					Queso aux = Fabrica.getInstance().buscarQuesoByID(cod);
					total += aux.PrecioReal();
				}
				
				txttotal.setText(Float.toString(total));
			}	
		});
		button_1.setBounds(240, 109, 44, 23);
		panel_1.add(button_1);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(301, 170, 48, 14);
		panel_1.add(lblTotal);
		
		txttotal = new JTextField();
		txttotal.setEditable(false);
		txttotal.setText("0.0");
		txttotal.setBounds(338, 167, 125, 20);
		panel_1.add(txttotal);
		txttotal.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Facturar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txtNombre.getText().equalsIgnoreCase("")|| txtDireccion.getText().equalsIgnoreCase("") || txtTelefono.getText().equalsIgnoreCase("") || txtid.getText().equalsIgnoreCase(""))
						{
							JOptionPane.showMessageDialog(null, "Campos del cliente vacios", "Notificación", JOptionPane.INFORMATION_MESSAGE);

						}else {
						Cliente aux = Fabrica.getInstance().buscarClienteByID(txtid.getText());
						if(aux != null) {
							if(txttotal.getText().equalsIgnoreCase("0.0")) {
								JOptionPane.showMessageDialog(null, "No se agregó ningún artículo al carrito", "Notificación", JOptionPane.INFORMATION_MESSAGE);

							}else {
								ArrayList<Queso>misquesos = new ArrayList<Queso>();
								
								for (int i = 0; i < listCarrito.getItemCount(); i++) {
									int pos = listCarrito.getItem(i).indexOf(" -");
									String cod = listCarrito.getItem(i).substring(0, pos);
									Queso queso = Fabrica.getInstance().buscarQuesoByID(cod);
									misquesos.add(queso);
									listCarrito.clear();
									Fabrica.getInstance().getMisQuesos().remove(posicionQueso(queso));
									
								}
								
								Factura fact = new Factura(aux, "F-"+(Fabrica.getInstance().getMisFacturas().size()+1), misquesos);
								Fabrica.getInstance().getMisFacturas().add(fact);
								JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Notificación", JOptionPane.INFORMATION_MESSAGE);
								clean();
							}
						}else {
							if(txttotal.getText().equalsIgnoreCase("0.0")) {
								JOptionPane.showMessageDialog(null, "No se agregó ningún artículo al carrito", "Notificación", JOptionPane.INFORMATION_MESSAGE);

							}else {
							aux = new Cliente(txtNombre.getText(), txtDireccion.getText(), txtTelefono.getText(), txtid.getText());
							Fabrica.getInstance().getMisClientes().add(aux);
							ArrayList<Queso>misquesos = new ArrayList<Queso>();
							
							for (int i = 0; i < listCarrito.getItemCount(); i++) {
								int pos = listCarrito.getItem(i).indexOf(" -");
								String cod = listCarrito.getItem(i).substring(0, pos);
								Queso queso = Fabrica.getInstance().buscarQuesoByID(cod);
								misquesos.add(queso);
								listCarrito.remove(i);
								Fabrica.getInstance().getMisQuesos().remove(posicionQueso(queso));
								
							}
							
							Factura fact = new Factura(aux, "F-"+(Fabrica.getInstance().getMisFacturas().size()+1), misquesos);
							Fabrica.getInstance().getMisFacturas().add(fact);
							JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Notificación", JOptionPane.INFORMATION_MESSAGE);
							clean();
						}
						}
					}}

					private int posicionQueso(Queso queso) {
						int aux = 0;
						boolean encontrado = false;
						int i = 0;
						while (i < Fabrica.getInstance().getMisQuesos().size() && !encontrado) {
							if(Fabrica.getInstance().getMisQuesos().get(i) == queso){
								aux = i;
								encontrado = true;
							}
							i++;
						}
							return aux;
				
					}

					private void clean() {
						txtid.setText("");
						txtDireccion.setText("");
						txtNombre.setText("");
						txtTelefono.setText("");
						
					}
				});
				okButton.setBackground(SystemColor.inactiveCaptionBorder);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setBackground(SystemColor.inactiveCaptionBorder);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
