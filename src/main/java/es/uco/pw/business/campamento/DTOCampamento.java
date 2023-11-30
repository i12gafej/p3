package es.uco.pw.business.campamento;

import java.util.ArrayList;
import java.time.LocalDate;

import es.uco.pw.business.actividad.*;
import es.uco.pw.business.monitor.*;

/**
 * Constructor vacío de la clase Campamento.
 * Inicializa las listas de actividades como una lista vacía y los monitores responsables como nulos.
 * @author Manuel Cabrera Crespo
 * @author Javier García Fernández
 * @author Álvaro Eusebio Pérez
 * @author Francisco Javier Fernández Pastor
 */

public class DTOCampamento {
    private int identificador;
    /** Identificador del campamento*/
    
    private LocalDate fechaInicio;
    /** Fecha de inicio del campamento*/

    private LocalDate fechaFin;
    /** Fecha de finalizacion del campamento*/

    private NivelEducativo nivelEducativo;
    /** Nivel educativo*/

    private int maxAsistentes;
    /** Numero maximo de asistentes*/

    
    int idMonitorEspecial;
    /** Monitor especial del campamento*/


    /**
     * Constructor vacio de la clase Campamento.
     */

	public DTOCampamento() {
        
    }

    /**
     * Constructor parametrizado de la clase Campamento.
     *
     * @param identificador El identificador del campamento.
     * @param fechaInicio La fecha de inicio del campamento.
     * @param fechaFin La fecha de fin del campamento.
     * @param nivelEducativo El nivel educativo del campamento.
     * @param maxAsistentes El número máximo de asistentes al campamento.     * @param maxAsistentes El número máximo de asistentes al campamento.
     * @param monitorEspecial El identificador del monitor especial.

     */
    public DTOCampamento(int identificador, LocalDate fechaInicio, LocalDate fechaFin, NivelEducativo nivelEducativo, int maxAsistentes, int idMonitorEspecial) {
        this.identificador = identificador;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nivelEducativo = nivelEducativo;
        this.maxAsistentes = maxAsistentes;
        this.idMonitorEspecial= idMonitorEspecial;
    
    }

    /**
     * Obtiene el identificador del campamento.
     *
     * @return El identificador del campamento.
     */
    public int getIdentificador() {
        return identificador;
    }

    /**
     * Establece el identificador del campamento.
     *
     * @param identificador El identificador del campamento.
     */
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    /**
   	 * @return es la fechaInicio
   	 */
   	public LocalDate getFechaInicio() {
   		return fechaInicio;
   	}

   	/**
   	 * @param fechaInicio es la fechaInicio para settear
   	 */
   	public void setFechaInicio(LocalDate fechaInicio) {
   		this.fechaInicio = fechaInicio;
   	}
   	
   	/**
	 * @return es la fechaFin
	 */
	public LocalDate getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin es la  fechaFin para settear
	 */
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return el nivelEducativo
	 */
	public NivelEducativo getNivelEducativo() {
		return nivelEducativo;
	}

	/**
	 * @param nivelEducativo es el nivelEducativo para settear
	 */
	public void setNivelEducativo(NivelEducativo nivelEducativo) {
		this.nivelEducativo = nivelEducativo;
	}

	/**
	 * @return el maxAsistentes
	 */
	public int getMaxAsistentes() {
		return maxAsistentes;
	}

	/**
	 * @param maxAsistentes es el maxAsistentes para settear
	 */
	public void setMaxAsistentes(int maxAsistentes) {
		this.maxAsistentes = maxAsistentes;
	}
    
    public int getMonitorEspecial() {
        return idMonitorEspecial;
    }
    

	/**
	 * @param monitorEspecial es el monitorEspecial para settear
	 */
	public void setMonitorEspecial(int monitorEspecial) {
		this.idMonitorEspecial = monitorEspecial;
	}
    /**
     * Asocia una actividad al campamento si el nivel educativo de la actividad coincide con el del campamento.
     *
     * @param actividad La actividad a asociar.
     */
    //public boolean asociarActividad(DTOActividad actividad) {
    //    if (actividad.getNivelEducativo() == nivelEducativo) {
    //        actividades.add(actividad);
    //        return true;
    //    }
    //    return false;
    //}

    /**
     * Asocia un monitor responsable al campamento entre aquellos que están encargados de alguna de las actividades.
     *
     * @param monitor El monitor a asociar como responsable del campamento.
     */
   // public boolean asociarMonitor(DTOMonitor monitor) {
   //    if (actividades.stream().anyMatch(a -> a.getMonitoresAsignados().contains(monitor))) {
   //        this.monitoresResponsables.add(monitor);
   //        return true;
   //     }
   //     else{
   //         return false;
   //     }   
   // 
   // }

    /**
     * Asocia un segundo monitor especial como refuerzo, que no puede estar asociado a ninguna actividad del campamento.
     *
     * @param monitor El monitor especial a asociar al campamento.
     */
    //public boolean asociarMonitorEspecial(DTOMonitor monitor) {
    //    if (!actividades.stream().anyMatch(a -> a.getMonitoresAsignados().contains(monitor))) {
    //        this.idMonitorEspecial = monitor.getIdentificador();
    //        return true;
    //    }
    //    else{
    //        return false;
    //    }
    //}

    /**
     * Devuelve una representación en forma de cadena de la clase Campamento.
     *
     * @return Una cadena que representa la información del campamento.
     */
    public String toString() {
        return "Campamento [identificador=" + identificador + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
                + ", nivelEducativo=" + nivelEducativo + ", maxAsistentes=" + maxAsistentes + ", idMonitorEspecial="+ idMonitorEspecial+ "]";
    }
}

