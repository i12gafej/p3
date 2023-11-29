package es.uco.pw.business.asistente;

import java.util.*;
import java.time.LocalDate;
import java.io.FileNotFoundException;
import java.io.IOException;
import es.uco.pw.ficheros.*;
//import es.uco.pw.data.dao.*;
import es.uco.pw.data.dao.asistente.DAOasistente;

/**
 * Clase que gestiona los asistentes y proporciona un menú interactivo para dar de alta,
 * modificar y listar asistentes.
 * Los datos se guardan y recuperan de ficheros.
 * @author Manuel Cabrera Crespo
 * @author Javier García Fernández
 * @author Álvaro Eusebio Pérez
 * @author Francisco Javier Fernández Pastor
 */
public class GestorAsistentes{
    private ArrayList<DTOAsistente> listaAsistentes = new ArrayList<DTOAsistente>();
    

    Scanner leer = new Scanner(System.in);

	

    /**
     * Comprueba la existencia de un asistente en la lista de asistentes.
     *
     * @param id El identificador del asistente a comprobar.
     * @return {@code true} si el asistente existe, {@code false} si no.
     * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
     * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
     */  
    public boolean comprobarExistenciaAsistente(int id) throws FileNotFoundException, IOException{
		DAOasistente asistenteDAO = new DAOasistente();
    	//Fichero fichero =new Fichero();
    	listaAsistentes=asistenteDAO.ListaAsistentes();
    	
    	for(DTOAsistente a : listaAsistentes) {
    		if(a.getIdentificador()==id) {
    			return true;
    		}
    	}
    	return false;
    }
    

	/**
     * Permite dar de alta a un nuevo asistente.
     * @return true si el asistente se da de alta exitosamente, false si no se pudo dar de alta.
     * @throws FileNotFoundException Si hay un error al acceder a un archivo.
     * @throws IOException Si ocurre un error de lectura o escritura de datos.
     */
    @SuppressWarnings("static-access")
	public boolean altaAsistente(DTOAsistente asistente) throws FileNotFoundException, IOException{
		DAOasistente asistenteDAO = new DAOasistente();
		
			
		if(comprobarExistenciaAsistente(asistente.getIdentificador())==false) {
			return asistenteDAO.Almacenar(asistente);
		}
		return false;
	}

	/**
     * Permite modificar los datos de un asistente existente.
     * @return true si el asistente se modifica exitosamente, false si no se pudo modificar.
     * @throws FileNotFoundException Si hay un error al acceder a un archivo.
     * @throws IOException Si ocurre un error de lectura o escritura de datos.
     */
    @SuppressWarnings("static-access")
	public boolean modificarAsistente(DTOAsistente nuevo) throws FileNotFoundException, IOException{
	
		DAOasistente asistenteDAO = new DAOasistente();
		
		return asistenteDAO.Modificar(nuevo);

      		
	}
    
    /**
     * Devuelve el asistente asignado a un ID.
     *
     * @param id El identificador del asistente a buscar.
     * @return El objeto {@code DTOAsistente} correspondiente al ID proporcionado, o un objeto vacío si no se encuentra.
     * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
     * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
     */
    
    public DTOAsistente asistentePorID(int id)throws FileNotFoundException, IOException{ //devuelve el asistente asignado a un id
    	DTOAsistente a=new DTOAsistente();
		DAOasistente asistenteDAO = new DAOasistente();
    	//Fichero fichero=new Fichero();
    	//listaAsistentes=fichero.cargarFicheroAsistente();
		listaAsistentes=asistenteDAO.ListaAsistentes();

    	for(DTOAsistente aux : listaAsistentes) {
    		if(aux.getIdentificador()==id) {
    			a=aux;
    		}
    	}
    	return a;
    }
   

	/**
     * Lista todos los asistentes registrados.
     * @throws FileNotFoundException Si hay un error al acceder a un archivo.
     * @throws IOException Si ocurre un error de lectura o escritura de datos.
     */
	public void listadoAsistente()throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaAsistentes = fichero.cargarFicheroAsistente();
		DAOasistente asistenteDAO = new DAOasistente();
		listaAsistentes = asistenteDAO.ListaAsistentes();

		for(DTOAsistente a : listaAsistentes) {
			System.out.println(a.toString());
		}
	}
}