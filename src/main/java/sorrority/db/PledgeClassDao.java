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
import sorrority.db.mappers.PledgeClassRowMapper;
import sorrority.db.models.PledgeClassRow;

@RegisterRowMapper(PledgeClassRowMapper.class)
public interface PledgeClassDao {
  @CreateSqlObject
  PledgeClassDao createPledgeClassDao();

  @SqlUpdate(
      "INSERT INTO pledge_classes (uuid, name, chapter_uuid) "
          + "VALUES (:uuid, :name, :chapterUuid)"
          + "ON CONFLICT (name,chapter_uuid) DO NOTHING")
  void insert(@BindBean PledgeClassRow pledgeClassRow);

  @SqlQuery(
      "SELECT EXISTS (SELECT 1 FROM pledge_classes WHERE (name = :name)AND(chapter_uuid =: chapterUuid))")
  boolean exists(String name, UUID chapterUuid);

  @SqlQuery("SELECT * FROM pledge_classes WHERE uuid = :uuid")
  Optional<PledgeClassRow> findBy(UUID uuid);

  @SqlUpdate("DELETE FROM pledge_classes WHERE uuid = :uuid")
  void deletePledgeClass(UUID uuid);

  @SqlQuery("SELECT * FROM pledge_classes")
  List<PledgeClassRow> getAll();
}
