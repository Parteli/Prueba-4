package prueba4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/*No creé una interfaz de usuario para este ejercicio porque
 *  no se solicitó y lleva demasiado tiempo. Tengo problemas
 *   personales y necesito hacer los ejercicios rápidamente.*/
public class Aplicacion {

	public static void main(String[] args) {
		
		/*Las Tablas
		 * 
		CREATE DATABASE prueba4;
		
		USE prueba4;
		
		CREATE TABLE medicos (dni varchar(11) NOT NULL, nombre varchar(50),
		 	apellidos varchar(50), especialidad varchar(255),
			CONSTRAINT PK_DNI PRIMARY KEY(dni) );
		
		CREATE TABLE pacientes (dni varchar(11) NOT NULL, nombre varchar(50),
			apellidos varchar(50), CONSTRAINT PK_DNI PRIMARY KEY(dni) );
		*/
		
		SessionFactory sessionfactory;
		Configuration configuration = new Configuration();
		configuration.configure();
		
		ServiceRegistry serviceRegistry = 
				new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		
		sessionfactory = configuration.buildSessionFactory(serviceRegistry);

		
		Session sesion = sessionfactory.openSession();


		Hospital hospital = new Hospital(sesion);

		/*----------------------- INSERT ----------------------*/
		
		hospital.AltaMedico(sesion, new Medicos("111111111", "Andre", "Alvarez", "General"));
		hospital.AltaMedico(sesion, new Medicos("222222222", "Bernardo", "Buchi", "Dermatología"));
		hospital.AltaMedico(sesion, new Medicos("333333333", "Cezar", "Caldaro", "Oftalmología "));
		hospital.AltaMedico(sesion, new Medicos("444444444", "Daniela", "Damasco", "Voodoo"));
		
		hospital.AltaPaciente(sesion, new Pacientes("999999999", "Roberto", "Rodrigues"));
		hospital.AltaPaciente(sesion, new Pacientes("888888888", "Samanta", "Sanchez"));
	
		

		/*----------------------- SELECT ----------------------*/
		
		//Búsqueda en el banco de datos
		Medicos medico = hospital.BusquedaMedico(sesion, "222222222");
		System.out.println(medico.toString());
		
		//Búsqueda en la lista local
		Pacientes paciente = hospital.BusquedaPaciente("999999999");
		System.out.println(paciente.toString());
		
		/*----------------------- UPDATE ----------------------*/
		medico.setNombre("NOT Bernardo");
		hospital.ActualizarMedico(sesion, medico);


		/*------------------------ DELETE -----------------------------*/
		
		hospital.BajaMedico(sesion, medico.getDni());
		
		
		/*------------------------ ----  ----------------------------*/
		
		hospital.ActualizarListaMedicos(sesion);
		hospital.ActualizarListaMedicos(sesion);
		
		
		
		sesion.close();
		sessionfactory.close();
		
	} 
}
