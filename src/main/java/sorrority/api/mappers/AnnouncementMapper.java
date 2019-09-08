package sorrority.api.mappers;

import java.time.Instant;
import java.util.UUID;
import lombok.NonNull;
import sorrority.api.models.AnnouncementRequest;
import sorrority.service.models.Announcement;

public final class AnnouncementMapper {
  private AnnouncementMapper() {}

  public static Announcement map(@NonNull final AnnouncementRequest announcement) {
    return Announcement.builder()
        .uuid(UUID.randomUUID())
        .announcement(announcement.getAnnouncement())
        .createdAt(Instant.now())
        .build();
  }
}
