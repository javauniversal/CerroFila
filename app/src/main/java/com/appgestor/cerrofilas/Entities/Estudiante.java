package com.appgestor.cerrofilas.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Estudiante {

    @SerializedName("identificacion")
    private String idIdentificacion;

    @SerializedName("carnet")
    private String carnet;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("apellido")
    private String apellidos;

    @SerializedName("direccion")
    private String direccion;

    @SerializedName("correo")
    private String correo;

    @SerializedName("password")
    private String password;

    @SerializedName("tipoidentificacion")
    private String tipoIdentificacion;

    @SerializedName("fechanacimiento")
    private Date fechaNacimiento;

    @SerializedName("celular")
    private String celular;

    @SerializedName("estadocivil")
    private String estadoCivil;

    @SerializedName("foto")
    private String foto;

    @SerializedName("estado")
    private int estado;

    public static Estudiante estudiante;

    public String getIdIdentificacion() {
        return idIdentificacion;
    }

    public void setIdIdentificacion(String idIdentificacion) {
        this.idIdentificacion = idIdentificacion;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public static Estudiante getEstudiante() {
        return estudiante;
    }

    public static void setEstudiante(Estudiante estudiante) {
        Estudiante.estudiante = estudiante;
    }

}
