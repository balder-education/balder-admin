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
import br.edu.unitri.dao.impl.address.CityDao;
import br.edu.unitri.model.address.City;
import br.edu.unitri.repository.CrudBean;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class CityBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private City cidade = new City();
	private City cidadeSel;
	private List<City> listCidade = new ArrayList<City>();

	@Inject
	private CityDao cityDao;

	public CityBean() {
		super();
	}

	public City getCidade() {
		return cidade;
	}

	public void setCidade(City cidade) {
		this.cidade = cidade;
	}

	public City getCidadeSel() {
		return cidadeSel;
	}

	public void setCidadeSel(City cidadeSel) {
		this.cidadeSel = cidadeSel;
	}

	public List<City> getListCidades() {
		return listCidade;
	}

	public void setListCidades(List<City> listCidade) {
		this.listCidade = listCidade;
	}
	
	@PostConstruct
	public void init() {
		cidade = new City();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (cidade.getId() != null) {
			cityDao.update(cidade);
		} else {
			cityDao.save(cidade);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		listCidade.clear();
		listCidade = cityDao.findByEntity(cidade);
		if (listCidade.size() == 0) {
			UtilBeanFaces.addMessage("Não existe cidades para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (cidadeSel != null) {
			if (cityDao.deleteEntity(cidadeSel)){
				init();
				UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
			} else {
				UtilBeanFaces.addMessage("Operação não pode ser realizada-> Existem relações com outras entidades!", null, "WARNING", null);
			}
		}
	}

	@Override
	public void editar() {
		setCidade(getCidadeSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listCidade = cityDao.findAll();
	}

}
