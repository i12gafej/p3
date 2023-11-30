package es.uco.pw.business.inscripcion;

//import es.uco.pw.ficheros.Fichero;
import es.uco.pw.business.asistente.*;
import es.uco.pw.business.actividad.*;
import es.uco.pw.business.campamento.*;
import es.uco.pw.data.dao.actividad.DAOactividad;
import es.uco.pw.data.dao.asistente.DAOasistente;
import es.uco.pw.data.dao.campamento.DAOcampamento;
import es.uco.pw.data.dao.inscripcion.DAOinscripcion;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.*;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;




public class GestorInscripciones {
	
    private ArrayList<DTOCampamento> listaCampamentos = new ArrayList<DTOCampamento>();
	/**Lista de campamentos en la que se volcara la informacion de las bases de datos  */

	private ArrayList<InscripcionCompleta> listaInscripcionesCompletas = new ArrayList<InscripcionCompleta>();
	/**Lista de inscripciones completas en la que se volcara la informacion de las bases de datos  */

    private ArrayList<InscripcionParcial> listaInscripcionesParciales = new ArrayList<InscripcionParcial>();
	/**Lista de inscripciones completas en la que se volcara la informacion de las bases de datos  */


    private ArrayList<DTOAsistente> listaAsistentes = new ArrayList<DTOAsistente>();
	/**Lista de asistentes en la que se volcara la informacion de las bases de datos  */



    Scanner leer = new Scanner(System.in);
	
	


	public boolean comprobarExistenciaAsistente(int idAsistente)
	{
		//Fichero ficheroA=new Fichero();
		//listaAsistentes=ficheroA.cargarFicheroAsistente();

		DAOasistente asistenteDAO = new DAOasistente();
		
		listaAsistentes = asistenteDAO.ListaAsistentes();

		for (DTOAsistente a: listaAsistentes) {
			if (a.getIdentificador()==(idAsistente)) {
				return true;
			}
		}
		return false;

	}

	public boolean comprobarExistenciaCampamento(int idCampamento)
	{
		//Fichero ficheroC=new Fichero();
		//listaCampamentos=ficheroC.cargarFicheroCampamento();
		DAOcampamento campamentoDAO = new DAOcampamento();
		
		listaCampamentos = campamentoDAO.ListaCampamento();

		for (DTOCampamento c: listaCampamentos) {
			if (c.getIdentificador()==(idCampamento)) {
				return true;
			}
		}
		return false;
	}

	public DTOAsistente buscarAsistente(int idAsistente) throws FileNotFoundException, IOException{
		DTOAsistente asistente = new DTOAsistente();
		DAOasistente daoAsistente = new DAOasistente();
		
		listaAsistentes = daoAsistente.ListaAsistentes();

	

		for (DTOAsistente a: listaAsistentes) {
			if (a.getIdentificador()==(idAsistente)) {
				asistente = a;

			}
		}

		return asistente;

	}


	public DTOCampamento buscarCampamento(DTOCampamento campamento, int idCampamento) throws FileNotFoundException, IOException{
		
		DAOcampamento daoCampamento = new DAOcampamento();
		listaCampamentos = daoCampamento.ListaCampamento();

		for (DTOCampamento c: listaCampamentos) {
			if (c.getIdentificador()==(idCampamento) &&  plazasDisponibles(c) > 0) {
				campamento = c;
			}
		}

		return campamento;

	}
	/**
	 * Funcion para calcular si el registro es temprano
	 * @param idCampamento
	 * @return true si esta dentro del plazo (antes de los 15 dias de la fecha de inicio) y false si no lo cumple
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public boolean funcionCalcularTemprano(int idCampamento) throws FileNotFoundException, IOException{


		DAOcampamento daoCampamento = new DAOcampamento();
		listaCampamentos = daoCampamento.ListaCampamento();

		for(DTOCampamento c: listaCampamentos){
			if(c.getIdentificador() == idCampamento){
	            
				if(LocalDate.now().isBefore(c.getFechaInicio().minusDays(15))){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Funcion para calcular si el registro es tardio
	 * @param idCampamento
	 * @return true si esta dentro del plazo (despues de 15 dias y 2 dias antes de la fecha de inicio y ) y false si no lo cumple
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public boolean funcionCalcularTardio(int idCampamento) throws FileNotFoundException, IOException{
	

		DAOcampamento daoCampamento = new DAOcampamento();
		listaCampamentos= daoCampamento.ListaCampamento();
		
		
		
		for(DTOCampamento c: listaCampamentos){
			if(c.getIdentificador() == idCampamento){
				if(LocalDate.now().isAfter(c.getFechaInicio().minusDays(15)) && LocalDate.now().isBefore(c.getFechaInicio().minusDays(2))){
					return true;
				}
			}
		}
	
		return false;
	}
	/**
	 * Metodo para realizar un registro temprano
	 * Permite crear una inscripcion completa y parcial
	 * @param idCampamento
	 * @return true si se ha podido completar la inscripcion y false si ha ocurrido algun problema
	 * @throws FileNotFoundException
	 * @throws IOException
	 */



	

    public boolean realizarRegistroTemprano(int numeroActividades, DTOCampamento campamento, DTOAsistente asistente, int opcion) throws FileNotFoundException, IOException{
		
		RegistroTemprano registro = new RegistroTemprano();
		DAOinscripcion daoInscripcion = new DAOinscripcion();
		switch(opcion){
			case 1:
				
				
					float precioCompleta = 300 + numeroActividades*20;
					InscripcionCompleta ic = registro.creadorInscripcionCompleta(campamento, asistente, precioCompleta);
					ic.toString();
					
					daoInscripcion.Almacenar(ic);
					break;
			case 2:
				
				
					float precioParcial = 100 + numeroActividades*20;
					InscripcionParcial ip = registro.creadorInscripcionParcial(campamento, asistente, precioParcial);
					
					daoInscripcion.Almacenar(ip);
					break;

			default:
				return false;				
		}
							
		return true;
	}
/**	 
 * Metodo para realizar un registro tardio
 * Permite crear una inscripcion completa o parcial
 * @param idCampamento
 * @return true si se ha podido completar la inscripcion y false si ha ocurrido algun problema
 * @throws FileNotFoundException
 * @throws IOException
 */

	public boolean realizarRegistroTardio(int numeroActividades, DTOCampamento campamento, DTOAsistente asistente, int opcion ) throws FileNotFoundException, IOException{
		
		
		RegistroTardio registro = new RegistroTardio();
		DAOinscripcion daoInscripcion = new DAOinscripcion();
		
		switch(opcion){
			case 1:
				
				
					float precioCompleta = 300 + numeroActividades*20;
					InscripcionCompleta ic = registro.creadorInscripcionCompleta(campamento, asistente, precioCompleta);
					daoInscripcion.Almacenar(ic);
					break;
				
			case 2:
				
					float precioParcial = 100 + numeroActividades*20;
					InscripcionParcial ip = registro.creadorInscripcionParcial(campamento, asistente, precioParcial);
					daoInscripcion.Almacenar(ip);
					break;

			default:
			return false;

		}
							
		return true;
	}

	public boolean comprobarAsistenteInscrito(DTOAsistente asistente, DTOCampamento campamento) throws FileNotFoundException, IOException{

		DAOinscripcion daoInscripcion = new DAOinscripcion();
		return daoInscripcion.BuscarAsistenteInscrito(asistente,campamento);
		
	}

/**
 * Metodo que permite cancelar una inscripcion completa o parcial
 * Solo se puede en caso de que el registro haya sido temprano
 * @return true si se ha podido completar la cancelacion y false en caso contrario
 * @throws FileNotFoundException
 * @throws IOException
 */

	public boolean cancelarInscripcion(DTOAsistente a, DTOCampamento c) throws FileNotFoundException, IOException{


		DAOinscripcion daoInscripcion = new DAOinscripcion();
		return daoInscripcion.Borrar(a,c);
		
		
	}

	/**
	 * Metodo para listar los campamentos disponibles
	 * @throws FileNotFoundException
	 * @throws IOException
	 */

	public void listarCampamentos() throws FileNotFoundException, IOException{
		DAOcampamento daoCampamento = new DAOcampamento();
		listaCampamentos = daoCampamento.ListaCampamento();
		int i = 1;
			
		for(DTOCampamento c : listaCampamentos)
		{
			if (c.getFechaInicio().isAfter(LocalDate.now()) && plazasDisponibles(c) > 0) {
            System.out.println("\nCampamento "+i+":"+"\n\tid: "+c.getIdentificador()+"\n\tfecha inicio: "+c.getFechaInicio()+ "\n\tfecha fin: "+ c.getFechaFin() + "\n\tnivel educativo: "+c.getNivelEducativo()+ "\n\taforo : "+c.getMaxAsistentes());
            i++;
			}
		}
	}
/**
 * Metodo para contemplar las plazas disponibles de un campamento
 * @param c siendo el campamento a recorrer
 * @return el numero de plazas disponibles en el campamento c
 * @throws IOException
 * @throws FileNotFoundException
 */
	public int plazasDisponibles(DTOCampamento c) throws FileNotFoundException, IOException {

		
		DAOinscripcion daoInscripcion = new DAOinscripcion();
		int plazasMaximas = c.getMaxAsistentes();
		int plazasOcupadas = daoInscripcion.ContarPlazasOcupadas(c);
		return plazasMaximas - plazasOcupadas;
	}

	public int numeroActividadesCampamento(int identificador)
	{
		DAOcampamento daoCampamento = new DAOcampamento();
		if( daoCampamento.ContarActividadesCampamento(identificador) > 0 )
		{
			return daoCampamento.ContarActividadesCampamento(identificador);
		}
		return 0;

	}

	


	

	/**
	 * Get de la lista de campamentos
	 * @return la lista de campamentos
	 */
	public ArrayList<DTOCampamento>  getListaCampamentos(){
		return listaCampamentos;
	}
	/**
	 * Get de la lista de asistentes 
	 * @return la lista de asistentes
	 */
	public ArrayList<DTOAsistente> getListaAsistentes(){
		return listaAsistentes;
	}
	/**
	 * Get de la lista de inscripciones completas
	 * @return la lista de inscripciones completas
	 */
	public ArrayList<InscripcionCompleta>  getListaInscripcionCompletas(){
		return listaInscripcionesCompletas;
	}
	/**
	 * Get de la lista de inscripciones parciales
	 * @return la lista de inscripciones parciales
	 */
	public ArrayList<InscripcionParcial>  getListaInscripcionParciales(){
		return listaInscripcionesParciales;
	}	

}
	/**public  */
	
