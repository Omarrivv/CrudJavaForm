/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_formulario_paginaweb;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Usuario
 */
public class CAlumnos {
    
    int codigo;
    String nombreAlumno;
    String apellidoAlumno;
    String correoAlumno;
    String telefonoAlumno;
    String MensajeAlumno;

    public int getCodigo() { // obtener
        return codigo;
    }

    public void setCodigo(int codigo) { // incorporar
        this.codigo = codigo;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getApellidoAlumno() {
        return apellidoAlumno;
    }

    public void setApellidoAlumno(String apellidoAlumno) {
        this.apellidoAlumno = apellidoAlumno;
    }

    public String getCorreoAlumno() {
        return correoAlumno;
    }

    public void setCorreoAlumno(String correoAlumno) {
        this.correoAlumno = correoAlumno;
    }

    public String getTelefonoAlumno() {
        return telefonoAlumno;
    }

    public void setTelefonoAlumno(String telefonoAlumno) {
        this.telefonoAlumno = telefonoAlumno;
    }

    public String getMensajeAlumno() {
        return MensajeAlumno;
    }

    public void setMensajeAlumno(String MensajeAlumno) {
        this.MensajeAlumno = MensajeAlumno;
    }
    
    public void InsertarAlumno(JTextField paramNombre, JTextField paramApellido, JTextField paramCorreo, JTextField paramTelefono, JTextField paramMensaje){
        setNombreAlumno(paramNombre.getText());
        setApellidoAlumno(paramApellido.getText());
        setCorreoAlumno(paramCorreo.getText());
        setTelefonoAlumno(paramTelefono.getText());
        setMensajeAlumno(paramMensaje.getText());
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "insert into DatosPersonales (Nombre, Apellido, CorreoElectronico, Telefono, Mensaje) values (?, ?, ?, ?, ?);";
        
        try {
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, getNombreAlumno());
            cs.setString(2, getApellidoAlumno());
            cs.setString(3, getCorreoAlumno());
            cs.setString(4, getTelefonoAlumno());
            cs.setString(5, getMensajeAlumno());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se inserto correctamente ala Persona");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "No se inserto correctamente el alumno error " + e.toString());
          
        }
        
    }
    
    
    public void MostrarAlumnos(JTable paramTablaTotalAlumnos){
        
        CConexion objetoConeccion = new CConexion(); // crear coeccion un objeto preparacion
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo); //si le hago click en la cabeserapara q se ordena alfabeticamente como un excel
        paramTablaTotalAlumnos.setRowSorter(OrdenarTabla); // incorpara el orden de cabesera a :: paramTablaTotalAlumnos
        
        String sql="";
        
        modelo.addColumn("Id");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("CorreoElectronico");
        modelo.addColumn("Telefono");
        modelo.addColumn("Mensaje");
        
        paramTablaTotalAlumnos.setModel(modelo);
        
        sql = "select * from DatosPersonales";
        
        String[] datos = new String[6]; //llenar depende de la consulta q aya seleccionado
        Statement st; // preparando para recien ejecutar
        // longitud de 3
        try {
            
            st = objetoConeccion.estableceConexion().createStatement(); // realizando la conexion
            ResultSet rs = st.executeQuery(sql); // ejecutar
            
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                
                modelo.addRow(datos);
                
            }
            
            paramTablaTotalAlumnos.setModel(modelo);
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros error " + e.toString());
        }
        
    }
    
    public void SeleccionarAlumno(JTable paramTablaAlumnos, JTextField paramId, JTextField paramNombres, JTextField paramApellidos, JTextField paramCorreo, JTextField paramTelefono, JTextField paramMensaje){
        try {
            
            int fila = paramTablaAlumnos.getSelectedRow();
            
            if (fila>=0){
                paramId.setText(paramTablaAlumnos.getValueAt(fila, 0).toString());
                paramNombres.setText(paramTablaAlumnos.getValueAt(fila, 1).toString());
                paramApellidos.setText(paramTablaAlumnos.getValueAt(fila, 2).toString());
                paramCorreo.setText(paramTablaAlumnos.getValueAt(fila, 3).toString());
                paramTelefono.setText(paramTablaAlumnos.getValueAt(fila, 4).toString());
                paramMensaje.setText(paramTablaAlumnos.getValueAt(fila, 5).toString());
            }else{
                JOptionPane.showMessageDialog(null, "Fila no seleccionada Error");
            }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Error de Selleccion : Error : " + e.toString());
            
        }
    }
    
    
    public void ModificarAlumnos(JTextField paramCodigo, JTextField paramNombre, JTextField paramApellido, JTextField paramCorreo, JTextField paramTelefono, JTextField paramMensaje){
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNombreAlumno(paramNombre.getText());
        setApellidoAlumno(paramApellido.getText());
        setCorreoAlumno(paramCorreo.getText());
        setTelefonoAlumno(paramTelefono.getText());
        setMensajeAlumno(paramMensaje.getText());
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "UPDATE DatosPersonales SET datosPersonales.Nombre = ?, datosPersonales.Apellido = ?, datosPersonales.CorreoElectronico = ?, datosPersonales.Telefono = ?, datosPersonales.Mensaje = ? WHERE datosPersonales.id=?;";
        
        try {
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, getNombreAlumno());
            cs.setString(2, getApellidoAlumno());
            cs.setString(3, getCorreoAlumno());
            cs.setString(4, getTelefonoAlumno());
            cs.setString(5, getMensajeAlumno());
            cs.setInt(6, getCodigo());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Modificacion exitosa");
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se Modifico , error: " + e.toString());
        }
        
        
    }
    
    public void EliminarAlumnos(JTextField paramCodigo){
        
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "DELETE FROM DatosPersonales WHERE datosPersonales.id=?;";
        
        try {
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setInt(1, getCodigo());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Eliminacion exitosa");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se Elimino Correctamente Error : " + e.toString());
        }
        
    }
    
    
    
}
