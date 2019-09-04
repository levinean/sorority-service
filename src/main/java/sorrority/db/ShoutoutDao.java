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
import sorrority.db.mappers.ShoutoutRowMapper;
import sorrority.db.models.ShoutoutRow;

@RegisterRowMapper(ShoutoutRowMapper.class)
public interface ShoutoutDao {
  @CreateSqlObject
  ShoutoutDao createShoutoutDao();

  @SqlUpdate(
      "INSERT INTO shoutouts (uuid, shoutout, created_at) "
          + "VALUES (:uuid, :shoutout, :createdAt)"
          + "ON CONFLICT (name,chapter_uuid) DO NOTHING")
  void insert(@BindBean ShoutoutRow shoutoutRow);

  @SqlQuery("SELECT EXISTS (SELECT 1 FROM shoutouts WHERE uuid = :uuid)")
  boolean exists(UUID uuid);

  @SqlUpdate("DELETE FROM shoutouts WHERE uuid = :uuid")
  void deleteShoutout(UUID uuid);

  @SqlUpdate("DELETE FROM shoutouts WHERE created_at < :time")
  void cleanUpShoutouts(Instant time);

  @SqlQuery("SELECT * FROM shoutouts")
  List<ShoutoutRow> getAll();
}
