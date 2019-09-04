package sorrority.service.models;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Event {
  private UUID uuid;
  private String name;
  private String description;
  private String eventTime;
  private String eventDay;
  private int score;
  private int numberAttended;
}
