package es.uco.pw.data.dao.campamento;

import es.uco.pw.business.campamento.DTOCampamento;
import es.uco.pw.business.monitor.DTOMonitor;
import es.uco.pw.business.actividad.DTOActividad;
import es.uco.pw.business.actividad.Horario;
import es.uco.pw.business.actividad.NivelEducativo;
import es.uco.pw.business.asistente.DTOAsistente;
import es.uco.pw.data.common.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Properties;
/**
 * Un DAO para los campamentos que hace uso de una conexión a una base de datos MySQL a través de JDBC. 
 * @author Manuel Cabrera Crespo
 * @author Javier García Fernández
 * @author Álvaro Eusebio Pérez
 * @author Francisco Javier Fernández Pastor
 */
 
public class DAOcampamento extends DBconnection {


	
	/**
	 * Almacena un campamento en la base de datos.
	 *
	 * @param campamento El DTO del campamento que se va a almacenar.
	 * @return true si la operación fue exitosa, false de lo contrario.
	 */
    
    public boolean Almacenar(DTOCampamento campamento)
    {
    	DBconnection dbConnection = new DBconnection();        
        Connection con =  dbConnection.getConnection();
        Properties sqlProperties = getSQLProperties();

        try{
            String statement = sqlProperties.getProperty("GuardarCampamento");
			PreparedStatement ps = con.prepareStatement(statement);

            java.sql.Date fechaInicio = java.sql.Date.valueOf(campamento.getFechaInicio());
            ps.setDate(1, fechaInicio);
            java.sql.Date fechaFin = java.sql.Date.valueOf(campamento.getFechaFin());
            ps.setDate(2, fechaFin);
            
            String nivel_educativo = null;
            if(campamento.getNivelEducativo() == NivelEducativo.juvenil){
                nivel_educativo = "juvenil";
            }
            else if(campamento.getNivelEducativo() == NivelEducativo.adolescente){
                nivel_educativo = "adolescente";
            }
            else{
                nivel_educativo = "infantil";
            }
            ps.setString(3,nivel_educativo);

            ps.setInt(4, campamento.getMaxAsistentes());

                   

            return ps.executeUpdate() > 0;
        }catch(Exception e) {
			System.out.println(e);

        }
        return false;

    }

    /**
	 * Modifica un campamento en la base de datos.
	 *
	 * @param campamento El DTO del campamento que se va a modificar.
	 * @return true si la operación fue exitosa, false de lo contrario.
	 */
    
    public boolean Modificar(int identificador, DTOCampamento campamento)
    {
    	DBconnection dbConnection = new DBconnection();        
        Connection con =  dbConnection.getConnection();
        Properties sqlProperties = getSQLProperties();

        try{
            String statement = sqlProperties.getProperty("ModificarCampamento");
			PreparedStatement ps = con.prepareStatement(statement);

            java.sql.Date fechaInicio = java.sql.Date.valueOf(campamento.getFechaInicio());
            ps.setDate(1, fechaInicio);
            java.sql.Date fechaFin = java.sql.Date.valueOf(campamento.getFechaFin());
            ps.setDate(2, fechaFin);
            
            String nivel_educativo = null;
            if(campamento.getNivelEducativo() == NivelEducativo.juvenil){
                nivel_educativo = "juvenil";
            }
            else if(campamento.getNivelEducativo() == NivelEducativo.adolescente){
                nivel_educativo = "adolescente";
            }
            else{
                nivel_educativo = "infantil";
            }
            ps.setString(3,nivel_educativo);

            ps.setInt(4, campamento.getMaxAsistentes());

                  


            return ps.executeUpdate() > 0;

        }catch(Exception e) {
			System.out.println(e);

        }
        return false;
    }
    
    /**
	 * Borra un campamento en la base de datos.
	 *
	 * @param campamento El DTO del campamento que se va a borrar.
	 * @return true si la operación fue exitosa, false de lo contrario.
	 */
    public boolean Borrar(DTOCampamento campamento){
    	DBconnection dbConnection = new DBconnection();        
        Connection con =  dbConnection.getConnection();
        Properties sqlProperties = getSQLProperties();

        try{
            String statement = sqlProperties.getProperty("BorrarCampamento");
            PreparedStatement ps = con.prepareStatement(statement);

            ps.setInt(1, campamento.getIdentificador());

            return ps.executeUpdate() > 0;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;

    }
    
    /**
     * Almacena una relación entre una actividad y un campamento en la base de datos.
     *
     * @param nombreActividad El nombre de la actividad.
     * @param idCampamento El identificador del campamento.
     * @return true si la operación fue exitosa, false de lo contrario.
     */

    public boolean AlmacenarActividadEnCampamento(String nombreActividad, int idCampamento){
    	DBconnection dbConnection = new DBconnection();        
        Connection con =  dbConnection.getConnection();
        Properties sqlProperties = getSQLProperties();

        try{
            String statement = sqlProperties.getProperty("GuardarActividadEnCampamento");
            PreparedStatement ps = con.prepareStatement(statement);

            ps.setString(1, nombreActividad);
            ps.setInt(2, idCampamento);

            return ps.executeUpdate() > 0;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }
    
    /**
     * Borra una relación entre una actividad y un campamento en la base de datos.
     *
     * @param nombreActividad El nombre de la actividad.
     * @param idCampamento El identificador del campamento.
     * @return true si la operación fue exitosa, false de lo contrario.
     */
    public boolean BorrarActividadDeCampamento(String nombreActividad, int idCampamento){
    	DBconnection dbConnection = new DBconnection();        
        Connection con =  dbConnection.getConnection();
        Properties sqlProperties = getSQLProperties();

        try{
            String statement = sqlProperties.getProperty("BorrarCampamento");
            PreparedStatement ps = con.prepareStatement(statement);

            ps.setString(1, nombreActividad);
            ps.setInt(2, idCampamento);

            return ps.executeUpdate() > 0;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }
    /**
     * Asigna un monitor como responsable a un campamento en la base de datos.
     *
     * @param idMonitor El identificador del monitor.
     * @param idCampamento El identificador del campamento.
     * @return true si la operación fue exitosa, false de lo contrario.
     */
    
    public boolean AsignarMonitorResponsable(int idMonitor, int idCampamento){
    	DBconnection dbConnection = new DBconnection();        
        Connection con =  dbConnection.getConnection();
        Properties sqlProperties = getSQLProperties();

        try{

            String statement = sqlProperties.getProperty("AsignarMonitorResponsable");
			PreparedStatement ps = con.prepareStatement(statement);

            ps.setInt(1, idMonitor);
            ps.setInt(2, idCampamento);

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            System.err.println(e);
        }
        return false;
    }
    
    /**
     * Asigna un monitor especial como responsable a un campamento en la base de datos.
     *
     * @param idMonitor El identificador del monitor.
     * @param idCampamento El identificador del campamento.
     * @return true si la operación fue exitosa, false de lo contrario.
     */
    public boolean AsignarMonitorEspecial(int idMonitor, int identificador){
    	DBconnection dbConnection = new DBconnection();        
        Connection con =  dbConnection.getConnection();
        Properties sqlProperties = getSQLProperties();

        try{
            String statement = sqlProperties.getProperty("AsignarMonitorEspecial");
			PreparedStatement ps = con.prepareStatement(statement);

            ps.setInt(1, idMonitor);
            ps.setInt(2, identificador);

            return ps.executeUpdate() > 0;

        }catch(Exception e){
            System.err.println(e);
        }
        return false;
    }
    
    /**
     * Obtiene la lista de campamentos desde la base de datos.
     *
     * @return ArrayList con los objetos DTOCampamento.
     */

    public ArrayList<DTOCampamento> ListaCampamento(){
        ArrayList<DTOCampamento> lista = new ArrayList<DTOCampamento>();
        DBconnection dbConnection = new DBconnection();        
        Connection con =  dbConnection.getConnection();
        Properties sql_properties = getSQLProperties();

        try{
            String statement = sql_properties.getProperty("LeerCampamento");
			PreparedStatement ps = con.prepareStatement(statement);
		    ResultSet rs = ps.executeQuery(statement);

            while(rs.next()){
                int identificador = rs.getInt("identificador");
                LocalDate inicio = java.time.LocalDate.parse(rs.getDate("fechaInicio").toString());
                LocalDate fin = java.time.LocalDate.parse(rs.getDate("fechaFin").toString());
                String nivel = rs.getString("nivelEducativo");
                int max = rs.getInt("maxAsistentes");
                int especial = rs.getInt("monitorEspecial");

                DTOCampamento aux = new DTOCampamento();
                
               
                NivelEducativo nivel_educativo = null;
                if(nivel.equals("juvenil")){
                    nivel_educativo = NivelEducativo.juvenil;
                }
                else if(nivel.equals("infantil")){
                    nivel_educativo = NivelEducativo.infantil;
                }
                else{
                    nivel_educativo = NivelEducativo.adolescente;
                }
                


                aux.setIdentificador(identificador);
                aux.setFechaInicio(inicio);
                aux.setFechaFin(fin);
                aux.setNivelEducativo(nivel_educativo);
                aux.setMaxAsistentes(max);
               
                aux.setMonitorEspecial(especial);

                lista.add(aux);
                
            }


        } catch(Exception e) {
			System.out.println(e);
		}

        return lista;
    }
    
    /**
     * Comprueba si un monitor está asociado a alguna actividad en la base de datos.
     *
     * @param idMonitor Identificador del monitor a comprobar.
     * @return true si el monitor está asociado, false en caso contrario.
     */

    
    public boolean ComprobarMonitorAsociado(int idMonitor,int idCampamento){
    	DBconnection dbConnection = new DBconnection();        
        Connection con =  dbConnection.getConnection();
        Properties sql_properties = getSQLProperties();
        //System.out.println(idMonitor);

        try{

            

        	String statement = sql_properties.getProperty("ComprobarMonitorAsociado");
        	PreparedStatement ps = con.prepareStatement(statement);
        	ps.setInt(1, idMonitor);
        	ps.setInt(2, idCampamento);
        	ResultSet rs = ps.executeQuery();




            if(rs.next()){
                return true;
            }
       

        }catch(Exception e){
            System.err.println(e);
        }
    
        return false;
    }
    
    /**
     * Obtiene un campamento de la base de datos por su identificador.
     *
     * @param identificador Identificador del campamento a obtener.
     * @return Objeto DTOCampamento correspondiente al identificador proporcionado, o null si no se encuentra.
     */
    
    public DTOCampamento getCampamento(int identificador)
    {
    	DBconnection dbConnection = new DBconnection();        
        Connection con =  dbConnection.getConnection();
        Properties sql_properties = DBconnection.getSQLProperties();
        DTOCampamento campamento = new DTOCampamento();
        try
        {
            String statement = sql_properties.getProperty("LeerCampamentoPorId");
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, identificador);
            ResultSet rs = ps.executeQuery(statement);
            if(rs.next())
            {
                campamento.setIdentificador(identificador);
                campamento.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
                campamento.setFechaFin(rs.getDate("fechaFin").toLocalDate());
                campamento.setMaxAsistentes(rs.getInt("maxAsistentes"));
                campamento.setMonitorEspecial(rs.getInt("monitorEspecial"));


                String nivel = rs.getString("nivelEducativo");
                NivelEducativo nivel_educativo = null;
                if(nivel.equals("juvenil")){
                    nivel_educativo = NivelEducativo.juvenil;
                }
                else if(nivel.equals("infantil")){
                    nivel_educativo = NivelEducativo.infantil;
                }
                else{
                    nivel_educativo = NivelEducativo.adolescente;
                }
                campamento.setNivelEducativo(nivel_educativo);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return campamento;
    }
    
    
    /**
     * Obtiene el número de actividades asociadas a un campamento.
     *
     * @param identificador Identificador del campamento.
     * @return Número de actividades asociadas al campamento.
     */
    
    public int ContarActividadesCampamento(int identificador)
    {
    	DBconnection dbConnection = new DBconnection();        
        Connection con =  dbConnection.getConnection();
        Properties sql_properties = DBconnection.getSQLProperties();
        int count = 0;
        try
        {
            String statement = sql_properties.getProperty("ContarActividadesCampamento");
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, identificador);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                count = rs.getInt("numActividades");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return count;
    }
    

    /**
     * Comprueba si una actividad ya esta asociada a un campamento
     *
     * @param actividad DTO de actividad
     * @param campamento DTO de campamento
     * @return true si ya esta asociada, false si no
     */
    public boolean actividadEnCampamento(DTOActividad actividad, DTOCampamento campamento)
    {
            DBconnection dbConnection = new DBconnection();        
            Connection con =  dbConnection.getConnection();
            Properties sql_properties = DBconnection.getSQLProperties();
            try
            {
                String statement = sql_properties.getProperty("ActividadEnCampamento");
                PreparedStatement ps = con.prepareStatement(statement);
                ps.setString(1, actividad.getNombreActividad());
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
     * Comprueba si un monitor ya esta asociado a un campamento
     *
     * @param monitor DTO de monitor
     * @param campamento DTO de campamento
     * @return true si ya esta asociado, false si no
     */

    public boolean monitorEnCampamento(DTOMonitor monitor, DTOCampamento campamento)
    {
            DBconnection dbConnection = new DBconnection();        
            Connection con =  dbConnection.getConnection();
            Properties sql_properties = DBconnection.getSQLProperties();
            try
            {
                String statement = sql_properties.getProperty("MonitorEnCampamento");
                
                PreparedStatement ps = con.prepareStatement(statement);
                ps.setInt(1, monitor.getIdentificador());
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

     
}


