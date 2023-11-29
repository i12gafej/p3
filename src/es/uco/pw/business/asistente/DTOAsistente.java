package es.uco.pw.business.asistente;

import java.time.LocalDate;

/**
 * La clase Asistente representa a una persona que asiste al campamento de verano.
 * Esta clase almacena información sobre el asistente, como su identificador, nombre y apellidos,
 * fecha de nacimiento y si requiere atención especial.
 * @author Manuel Cabrera Crespo
 * @author Javier García Fernández
 * @author Álvaro Eusebio Pérez
 * @author Francisco Javier Fernández Pastor
 */
public class DTOAsistente 
{
    private int identificador;
    /** Identificador del asistente */
    
    private String nombre_apellidos;
    /**Nombre y apellidos del asistente */
    
    private LocalDate fechaNacimiento; 
    /** Fecha de nacimiento del asistente */
    
    private boolean requiereAtencionEspecial=false;
    /** Indica si requiere o no atención especial */

   


	/**
     * Constructor vacio de la clase Asistente.
     * Inicializa todas las propiedades con valores predeterminados.
     */
    public DTOAsistente()
    {
        
    }

    /**
     * Constructor de la clase Asistente que acepta parámetros para inicializar todas las propiedades.
     *
     * @param identificador El identificador único del asistente.
     * @param nombre_apellidos El nombre y apellidos del asistente.
     * @param fechaNacimiento La fecha de nacimiento del asistente.
     * @param requiereAtencionEspecial Indica si el asistente requiere atención especial.
     */
    public DTOAsistente(int identificador, String nombre_apellidos, LocalDate fechaNacimiento, boolean requiereAtencionEspecial)
    {
        this.identificador = identificador;
        this.nombre_apellidos = nombre_apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.requiereAtencionEspecial = requiereAtencionEspecial;
    }

    /**
     * Obtiene el identificador del asistente.
     *
     * @return El identificador del asistente.
     */
    public int getIdentificador()
    {
        return identificador;
    }

    /**
     * Establece el identificador del asistente.
     *
     * @param identificador El identificador único del asistente.
     */
    public void setIdentificador(int identificador)
    {
        this.identificador = identificador;
    }

    /**
     * Obtiene el nombre y apellidos del asistente.
     *
     * @return El nombre y apellidos del asistente.
     */
    public String getNombreApellidos()
    {
        return nombre_apellidos;
    }

    /**
     * Establece el nombre y apellidos del asistente.
     *
     * @param nombre_apellidos El nombre y apellidos del asistente.
     */
    public void setNombreApellidos(String nombre_apellidos)
    {
        this.nombre_apellidos = nombre_apellidos;
    }

    /**
     * Obtiene la fecha de nacimiento del asistente.
     *
     * @return La fecha de nacimiento del asistente.
     */
    public LocalDate getFechaNacimiento()
    {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del asistente.
     *
     * @param fechaNacimiento La fecha de nacimiento del asistente.
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento)
    {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Comprueba si el asistente requiere atención especial.
     *
     * @return true si el asistente requiere atención especial, false en caso contrario.
     */
    public boolean esrequiereAtencionEspecial()
    {
        return requiereAtencionEspecial;
    }

    /**
     * Establece si el asistente requiere atención especial.
     *
     * @param requiereAtencionEspecial true si el asistente requiere atención especial, false en caso contrario.
     */
    public void setRequiereAtencionEspecial(boolean requiereAtencionEspecial)
    {
        this.requiereAtencionEspecial = requiereAtencionEspecial;
    }

    /**
     * Devuelve una representación en forma de cadena de la clase Asistente.
     *
     * @return Una cadena que representa los datos del asistente.
     */
    public String toString() 
    {
        return "Asistente [identificador=" + identificador + ", nombre_apellidos=" + nombre_apellidos + ", fechaNacimiento=" + fechaNacimiento + ", requiereAtencionEspecial=" + requiereAtencionEspecial
                + "]";
    } 
   
}

