/**
 * 
 */
package br.edu.unitri.model.Content;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.edu.unitri.interfaces.SimpleEntity;
import br.edu.unitri.model.person.Person;

/**
 * @author marcos.fernando
 *
 */
@Entity
@Table(name = "tbContentSession")
public class ContentSession implements Serializable, SimpleEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date initialTime;
	private Date finalTime;

	@OneToOne
	@JoinColumn(name = "person_id", referencedColumnName = "id")
	private Person person;

	public ContentSession() {
		super();
	}

	public ContentSession(long id, Date initialTime, Date finalTime,
			Person person) {
		super();
		this.id = id;
		this.initialTime = initialTime;
		this.finalTime = finalTime;
		this.person = person;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInitialTime() {
		return initialTime;
	}

	public void setInitialTime(Date initialTime) {
		this.initialTime = initialTime;
	}

	public Date getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(Date finalTime) {
		this.finalTime = finalTime;
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
		ContentSession other = (ContentSession) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
