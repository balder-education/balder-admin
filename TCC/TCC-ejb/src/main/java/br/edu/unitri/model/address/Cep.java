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
@Table(name = "tbCep")
public class Cep implements Serializable, SimpleEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "city_id", referencedColumnName = "id")
	private City city;

	@Column(name = "street", length = 255)
	private String street;

	@Column(name = "zipcode", length = 60)
	private String zipCode;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cep", cascade=CascadeType.REMOVE)
	private Collection<Address> listAddress;


	public Cep() {
		super();
	}

	public Cep(Long id, City city, String street, String zipCode) {
		super();
		this.id = id;
		this.city = city;
		this.street = street;
		this.zipCode = zipCode;
	}

	public Cep(City city, String street, String zipCode) {
		super();
		this.city = city;
		this.street = street;
		this.zipCode = zipCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@XmlTransient
	public Collection<Address> getListAddress() {
		return listAddress;
	}

	public void setListAddress(Collection<Address> listAddress) {
		this.listAddress = listAddress;
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
		Cep other = (Cep) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
