/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_formulario_paginaweb;


import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class CConexion {
    Connection conectar = null;
    String usuario = "root";
    String contraseña = "admin";
    String bd = "formDatos";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd; // cadena De conexion
    
    public Connection estableceConexion(){
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contraseña); 
// obtener la coneccion dentro de comillas los valoreess
            JOptionPane.showMessageDialog(null, "La connecion se ha realizado con exito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos error " + e.toString()); // me devuelve en cadena con tostring
        }
        return conectar;
    }
    
}
