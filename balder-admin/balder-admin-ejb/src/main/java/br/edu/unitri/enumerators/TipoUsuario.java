package br.edu.unitri.enumerators;

/**
 * @author marcos.fernando
 *
 */
public enum TipoUsuario implements TipoEnum {

	ADMINISTRADOR("0", "Administrator"), USERSITE("1", "User");

	private final String tipo;
	private final String descricao;

	TipoUsuario(String tipo, String descricao) {
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
