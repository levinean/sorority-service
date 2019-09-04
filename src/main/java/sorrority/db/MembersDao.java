/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sorrority.db;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.jdbi.v3.sqlobject.CreateSqlObject;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import sorrority.db.mappers.MemberRowMapper;
import sorrority.db.models.MemberRow;

@RegisterRowMapper(MemberRowMapper.class)
public interface MembersDao {
  @CreateSqlObject
  MembersDao createMembersDao();

  @SqlUpdate(
      "INSERT INTO members (uuid, name, chapter_uuid, pledge_class_uuid, graduating_year,"
          + " big, sisterhood_points, executive, phone_number, email, birthday, dues_paid) "
          + "VALUES (:uuid, :name, :chapterUuid, :pledgeClassUuid, :graduatingYear,"
          + " big, :sisterhoodPoints, :executive, :phoneNumber, :email, :birthday, :duesPaid)")
  void insert(@BindBean MemberRow memberRow);

  @SqlQuery(
      "SELECT EXISTS (SELECT 1 FROM members WHERE (name = :name) AND (email = :email) AND (birthday = :birthday))")
  boolean exists(String name, String email, String birthday);

  @SqlQuery("SELECT EXISTS (SELECT 1 FROM members WHERE uuid = :uuid)")
  boolean exists(UUID uuid);

  @SqlQuery("SELECT * FROM members WHERE uuid = :uuid")
  Optional<MemberRow> findBy(UUID uuid);

  @SqlQuery("SELECT * FROM members WHERE pledge_class_uuid = :pledgeClassUuid")
  List<MemberRow> findAllMembersInPledgeClass(UUID pledgeClassUuid);

  @SqlQuery("SELECT * FROM members WHERE chapter_uuid = :chapterUuid")
  List<MemberRow> findAllMembersInChapter(UUID chapterUuid);

  @SqlUpdate("DELETE FROM members WHERE uuid = :uuid")
  void deleteMember(UUID uuid);

  @SqlUpdate("UPDATE members SET sisterhood_points = :newPoints WHERE uuid = :uuid")
  void updateSisterhoodPoints(UUID uuid, int newPoints);

  @SqlUpdate("UPDATE members SET sisterhood_points = 0 WHERE chapter_uuid = :chapterUuid")
  void resetSisterhoodPoints(UUID chapterUuid);
}
