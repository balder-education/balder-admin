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
import br.edu.unitri.dao.impl.user.RoleDao;
import br.edu.unitri.model.user.Role;
import br.edu.unitri.repository.CrudBean;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class RoleBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Role perfil = new Role();
	private Role perfilSel;
	private List<Role> listperfils = new ArrayList<Role>();

	@Inject
	private RoleDao perfilDao;

	public RoleBean() {
		super();
	}

	public Role getPerfil() {
		return perfil;
	}

	public void setPerfil(Role perfil) {
		this.perfil = perfil;
	}

	public Role getPerfilSel() {
		return perfilSel;
	}

	public void setPerfilSel(Role perfilSel) {
		this.perfilSel = perfilSel;
	}

	public List<Role> getListperfils() {
		return listperfils;
	}

	public void setListperfils(List<Role> listperfils) {
		this.listperfils = listperfils;
	}

	@PostConstruct
	public void init() {
		perfil = new Role();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (perfil.getId() != null) {
			perfilDao.update(perfil);
		} else {
			perfilDao.save(perfil);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		listperfils.clear();
		listperfils = perfilDao.findByEntity(perfil);
		if (listperfils.size() == 0) {
			UtilBeanFaces.addMessage("Não existem perfis para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (perfilSel != null) {
			perfilDao.deleteEntity(perfilSel);
			init();
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
	}

	@Override
	public void editar() {
		setPerfil(getPerfilSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listperfils = perfilDao.findAll();
	}
}
