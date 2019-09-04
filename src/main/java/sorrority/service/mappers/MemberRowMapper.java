package sorrority.service.mappers;

import java.util.UUID;
import lombok.NonNull;
import sorrority.db.models.MemberRow;
import sorrority.service.models.Member;

public final class MemberRowMapper {
  private MemberRowMapper() {}

  public static MemberRow map(@NonNull final Member member) {
    return MemberRow.builder()
        .big(member.getBig())
        .birthday(member.getBirthday())
        .chapterUuid(member.getChapterUuid())
        .duesPaid(member.getDuesPaid())
        .email(member.getEmail())
        .executive(member.getExecutive())
        .graduatingYear(member.getGraduatingYear())
        .name(member.getName())
        .phoneNumber(member.getPhoneNumber())
        .pledgeClassUuid(member.getPledgeClassUuid())
        .sisterhoodPoints(member.getSisterhoodPoints())
        .uuid(UUID.randomUUID())
        .build();
  }
}
