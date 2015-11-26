/**
 * 
 */
package br.edu.unitri.controller.impl.address;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.unitri.controller.UtilBeanFaces;
import br.edu.unitri.dao.impl.address.CepDao;
import br.edu.unitri.model.address.Cep;
import br.edu.unitri.repository.CrudBean;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class CepBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cep cep = new Cep();
	private Cep cepSel;
	private List<Cep> listCep = new ArrayList<Cep>();

	@Inject
	private CepDao cepDao;

	public CepBean() {
		super();
	}

	public Cep getCep() {
		return cep;
	}

	public void setCep(Cep cep) {
		this.cep = cep;
	}

	public Cep getCepSel() {
		return cepSel;
	}

	public void setCepSel(Cep cepSel) {
		this.cepSel = cepSel;
	}

	public List<Cep> getListCep() {
		return listCep;
	}

	public void setListCep(List<Cep> listCep) {
		this.listCep = listCep;
	}

	@PostConstruct
	public void init() {
		cep = new Cep();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (cep.getId() != null) {
			cepDao.update(cep);
		} else {
			cepDao.save(cep);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		listCep.clear();
		listCep = cepDao.findByEntity(cep);
		if (listCep.size() == 0) {
			UtilBeanFaces.addMessage("Não existe ceps para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (cepSel != null) {
			if (cepDao.deleteEntity(cepSel)){
				init();
				UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
			} else {
				UtilBeanFaces.addMessage("Operação não pode ser realizada-> Existem relações com outras entidades!", null, "WARNING", null);
			}
		}
	}

	@Override
	public void editar() {
		setCep(getCepSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listCep = cepDao.findAll();
	}

}
