package sorrority.db.models;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AnnouncementRow {
  private UUID uuid;
  private String announcement;
  private Instant createdAt;
}