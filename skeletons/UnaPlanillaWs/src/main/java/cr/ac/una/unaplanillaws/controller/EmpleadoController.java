package cr.ac.una.unaplanillaws.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cr.ac.una.unaplanillaws.model.EmpleadoDto;
import cr.ac.una.unaplanillaws.service.EmpleadoService;
import cr.ac.una.unaplanillaws.util.CodigoRespuesta;
import cr.ac.una.unaplanillaws.util.Respuesta;
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

@Path("/EmpleadoController")
public class EmpleadoController {

    @EJB
    EmpleadoService empleadoService;

    @GET
    @Path("/empleado/{usuario}/{clave}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validarUsuario(@PathParam("usuario") String usuario, @PathParam("clave") String clave) {
        try {
            Respuesta respuesta = empleadoService.validarUsuario(usuario, clave);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getResultado())
                        .build();
            }
            return Response.ok(respuesta.getResultado("Empleado")).build();
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue())
                    .entity("Error validando el usuario y la clave").build();
        }
    }

    @GET
    @Path("/empleado/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getEmpleado(@PathParam("id") Long id) {
        try {
            Respuesta respuesta = empleadoService.getEmpleado(id);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getResultado())
                        .build();
            }
            return Response.ok(respuesta.getResultado("Empleado")).build();
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el empleado")
                    .build();
        }
    }

    @GET
    @Path("/empleados/{cedula}/{nombre}/{pApellido}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getEmpleados(@PathParam("cedula") String cedula, @PathParam("nombre") String nombre,
            @PathParam("pApellido") String pApellido) {
        try {
            Respuesta respuesta = empleadoService.getEmpleados(cedula, nombre, pApellido);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getResultado())
                        .build();
            }
            return Response
                    .ok(new GenericEntity<List<EmpleadoDto>>((List<EmpleadoDto>) respuesta.getResultado("Empleados")) {
                    }).build();
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los empleados")
                    .build();
        }
    }

    @POST
    @Path("/empleado")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarEmpleado(EmpleadoDto empleado) {
        try {
            Respuesta respuesta = empleadoService.guardarEmpleado(empleado);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getResultado())
                        .build();
            }
            return Response.ok(respuesta.getResultado("Empleado")).build();
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el empleado")
                    .build();
        }
    }

    @DELETE
    @Path("/empleado/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarEmpleado(@PathParam("id") Long id) {
        try {
            Respuesta respuesta = empleadoService.eliminarEmpleado(id);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getResultado())
                        .build();
            }
            return Response.ok(respuesta.getResultado("Empleado")).build();
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el empleado")
                    .build();
        }
    }

}
