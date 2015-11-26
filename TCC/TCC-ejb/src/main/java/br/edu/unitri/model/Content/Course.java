/**
 * 
 */
package br.edu.unitri.model.Content;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

import br.edu.unitri.interfaces.SimpleEntity;
import br.edu.unitri.model.person.Student;
import br.edu.unitri.model.person.Teacher;

/**
 * @author marcos.fernando
 *
 */
@Entity
@Table(name = "tbCourse")
public class Course implements Serializable, SimpleEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Temporal(TemporalType.DATE)
	private Date initialDate;

	@Temporal(TemporalType.DATE)
	private Date endDate;

	@OneToOne
	@JoinColumn(name = "teacher_id", referencedColumnName = "id")
	private Teacher teacher;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "student_id", referencedColumnName = "id")
	private Collection<Student> listStudent;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "content_id", referencedColumnName = "id")
	private Collection<Content> listContents;

	public Course() {
		super();
	}

	public Course(Long id, String description, Date initialDate, Date endDate,
			Teacher teacher, Collection<Student> listStudent,
			Collection<Content> listContents) {
		super();
		this.id = id;
		this.description = description;
		this.initialDate = initialDate;
		this.endDate = endDate;
		this.teacher = teacher;
		this.listStudent = listStudent;
		this.listContents = listContents;
	}

	public Course(String description, Date initialDate, Date endDate,
			Teacher teacher, Collection<Student> listStudent,
			Collection<Content> listContents) {
		super();
		this.description = description;
		this.initialDate = initialDate;
		this.endDate = endDate;
		this.teacher = teacher;
		this.listStudent = listStudent;
		this.listContents = listContents;
	}

	public Course(String description, Date initialDate, Date endDate,
			Teacher teacher) {
		super();
		this.description = description;
		this.initialDate = initialDate;
		this.endDate = endDate;
		this.teacher = teacher;
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

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@XmlTransient
	public Collection<Student> getListStudent() {
		return listStudent;
	}

	public void setListStudent(Collection<Student> listStudent) {
		this.listStudent = listStudent;
	}

	@XmlTransient
	public Collection<Content> getListContents() {
		return listContents;
	}

	public void setListContents(Collection<Content> listContents) {
		this.listContents = listContents;
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
		Course other = (Course) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
