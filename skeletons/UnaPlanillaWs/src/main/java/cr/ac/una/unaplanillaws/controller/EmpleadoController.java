package cr.ac.una.unaplanillaws.controller;

import cr.ac.una.unaplanillaws.service.EmpleadoService;
import cr.ac.una.unaplanillaws.util.Respuesta;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/EmpleadoController")
public class EmpleadoController {

    @EJB
    EmpleadoService empleadoService;

    @GET
    @Path("/empleado/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getEmpleado(@PathParam("id") Long id) {
        Respuesta respuesta = empleadoService.getEmpleado(id);
        return Response.ok(respuesta.getResultado("Empleado")).build();
    }

}
