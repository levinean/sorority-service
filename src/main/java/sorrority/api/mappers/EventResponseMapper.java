package sorrority.api.mappers;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import lombok.NonNull;
import sorrority.api.models.EventResponse;
import sorrority.api.models.EventsResponse;
import sorrority.service.models.Event;

public final class EventResponseMapper {
  private EventResponseMapper() {}

  public static EventResponse map(@NonNull final Event event) {
    return EventResponse.builder()
        .uuid(event.getUuid())
        .name(event.getName())
        .description(event.getDescription())
        .eventDay(event.getEventDay())
        .eventTime(event.getEventTime())
        .score(event.getScore())
        .numberAttended(event.getNumberAttended())
        .build();
  }

  public static EventsResponse map(@NonNull final List<Event> rows) {
    return new EventsResponse(unmodifiableList(rows.stream().map(row -> map(row)).collect(toList())));
  }
}
