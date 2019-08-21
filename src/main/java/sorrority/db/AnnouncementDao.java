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

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.jdbi.v3.sqlobject.CreateSqlObject;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import sorrority.db.mappers.AnnouncementRowMapper;
import sorrority.db.models.AnnouncementRow;

@RegisterRowMapper(AnnouncementRowMapper.class)
public interface AnnouncementDao {
  @CreateSqlObject
  AnnouncementDao createAnnouncementDao();

  @SqlUpdate(
      "INSERT INTO announcements (uuid, announcement, created_at) "
          + "VALUES (:uuid, :announcement, :createdAt)"
          + "ON CONFLICT (name,chapter_uuid) DO NOTHING")
  void insert(@BindBean AnnouncementRow AnnouncementRow);

  @SqlUpdate("DELETE FROM announcements WHERE uuid = :uuid")
  void deleteAnnouncement(UUID uuid);

  @SqlUpdate("DELETE FROM announcements WHERE created_at < :time")
  void cleanUpAnnouncements(Instant time);

  @SqlQuery("SELECT * FROM announcements")
  List<AnnouncementRow> getAll();
}
