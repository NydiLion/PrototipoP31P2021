/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;
import Dominio.Maestro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Nay Ale
 */
public class MaestroDAO {
        private static final String SQL_SELECT = "SELECT codigo_maestro, nombre_maestro, direccion_maestro, telefono_maetro, email_maestro, estatus_maestro FROM maestros";
    private static final String SQL_INSERT = "INSERT INTO maestros(codigo_maestro, nombre_maestro, direccion_maestro, telefono_maetro, email_maestro, estatus_maestro) VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE maestros SET nombre_maestro=?, direccion_maestro=?, telefono_maetro=?, email_maestro=?, estatus_maestro=? WHERE codigo_maestro = ?";
    private static final String SQL_DELETE = "DELETE FROM maestros WHERE codigo_maestro=?";
    private static final String SQL_QUERY = "SELECT codigo_maestro, nombre_maestro, direccion_maestro, telefono_maetro, email_maestro, estatus_maestro FROM maestros WHERE codigo_maestro = ?";

    public List<Maestro> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Maestro maestros = null;
        List<Maestro> Maestro = new ArrayList<Maestro>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int codigo_maestro = rs.getInt("codigo_maestro");
                String nombre_maestro = rs.getString("nombre_maestro");
                String direccion_maestro = rs.getString("direccion_maestro");
                String telefono_maetro = rs.getString("telefono_maetro");
                String email_maestro = rs.getString("email_maestro");
                String estatus_maestro = rs.getString("estatus_maestro");
                
                

                maestros = new Maestro();
                maestros.setCodigo_maestro(codigo_maestro);
                maestros.setNombre_maestro(nombre_maestro);
                maestros.setDireccion_maestro(direccion_maestro);
                maestros.setTelefono_maetro(telefono_maetro);
                maestros.setEmail_maestro(email_maestro);
                maestros.setEstatus_maestro(estatus_maestro);
                Maestro.add(maestros);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return Maestro;
    }

    public int insert(Maestro maestros) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, maestros.getNombre_maestro());
            stmt.setString(2, maestros.getDireccion_maestro());
            stmt.setString(3, maestros.getTelefono_maetro());
            stmt.setString(4, maestros.getEmail_maestro());
            stmt.setString(5, maestros.getEstatus_maestro());

            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(Maestro maestros) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, maestros.getNombre_maestro());
            stmt.setString(2, maestros.getDireccion_maestro());
            stmt.setString(3, maestros.getTelefono_maetro());
            stmt.setString(4, maestros.getEmail_maestro());
            stmt.setString(5, maestros.getEstatus_maestro());
            stmt.setInt(6, maestros.getCodigo_maestro());

            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(Maestro maestros) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, maestros.getCodigo_maestro());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

//    public List<Persona> query(Persona vendedor) { // Si se utiliza un ArrayList
    public Maestro query(Maestro maestros) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Maestro> vendedores = new ArrayList<Maestro>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, maestros.getCodigo_maestro());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int codigo_maestro = rs.getInt("codigo_maestro");
                String nombre_maestro = rs.getString("nombre_maestro");
                String direccion_maestro = rs.getString("direccion_maestro");
                String telefono_maetro = rs.getString("telefono_maetro");
                String email_maestro = rs.getString("email_maestro");
                String estatus_maestro = rs.getString("estatus_maestro");
                
                maestros = new Maestro();
                maestros.setCodigo_maestro(codigo_maestro);
                maestros.setNombre_maestro(nombre_maestro);
                maestros.setDireccion_maestro(direccion_maestro);
                maestros.setTelefono_maetro(telefono_maetro);
                maestros.setEmail_maestro(email_maestro);
                maestros.setEstatus_maestro(estatus_maestro);
                
                //vendedores.add(vendedor); // Si se utiliza un ArrayList
            }
            //System.out.println("Registros buscado:" + vendedor);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        //return vendedores;  // Si se utiliza un ArrayList
        return maestros;
    }
}
