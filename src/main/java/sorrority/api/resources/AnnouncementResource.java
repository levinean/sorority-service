package sorrority.api.resources;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.ResponseMetered;
import com.codahale.metrics.annotation.Timed;
import java.time.Instant;
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
import sorrority.api.mappers.AnnouncementMapper;
import sorrority.api.mappers.AnnouncementResponseMapper;
import sorrority.api.models.AnnouncementRequest;
import sorrority.api.models.AnnouncementsResponse;
import sorrority.service.AnnouncementService;
import sorrority.service.models.Announcement;

@Path("/api/v1")
public final class AnnouncementResource {
  private final AnnouncementService announcementService;

  public AnnouncementResource(@NonNull final AnnouncementService announcementService) {
    this.announcementService = announcementService;
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @PUT
  @Path("/announcements/")
  @Consumes(APPLICATION_JSON)
  public void createAnnouncement(@Valid AnnouncementRequest request) {
    final Announcement newAnnouncement = AnnouncementMapper.map(request);
    announcementService.createAnnouncement(newAnnouncement);
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @GET
  @Path("/announcements/")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response getAllAnnouncement() {
    final List<Announcement> Announcements = announcementService.getAll();
    final AnnouncementsResponse response = AnnouncementResponseMapper.map(Announcements);
    return Response.ok(response).build();
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @DELETE
  @Path("/announcements/cleanup/{time}")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public void cleanUpAnnouncement(@PathParam("time") Instant time) {
    announcementService.cleanUpAnnouncements(time);
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @DELETE
  @Path("/announcements/{announcementUuid}")
  public void deleteAnnouncement(@PathParam("announcementUuid") UUID uuid) {
    announcementService.deleteAnnouncement(uuid);
  }
}
