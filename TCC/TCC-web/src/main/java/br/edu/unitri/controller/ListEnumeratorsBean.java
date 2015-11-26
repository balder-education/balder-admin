/**
 * 
 */
package br.edu.unitri.controller;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import br.edu.unitri.enumerators.TipoContato;
import br.edu.unitri.enumerators.TipoConteudo;
import br.edu.unitri.enumerators.TipoEndereco;
import br.edu.unitri.enumerators.TipoEnum;
import br.edu.unitri.enumerators.TipoLevelContent;
import br.edu.unitri.enumerators.TipoLevelStudent;
import br.edu.unitri.enumerators.TipoStatus;
import br.edu.unitri.enumerators.TipoUsuario;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class ListEnumeratorsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SelectItem> listTiposContatos = getTiposContatos();
	private List<SelectItem> listTiposConteudos = getTiposConteudos();
	private List<SelectItem> listTiposEnderecos = getTiposEnderecos();
	private List<SelectItem> listTiposLevelsContent = getTiposLevelsContent();
	private List<SelectItem> listTiposLevelsStudent = getTiposLevelsStudent();
	private List<SelectItem> listTiposUsuarios = getTiposUsuarios();
	private List<SelectItem> listTiposStatus = getTiposStatus();

	protected List<SelectItem> getTipoEnum(Enum<? extends TipoEnum>[] valor) {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (Enum<?> v : valor) {
			try {
				String descricao = (String) v.getClass().getDeclaredMethod("getDescricao").invoke(v);
				items.add(new SelectItem(v, descricao));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return items;
	}

	private List<SelectItem> getTiposStatus() {
		return getTipoEnum(TipoStatus.values());
	}

	private List<SelectItem> getTiposUsuarios() {
		return getTipoEnum(TipoUsuario.values());
	}

	private List<SelectItem> getTiposLevelsStudent() {
		return getTipoEnum(TipoLevelStudent.values());
	}

	private List<SelectItem> getTiposLevelsContent() {
		return getTipoEnum(TipoLevelContent.values());
	}

	private List<SelectItem> getTiposEnderecos() {
		return getTipoEnum(TipoEndereco.values());
	}

	private List<SelectItem> getTiposConteudos() {
		return getTipoEnum(TipoConteudo.values());
	}

	private List<SelectItem> getTiposContatos() {
		return getTipoEnum(TipoContato.values());
	}

	public List<SelectItem> getListTiposContatos() {
		return listTiposContatos;
	}

	public void setListTiposContatos(List<SelectItem> listTiposContatos) {
		this.listTiposContatos = listTiposContatos;
	}

	public List<SelectItem> getListTiposConteudos() {
		return listTiposConteudos;
	}

	public void setListTiposConteudos(List<SelectItem> listTiposConteudos) {
		this.listTiposConteudos = listTiposConteudos;
	}

	public List<SelectItem> getListTiposEnderecos() {
		return listTiposEnderecos;
	}

	public void setListTiposEnderecos(List<SelectItem> listTiposEnderecos) {
		this.listTiposEnderecos = listTiposEnderecos;
	}

	public List<SelectItem> getListTiposLevelsContent() {
		return listTiposLevelsContent;
	}

	public void setListTiposLevelsContent(List<SelectItem> listTiposLevelsContent) {
		this.listTiposLevelsContent = listTiposLevelsContent;
	}

	public List<SelectItem> getListTiposLevelsStudent() {
		return listTiposLevelsStudent;
	}

	public void setListTiposLevelsStudent(List<SelectItem> listTiposLevelsStudent) {
		this.listTiposLevelsStudent = listTiposLevelsStudent;
	}

	public List<SelectItem> getListTiposUsuarios() {
		return listTiposUsuarios;
	}

	public void setListTiposUsuarios(List<SelectItem> listTiposUsuarios) {
		this.listTiposUsuarios = listTiposUsuarios;
	}

	public List<SelectItem> getListTiposStatus() {
		return listTiposStatus;
	}

	public void setListTiposStatus(List<SelectItem> listTiposStatus) {
		this.listTiposStatus = listTiposStatus;
	}

}
