package prueba4;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Clase que almacena las informaciones de los médicos y pacientes.
 * Verificará su lista local antes de hablar con el banco de datos.
 * ** Todas las funciones de esta clase necesitan una referencia
 *  de la Session para garantir que es una sesion valida **
 * @author Murilo
 *
 */
public class Hospital {

	/**
	 * Lista local de médicos para almacenamiento de los datos.
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
	 * Agregue un nuevo médico a la lista.
	 * Comprueba en la lista local si el médico ya existe.
	 * @param sesion Sesión actual del sistema.
	 * @param medico Referencia del médico para agregar
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
		System.out.println("Médico adicionado con éxito");
	}
	
	/**
	 * Elimina un médico de la lista.
	 * Comprueba en la lista local si el médico existe.
	 * @param sesion Sesión actual del sistema.
	 * @param dni Identificador de lo médico.
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
				System.out.println("Médico eliminado con éxito");
				return;
			}
		}
		System.out.println("ERROR: Médico no encontrado");
	}

	/**
	 * Busca un médico en la Base de Datos.
	 * @param sesion Sesión actual del sistema.
	 * @param dni Identificador de lo médico.
	 */
	public Medicos BusquedaMedico (Session sesion, String dni) {
		
		return (Medicos)sesion.get(Medicos.class, dni);
	}

	/**
	 * Busca un nuevo médico ea la lista local.
	 * @param dni Identificador de lo médico.
	 */
	public Medicos BusquedaMedico (String dni) {
		
		for( Medicos entity : listaMedicos )
		{
			if(dni.equals(entity.getDni()) )
			{
				return entity;
			}
		}
		System.out.println("ERROR: Médico no encontrado");
		return null;
	}

	/**
	 * Actualiza un médico existente.
	 * Comprueba en la lista local si el médico ya existe.
	 * @param sesion Sesión actual del sistema.
	 * @param medico Referencia del médico para actualizar.
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
				System.out.println("Médico actualizado con éxito");
				return;
			}
		}
		System.out.println("ERROR: Médico no encontrado");
	}

	/**
	 * Actualiza la lista local con la lista del banco de datos
	 * @param sesion Sesión actual del sistema.
	 */
	public void ActualizarListaMedicos(Session sesion)
	{
		Query query = sesion.createQuery("SELECT m FROM Medicos m");
		listaMedicos = query.list();
	}


	/*----------- Pacientes -----------------*/

	/**
	 * Agregue un nuevo paciente a la lista.
	 * Comprueba en la lista local si el médico ya existe.
	 * @param sesion Sesión actual del sistema.
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
		System.out.println("Paciente adicionado con éxito");
	}

	/**
	 * Elimina un paciente de la lista.
	 * Comprueba en la lista local si el paciente existe.
	 * @param sesion Sesión actual del sistema.
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
				System.out.println("Paciente eliminado con éxito");
				return;
			}
		}
		System.out.println("ERROR: Paciente no encontrado");
	}

	/**
	 * Busca un paciente en la Base de Datos.
	 * @param sesion Sesión actual del sistema.
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
	 * @param sesion Sesión actual del sistema.
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
				System.out.println("Paciente actualizado con éxito");
				return;
			}
		}
		System.out.println("ERROR: Paciente no encontrado");
	}


	/**
	 * Actualiza la lista local con la lista del banco de datos
	 * @param sesion Sesión actual del sistema.
	 */
	public void ActualizarListaPacientes(Session sesion)
	{
		Query query = sesion.createQuery("SELECT p FROM Pacientes p");
		listaPacientes = query.list();
	}
}
