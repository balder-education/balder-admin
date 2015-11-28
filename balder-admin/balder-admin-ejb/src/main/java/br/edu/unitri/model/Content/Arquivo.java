/**
 * 
 */
package br.edu.unitri.model.Content;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.edu.unitri.enumerators.TipoConteudo;

/**
 * @author marcos.fernando
 *
 */
@Entity
@Table(name = "tbArquivos")
public class Arquivo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "conteudo_id", referencedColumnName = "id")
	private Content conteudo;

	@Column(name = "TPCONTEUDO")
	@Enumerated(EnumType.STRING)
	private TipoConteudo tipoConteudo;

	@Column(name = "IMAGEM", nullable = true)
	private String nomeImagem;

	@Column(name = "EXTENSAO", nullable = true)
	private String extensao;

	@Column(name = "TAMANHO", nullable = true)
	private Long tamanho;

	public Arquivo() {
		super();
	}

	public Arquivo(Long id, Content conteudo, TipoConteudo tipoConteudo, String nomeImagem, String extensao,
			Long tamanho) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.tipoConteudo = tipoConteudo;
		this.nomeImagem = nomeImagem;
		this.extensao = extensao;
		this.tamanho = tamanho;
	}

	public Arquivo(Content conteudo, TipoConteudo tipoConteudo, String nomeImagem, String extensao, Long tamanho) {
		super();
		this.conteudo = conteudo;
		this.tipoConteudo = tipoConteudo;
		this.nomeImagem = nomeImagem;
		this.extensao = extensao;
		this.tamanho = tamanho;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Content getConteudo() {
		return conteudo;
	}

	public void setConteudo(Content conteudo) {
		this.conteudo = conteudo;
	}

	public TipoConteudo getTipoConteudo() {
		return tipoConteudo;
	}

	public void setTipoConteudo(TipoConteudo tipoConteudo) {
		this.tipoConteudo = tipoConteudo;
	}

	public String getNomeImagem() {
		return nomeImagem;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public Long getTamanho() {
		return tamanho;
	}

	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Arquivo other = (Arquivo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
