/**
 * 
 */
package br.edu.unitri.model.Content;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.edu.unitri.enumerators.TipoConteudo;
import br.edu.unitri.enumerators.TipoLevelContent;
import br.edu.unitri.interfaces.SimpleEntity;

/**
 * @author marcos.fernando
 *
 */
@Entity
@Table(name = "tbContent")
public class Content implements Serializable, SimpleEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Column(name = "TPCONTEUDO")
	@Enumerated(EnumType.STRING)
	private TipoConteudo tipoConteudo;

	@Column(name = "TPLEVCONT")
	@Enumerated(EnumType.STRING)
	private TipoLevelContent tipoLevel;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "curso_id", referencedColumnName = "id")
	private Course curso;

	public Content() {
		super();
	}

	public Content(Long id, String description, TipoConteudo tipoConteudo, TipoLevelContent tipoLevel, Course curso) {
		super();
		this.id = id;
		this.description = description;
		this.tipoConteudo = tipoConteudo;
		this.tipoLevel = tipoLevel;
		this.curso = curso;
	}

	public Content(String description, TipoConteudo tipoConteudo, TipoLevelContent tipoLevel, Course curso) {
		super();
		this.description = description;
		this.tipoConteudo = tipoConteudo;
		this.tipoLevel = tipoLevel;
		this.curso = curso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TipoConteudo getTipoConteudo() {
		return tipoConteudo;
	}

	public void setTipoConteudo(TipoConteudo tipoConteudo) {
		this.tipoConteudo = tipoConteudo;
	}

	public TipoLevelContent getTipoLevel() {
		return tipoLevel;
	}

	public void setTipoLevel(TipoLevelContent tipoLevel) {
		this.tipoLevel = tipoLevel;
	}

	public Course getCurso() {
		return curso;
	}

	public void setCurso(Course curso) {
		this.curso = curso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Content other = (Content) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
