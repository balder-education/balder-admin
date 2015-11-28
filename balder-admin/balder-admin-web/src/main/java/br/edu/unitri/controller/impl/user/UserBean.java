/**
 * 
 */
package br.edu.unitri.controller.impl.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.unitri.controller.UtilBeanFaces;
import br.edu.unitri.dao.impl.user.UserDao;
import br.edu.unitri.model.user.User;
import br.edu.unitri.repository.CrudBean;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class UserBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User usuario = new User();
	private User usuarioSel;
	private User usuarioLogado;
	private List<User> listUsuarios = new ArrayList<User>();
	
	@Inject
	private UserDao usuarioDao;
	
	public UserBean() {
		super();
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public User getUsuarioSel() {
		return usuarioSel;
	}

	public void setUsuarioSel(User usuarioSel) {
		this.usuarioSel = usuarioSel;
	}

	public List<User> getListUsuarios() {
		return listUsuarios;
	}

	public void setListUsuarios(List<User> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}

	@PostConstruct
	public void init() {
		usuario = new User();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (usuario.getId() != null) {
			usuarioDao.update(usuario);
		} else {
			usuarioDao.save(usuario);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		listUsuarios.clear();
		listUsuarios = usuarioDao.findByEntity(usuario);
		if (listUsuarios.size() == 0) {
			UtilBeanFaces.addMessage("Não existem usuários para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (usuarioSel != null) {
			usuarioDao.deleteEntity(usuarioSel);
			init();
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
	}

	@Override
	public void editar() {
		setUsuario(getUsuarioSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listUsuarios = usuarioDao.findAll();
	}
	
	public User getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(User usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String logar() {
		usuarioLogado = usuarioDao.findByUser(usuario);
		if (usuarioLogado != null) {
			if (usuarioLogado.getPassword().equals(usuario.getPassword())) {
				return "index.xhtml";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

}
