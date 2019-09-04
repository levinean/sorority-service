package sorrority.service.models;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comment {
  private UUID uuid;
  private String comment;
  private UUID eventUuid;
}
