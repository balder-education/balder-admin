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
import br.edu.unitri.dao.impl.Content.ContentLogDao;
import br.edu.unitri.model.Content.ContentLog;
import br.edu.unitri.repository.CrudBean;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class ContentLogBean implements CrudBean, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ContentLog conteudoLog = new ContentLog();
	private ContentLog conteudoLogSel;
	private List<ContentLog> listConteudoLog = new ArrayList<ContentLog>();
	
	@Inject
	private ContentLogDao conteudoLogDao;

	public ContentLogBean() {
		super();
	}

	public ContentLog getConteudoLog() {
		return conteudoLog;
	}

	public void setConteudoLog(ContentLog conteudoLog) {
		this.conteudoLog = conteudoLog;
	}

	public ContentLog getConteudoLogSel() {
		return conteudoLogSel;
	}

	public void setConteudoLogSel(ContentLog conteudoLogSel) {
		this.conteudoLogSel = conteudoLogSel;
	}

	public List<ContentLog> getListConteudoLog() {
		return listConteudoLog;
	}

	public void setListConteudoLog(List<ContentLog> listConteudoLog) {
		this.listConteudoLog = listConteudoLog;
	}

	@PostConstruct
	public void init() {
		conteudoLog = new ContentLog();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (conteudoLog.getId() != null) {
			conteudoLogDao.update(conteudoLog);
		} else {
			conteudoLogDao.save(conteudoLog);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		listConteudoLog.clear();
		listConteudoLog = conteudoLogDao.findByEntity(conteudoLog);
		if (listConteudoLog.size() == 0) {
			UtilBeanFaces.addMessage("Não existem logs de conteúdos para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (conteudoLogSel != null) {
			conteudoLogDao.deleteEntity(conteudoLogSel);
			init();
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
	}

	@Override
	public void editar() {
		setConteudoLog(getConteudoLogSel());;
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listConteudoLog = conteudoLogDao.findAll();
	}
}
