package prueba4;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Clase que almacena las informaciones de los m�dicos y pacientes.
 * Verificar� su lista local antes de hablar con el banco de datos.
 * ** Todas las funciones de esta clase necesitan una referencia
 *  de la Session para garantir que es una sesion valida **
 * @author Murilo
 *
 */
public class Hospital {

	/**
	 * Lista local de m�dicos para almacenamiento de los datos.
	 */
	private List<Medicos> listaMedicos;
	/**
	 * Lista local de pacientes para almacenamiento de los datos.
	 */
	private List<Pacientes> listaPacientes;
	
	/**
	 * Constructor de la clase Hospital.
	 * @param sesion
	 */
	public Hospital(Session sesion) {
		
		ActualizarListaMedicos(sesion);
		ActualizarListaPacientes(sesion);
	}
	
	/*----------- Medicos -----------------*/
	
	/**
	 * Agregue un nuevo m�dico a la lista.
	 * Comprueba en la lista local si el m�dico ya existe.
	 * @param sesion Sesi�n actual del sistema.
	 * @param medico Referencia del m�dico para agregar
	 */
	public void AltaMedico(Session sesion, Medicos medico) {
		
		for( Medicos entity : listaMedicos )
		{
			if(entity.getDni().equals(medico.getDni()) )
			{
				System.out.println("ERROR: Este medico ya esta cadastrado");
				return;
			}
		}
		sesion.beginTransaction();
		sesion.save(medico);
		sesion.getTransaction().commit();
		listaMedicos.add(medico);
		System.out.println("M�dico adicionado con �xito");
	}
	
	/**
	 * Elimina un m�dico de la lista.
	 * Comprueba en la lista local si el m�dico existe.
	 * @param sesion Sesi�n actual del sistema.
	 * @param dni Identificador de lo m�dico.
	 */
	public void BajaMedico(Session sesion, String dni) {
		for( Medicos entity : listaMedicos )
		{
			if(dni.equals(entity.getDni()) )
			{
				sesion.beginTransaction();
				sesion.delete(entity);
				listaMedicos.remove(entity);
				sesion.getTransaction().commit();
				System.out.println("M�dico eliminado con �xito");
				return;
			}
		}
		System.out.println("ERROR: M�dico no encontrado");
	}

	/**
	 * Busca un m�dico en la Base de Datos.
	 * @param sesion Sesi�n actual del sistema.
	 * @param dni Identificador de lo m�dico.
	 */
	public Medicos BusquedaMedico (Session sesion, String dni) {
		
		return (Medicos)sesion.get(Medicos.class, dni);
	}

	/**
	 * Busca un nuevo m�dico ea la lista local.
	 * @param dni Identificador de lo m�dico.
	 */
	public Medicos BusquedaMedico (String dni) {
		
		for( Medicos entity : listaMedicos )
		{
			if(dni.equals(entity.getDni()) )
			{
				return entity;
			}
		}
		System.out.println("ERROR: M�dico no encontrado");
		return null;
	}

	/**
	 * Actualiza un m�dico existente.
	 * Comprueba en la lista local si el m�dico ya existe.
	 * @param sesion Sesi�n actual del sistema.
	 * @param medico Referencia del m�dico para actualizar.
	 */
	public void ActualizarMedico(Session sesion, Medicos medico) {
		
		for(int i = 0; i < listaMedicos.size(); i++)
		{
			if(medico.getDni().equals(listaMedicos.get(i).getDni()) )
			{
				sesion.beginTransaction();
				sesion.update(medico);
				sesion.getTransaction().commit();
				listaMedicos.set(i, medico);
				System.out.println("M�dico actualizado con �xito");
				return;
			}
		}
		System.out.println("ERROR: M�dico no encontrado");
	}

	/**
	 * Actualiza la lista local con la lista del banco de datos
	 * @param sesion Sesi�n actual del sistema.
	 */
	public void ActualizarListaMedicos(Session sesion)
	{
		Query query = sesion.createQuery("SELECT m FROM Medicos m");
		listaMedicos = query.list();
	}


	/*----------- Pacientes -----------------*/

	/**
	 * Agregue un nuevo paciente a la lista.
	 * Comprueba en la lista local si el m�dico ya existe.
	 * @param sesion Sesi�n actual del sistema.
	 * @param paciente Referencia del paciente para agregar
	 */
	public void AltaPaciente(Session sesion, Pacientes paciente) {
		
		for( Pacientes entity : listaPacientes )
		{
			if(entity.getDni().equals(paciente.getDni()) )
			{
				System.out.println("ERROR: Este paciente ya esta cadastrado");
				return;
			}
		}
		sesion.beginTransaction();
		sesion.save(paciente);
		sesion.getTransaction().commit();
		listaPacientes.add(paciente);
		System.out.println("Paciente adicionado con �xito");
	}

	/**
	 * Elimina un paciente de la lista.
	 * Comprueba en la lista local si el paciente existe.
	 * @param sesion Sesi�n actual del sistema.
	 * @param dni Identificador de lo paciente.
	 */
	public void BajaPaciente(Session sesion, String dni) {
		for( Pacientes entity : listaPacientes )
		{
			if(dni.equals(entity.getDni()) )
			{
				sesion.beginTransaction();
				sesion.delete(entity);
				listaPacientes.remove(entity);
				sesion.getTransaction().commit();
				System.out.println("Paciente eliminado con �xito");
				return;
			}
		}
		System.out.println("ERROR: Paciente no encontrado");
	}

	/**
	 * Busca un paciente en la Base de Datos.
	 * @param sesion Sesi�n actual del sistema.
	 * @param dni Identificador de lo paciente.
	 */
	public Pacientes BusquedaPaciente (Session sesion, String dni) {
		
		return (Pacientes)sesion.get(Pacientes.class, dni);
	}
	/**
	 * Busca un paciente en la lista local.
	 * @param dni Identificador de lo paciente.
	 */
	public Pacientes BusquedaPaciente (String dni) {
		
		for( Pacientes entity : listaPacientes )
		{
			if(dni.equals(entity.getDni()) )
			{
				return entity;
			}
		}
		System.out.println("ERROR: Paciente no encontrado");
		return null;
	}
	/**
	 * Actualiza un paciente existente.
	 * Comprueba en la lista local si el paciente ya existe.
	 * @param sesion Sesi�n actual del sistema.
	 * @param paciente Referencia del paciente para actualizar.
	 */
	public void ActualizarPaciente(Session sesion, Pacientes paciente) {
		
		for(int i = 0; i < listaPacientes.size(); i++)
		{
			if(paciente.getDni().equals(listaPacientes.get(i).getDni()) )
			{
				sesion.beginTransaction();
				sesion.update(paciente);
				sesion.getTransaction().commit();
				listaPacientes.set(i, paciente);
				System.out.println("Paciente actualizado con �xito");
				return;
			}
		}
		System.out.println("ERROR: Paciente no encontrado");
	}


	/**
	 * Actualiza la lista local con la lista del banco de datos
	 * @param sesion Sesi�n actual del sistema.
	 */
	public void ActualizarListaPacientes(Session sesion)
	{
		Query query = sesion.createQuery("SELECT p FROM Pacientes p");
		listaPacientes = query.list();
	}
}
