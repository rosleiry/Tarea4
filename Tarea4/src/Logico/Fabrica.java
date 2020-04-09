package Logico;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



public class Fabrica {
	private ArrayList<Queso>misQuesos;
	private ArrayList<Factura>misFacturas;
	private ArrayList<Cliente>misClientes;
	private static Fabrica fabri = null;
	private static int codQueso = 1;
	
	public Fabrica() {
		super();
		this.misQuesos = new ArrayList<Queso>();
		this.misFacturas = new ArrayList<Factura>();
		this.misClientes = new ArrayList<Cliente>();
	}
	public static Fabrica getInstance(){
		 if(fabri == null){
			 fabri = new Fabrica();
		 }
		 
		 return fabri;
	 } 
	
	public static Fabrica getTienda() {
		return fabri;
	}


	public static void setFabrica(Fabrica fabri) {
		Fabrica.fabri = fabri;
	}

	public ArrayList<Queso> getMisQuesos() {
		return misQuesos;
	}

	public void setMisQuesos(ArrayList<Queso> misQuesos) {
		this.misQuesos = misQuesos;
	}

	public ArrayList<Factura> getMisFacturas() {
		return misFacturas;
	}

	public void setMisFacturas(ArrayList<Factura> misFacturas) {
		this.misFacturas = misFacturas;
	}

	public ArrayList<Cliente> getMisClientes() {
		return misClientes;
	}

	public void setMisClientes(ArrayList<Cliente> misClientes) {
		this.misClientes = misClientes;
	}
	
	public int getCodQueso() {
		return codQueso;
	}
	public void aumentar() {
		codQueso += 1;
	}
	
	public Cliente buscarClienteByID(String codigo) {
		Cliente aux = null;
		boolean encontrado = false;
		int i = 0;
	
		while (i<misClientes.size() && !encontrado) {
			if(misClientes.get(i).getId().equalsIgnoreCase(codigo)){
				aux = misClientes.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	public Queso buscarQuesoByID(String codigo) {
		Queso aux = null;
		boolean encontrado = false;
		int i = 0;
		while (i<misQuesos.size() && !encontrado) {
			if(misQuesos.get(i).getId().equalsIgnoreCase(codigo)){
				aux = misQuesos.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	public Factura buscarFacturaByID(String codigo) {
		Factura aux = null;
		boolean encontrado = false;
		int i = 0;
		while (i<misFacturas.size() && !encontrado) {
			if(misFacturas.get(i).getId().equalsIgnoreCase(codigo)){
				aux = misFacturas.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	public float totalFactura(String codigo) {
		Factura aux = buscarFacturaByID(codigo);
		if(aux != null) {
			return aux.totalFactura();
		}
		return -1;
	}
	
	public int[] totalQuesoPorTipo() {
		int[] quesos = {0,0,0};
		for (Queso aux : misQuesos) {
			if(aux instanceof Esfera) {
				quesos[0]++;
			}
			if(aux instanceof Cilindro) {
				quesos[1]++;
			}
			if(aux instanceof CilindroHueco) {
				quesos[2]++;
			}
		}
		return quesos;
	}
	
	public Esfera esferaMayorDeFactura(String codigo) {
		Factura aux = buscarFacturaByID(codigo);
		if(aux != null) {
			return aux.esferaMayor();
		}
		return null;
		
	}
	
	public void cargarDatos() {
		try {
			FileInputStream input = new FileInputStream("Data/Fabrica.txt");
			ObjectInputStream inputFabrica = new ObjectInputStream(input);
			
			Fabrica aux = (Fabrica) inputFabrica.readObject();
			Fabrica.setFabrica(aux);
			
			inputFabrica.close();
			input.close();
			
		} catch (ClassNotFoundException e) {
			System.out.println("Error: Clase no encontrada");
		} catch(FileNotFoundException e) {
			guardarDatos();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void guardarDatos() {
		
		try {	
			 FileOutputStream output = new FileOutputStream("Data/Fabrica.txt");
			 ObjectOutputStream outputFabrica = new ObjectOutputStream(output);
			  
			 outputFabrica.writeObject(fabri);
				
			 outputFabrica.close();
			 output.close();
		
		}catch (FileNotFoundException e) {
			System.out.println("Error: Archivo no Encontrado");
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
