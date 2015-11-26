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
import br.edu.unitri.dao.impl.address.CountryDao;
import br.edu.unitri.model.address.Country;
import br.edu.unitri.repository.CrudBean;
import br.edu.unitri.util.RegexUtil;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class CountryBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Country pais = new Country();
	private Country paisSel;
	private List<Country> listPais = new ArrayList<Country>();
	
	@Inject
	private CountryDao paisDao; 
	

	public CountryBean() {
		super();
	}

	public Country getPais() {
		return pais;
	}

	public void setPais(Country pais) {
		this.pais = pais;
	}

	public Country getPaisSel() {
		return paisSel;
	}

	public void setPaisSel(Country paisSel) {
		this.paisSel = paisSel;
	}

	public List<Country> getListPais() {
		return listPais;
	}

	public void setListPais(List<Country> listPais) {
		this.listPais = listPais;
	}
	
	@PostConstruct
	public void init() {
		pais = new Country();
		listarTodos();
	}
	
	@Override
	public void salvar() {
		if (pais.getId() != null) {
			paisDao.update(pais);
		} else {
			paisDao.save(pais);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}
	
	private boolean isValidBusca(Country pais) {
		if (!pais.getDescription().isEmpty()) {			
			return RegexUtil.isValidCampoString(pais.getDescription());
		}
		return true;
	}

	@Override
	public void buscar() {
		if (isValidBusca(pais)) {
			listPais.clear();
			listPais = paisDao.findByEntity(pais);
			if (listPais.size() == 0) {
				UtilBeanFaces.addMessage("Não existe países para serem exibidos", null, "WARNING", null);
			}
		} else {
			UtilBeanFaces.addMessage("Descrição do país deve conter somente caracteres",null,"ERRO","Erro de Validação");
		}
	}

	@Override
	public void excluir() {
		if (paisSel != null) {
			if (paisDao.deleteEntity(paisSel)){
				init();
				UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
			} else {
				UtilBeanFaces.addMessage("Operação não pode ser realizada-> Existem relações com outras entidades!", null, "WARNING", null);
			}
		}
	}

	@Override
	public void editar() {
		setPais(getPaisSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listPais = paisDao.findAll();
	}

}
