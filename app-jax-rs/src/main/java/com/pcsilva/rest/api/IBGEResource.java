package com.pcsilva.rest.api;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.pcsilva.rest.exception.ServicoIndisponivelException;
import com.pcsilva.rest.infra.IBGEScope;
import com.pcsilva.rest.representation.LocalidadesRepresentation;

import javax.ws.rs.core.Response.Status;


@Path("/v1")
public class IBGEResource {

	@Inject
	private IBGEScope ibgeScope;

	@GET
	@Path("/cidade/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarIdCidadePorNomeCidade(@PathParam("id") Long id) {

		try {

			
			LocalidadesRepresentation cidade = ibgeScope.getCidadeById(id);
			
			if(null == cidade) {
				
				return Response.status(Status.NO_CONTENT).entity("{}").build();
				
			}
			
			return Response.ok(new ModelMapper().map(cidade, LocalidadesRepresentation.class)).build();

		} catch (ServicoIndisponivelException e) {

			return Response.ok(e.getMessage()).build();

		}

	}

	@GET
	@Path("/uf/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarIdUfPorNomeCidade(@PathParam("id") Long id) {


		try {

			
			String uf = ibgeScope.getUfById(id);
			
			if(null == uf) {
				
				return Response.status(Status.NO_CONTENT).entity("''").build();
				
			}
			
			return Response.ok(new ModelMapper().map(uf, String.class)).build();

		} catch (ServicoIndisponivelException e) {

			return Response.ok(e.getMessage()).build();

		}	

	}
	
	
	@GET
	@Path("/json")
	@Produces("application/json; charset=UTF-8")
	public Response listarLocalidadesJson() {

		try {

			return Response.ok(new ModelMapper().map(ibgeScope.getCidades(), new TypeToken<List<LocalidadesRepresentation>>() {}.getType())).build();

		} catch (ServicoIndisponivelException e) {

			return Response.ok(e.getMessage()).build();

		}

	}

	@GET
	@Path("/csv")
	@Produces("application/octet-stream; charset=UTF-8")
	public StreamingOutput listarLocalidadesCSV() {

		return new CsvReturnStreamingOutput(ibgeScope.getCidades());

	}
	
		    public static class CsvReturnStreamingOutput implements StreamingOutput {
		
		    	private List<LocalidadesRepresentation> localidades;
		
		        public CsvReturnStreamingOutput(List<LocalidadesRepresentation> localidades) {
		        	
					this.localidades = localidades;
				}
		
				@Override
		        public void write(OutputStream output) throws IOException, WebApplicationException {
		
	                Writer writer = new BufferedWriter(new OutputStreamWriter(output, "UTF-8"));
		            
	                writer.write("'idEstado','siglaEstado','regiaoNome','nomeCidade','nomeMesorregiao','nomeFormatado' \n");
	                
	                for (LocalidadesRepresentation localidade : this.localidades ) {
	                	
	                	writer.write(localidade.toString()+"\n");
	                	
		            }

	                writer.flush();
		            
		        }
		    }

}
