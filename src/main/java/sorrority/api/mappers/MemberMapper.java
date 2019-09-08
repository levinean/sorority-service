package sorrority.api.mappers;

import java.util.UUID;
import lombok.NonNull;
import sorrority.api.models.MemberRequest;
import sorrority.service.models.Member;

public final class MemberMapper {
  private MemberMapper() {}

  public static Member map(@NonNull final MemberRequest member) {
    return Member.builder()
        .big("")
        .birthday(member.getBirthday())
        .chapterUuid(member.getChapterUuid())
        .duesPaid(0)
        .email(member.getEmail())
        .executive(member.getExecutive())
        .graduatingYear(member.getGraduatingYear())
        .name(member.getName())
        .phoneNumber(member.getPhoneNumber())
        .pledgeClassUuid(member.getPledgeClassUuid())
        .sisterhoodPoints(0)
        .uuid(UUID.randomUUID())
        .build();
  }
}
