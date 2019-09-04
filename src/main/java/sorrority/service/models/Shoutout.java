package sorrority.service.models;

import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Shoutout {
  private UUID uuid;
  private String shoutout;
  private Instant createdAt;
}
