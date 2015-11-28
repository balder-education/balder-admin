/**
 * 
 */
package br.edu.unitri.model.person;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

import br.edu.unitri.interfaces.SimpleEntity;
import br.edu.unitri.model.history.PersonFeedback;
import br.edu.unitri.model.user.User;

/**
 * @author marcos.fernando
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tbPerson")
public class Person implements Serializable, SimpleEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 75, nullable = true)
	private String name;

	@Temporal(TemporalType.DATE)
	private Date burnDate;

	@Column(name = "rg")
	private String rg;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "email", length = 60)
	private String email;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "person", cascade = CascadeType.REMOVE)
	private Collection<Endereco> listaEnderecos;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "person", cascade = CascadeType.REMOVE)
	private Collection<Contato> listaContatos;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "person", cascade = CascadeType.REMOVE)
	private Collection<Occupation> listaOcupacao;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "feedback_id", referencedColumnName = "id")
	private PersonFeedback feedback;

	public Person() {
		super();
	}

	public Person(Long id, String name, Date burnDate, String rg, String cpf, String email,
			Collection<Endereco> listaEnderecos, Collection<Contato> listaContatos,
			Collection<Occupation> listaOcupacao, User user, PersonFeedback feedback) {
		super();
		this.id = id;
		this.name = name;
		this.burnDate = burnDate;
		this.rg = rg;
		this.cpf = cpf;
		this.email = email;
		this.listaEnderecos = listaEnderecos;
		this.listaContatos = listaContatos;
		this.listaOcupacao = listaOcupacao;
		this.user = user;
		this.feedback = feedback;
	}

	public Person(String name, Date burnDate, String rg, String cpf, String email, Collection<Endereco> listaEnderecos,
			Collection<Contato> listaContatos, Collection<Occupation> listaOcupacao, User user,
			PersonFeedback feedback) {
		super();
		this.name = name;
		this.burnDate = burnDate;
		this.rg = rg;
		this.cpf = cpf;
		this.email = email;
		this.listaEnderecos = listaEnderecos;
		this.listaContatos = listaContatos;
		this.listaOcupacao = listaOcupacao;
		this.user = user;
		this.feedback = feedback;
	}

	public Person(String name, Date burnDate, String rg, String cpf, String email, User user, PersonFeedback feedback) {
		super();
		this.name = name;
		this.burnDate = burnDate;
		this.rg = rg;
		this.cpf = cpf;
		this.email = email;
		this.user = user;
		this.feedback = feedback;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBurnDate() {
		return burnDate;
	}

	public void setBurnDate(Date burnDate) {
		this.burnDate = burnDate;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlTransient
	public Collection<Endereco> getListaEnderecos() {
		return listaEnderecos;
	}

	public void setListaEnderecos(Collection<Endereco> listaEnderecos) {
		this.listaEnderecos = listaEnderecos;
	}

	@XmlTransient
	public Collection<Contato> getListaContatos() {
		return listaContatos;
	}

	public void setListaContatos(Collection<Contato> listaContatos) {
		this.listaContatos = listaContatos;
	}

	@XmlTransient
	public Collection<Occupation> getListaOcupacao() {
		return listaOcupacao;
	}

	public void setListaOcupacao(Collection<Occupation> listaOcupacao) {
		this.listaOcupacao = listaOcupacao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PersonFeedback getFeedback() {
		return feedback;
	}

	public void setFeedback(PersonFeedback feedback) {
		this.feedback = feedback;
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
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
