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

import br.edu.unitri.dao.impl.person.OccupationDao;
import br.edu.unitri.model.person.Occupation;

/**
 * @author marcos.fernando
 *
 */
@Path("/ocupacaoPessoa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OccupationService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private OccupationDao ocupacaoDao;
	
	@GET
	@Path("/todos")
	public List<Occupation> listAll() {	
		return ocupacaoDao.findAll();
	}

	@GET
	@Path("/porId")
	public Occupation getById(Long id) {
		return ocupacaoDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<Occupation> listByEmail(Occupation ocupacao) {
		return ocupacaoDao.findByEntity(ocupacao);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(Occupation ocupacao) {
		try {
			if (ocupacao.getId() != null) {
				ocupacaoDao.update(ocupacao);
			} else {
				ocupacaoDao.save(ocupacao);
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
			Occupation ocupacao = new ObjectMapper().readValue(entidade, Occupation.class);
			ocupacaoDao.deleteEntity(ocupacao);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}
}
