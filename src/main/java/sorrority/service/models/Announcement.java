package sorrority.service.models;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Announcement {
  private UUID uuid;
  private String announcement;
  private Instant createdAt;
}
