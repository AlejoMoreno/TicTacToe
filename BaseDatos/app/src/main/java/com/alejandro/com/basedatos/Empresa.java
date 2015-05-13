package com.alejandro.com.basedatos;

import java.util.ArrayList;

/**
 * Created by GSN on 12/05/2015.
 */
public class Empresa {

    private int id;
    private String nombre;
    private String url;
    private String telefono;
    private String email;
    private String productos;
    private String clasificacion;
    public ArrayList<Empresa> listaEmpleado = new ArrayList<Empresa>();

    public Empresa(){
        //constructor vacio
    }

    public Empresa(int id, String nombre, String url, String telefono, String email, String productos, String clasificacion) {
        this.id = id;
        this.nombre = nombre;
        this.url = url;
        this.telefono = telefono;
        this.email = email;
        this.productos = productos;
        this.clasificacion = clasificacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email;  }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public ArrayList<Empresa> addlistEmpleado(Empresa empresa){
        listaEmpleado.add(empresa);
        return listaEmpleado;
    }
}
