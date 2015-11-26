/**
 * 
 */
package br.edu.unitri.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class MenuCons implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public void csPaises() throws IOException {
		UtilBeanFaces.navegador("csPaises.xhtml");
	}

	public void csEstados() throws IOException {
		UtilBeanFaces.navegador("csEstados.xhtml");
	}

	public void csCidades() throws IOException {
		UtilBeanFaces.navegador("csCidades.xhtml");
	}

	public void csCeps() throws IOException {
		UtilBeanFaces.navegador("csCeps.xhtml");
	}

	public void csEnderecos() throws IOException {
		UtilBeanFaces.navegador("csEnderecos.xhtml");
	}

	public void csCurso() throws IOException {
		UtilBeanFaces.navegador("csCurso.xhtml");
	}

	public void csConteudo() throws IOException {
		UtilBeanFaces.navegador("csConteudo.xhtml");
	}

	public void csTemplates() throws IOException {
		UtilBeanFaces.navegador("csTemplates.xhtml");
	}

	public void csPessoa() throws IOException {
		UtilBeanFaces.navegador("csPessoa.xhtml");
	}

	public void csEstudante() throws IOException {
		UtilBeanFaces.navegador("csEstudante.xhtml");
	}

	public void csProfessor() throws IOException {
		UtilBeanFaces.navegador("csProfessor.xhtml");
	}

	public void csArquivos() throws IOException {
		UtilBeanFaces.navegador("csArquivos.xhtml");
	}
}
