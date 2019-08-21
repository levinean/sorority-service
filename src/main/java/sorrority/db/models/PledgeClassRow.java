package sorrority.db.models;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PledgeClassRow {
  private UUID uuid;
  private String name;
  private UUID sorrorityUuid;
  private UUID chapterUuid;
}
