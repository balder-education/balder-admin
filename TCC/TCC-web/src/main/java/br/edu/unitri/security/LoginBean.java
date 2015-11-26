package br.edu.unitri.security;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.unitri.controller.UtilBeanFaces;
import br.edu.unitri.controller.impl.user.UserBean;
import br.edu.unitri.dao.impl.user.UserDao;
import br.edu.unitri.model.user.User;

/**
 * @author marcos.fernando
 *
 */

@Named
@RequestScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserDao usuarioDao;	
	
	@Inject
	UserBean usuarioBean;
	

	private String userName;
	private String password;

	public String login() {
		
		User usuario = new User();
		User user = new User();
		usuario.setUserName(userName);
		usuario.setPassword(password);
		boolean success = false;
		
		user = usuarioDao.findByUser(usuario);
		if (user != null) {
			if (user.getPassword().equals(usuario.getPassword())) {
				success = true;
			} else {
				success = false;
			}
		} else {
			success = false;
		}
		 
		if (!success) {
			UtilBeanFaces.addMessage("Login ou senha inválidos!", null, "WARNING", null);
			return "falhaLogin";
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentUser", user);
		usuarioBean.setUsuarioLogado(user);
		return "sucessoLogin";
	}

	public String logout() {
		return "login";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
