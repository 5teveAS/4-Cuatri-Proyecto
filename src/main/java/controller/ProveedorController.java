/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.ProveedorGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Proveedor;

/**
 *
 * @author AndySpino20
 */
@Named(value = "proveedorController")
@SessionScoped
public class ProveedorController extends Proveedor implements Serializable {

    public ProveedorController() {
    }

    public String registroProveedor() {
        return "registroProveedor.xhtml";
    }

    public String insertProveedor() {
        if (ProveedorGestion.insertProveedor(this)) {
            return "listaProveedor.xhtml";

        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al insertar el proveedor");
            FacesContext.getCurrentInstance().addMessage("registroProveedorForm:cedulaJuridica", message);
            return "registroProveedor.xhtml";
        }
    }

    public String updateProveedor() {

        if (ProveedorGestion.updateProveedor(this)) {
            return "listaProveedor.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al actualizar al proveedor");
            FacesContext.getCurrentInstance().addMessage("editaProveedorForm:cedulaJuridica", message);
            return "editaProveedor.xhtml";
        }
    }

    public String deleteProveedor() {
        if (ProveedorGestion.deleteProveedor(this)) {
            return "listaProveedor.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "Ocurrio un error al eliminar al proveedor");
            FacesContext.getCurrentInstance().addMessage("editaProveedorForm:cedulaJuridica", message);
            return "editaProveedor.xhtml";
        }
    }

    public List<Proveedor> getProveedor() {
        return ProveedorGestion.getProvedores();
    }

    public String editaProveedor(int proveeid) {
        Proveedor proveedor = ProveedorGestion.getProveedor(proveeid);
        if (proveedor != null) {
            this.setProveeid(proveedor.getProveeid());
            this.setCedulaJuridica(proveedor.getCedulaJuridica());
            this.setNombre(proveedor.getNombre());
            this.setDirreccion(proveedor.getDirreccion());
            this.setTelefono(proveedor.getTelefono());
            this.setCorreo(proveedor.getCorreo());
            this.setFechaIngre(proveedor.getFechaIngre());
            return "editaProveedor.xhtml";

        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "El registro del proveedor seleccionado no existe");
            FacesContext.getCurrentInstance().addMessage("editaProveedorForm:cedulaJuridica", message);
            return "listaProveedor.xhtml";
        }
    }

    private boolean noImprimir = true;

    public boolean isNoImprimir() {
        return noImprimir;
    }

    public void setNoImprimir(boolean noImprimir) {
        this.noImprimir = noImprimir;
    }

    public void buscarProveedor(String cedulaJuridica) {
        Proveedor proveedor = ProveedorGestion.buscarProveedor(cedulaJuridica);
        if (proveedor != null) {
            this.setProveeid(proveedor.getProveeid());
            this.setCedulaJuridica(proveedor.getCedulaJuridica());
            this.setNombre(proveedor.getNombre());
            this.setDirreccion(proveedor.getDirreccion());
            this.setTelefono(proveedor.getTelefono());
            this.setCorreo(proveedor.getCorreo());
            this.setFechaIngre(proveedor.getFechaIngre());
            noImprimir = false;
        } else {
            this.setCedulaJuridica("");
            this.setNombre("");
            this.setDirreccion("");
            this.setTelefono(0);
            this.setCorreo("");
            this.setFechaIngre(null);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al Conectar",
                    "El registro no se encontro");
            FacesContext.getCurrentInstance().addMessage("reporteProveedorForm:cedulaJuridica", message);
            noImprimir = true;
        }
    }
}
