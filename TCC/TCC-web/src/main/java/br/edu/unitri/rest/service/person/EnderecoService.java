/**
 * 
 */
package br.edu.unitri.rest.service.person;

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

import br.edu.unitri.dao.impl.person.EnderecoDao;
import br.edu.unitri.model.person.Endereco;

/**
 * @author marcos.fernando
 *
 */
@Path("/enderecoPessoa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EnderecoService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EnderecoDao enderecoDao;
	
	@GET
	@Path("/todos")
	public List<Endereco> listAll() {	
		return enderecoDao.findAll();
	}

	@GET
	@Path("/porId")
	public Endereco getById(Long id) {
		return enderecoDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<Endereco> listByEmail(Endereco endereco) {
		return enderecoDao.findByEntity(endereco);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(Endereco endereco) {
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
			Endereco endereco = new ObjectMapper().readValue(entidade, Endereco.class);
			enderecoDao.deleteEntity(endereco);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}
}
