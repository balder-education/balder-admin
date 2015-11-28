/**
 * 
 */
package br.edu.unitri.model.Content;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.edu.unitri.interfaces.SimpleEntity;
import br.edu.unitri.model.person.Student;

/**
 * @author marcos.fernando
 *
 */
@Entity
@Table(name = "tbContentLog")
public class ContentLog implements Serializable, SimpleEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "student_id", referencedColumnName = "id")
	private Student student;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "content_id", referencedColumnName = "id")
	private Content content;

	private int score;

	public ContentLog() {
		super();
	}

	public ContentLog(Long id, Student student, Content content, int score) {
		super();
		this.id = id;
		this.student = student;
		this.content = content;
		this.score = score;
	}

	public ContentLog(Student student, Content content, int score) {
		super();
		this.student = student;
		this.content = content;
		this.score = score;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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
		ContentLog other = (ContentLog) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
