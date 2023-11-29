package es.uco.pw.business.campamento;
import java.time.LocalDate;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException; 
//import es.uco.pw.ficheros.Fichero;
import es.uco.pw.business.asistente.*;
import es.uco.pw.business.actividad.*;
import es.uco.pw.business.monitor.*;
import es.uco.pw.data.dao.actividad.*;
import es.uco.pw.data.dao.monitor.*;
import es.uco.pw.data.dao.campamento.*;
import es.uco.pw.data.dao.inscripcion.DAOinscripcion;
import es.uco.pw.data.dao.asistente.*;


/**
 * Clase que gestiona los campamentos, actividades y monitores disponibles.
 * Permite la creación y asociación de actividades, monitores y campamentos,
 * así como la gestión de inscripciones. Los datos se guardan y recuperan de ficheros.
 * @author Manuel Cabrera Crespo
 * @author Javier García Fernández
 * @author Álvaro Eusebio Pérez
 * @author Francisco Javier Fernández Pastor
 */

public class GestorCampamentos{
	private ArrayList<DTOActividad> listaActividades = new ArrayList<DTOActividad>();	//Actividades que estan disponibles
	private ArrayList<DTOMonitor> listaMonitores = new ArrayList<DTOMonitor>();	//Monitores que estan disponibles
	private ArrayList<DTOCampamento> listaCampamentos = new ArrayList<DTOCampamento>();//Campamentos que estan disponibles

	Scanner leer = new Scanner(System.in);//Scanner para leer datos por teclado
	
	
	/**
	 * Comprueba la existencia de una actividad por su nombre.
	 *
	 * @param nombre El nombre de la actividad a buscar.
	 * @return true si la actividad existe, false en caso contrario.
	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */
	public boolean comprobarExistenciaActividad(String nombre) throws FileNotFoundException, IOException{

		DAOactividad actividadDAO = new DAOactividad();
		//Fichero fichero = new Fichero();
		listaActividades=actividadDAO.ListaActividades();
		
	    for(DTOActividad a : listaActividades) {
			if(a.getNombreActividad().equals(nombre)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Obtiene la actividad por su nombre.
	 *
	 * @param nombre El nombre de la actividad a buscar.
	 * @return La actividad correspondiente al nombre proporcionado.
	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */
	public DTOActividad actividadPorNombre(String nombre) throws FileNotFoundException, IOException{
		
		DAOactividad actividadDAO = new DAOactividad();
		listaActividades=actividadDAO.ListaActividades();

		DTOActividad actividad=new DTOActividad();
				
	    for(DTOActividad a : listaActividades) {
			if(a.getNombreActividad().equals(nombre)) {
				actividad=a;
			}
	    }
	    return actividad;
	}
	
	/**
	 * Imprime la lista de actividades.
	 *
	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */

	public void listarActividades() throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		DAOactividad actividadDAO = new DAOactividad();
		listaActividades=actividadDAO.ListaActividades();

		
	    for(DTOActividad a : listaActividades) {
			System.out.println(a.toString());
		}
	}

	/**
     * Crea una nueva actividad y la añade a la lista de actividades disponibles.
     * @return true si la actividad se crea exitosamente, false si no se pudo crear.
     * @throws FileNotFoundException Si hay un error al acceder a un archivo.
     * @throws IOException Si ocurre un error de lectura o escritura de datos.
     */
	public boolean crearActividad(DTOActividad actividad) throws FileNotFoundException, IOException{
		DAOactividad actividadDAO = new DAOactividad();
		
		//Fichero fichero = new Fichero();
		//listaActividades=actividadDAO.ListaActividades();
		
		if(comprobarExistenciaActividad(actividad.getNombreActividad())==false){
			return actividadDAO.Almacenar(actividad);
		}
		return false;
	}
	

	/**
	 * Verifica la existencia de un monitor mediante su identificador.
	 *
	 * @param id El identificador del monitor a verificar.
	 * @return true si el monitor existe, false si no.
	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */

	public boolean comprobarExistenciaMonitor(int id) throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaMonitores=fichero.cargarFicheroMonitor();
		DAOmonitor monitorDAO = new DAOmonitor();
		listaMonitores=monitorDAO.ListaMonitores();

		for(DTOMonitor m : listaMonitores){
			if(m.getIdentificador()==id){
				return true;
			}
		}	
		return false;
	}
	/**
	 * Imprime todos los monitores.
	 *
	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */

	public void listarMonitores() throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaMonitores=fichero.cargarFicheroMonitor();

		DAOmonitor monitorDAO = new DAOmonitor();
		listaMonitores=monitorDAO.ListaMonitores();
		
		for(DTOMonitor m : listaMonitores){
			System.out.println(m.toString());
		}	
	}
	
	/**
	 * Muestra los monitores especiales.
	 *
	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */
	
	public void listarMonitoresEspeciales() throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaMonitores=fichero.cargarFicheroMonitor();
		
		DAOmonitor monitorDAO = new DAOmonitor();
		listaMonitores=monitorDAO.ListaMonitores();

		for(DTOMonitor m : listaMonitores){
			if(m.getEducadorEspecial()) {
				System.out.println(m.toString());
			}
		}	
	}
	
	
	/**
	 * Obtiene un monitor mediante su identificador.
	 *
	 * @param id El identificador del monitor a obtener.
	 * @return El DTO del monitor encontrado, o un objeto monitor vacío si no se encuentra.
	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */
	public DTOMonitor monitorPorId(int id) throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaMonitores=fichero.cargarFicheroMonitor();
		DTOMonitor monitor=new DTOMonitor();
		DAOmonitor monitorDAO = new DAOmonitor();
		listaMonitores=monitorDAO.ListaMonitores();
		
		for(DTOMonitor m : listaMonitores){
			if(m.getIdentificador()==id)
			{
				monitor=m;
			}
		}	
		return monitor;
	}
	
	/**
	 * Obtiene un monitor especial mediante su identificador.
	 *
	 * @param id El identificador del monitor a obtener.
	 * @return El DTO del monitor encontrado, o un objeto monitor vacío si no se encuentra.
	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */
	
	public DTOMonitor monitorEspecialPorId(int id) throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaMonitores=fichero.cargarFicheroMonitor();
		DTOMonitor monitor=new DTOMonitor();
		DAOmonitor monitorDAO = new DAOmonitor();
		listaMonitores=monitorDAO.ListaMonitores();

		for(DTOMonitor m : listaMonitores){
			if(m.getIdentificador()==id && m.getEducadorEspecial()==true)
			{
				monitor=m;
			}
		}	
		return monitor;
	}

	/**
     * Crea un nuevo monitor y lo añade a la lista de monitores disponibles.
     * @return true si el monitor se crea exitosamente, false si no se pudo crear.
     * @throws FileNotFoundException Si hay un error al acceder a un archivo.
     * @throws IOException Si ocurre un error de lectura o escritura de datos.
     */
	public boolean crearMonitor(DTOMonitor monitor) throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaMonitores=fichero.cargarFicheroMonitor();
		DAOmonitor monitorDAO = new DAOmonitor();
		//listaMonitores=monitorDAO.ListaMonitores();
		
		if(comprobarExistenciaMonitor(monitor.getIdentificador())==false){
			return monitorDAO.Almacenar(monitor);
		}
		
		return false;
	}
	
	/**
	 * Comprueba la existencia de un campamento mediante su identificador.
	 *
	 * @param id El identificador del campamento a comprobar.
	 * @return `true` si el campamento existe, `false` en caso contrario.
	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */
	public boolean comprobarExistenciaCampamento(int id) throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaCampamentos=fichero.cargarFicheroCampamento();

		DAOcampamento campamentoDAO = new DAOcampamento();
		
		listaCampamentos=campamentoDAO.ListaCampamento();
		
		for(DTOCampamento c : listaCampamentos) {
			if(c.getIdentificador()==id) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Busca un campamento por su ID.
	 *
	 * @param id El identificador del campamento a comprobar.
	 * @return el campamento a buscar
	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */
	
	public DTOCampamento campamentoPorId(int id) throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaCampamentos=fichero.cargarFicheroCampamento();
		DTOCampamento campamento=new DTOCampamento();
		
		DAOcampamento campamentoDAO = new DAOcampamento();
		listaCampamentos=campamentoDAO.ListaCampamento();
		
		for(DTOCampamento c : listaCampamentos) {
			if(c.getIdentificador()==id) {
				campamento=c;
			}
		}
		return campamento;
	}

	
	/**
	 * Lista todos los campamentos.
	 *

	 * @throws FileNotFoundException Si hay un error al intentar acceder al archivo o directorio.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
	 */
	public void listarCampamentos() throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaCampamentos=fichero.cargarFicheroCampamento();
		DAOcampamento campamentoDAO = new DAOcampamento();	
		listaCampamentos=campamentoDAO.ListaCampamento();
		
		for(DTOCampamento c : listaCampamentos) {
			System.out.println(c.toString());
		}
	}

	/**
     * Crea un nuevo campamento y lo añade a la lista de campamentos disponibles.
     * @return true si el campamento se crea exitosamente, false si no se pudo crear.
     * @throws FileNotFoundException Si hay un error al acceder a un archivo.
     * @throws IOException Si ocurre un error de lectura o escritura de datos.
     */
	public boolean crearCampamento(DTOCampamento campamento) throws FileNotFoundException, IOException{
		//Fichero fichero = new Fichero();
		//listaCampamentos=fichero.cargarFicheroCampamento();
		DAOcampamento campamentoDAO = new DAOcampamento();
		listaCampamentos=campamentoDAO.ListaCampamento();
		
		if(comprobarExistenciaCampamento(campamento.getIdentificador())==false){
			
			return campamentoDAO.Almacenar(campamento);
			
		}
		return false;
	}
	
	/**
     * Asocia un monitor a una actividad específica.
     * @throws FileNotFoundException Si hay un error al acceder a un archivo.
     * @throws IOException Si ocurre un error de lectura o escritura de datos.
     */
	public boolean monitorAActividad(DTOMonitor monitor,DTOActividad actividad) throws FileNotFoundException, IOException{
		DAOactividad actividadDAO = new DAOactividad();

		if ( actividadDAO.monitorEnActividad(monitor, actividad))
		{
			return false;
		}
		return actividadDAO.AlmacenarMonitor(actividad.getNombreActividad(), monitor.getIdentificador());

		
	}

	/**
     * Asocia una actividad a un campamento específico.
     * @throws FileNotFoundException Si hay un error al acceder a un archivo.
     * @throws IOException Si ocurre un error de lectura o escritura de datos.
     */
	public boolean actividadACampamento(DTOCampamento campamento,DTOActividad actividad) throws FileNotFoundException, IOException{
		
		DAOcampamento campamentoDAO = new DAOcampamento();

		if(campamentoDAO.actividadEnCampamento(actividad, campamento))
		{
			return false;
		}

		return campamentoDAO.AlmacenarActividadEnCampamento(actividad.getNombreActividad(), campamento.getIdentificador());
		
		
	}

	/**
     * Asocia un monitor a un campamento específico.
     * @throws FileNotFoundException Si hay un error al acceder a un archivo.
     * @throws IOException Si ocurre un error de lectura o escritura de datos.
     */
	public boolean monitorResponsableACampamento(DTOMonitor monitor,DTOCampamento campamento) throws FileNotFoundException, IOException{
		
		DAOcampamento campamentoDAO = new DAOcampamento();

		if (campamentoDAO.ComprobarMonitorAsociado(monitor.getIdentificador(),campamento.getIdentificador()))
		{
			if (campamentoDAO.monitorEnCampamento(monitor, campamento))
			{
				return false;
			}
			return campamentoDAO.AsignarMonitorResponsable(monitor.getIdentificador(), campamento.getIdentificador());

		}

		return false;

		
	}	

	/**
     * Asocia un monitor de atención especial a un campamento específico.
     * @throws FileNotFoundException Si hay un error al acceder a un archivo.
     * @throws IOException Si ocurre un error de lectura o escritura de datos.
     */
	public boolean monitorEspecialACampamento(DTOMonitor monitor,DTOCampamento campamento) throws FileNotFoundException, IOException{
		
		DAOcampamento campamentoDAO = new DAOcampamento();

		if (!(campamentoDAO.ComprobarMonitorAsociado(monitor.getIdentificador(),campamento.getIdentificador())))
		{
			return campamentoDAO.AsignarMonitorEspecial(monitor.getIdentificador(), campamento.getIdentificador());

		}

		return false;
		
	}	

	/**
     * Obtiene la lista de campamentos disponibles.
     * @return ArrayList de campamentos.
     */
	public ArrayList<DTOCampamento>  getListaCampamentos(){
		return listaCampamentos;
	}
	/**
     * Obtiene la lista de monitores disponibles.
     * @return ArrayList de monitores.
     */
	public ArrayList<DTOMonitor> getListaMonitores(){
		return listaMonitores;
	}
	/**
     * Obtiene la lista de actividades disponibles.
     * @return ArrayList de actividades.
     */
	public ArrayList<DTOActividad>  getListaActividades(){
		return listaActividades;
	}
}

