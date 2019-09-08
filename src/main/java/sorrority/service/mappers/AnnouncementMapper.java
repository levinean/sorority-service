package sorrority.service.mappers;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import lombok.NonNull;
import sorrority.db.models.AnnouncementRow;
import sorrority.service.models.Announcement;

public final class AnnouncementMapper {
  private AnnouncementMapper() {}

  public static Announcement map(@NonNull final AnnouncementRow announcement) {
    return Announcement.builder()
        .uuid(announcement.getUuid())
        .announcement(announcement.getAnnouncement())
        .createdAt(announcement.getCreatedAt())
        .sorrorityUuid(announcement.getSorrorityUuid())
        .build();
  }

  public static List<Announcement> map(@NonNull final List<AnnouncementRow> rows) {
    return unmodifiableList(rows.stream().map(row -> map(row)).collect(toList()));
  }
}
