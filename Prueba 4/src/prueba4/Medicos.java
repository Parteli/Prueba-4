package prueba4;

import java.io.Serializable;

public class Medicos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dni;
	private String nombre;
	private String apellidos;
	private String especialidad;
	
	public Medicos() {}

	public Medicos(String dni, String nombre, String apellidos, String especialidad) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.especialidad = especialidad;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	@Override
	public String toString()
	{
		return "Medico - DNI: " + getDni() + " |Nombre: " + getNombre() + " " + getApellidos() +
				" |Especialidad: " + getEspecialidad();
	}
}
