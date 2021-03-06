/**
 * 
 */
package br.edu.unitri.rest.service.address;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import br.edu.unitri.dao.impl.address.AddressDao;
import br.edu.unitri.model.address.Address;

/**
 * @author marcos.fernando
 *
 */
@Path("/endereco")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AddressService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private AddressDao enderecoDao;
	
	@GET
	@Path("/todos")
	public List<Address> listAll() {	
		return enderecoDao.findAll();
	}

	@GET
	@Path("/porId")
	public Address getById(Long id) {
		return enderecoDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<Address> listByEmail(Address endereco) {
		return enderecoDao.findByEntity(endereco);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(Address endereco) {
		try {
			if (endereco.getId() != null) {
				enderecoDao.update(endereco);
			} else {
				enderecoDao.save(endereco);
			}
			return Response.ok().build();
		} catch (Exception ex) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("erro", ex.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(responseObj).build();
		}
	}
	
	@DELETE
	@Path("/excluir/{entidade}")	
	public Response excluir(@PathParam("entidade") String entidade) throws Exception{
		try {			
			Address endereco = new ObjectMapper().readValue(entidade, Address.class);
			enderecoDao.deleteEntity(endereco);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}
}
