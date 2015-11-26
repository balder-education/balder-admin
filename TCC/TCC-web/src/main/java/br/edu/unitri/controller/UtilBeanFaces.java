/**
 * 
 */
package br.edu.unitri.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * @author marcos.fernando
 *
 */
@Named
public class UtilBeanFaces implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String getAbsolutePathApp() {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		int porta = request.getServerPort();
		String port = porta != 0 ? ":" + porta : "";
		String contextPath = request.getContextPath() != null ? request
				.getContextPath() : "";
		String absolutePathApp = request.getServerName() + port + contextPath;
		return "http://" + absolutePathApp;
	}

	public static void navegador(String pagina) {
		FacesContext fc = FacesContext.getCurrentInstance();
		NavigationHandler nh = fc.getApplication().getNavigationHandler();
		nh.handleNavigation(fc, null, pagina.concat("?faces-redirect=true"));
	}
	
	public static String getFileTempResource(){
		return FacesContext.getCurrentInstance().getExternalContext().getRealPath("//temp//");
	}

	public static String getFilesResource(){
		return FacesContext.getCurrentInstance().getExternalContext().getRealPath("//files//");
	}
	
	public static void addMessage(String msg, String cliente, String tipo,  String erro) {
		
		String[] mensagens = msg.split("[.]");
		if (mensagens.length > 1) {
			for (int i = 0; i < mensagens.length; i++) {
				switch (tipo) {
				case "INFO":
					FacesContext.getCurrentInstance().addMessage(cliente,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", mensagens[i]));
					break;
				case "ERRO":
					FacesContext.getCurrentInstance().addMessage(cliente,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", mensagens[i]));
					break;
				case "WARNING":
					FacesContext.getCurrentInstance().addMessage(cliente,
							new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção: ", mensagens[i]));
					break;
				}
			}
		} else {
			switch (tipo) {
			case "INFO":
				FacesContext.getCurrentInstance().addMessage(cliente,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", msg));
				break;
			case "ERRO":
				FacesContext.getCurrentInstance().addMessage(cliente,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", msg + erro));
				break;
			case "WARNING":
				FacesContext.getCurrentInstance().addMessage(cliente,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção: ", msg));
				break;
			}
			
		}
	}

}
