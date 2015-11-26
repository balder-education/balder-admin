package br.edu.unitri.model.address;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import br.edu.unitri.interfaces.SimpleEntity;

/**
 * @author marcos.fernando
 *
 */
@Entity
@Table(name = "tbCity")
public class City implements Serializable, SimpleEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "state_id", referencedColumnName = "id")
	private State state;

	@Column(name = "name", length = 255)
	private String name;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "city", cascade = CascadeType.REMOVE)
	private Collection<Cep> listCeps;

	public City() {
		super();
	}

	public City(Long id, State state, String name) {
		super();
		this.id = id;
		this.state = state;
		this.name = name;
	}

	public City(State state, String name) {
		super();
		this.state = state;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public Collection<Cep> getListCeps() {
		return listCeps;
	}

	public void setListCeps(Collection<Cep> listCeps) {
		this.listCeps = listCeps;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		City other = (City) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
