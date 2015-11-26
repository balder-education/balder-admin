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
import br.edu.unitri.dao.impl.address.StateDao;
import br.edu.unitri.model.address.State;
import br.edu.unitri.repository.CrudBean;
import br.edu.unitri.util.RegexUtil;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class StateBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private State estado = new State();
	private State estadoSel;
	private List<State> listEstado = new ArrayList<State>();
	private StringBuilder mensagem = new StringBuilder();

	@Inject
	private StateDao estadoDao;

	public StateBean() {
		super();
	}

	public State getEstado() {
		return estado;
	}

	public void setEstado(State estado) {
		this.estado = estado;
	}

	public State getEstadoSel() {
		return estadoSel;
	}

	public void setEstadoSel(State estadoSel) {
		this.estadoSel = estadoSel;
	}

	public List<State> getListEstado() {
		return listEstado;
	}

	public void setListEstado(List<State> listEstado) {
		this.listEstado = listEstado;
	}

	public StringBuilder getMensagem() {
		return mensagem;
	}

	public void setMensagem(StringBuilder mensagem) {
		this.mensagem = mensagem;
	}

	@PostConstruct
	public void init() {
		estado = new State();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (estado.getId() != null) {
			estadoDao.update(estado);
		} else {
			estadoDao.save(estado);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}
	
	private boolean isValidBusca(State estado) {
		getMensagem().delete(0,getMensagem().length());
		if (!estado.getName().isEmpty()) {
		   if (!RegexUtil.isValidCampoString(estado.getName())) {
			  getMensagem().append("Nome do estado deve conter somente letras.");  
		  };
		}
		if (!estado.getDescription().isEmpty()) {
		  if (!RegexUtil.isValidCampoString(estado.getDescription())){
			  getMensagem().append("Descrição do nome do estado deve conter somente letras.");
		  }
		}
		return getMensagem().length() == 0;
	}


	@Override
	public void buscar() {
		if (isValidBusca(estado)) {
			listEstado.clear();
			listEstado = estadoDao.findByEntity(estado);
			if (listEstado.size() == 0) {
				UtilBeanFaces.addMessage("Não existe estados para serem exibidos", null, "WARNING", null);
			}
		} else {
			UtilBeanFaces.addMessage(getMensagem().toString(),null,"ERRO","Erro de validação dos campos");
		}
	}

	@Override
	public void excluir() {
		if (estadoSel != null) {
			if (estadoDao.deleteEntity(estadoSel)){
				init();
				UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
			} else {
				UtilBeanFaces.addMessage("Operação não pode ser realizada-> Existem relações com outras entidades!", null, "WARNING", null);
			}
		}
	}

	@Override
	public void editar() {
		setEstado(getEstadoSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listEstado = estadoDao.findAll();
	}

}
