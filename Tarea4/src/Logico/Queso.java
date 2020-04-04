package Logico;

public abstract class Queso {
	protected String id;
	protected float precioBase;
	protected float precioUnitario;
	
	
	public Queso(float precioBase, float precioUnitario, String id) {
		super();
		this.precioBase = precioBase;
		this.precioUnitario = precioUnitario;
		this.id = id;
	}
	
	public abstract float volumen();
	public float PrecioReal() {
		return precioBase + precioUnitario * volumen(); 
	}

	public float getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(float precioBase) {
		this.precioBase = precioBase;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
}

