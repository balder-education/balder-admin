/**
 * 
 */
package br.edu.unitri.model.person;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author marcos.fernando
 *
 */
@Embeddable
public class TeacherResume implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(length = 255)
	private String academicQualification;

	private String profile;

	private String skills;

	private String certificates;

	@Column(length = 45)
	private String professionalExperience;

	public TeacherResume() {
		super();
	}

	public TeacherResume(String academicQualification, String profile,
			String skills, String certificates, String professionalExperience) {
		super();
		this.academicQualification = academicQualification;
		this.profile = profile;
		this.skills = skills;
		this.certificates = certificates;
		this.professionalExperience = professionalExperience;
	}

	public String getAcademicQualification() {
		return academicQualification;
	}

	public void setAcademicQualification(String academicQualification) {
		this.academicQualification = academicQualification;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getCertificates() {
		return certificates;
	}

	public void setCertificates(String certificates) {
		this.certificates = certificates;
	}

	public String getProfessionalExperience() {
		return professionalExperience;
	}

	public void setProfessionalExperience(String professionalExperience) {
		this.professionalExperience = professionalExperience;
	}

}
