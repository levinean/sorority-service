package sorrority.api.mappers;

import java.util.UUID;
import lombok.NonNull;
import sorrority.api.models.EventRequest;
import sorrority.service.models.Event;

public final class EventMapper {
  private EventMapper() {}

  public static Event map(@NonNull final EventRequest event) {
    return Event.builder()
        .uuid(UUID.randomUUID())
        .name(event.getName())
        .description(event.getDescription())
        .eventDay(event.getEventDay())
        .eventTime(event.getEventTime())
        .score(0)
        .numberAttended(0)
        .build();
  }
}
