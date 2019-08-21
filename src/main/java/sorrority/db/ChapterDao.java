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
import sorrority.db.mappers.ChapterRowMapper;
import sorrority.db.models.ChapterRow;

@RegisterRowMapper(ChapterRowMapper.class)
public interface ChapterDao {
  @CreateSqlObject
  ChapterDao createChapterDao();

  @SqlUpdate(
      "INSERT INTO chapters (uuid, name, sorrority_uuid) "
          + "VALUES (:uuid, :name, :sorrorityUuid)"
          + "ON CONFLICT (name,sorrority_uuid) DO NOTHING")
  void insert(@BindBean ChapterRow chapterRow);

  @SqlQuery(
      "SELECT EXISTS (SELECT 1 FROM chapters WHERE (name = :name)AND(sorrority_uuid =: sorrorityUuid))")
  boolean exists(String name, UUID sorrorityUuid);

  @SqlQuery("SELECT * FROM chapters WHERE uuid = :uuid")
  Optional<ChapterRow> findBy(UUID uuid);

  @SqlUpdate("DELETE FROM chapters WHERE uuid = :uuid")
  void deleteChapter(UUID uuid);

  @SqlQuery("SELECT * FROM chapters")
  List<ChapterRow> getAll();
}
