package sorrority.service.mappers;

import lombok.NonNull;
import sorrority.db.models.EventRow;
import sorrority.service.models.Event;

public final class EventRowMapper {
  private EventRowMapper() {}

  public static EventRow map(@NonNull final Event event) {
    return EventRow.builder()
        .uuid(event.getUuid())
        .name(event.getName())
        .description(event.getDescription())
        .eventDay(event.getEventDay())
        .eventTime(event.getEventTime())
        .score(event.getScore())
        .numberAttended(event.getNumberAttended())
        .build();
  }
}
