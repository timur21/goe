package kg.goent.controllers;


import org.primefaces.component.inputtext.InputTextRenderer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean(name = "printController")
@SessionScoped
public class PrintController implements Serializable {

    public void createPDF(){
        String PDF_FILE_NAME = "converted.pdf";
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest)externalContext.getRequest();
        HttpSession session = (HttpSession)externalContext.getSession(true);
        String url = request.getRequestURL().append(":jsessionid=").append(session.getId().toString()).toString();



    }
}
