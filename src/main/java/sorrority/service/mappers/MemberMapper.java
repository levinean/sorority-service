package sorrority.service.mappers;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import lombok.NonNull;
import sorrority.db.models.MemberRow;
import sorrority.service.models.Member;

public final class MemberMapper {
  private MemberMapper() {}

  public static Member map(@NonNull final MemberRow member) {
    return Member.builder()
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
        .uuid(member.getUuid())
        .build();
  }

  public static List<Member> map(@NonNull final List<MemberRow> rows) {
    return unmodifiableList(rows.stream().map(row -> map(row)).collect(toList()));
  }
}
