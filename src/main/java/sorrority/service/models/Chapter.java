package sorrority.service.models;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Chapter {
  private UUID uuid;
  private String name;
  private UUID sorrorityUuid;
}
