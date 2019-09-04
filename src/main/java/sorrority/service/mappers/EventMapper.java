package sorrority.service.mappers;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import lombok.NonNull;
import sorrority.db.models.EventRow;
import sorrority.service.models.Event;

public final class EventMapper {
  private EventMapper() {}

  public static Event map(@NonNull final EventRow event) {
    return Event.builder()
        .uuid(event.getUuid())
        .name(event.getName())
        .description(event.getDescription())
        .eventDay(event.getEventDay())
        .eventTime(event.getEventTime())
        .score(event.getScore())
        .numberAttended(event.getNumberAttended())
        .build();
  }

  public static List<Event> map(@NonNull final List<EventRow> rows) {
    return unmodifiableList(rows.stream().map(row -> map(row)).collect(toList()));
  }
}
