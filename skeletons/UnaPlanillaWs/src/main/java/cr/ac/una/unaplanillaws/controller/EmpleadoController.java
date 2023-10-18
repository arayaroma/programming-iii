package cr.ac.una.unaplanillaws.controller;

import java.util.logging.Logger;

import cr.ac.una.unaplanillaws.model.EmpleadoDto;
import cr.ac.una.unaplanillaws.service.EmpleadoService;
import cr.ac.una.unaplanillaws.util.JwtHelper;
import cr.ac.una.unaplanillaws.util.Respuesta;
import cr.ac.una.unaplanillaws.util.Secure;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

// @Secure
@Path("/EmpleadoController")
@Tag(name = "Empleado", description = "Gestiona todo lo relacionado con los empleados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpleadoController {

    private static final Logger logger = Logger.getLogger(EmpleadoController.class.getName());

    @EJB
    EmpleadoService empleadoService;

    @GET
    @Path("/user/{user}/{password}")
    public Response getUser(@PathParam("user") String user, @PathParam("password") String password) {
        try {
            Respuesta response = empleadoService.validarUsuario(user, password);
            if (!response.getEstado()) {
                return Response.status(response.getCodigoRespuesta().getValue()).entity(response).build();
            }
            EmpleadoDto empleado = (EmpleadoDto) response.getResultado("Empleado");
            empleado.setToken(JwtHelper.getInstance().generatePrivateKey(user));
            return Response.ok(empleado).build();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving user: " +
                    e.getMessage()).build();
        }
    }

}
