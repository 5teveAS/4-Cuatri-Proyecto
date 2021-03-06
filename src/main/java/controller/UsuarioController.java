/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.UsuarioGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Usuario;

/**
 *
 * @author AndySpino20
 */
@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController extends Usuario implements Serializable {

    public UsuarioController() {

    }

    public String getUsuario() {
        Usuario usuario = UsuarioGestion.getUsuario(this.getUserid(), this.getPwUsuario());
        if (usuario != null) {
            this.setNombreUsuario(usuario.getNombreUsuario());
            this.setIdRol(usuario.getIdRol());
            return "principal.xhtml";
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Usuario o contraseña incorrectos");
            FacesContext.getCurrentInstance().addMessage("LoginForm:clave", mensaje);
            return "index.xhtml";
        }
    }

    /* Este Cerrar Sesion no funciona en todas las paginas   
    public String logout() {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
                .getSession(true)).invalidate();
        return "index.xhtml";
    }
    <b:form>
    <b:commandButton value="Salir" action="#{usuarioController.logout}" />
    <b:form/>
     */
}
