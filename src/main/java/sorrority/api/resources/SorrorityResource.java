package sorrority.api.resources;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.ResponseMetered;
import com.codahale.metrics.annotation.Timed;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import lombok.NonNull;
import sorrority.api.mappers.SorrorityMapper;
import sorrority.api.mappers.SorrorityResponseMapper;
import sorrority.api.models.SorrorityRequest;
import sorrority.api.models.SorrorityResponse;
import sorrority.api.models.SorroritysResponse;
import sorrority.service.SorrorityService;
import sorrority.service.models.Sorrority;

@Path("/api/v1")
public final class SorrorityResource {
  private final SorrorityService sorrorityService;

  public SorrorityResource(@NonNull final SorrorityService sorrorityService) {
    this.sorrorityService = sorrorityService;
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @PUT
  @Path("/sorrority/")
  @Consumes(APPLICATION_JSON)
  public void createSorrority(@Valid SorrorityRequest request) {
    final Sorrority newSorrority = SorrorityMapper.map(request);
    sorrorityService.createSorrority(newSorrority);
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @GET
  @Path("/sorrority/{sorrorityUuid}")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response getSorrority(@PathParam("sorrorityUuid") UUID uuid) throws NotFoundException {
    final Optional<Sorrority> sorrority = sorrorityService.getSorrority(uuid);
    if (!sorrority.isPresent()) {
      throw new NotFoundException();
    }
    final SorrorityResponse response = SorrorityResponseMapper.map(sorrority.get());
    return Response.ok(response).build();
  }


  @Timed
  @ResponseMetered
  @ExceptionMetered
  @GET
  @Path("/sorrority/")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response getAllSorrority() {
    final List<Sorrority> sorrorities = sorrorityService.getAll();
    final SorroritysResponse response = SorrorityResponseMapper.map(sorrorities);
    return Response.ok(response).build();
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @DELETE
  @Path("/sorrority/{sorrorityUuid}")
  public void deleteSorrority(@PathParam("sorrorityUuid") UUID uuid) {
    sorrorityService.deleteSorrority(uuid);
  }
}
