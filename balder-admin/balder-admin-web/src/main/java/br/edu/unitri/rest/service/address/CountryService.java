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

import br.edu.unitri.dao.impl.address.CountryDao;
import br.edu.unitri.model.address.Country;

/**
 * @author marcos.fernando
 *
 */
@Path("/pais")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CountryService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private CountryDao paisDao;
	
	@GET
	@Path("/todos")
	public List<Country> listAll() {	
		return paisDao.findAll();
	}

	@GET
	@Path("/porId")
	public Country getById(Long id) {
		return paisDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<Country> listByEmail(Country pais) {
		return paisDao.findByEntity(pais);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(Country pais) {
		try {
			if (pais.getId() != null) {
				paisDao.update(pais);
			} else {
				paisDao.save(pais);
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
			Country pais = new ObjectMapper().readValue(entidade, Country.class);
			paisDao.deleteEntity(pais);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}
}
