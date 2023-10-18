package cr.ac.una.unaplanillaws.controller;

import java.util.List;
import java.util.logging.Logger;

import cr.ac.una.unaplanillaws.model.TipoPlanillaDto;
import cr.ac.una.unaplanillaws.service.TipoPlanillaService;
import cr.ac.una.unaplanillaws.util.Respuesta;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/TipoPlanillaController")
@Tag(name = "TipoPlanilla", description = "Gestiona todo lo relacionado con los tipos de planilla")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoPlanillaController {

    private static final Logger logger = Logger.getLogger(TipoPlanillaController.class.getName());

    @EJB
    TipoPlanillaService tipoPlanillaService;

    @GET
    @Path("/tipoPlanillas")
    public Response getTiposPlanillas() {
        try {
            Respuesta response = tipoPlanillaService.getTiposPlanillas();
            if (!response.getEstado()) {
                return Response.status(response.getCodigoRespuesta().getValue()).entity(response.getMensaje()).build();
            }
            List<TipoPlanillaDto> tipoPlanillas = (List<TipoPlanillaDto>) response.getResultado("TiposPlanillas");
            GenericEntity<List<TipoPlanillaDto>> entity = new GenericEntity<List<TipoPlanillaDto>>(tipoPlanillas) {
            };
            return Response.ok(entity).build();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving tipoPlanillas: " +
                    e.getMessage()).build();
        }
    }

    @POST
    @Path("/tipoPlanilla")
    public Response createTipoPlanilla(TipoPlanillaDto tipoPlanillaDto) {
        try {
            tipoPlanillaService.guardarTipoPlanilla(tipoPlanillaDto);
            return Response.ok().build();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating tipoPlanilla: " +
                    e.getMessage()).build();
        }
    }

    @GET
    @Path("/tipoPlanilla/{id}/{code}/{description}")
    public Response getTipoPlanilla(@PathParam("id") Long id, @PathParam("code") String code,
            @PathParam("description") String description) {
        try {
            tipoPlanillaService.getTipoPlanilla(id, code, description);
            return Response.ok().build();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving tipoPlanilla: " +
                    e.getMessage()).build();
        }
    }

    @GET
    @Path("/tipoPlailla/{id}")
    public Response getTipoPlanilla(@PathParam("id") Long id) {
        try {
            tipoPlanillaService.getTipoPlanilla(id);
            return Response.ok().build();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving tipoPlanilla: " +
                    e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/tipoPlanilla/{id}")
    public Response deleteTipoPlanilla(@PathParam("id") Long id) {
        try {
            tipoPlanillaService.eliminarTipoPlanilla(id);
            return Response.ok().build();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting tipoPlanilla: " +
                    e.getMessage()).build();
        }
    }

}
