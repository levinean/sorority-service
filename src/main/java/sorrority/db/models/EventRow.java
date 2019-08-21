package sorrority.db.models;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventRow {
  private UUID uuid;
  private String name;
  private String description;
  private String eventTime;
  private String eventDay;
  private int score;
}
