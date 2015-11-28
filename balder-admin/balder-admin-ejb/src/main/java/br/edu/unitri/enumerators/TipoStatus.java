/**
 * 
 */
package br.edu.unitri.enumerators;

/**
 * @author marcos.fernando
 *
 */
public enum TipoStatus implements TipoEnum {
	
	ATIVO("01","Ativo"), INATIVO("02","Inativo"), SUSPENSO("03","Suspenso"), OUTROS("04","Outros");
	
	private final String tipo;
	private final String descricao;

	TipoStatus(String tipo, String descricao) {
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
