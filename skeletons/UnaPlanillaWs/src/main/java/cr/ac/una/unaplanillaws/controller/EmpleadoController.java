package cr.ac.una.unaplanillaws.controller;

import cr.ac.una.unaplanillaws.service.EmpleadoService;
import cr.ac.una.unaplanillaws.util.Respuesta;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/EmpleadoController")
@Tag(name = "Empleado", description = "Gestiona todo lo relacionado con los empleados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpleadoController {

    @EJB
    EmpleadoService empleadoService;

    @GET
    @Path("/empleado/{id}")
    public Response getEmpleado(@PathParam("id") Long id) {
        Respuesta respuesta = empleadoService.getEmpleado(id);
        return Response.ok(respuesta.getResultado("Empleado")).build();
    }

}
