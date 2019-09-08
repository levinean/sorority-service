package sorrority.service.mappers;

import lombok.NonNull;
import sorrority.db.models.AnnouncementRow;
import sorrority.service.models.Announcement;

public final class AnnouncementRowMapper {
  private AnnouncementRowMapper() {}

  public static AnnouncementRow map(@NonNull final Announcement announcement) {
    return AnnouncementRow.builder()
        .uuid(announcement.getUuid())
        .announcement(announcement.getAnnouncement())
        .createdAt(announcement.getCreatedAt())
        .sorrorityUuid(announcement.getSorrorityUuid())
        .build();
  }
}
