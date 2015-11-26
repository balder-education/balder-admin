/**
 * 
 */
package br.edu.unitri.controller.impl.address;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.unitri.controller.UtilBeanFaces;
import br.edu.unitri.dao.impl.address.AddressDao;
import br.edu.unitri.model.address.Address;
import br.edu.unitri.repository.CrudBean;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class AddressBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Address address = new Address();
	private Address addressSel;
	private List<Address> listAddress = new ArrayList<Address>();

	@Inject
	private AddressDao addressDao;

	public AddressBean() {
		super();
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Address> getListAddress() {
		return listAddress;
	}

	public void setListAddress(List<Address> listAddress) {
		this.listAddress = listAddress;
	}

	public Address getAddressSel() {
		return addressSel;
	}

	public void setAddressSel(Address addressSel) {
		this.addressSel = addressSel;
	}
	
	@PostConstruct
	public void init(){
		address = new Address();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (address.getId() != null) {
			addressDao.update(address);
		} else {
			addressDao.save(address);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		listAddress.clear();
		listAddress = addressDao.findByEntity(address);
		if (listAddress.size() == 0) {
			UtilBeanFaces.addMessage("Não existe endereços para serem exibidos",null,"WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (addressSel != null) {
			if (addressDao.deleteEntity(addressSel)){
				init();
				UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
			} else {
				UtilBeanFaces.addMessage("Operação não pode ser realizada-> Existem relações com outras entidades!", null, "WARNING", null);
			}
		}
	}

	@Override
	public void editar() {
		setAddress(getAddressSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		listAddress = addressDao.findAll();
	}

}
