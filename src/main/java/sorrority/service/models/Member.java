package sorrority.service.models;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Member {
  private UUID uuid;
  private String name;
  private UUID chapterUuid;
  private UUID pledgeClassUuid;
  private String graduatingYear;
  private String big;
  private int sisterhoodPoints;
  private Boolean executive;
  private String phoneNumber;
  private String email;
  private String birthday;
  private int duesPaid;
}
