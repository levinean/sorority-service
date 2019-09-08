package sorrority.api.resources;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.ResponseMetered;
import com.codahale.metrics.annotation.Timed;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import lombok.NonNull;
import sorrority.api.mappers.ShoutoutMapper;
import sorrority.api.mappers.ShoutoutResponseMapper;
import sorrority.api.models.ShoutoutRequest;
import sorrority.api.models.ShoutoutsResponse;
import sorrority.service.ShoutoutService;
import sorrority.service.models.Shoutout;

@Path("/api/v1")
public final class ShoutoutResource {
  private final ShoutoutService shoutoutService;

  public ShoutoutResource(@NonNull final ShoutoutService shoutoutService) {
    this.shoutoutService = shoutoutService;
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @PUT
  @Path("/shoutouts/")
  @Consumes(APPLICATION_JSON)
  public void createShoutout(@Valid ShoutoutRequest request) {
    final Shoutout newShoutout = ShoutoutMapper.map(request);
    shoutoutService.createShoutout(newShoutout);
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @GET
  @Path("/shoutouts/")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response getAllShoutout() {
    final List<Shoutout> shoutouts = shoutoutService.getAll();
    final ShoutoutsResponse response = ShoutoutResponseMapper.map(shoutouts);
    return Response.ok(response).build();
  }

  // @Timed
  // @ResponseMetered
  // @ExceptionMetered
  // @DELETE
  // @Path("/shoutouts/cleanup/{time}")
  // @Consumes(APPLICATION_JSON)
  // @Produces(APPLICATION_JSON)
  // public void cleanUpShoutout(@PathParam("time") Instant time) {
  //   shoutoutService.cleanUpShoutouts(time);
  // }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @DELETE
  @Path("/shoutouts/{shoutoutUuid}")
  public void deleteShoutout(@PathParam("shoutoutUuid") UUID uuid) {
    shoutoutService.deleteShoutout(uuid);
  }
}
