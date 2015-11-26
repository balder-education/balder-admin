/**
 * 
 */
package br.edu.unitri.enumerators;

/**
 * @author marcos.fernando
 *
 */
public enum TipoLevelStudent implements TipoEnum {
	
	INICIANTE("01","Iniciante"), INTERMEDIARIO("02","Intermediario"), ADVANCED("03","Advanced"), EXTREME("04","Extremo");
	
	private final String tipo;
	private final String descricao;

	TipoLevelStudent(String tipo, String descricao) {
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
