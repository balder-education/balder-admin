/**
 * 
 */
package br.edu.unitri.enumerators;

/**
 * @author marcos.fernando
 *
 */
public enum TipoConteudo implements TipoEnum{
	
	FILE_PDF("01","Arquivo PDF"), FILE_AUD("02","Arquivo de Audio"), FILE_VID("03","Arquivo Audio e Video"),
	OUTROS("04","Outros");
	
	private final String tipo;
	private final String descricao;

	TipoConteudo(String tipo, String descricao) {
		this.tipo = tipo;
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	@Override
    public String toString() {
        return  this.getDescricao();
    }


}
