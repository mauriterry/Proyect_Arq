/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS_DAO;

import CS_DTO.ClienteDTO;
import CS_DTO.Solicitud_DTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camila
 */
public class Solicitud_DAO {
    public boolean crearSolicitud(Solicitud_DTO p, Connection con)
    {
        PreparedStatement pstmt = null;
        boolean respuesta = false;
        try {            
            
            Logger.getLogger(Solicitud_DAO.class.getName()).log(Level.INFO, "Ejecutando crearSolicitud...");
            
            pstmt = con.prepareStatement("INSERT INTO solicitudt "
                    + " VALUES (?,?,?,?,?,?,?,?,?)");
            
            pstmt.setInt(1,p.getIdSolicitud());
            pstmt.setString(2, p.getNombre1());
            pstmt.setString(3, p.getNombre2());
            pstmt.setString(4, p.getApellido1());
            pstmt.setString(5, p.getApellido2());
            pstmt.setString(6, p.getIdentificacion());
            pstmt.setString(7, p.getDireccion());
            pstmt.setString(8, p.getEmail());
            pstmt.setString(9, p.getTelef());
                        
            pstmt.execute();
            
            con.close();
            
            respuesta = true;
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return respuesta;

    }

    public ArrayList<Solicitud_DTO> consultarSolicitud(Solicitud_DTO p, Connection con)
    {
        
        ArrayList<Solicitud_DTO> datos = new ArrayList();
        
        Logger.getLogger(Solicitud_DAO.class.getName()).log(Level.INFO, "Ejecutando consultarPersona...");
        
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery ("select idSolicitud, nombreS_1,nombreS_2, "
                    + " apellidoS_1, apellidoS_2, identificacionS, direccionS, emailS, "
                    + " telefonoS "
                    + " from solicitudt "
                    + " where "
                    + " identificacionS='" + p.getIdentificacion()+"'"
                    + " AND nombreS_1='"+p.getNombre1()+"'");
            
            while (rs.next())
            { 
                Solicitud_DTO per = new Solicitud_DTO();
                per.setIdSolicitud(rs.getInt(1));
                per.setNombre1(rs.getString(2));
                per.setNombre2(rs.getString(3));
                per.setApellido1(rs.getString(4));
                per.setApellido2(rs.getString(5));
                per.setIdentificacion(rs.getString(6));
                per.setDireccion(rs.getString(7));
                per.setEmail(rs.getString(8));
                per.setTelef(rs.getString(9));
                
                
                datos.add(per);
                
            }
            
            Logger.getLogger(Solicitud_DAO.class.getName()).log(Level.INFO, "Ejecutando consultarPersona fin..." + datos.size());
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return datos;
    }
    
    
    public int obtenerId(Connection con)
    {
        int id = -1;
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery ("select max(idSolicitud)+1 from solicitudt");
            
            while (rs.next())
            { 
                id = rs.getInt(1);
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
         
    public boolean editarSolicitud(Solicitud_DTO p, Connection con)
    {
        PreparedStatement pstmt = null;
        boolean respuesta = false;
        try {            
            
            Logger.getLogger(Solicitud_DAO.class.getName()).log(Level.INFO, "Ejecutando editarSolicitud...");
            
            pstmt = con.prepareStatement("UPDATE solicitudt "
                    + " SET "
                    + " identificacion=?"
                    + " , nombreS_1=?"
                    + " , nombreS_2=?"
                    + " , apellidoS_1=?"
                    + " , apellidoS_2=?"
                    + " , direccionS=?"
                    + " , emailS=?"
                    + " , telefonoS=?"
                    + " where idSolicitud=?");
                        
             pstmt.setString(6, p.getIdentificacion());
            pstmt.setString(2, p.getNombre1());
            pstmt.setString(3, p.getNombre2());
            pstmt.setString(4, p.getApellido1());
            pstmt.setString(5, p.getApellido2());
            pstmt.setString(7, p.getDireccion());
            pstmt.setString(8, p.getEmail());
            pstmt.setString(9, p.getTelef());
            pstmt.setInt(1,p.getIdSolicitud());
            
            pstmt.executeUpdate();
            
            con.close();
            
            respuesta = true;
        } catch (SQLException ex) {
            Logger.getLogger(Solicitud_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return respuesta;

    }
}
