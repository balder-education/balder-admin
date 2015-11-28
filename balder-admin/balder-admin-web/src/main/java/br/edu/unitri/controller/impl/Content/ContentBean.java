/**
 * 
 */
package br.edu.unitri.controller.impl.Content;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.unitri.controller.UtilBeanFaces;
import br.edu.unitri.dao.impl.Content.ContentDao;
import br.edu.unitri.model.Content.Content;
import br.edu.unitri.repository.CrudBean;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class ContentBean implements CrudBean, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Content conteudo = new Content();
	private Content conteudoSel;
	private List<Content> listConteudo = new ArrayList<Content>();
	
	@Inject
	private ContentDao conteudoDao;

	public ContentBean() {
		super();
	}

	public Content getConteudo() {
		return conteudo;
	}

	public void setConteudo(Content conteudo) {
		this.conteudo = conteudo;
	}

	public Content getConteudoSel() {
		return conteudoSel;
	}

	public void setConteudoSel(Content conteudoSel) {
		this.conteudoSel = conteudoSel;
	}

	public List<Content> getListConteudo() {
		return listConteudo;
	}

	public void setListConteudo(List<Content> listConteudo) {
		this.listConteudo = listConteudo;
	}
	
	@PostConstruct
	public void init() {
		conteudo = new Content();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (conteudo.getId() != null) {
			conteudoDao.update(conteudo);
		} else {
			conteudoDao.save(conteudo);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		listConteudo.clear();
		listConteudo = conteudoDao.findByEntity(conteudo);
		if (listConteudo.size() == 0) {
			UtilBeanFaces.addMessage("Não existem conteúdos para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (conteudoSel != null) {
			if (conteudoDao.deleteEntity(conteudoSel)){
				init();
				UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
			} else {
				UtilBeanFaces.addMessage("Operação não pode ser realizada-> Existem relações com outras entidades!", null, "WARNING", null);
			}
		}
	}

	@Override
	public void editar() {
		setConteudo(getConteudoSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listConteudo = conteudoDao.findAll();
	}
	
}
