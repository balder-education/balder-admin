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

import br.edu.unitri.dao.impl.address.CepDao;
import br.edu.unitri.model.address.Cep;

/**
 * @author marcos.fernando
 *
 */
@Path("/cep")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CepService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private CepDao cepDao;
	
	@GET
	@Path("/todos")
	public List<Cep> listAll() {	
		return cepDao.findAll();
	}

	@GET
	@Path("/porId")
	public Cep getById(Long id) {
		return cepDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<Cep> listByEmail(Cep cep) {
		return cepDao.findByEntity(cep);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(Cep cep) {
		try {
			if (cep.getId() != null) {
				cepDao.update(cep);
			} else {
				cepDao.save(cep);
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
			Cep cep = new ObjectMapper().readValue(entidade, Cep.class);
			cepDao.deleteEntity(cep);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}
}
