/**
 * 
 */
package br.edu.unitri.controller.impl.person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.unitri.controller.UtilBeanFaces;
import br.edu.unitri.dao.impl.person.ContatoDao;
import br.edu.unitri.model.person.Contato;
import br.edu.unitri.repository.CrudBean;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class ContatoBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Contato contato = new Contato();
	private Contato contatoSel;
	private List<Contato> listcontatos = new ArrayList<Contato>();

	@Inject
	private ContatoDao contatoDao;

	public ContatoBean() {
		super();
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Contato getContatoSel() {
		return contatoSel;
	}

	public void setContatoSel(Contato contatoSel) {
		this.contatoSel = contatoSel;
	}

	public List<Contato> getListcontatos() {
		return listcontatos;
	}

	public void setListcontatos(List<Contato> listcontatos) {
		this.listcontatos = listcontatos;
	}

	@PostConstruct
	public void init() {
		contato = new Contato();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (contato.getId() != null) {
			contatoDao.update(contato);
		} else {
			contatoDao.save(contato);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		listcontatos.clear();
		listcontatos = contatoDao.findByEntity(contato);
		if (listcontatos.size() == 0) {
			UtilBeanFaces.addMessage("Não existem contatos para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (contatoSel != null) {
			contatoDao.deleteEntity(contatoSel);
			init();
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
	}

	@Override
	public void editar() {
		setContato(getContatoSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listcontatos = contatoDao.findAll();
	}
}
