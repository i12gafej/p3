package es.uco.pw.data.dao.asistente;

import es.uco.pw.business.actividad.DTOActividad;
import es.uco.pw.business.actividad.NivelEducativo;
import es.uco.pw.business.asistente.DTOAsistente;
import es.uco.pw.business.campamento.DTOCampamento;
import es.uco.pw.business.monitor.DTOMonitor;
import es.uco.pw.data.common.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Un DAO para los asistentes que hace uso de una conexión a una base de datos MySQL a través de JDBC. 
 * @author Manuel Cabrera Crespo
 * @author Javier García Fernández
 * @author Álvaro Eusebio Pérez
 * @author Francisco Javier Fernández Pastor
 */

public class DAOasistente extends DBconnection
{
	/**
	 * Almacena un asistente en la base de datos.
	 *
	 * @param asistente El DTO del asistente que se va a almacenar.
	 * @return true si la operación fue exitosa, false de lo contrario.
	 */

    public boolean Almacenar(DTOAsistente asistente)
    {
        

    	DBconnection dbConnection = new DBconnection();        
        Connection con =  dbConnection.getConnection();
        Properties sqlProperties = getSQLProperties();

        try{
            String statement = sqlProperties.getProperty("GuardarAsistente");
			PreparedStatement ps = con.prepareStatement(statement);

            ps.setString(1,asistente.getNombreApellidos());
            java.sql.Date sqlDate1 = java.sql.Date.valueOf(asistente.getFechaNacimiento());
            ps.setDate(2, sqlDate1);

            if(asistente.esrequiereAtencionEspecial())
            {
                ps.setBoolean(3, true);
            }
            else
            {
                ps.setBoolean(3, false);
            }
            return ps.executeUpdate()>0;

        }
        catch(Exception e)
        {
			System.out.println(e);

        }
        return false;

    }
    
    /**
     * Modifica un asistente en la base de datos.
     *
     * @param asistente El DTO del asistente que se va a modificar.
     * @return true si la operación fue exitosa, false de lo contrario.
     */
    public boolean Modificar(DTOAsistente asistente)
    {
    	DBconnection dbConnection = new DBconnection();        
        Connection con =  dbConnection.getConnection();
        Properties sqlProperties = getSQLProperties();        

        try
        {
            String statement = sqlProperties.getProperty("ModificarAsistente");
            PreparedStatement ps = con.prepareStatement(statement);
            
            ps.setString(1,asistente.getNombreApellidos());
            java.sql.Date sqlDate1 = java.sql.Date.valueOf(asistente.getFechaNacimiento());
            ps.setDate(2, sqlDate1);

            if(asistente.esrequiereAtencionEspecial())
            {
                ps.setBoolean(3, true);
            }
            else
            {
                ps.setBoolean(3, false);
            }

            ps.setInt(4, asistente.getIdentificador());
           return ps.executeUpdate() > 0;                

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Borra un asistente en la base de datos.
     *
     * @param asistente El DTO del asistente que se va a almacenar.
     * @return true si la operación fue exitosa, false de lo contrario.
     */
    public boolean Borrar(DTOAsistente asistente)
    {
    	DBconnection dbConnection = new DBconnection();        
        Connection con =  dbConnection.getConnection();
        Properties sqlProperties = getSQLProperties();

        try
        {
            String statement = sqlProperties.getProperty("BorrarAsistente");
            PreparedStatement ps = con.prepareStatement(statement);

            ps.setInt(1, asistente.getIdentificador());

            return ps.executeUpdate()>0;
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        return false;

    }
    
    /**
     * Saca la informacion de la tabla Asistente y lo guarda en una lista.
     *
     * @return la lista de actividades.
     */

    public ArrayList<DTOAsistente> ListaAsistentes(){
        ArrayList<DTOAsistente> lista = new ArrayList<DTOAsistente>();
        DBconnection dbConnection = new DBconnection();        
        Connection con =  dbConnection.getConnection();
        Properties sql_properties = getSQLProperties();

        try{
            String statement = sql_properties.getProperty("LeerAsistente");
			PreparedStatement ps = con.prepareStatement(statement);
		    ResultSet rs = ps.executeQuery(statement);

            while(rs.next()){
                int identificador = rs.getInt("identificador");
                String nombre = rs.getString("nombre_apellidos");
                LocalDate date = java.time.LocalDate.parse(rs.getDate("fechaNacimiento").toString());
                boolean atencion = rs.getBoolean("requiereAtencionEspecial");

                DTOAsistente aux = new DTOAsistente();
                aux.setIdentificador(identificador);
                aux.setNombreApellidos(nombre);
                aux.setFechaNacimiento(date);
                aux.setRequiereAtencionEspecial(atencion);

                lista.add(aux);
            }
        } catch(Exception e) {
			System.out.println(e);
		}
        return lista;
    }


     public DTOAsistente getAsistente(int identificador)
    {
    	 DBconnection dbConnection = new DBconnection();        
         Connection con =  dbConnection.getConnection();
         Properties sql_properties = DBconnection.getSQLProperties();
        DTOAsistente Asistente = new DTOAsistente();
        try
        {
            String statement = sql_properties.getProperty("LeerAsistentePorId");
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, identificador);
            ResultSet rs = ps.executeQuery(statement);
            if(rs.next())
            {
                Asistente.setIdentificador(identificador);
                Asistente.setFechaNacimiento(rs.getDate("fechaInicio").toLocalDate());
                Asistente.setNombreApellidos(rs.getString("nombre_apellidos"));
                Asistente.setRequiereAtencionEspecial(rs.getBoolean("requiereAtencionEspecial"));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return Asistente;
    }

    
}