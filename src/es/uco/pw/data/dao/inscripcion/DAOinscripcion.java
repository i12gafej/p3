package es.uco.pw.data.dao.inscripcion;

import es.uco.pw.data.common.DBconnection;
import es.uco.pw.business.actividad.DTOActividad;
import es.uco.pw.business.actividad.Horario;
import es.uco.pw.business.actividad.NivelEducativo;
import es.uco.pw.business.asistente.DTOAsistente;
import es.uco.pw.business.campamento.DTOCampamento;
import es.uco.pw.business.inscripcion.DTOInscripcion;
import es.uco.pw.business.inscripcion.InscripcionCompleta;
import es.uco.pw.business.inscripcion.InscripcionParcial;
import es.uco.pw.business.inscripcion.RegistroTardio;
import es.uco.pw.business.inscripcion.RegistroTemprano;
import es.uco.pw.business.monitor.DTOMonitor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Clase DAO para gestionar las operaciones relacionadas con las inscripciones en la base de datos.
 * @author Manuel Cabrera Crespo
 * @author Javier García Fernández
 * @author Álvaro Eusebio Pérez
 * @author Francisco Javier Fernández Pastor
 */
 
public class DAOinscripcion extends DBconnection{
	 
	 /**
	     * Almacena una inscripción en la base de datos.
	     *
	     * @param inscripcion Objeto DTOInscripcion que se va a almacenar.
	     * @return true si la operación fue exitosa, false en caso contrario.
	     */

    public boolean Almacenar(DTOInscripcion inscripcion){
    	DBconnection dbConnection = new DBconnection();        
        Connection con =  dbConnection.getConnection();
        Properties sqlProperties = DBconnection.getSQLProperties();

        try{
            String statement = sqlProperties.getProperty("GuardarInscripcion");
			PreparedStatement ps = con.prepareStatement(statement);
			//System.out.println(inscripcion.getFecha());
            ps.setInt(1, inscripcion.getIdAsistente());
            ps.setInt(2, inscripcion.getIdCampamento());
            java.sql.Date sqlDate1 = java.sql.Date.valueOf(inscripcion.getFecha());
            ps.setDate(3, sqlDate1);
            ps.setFloat(4, inscripcion.getPrecio());
            //System.out.println(inscripcion.getTipo());
            ps.setString(5, inscripcion.getTipo());
            ps.setString(6, inscripcion.getEstado());

            return ps.executeUpdate()>0;
        }catch(Exception e) {
			System.out.println(e);
        }
        return false;
    }

    /**
     * Borra una inscripción en la base de datos.
     *
     * @param inscripcion Objeto DTOInscripcion que se va a borrar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */

    public boolean Borrar(DTOAsistente asistente, DTOCampamento campamento){
    	DBconnection dbConnection = new DBconnection();        
        Connection con =  dbConnection.getConnection();
        Properties sqlProperties = DBconnection.getSQLProperties();

        try{
            String statement = sqlProperties.getProperty("BorrarInscripcion");
            PreparedStatement ps = con.prepareStatement(statement);

            ps.setInt(1, campamento.getIdentificador());
            ps.setInt(2, asistente.getIdentificador());

            return ps.executeUpdate()>0;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    
  
    /**
     * Busca un asistente inscrito en un campamento.
     *
     * @param asistente Asistente a buscar.
     * @param campamento Campamento en el que se busca.
     * @return Verdadero si el asistente está inscrito en el campamento, falso de lo contrario.
     */


    public boolean BuscarAsistenteInscrito(DTOAsistente asistente, DTOCampamento campamento)
    {
    	DBconnection dbConnection = new DBconnection();        
        Connection con =  dbConnection.getConnection();
        Properties sql_properties = DBconnection.getSQLProperties();
    
        try
        {
            String statement = sql_properties.getProperty("BuscarAsistenteInscrito");
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, asistente.getIdentificador());
            ps.setInt(2, campamento.getIdentificador());
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return true;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    
    /**
     * Cuenta las plazas ocupadas en un campamento.
     *
     * @param campamento Campamento del que se cuentan las plazas ocupadas.
     * @return Número de plazas ocupadas en el campamento.
     */

    public int ContarPlazasOcupadas(DTOCampamento campamento)
    {
    	DBconnection dbConnection = new DBconnection();        
        Connection con =  dbConnection.getConnection();
        Properties sql_properties = getSQLProperties();
        int count = 0;

        try {
            String statement = sql_properties.getProperty("ContarPlazasOcupadas");
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, campamento.getIdentificador());
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                count = rs.getInt("numAsistentes");
            }
        } catch (Exception e) {
            System.out.println(e);

        }
        return count;
    }
   
 


}








