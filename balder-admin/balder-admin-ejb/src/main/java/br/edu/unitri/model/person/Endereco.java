package br.edu.unitri.model.person;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.edu.unitri.enumerators.TipoEndereco;
import br.edu.unitri.interfaces.SimpleEntity;
import br.edu.unitri.model.address.Address;

/**
 * @author marcos.fernando
 *
 */
@Entity
@Table(name = "tbEndereco")
public class Endereco implements Serializable, SimpleEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id", referencedColumnName = "id")
	private Person person;

	@Column(name = "TPENDERECO")
	@Enumerated(EnumType.STRING)
	private TipoEndereco tipoEndereco;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

	public Endereco() {
		super();
	}

	public Endereco(Long id, TipoEndereco tipoEndereco, Address address) {
		super();
		this.id = id;
		this.tipoEndereco = tipoEndereco;
		this.address = address;
	}

	public Endereco(TipoEndereco tipoEndereco, Address address) {
		super();
		this.tipoEndereco = tipoEndereco;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
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
		Endereco other = (Endereco) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
