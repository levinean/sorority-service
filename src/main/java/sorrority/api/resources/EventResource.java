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
import sorrority.api.mappers.EventMapper;
import sorrority.api.mappers.EventResponseMapper;
import sorrority.api.models.EventRequest;
import sorrority.api.models.EventResponse;
import sorrority.api.models.EventsResponse;
import sorrority.service.EventService;
import sorrority.service.models.Event;

@Path("/api/v1")
public final class EventResource {
  private final EventService eventService;

  public EventResource(@NonNull final EventService eventService) {
    this.eventService = eventService;
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @PUT
  @Path("/events/")
  @Consumes(APPLICATION_JSON)
  public void createEvent(@Valid EventRequest request) {
    final Event newEvent = EventMapper.map(request);
    eventService.createEvent(newEvent);
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @GET
  @Path("/events/{eventUuid}")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response getEvent(@PathParam("eventUuid") UUID uuid) throws NotFoundException {
    final Optional<Event> event = eventService.getEvent(uuid);
    if (!event.isPresent()) {
      throw new NotFoundException();
    }
    final EventResponse response = EventResponseMapper.map(event.get());
    return Response.ok(response).build();
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @GET
  @Path("/events/day/{day}")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response getEventsOnDay(@PathParam("day") String day) {
    final List<Event> event = eventService.getAllEventsOnDay(day);
    final EventsResponse response = EventResponseMapper.map(event);
    return Response.ok(response).build();
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @GET
  @Path("/events/")
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Response getAllEvents() {
    final List<Event> event = eventService.getAllEvents();
    final EventsResponse response = EventResponseMapper.map(event);
    return Response.ok(response).build();
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @PUT
  @Path("/events/update/day/{eventUuid}/{newDay}")
  public void changeDay(@PathParam("eventUuid") UUID uuid, @PathParam("newDay") String newDay) {
    eventService.changeDay(uuid, newDay);
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @PUT
  @Path("/events/update/time/{eventUuid}/{newTime}")
  public void changeTime(@PathParam("eventUuid") UUID uuid, @PathParam("newTime") String newTime) {
    eventService.changeTime(uuid, newTime);
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @PUT
  @Path("/events/update/description/{eventUuid}/{newDescription}")
  public void changeDescription(
      @PathParam("eventUuid") UUID uuid, @PathParam("newDescription") String newDescription) {
    eventService.changeDescription(uuid, newDescription);
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @PUT
  @Path("/events/update/attender/{eventUuid}/{newScore}")
  public void addAttender(@PathParam("eventUuid") UUID uuid, @PathParam("newScore") int newScore) {
    eventService.addAttender(uuid, newScore);
  }

  @Timed
  @ResponseMetered
  @ExceptionMetered
  @DELETE
  @Path("/events/{eventUuid}")
  public void deleteEvent(@PathParam("eventUuid") UUID uuid) {
    eventService.deleteEvent(uuid);
  }
}
