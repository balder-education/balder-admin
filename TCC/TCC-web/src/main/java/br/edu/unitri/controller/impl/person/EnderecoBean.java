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
import br.edu.unitri.dao.impl.person.EnderecoDao;
import br.edu.unitri.model.person.Endereco;
import br.edu.unitri.repository.CrudBean;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class EnderecoBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Endereco endereco = new Endereco();
	private Endereco enderecoSel;
	private List<Endereco> listenderecos = new ArrayList<Endereco>();

	@Inject
	private EnderecoDao enderecoDao;

	public EnderecoBean() {
		super();
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Endereco getEnderecoSel() {
		return enderecoSel;
	}

	public void setEnderecoSel(Endereco enderecoSel) {
		this.enderecoSel = enderecoSel;
	}

	public List<Endereco> getListenderecos() {
		return listenderecos;
	}

	public void setListenderecos(List<Endereco> listenderecos) {
		this.listenderecos = listenderecos;
	}

	@PostConstruct
	public void init() {
		endereco = new Endereco();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (endereco.getId() != null) {
			enderecoDao.update(endereco);
		} else {
			enderecoDao.save(endereco);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		listenderecos.clear();
		listenderecos = enderecoDao.findByEntity(endereco);
		if (listenderecos.size() == 0) {
			UtilBeanFaces.addMessage("Não existem enderecos para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (enderecoSel != null) {
			enderecoDao.deleteEntity(enderecoSel);
			init();
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
	}

	@Override
	public void editar() {
		setEndereco(getEnderecoSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listenderecos = enderecoDao.findAll();
	}
}
