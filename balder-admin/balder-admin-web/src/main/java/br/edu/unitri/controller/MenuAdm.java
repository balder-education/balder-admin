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
public class MenuAdm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public void paises() throws IOException {
		UtilBeanFaces.navegador("paises.xhtml");
	}

	public void estados() throws IOException {
		UtilBeanFaces.navegador("estados.xhtml");
	}

	public void cidades() throws IOException {
		UtilBeanFaces.navegador("cidades.xhtml");
	}

	public void ceps() throws IOException {
		UtilBeanFaces.navegador("ceps.xhtml");
	}

	public void enderecos() throws IOException {
		UtilBeanFaces.navegador("enderecos.xhtml");
	}

	public void curso() throws IOException {
		UtilBeanFaces.navegador("curso.xhtml");
	}

	public void conteudo() throws IOException {
		UtilBeanFaces.navegador("conteudo.xhtml");
	}

	public void templates() throws IOException {
		UtilBeanFaces.navegador("templates.xhtml");
	}

	public void pessoa() throws IOException {
		UtilBeanFaces.navegador("pessoa.xhtml");
	}

	public void administrador() throws IOException {
		UtilBeanFaces.navegador("administrador.xhtml");
	}

	public void estudante() throws IOException {
		UtilBeanFaces.navegador("estudante.xhtml");
	}

	public void professor() throws IOException {
		UtilBeanFaces.navegador("professor.xhtml");
	}

	public void contatoPessoa() throws IOException {
		UtilBeanFaces.navegador("contatoPessoa.xhtml");
	}

	public void enderecoPessoa() throws IOException {
		UtilBeanFaces.navegador("enderecoPessoa.xhtml");
	}

	public void ocupacao() throws IOException {
		UtilBeanFaces.navegador("ocupacao.xhtml");
	}

	public void usuarios() throws IOException {
		UtilBeanFaces.navegador("usuarios.xhtml");
	}

	public void role() throws IOException {
		UtilBeanFaces.navegador("role.xhtml");
	}

	public void arquivos() throws IOException {
		UtilBeanFaces.navegador("arquivos.xhtml");
	}
}
