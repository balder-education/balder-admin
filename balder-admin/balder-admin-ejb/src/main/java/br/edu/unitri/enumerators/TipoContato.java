/**
 * 
 */
package br.edu.unitri.enumerators;

/**
 * @author marcos.fernando
 *
 */
public enum TipoContato implements TipoEnum {
	
	EMAIL_PRINCIPAL("01","Email Principal"), EMAIL_SECUNDARIO("02","Endereco Secundario"),
	EMAIL_COMERCIAL("03","Email Comercial"), TEL_RESIDENCIAL("04","Telefone Residencial"),
	TEL_COMERCIAL("05","Telefone Comercial"), TEL_CELULAR("06","Telefone Celular"), 
	TEL_CONTATO("07","Telefone Contato"), OUTROS("05","Outros");
	
	private final String tipo;
	private final String descricao;

	TipoContato(String tipo, String descricao) {
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
