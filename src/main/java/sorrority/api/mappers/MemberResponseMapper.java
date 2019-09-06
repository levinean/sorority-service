package sorrority.api.mappers;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

import java.util.List;

import java.util.UUID;
import lombok.NonNull;
import sorrority.api.models.MemberResponse;
import sorrority.api.models.MembersResponse;
import sorrority.service.models.Member;

public final class MemberResponseMapper {
  private MemberResponseMapper() {}

  public static MemberResponse map(@NonNull final Member member) {
    return MemberResponse.builder()
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

  public static MembersResponse map(@NonNull final List<Member> rows) {
    return new MembersResponse(unmodifiableList(rows.stream().map(row -> map(row)).collect(toList())));
  }
}
