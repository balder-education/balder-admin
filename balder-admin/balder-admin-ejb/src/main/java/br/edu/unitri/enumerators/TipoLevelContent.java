/**
 * 
 */
package br.edu.unitri.enumerators;

/**
 * @author marcos.fernando
 *
 */
public enum TipoLevelContent implements TipoEnum {
	
	PRE_INICIANTE0("00","Pre-Iniciante"), INICIANTE("01","Iniciante"), POS_INICIANTE("02","Pos-Iniciante"), 
	JUNIOR("03","Junior"), POS_JUNIOR("04","Pos-Junior"), PLENO("05","Pleno"), SENIOR("06","Senior"),
	ADVANCED("07","Advanced"), EXTREME("08","Extremo");
	
	private final String tipo;
	private final String descricao;

	TipoLevelContent(String tipo, String descricao) {
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
