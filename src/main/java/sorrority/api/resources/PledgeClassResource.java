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
import sorrority.api.mappers.PledgeClassMapper;
import sorrority.api.mappers.PledgeClassResponseMapper;
import sorrority.api.models.PledgeClassRequest;
import sorrority.api.models.PledgeClassResponse;
import sorrority.api.models.PledgeClassesResponse;
import sorrority.service.PledgeClassService;
import sorrority.service.models.PledgeClass;

@Path("/api/v1")
public final class PledgeClassResource {
  private final PledgeClassService pledgeClassService;

  public PledgeClassResource(@NonNull final PledgeClassService pledgeClassService) {
    this.pledgeClassService = pledgeClassService;
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @PUT
  @Path("/pledgeClass/")
  @Consumes(APPLICATION_JSON)
  public void createPledgeClass(@Valid PledgeClassRequest request) {
    final PledgeClass newPledgeClass = PledgeClassMapper.map(request);
    pledgeClassService.createPledgeClass(newPledgeClass);
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @GET
  @Path("/pledgeClass/{pledgeClassUuid}")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response getPledgeClass(@PathParam("pledgeClassUuid") UUID uuid) throws NotFoundException {
    final Optional<PledgeClass> PledgeClass = pledgeClassService.getPledgeClass(uuid);
    if (!PledgeClass.isPresent()) {
      throw new NotFoundException();
    }
    final PledgeClassResponse response = PledgeClassResponseMapper.map(PledgeClass.get());
    return Response.ok(response).build();
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @GET
  @Path("/pledgeClass/")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response getAllPledgeClass() {
    final List<PledgeClass> pledgeClasses = pledgeClassService.getAll();
    final PledgeClassesResponse response = PledgeClassResponseMapper.map(pledgeClasses);
    return Response.ok(response).build();
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @DELETE
  @Path("/pledgeClass/{pledgeClassUuid}")
  public void deletePledgeClass(@PathParam("pledgeClassUuid") UUID uuid) {
    pledgeClassService.deletePledgeClass(uuid);
  }
}
