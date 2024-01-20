/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1p2_luiscastro;

import java.util.Date;

/**
 *
 * @author lfern
 */
public class usuario {
    String nombre;
    String apellido;
    Date fecha;
    String nombre_correo;
    String dominio_correo;
    String contra;
    String correo;

    public usuario(String nombre, String apellido, Date fecha, String nombre_correo, String dominio_correo, String contra, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
        this.nombre_correo = nombre_correo;
        this.dominio_correo = dominio_correo;
        this.contra = contra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre_correo() {
        return nombre_correo;
    }

    public void setNombre_correo(String nombre_correo) {
        this.nombre_correo = nombre_correo;
    }

    public String getDominio_correo() {
        return dominio_correo;
    }

    public void setDominio_correo(String dominio_correo) {
        this.dominio_correo = dominio_correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
 
}

