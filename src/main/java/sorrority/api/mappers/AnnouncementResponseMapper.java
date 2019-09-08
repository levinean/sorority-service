package sorrority.api.mappers;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import lombok.NonNull;
import sorrority.api.models.AnnouncementResponse;
import sorrority.api.models.AnnouncementsResponse;
import sorrority.service.models.Announcement;

public final class AnnouncementResponseMapper {
  private AnnouncementResponseMapper() {}

  public static AnnouncementResponse map(@NonNull final Announcement announcement) {
    return AnnouncementResponse.builder()
        .uuid(announcement.getUuid())
        .announcement(announcement.getAnnouncement())
        .createdAt(announcement.getCreatedAt())
        .sorrorityUuid(announcement.getSorrorityUuid())
        .build();
  }

  public static AnnouncementsResponse map(@NonNull final List<Announcement> rows) {
    return new AnnouncementsResponse(
        unmodifiableList(rows.stream().map(row -> map(row)).collect(toList())));
  }
}
