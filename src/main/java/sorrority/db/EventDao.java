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
import java.util.Optional;
import java.util.UUID;
import org.jdbi.v3.sqlobject.CreateSqlObject;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import sorrority.db.mappers.EventRowMapper;
import sorrority.db.models.EventRow;

@RegisterRowMapper(EventRowMapper.class)
public interface EventDao {
  @CreateSqlObject
  EventDao createEventDao();

  @SqlUpdate(
      "INSERT INTO events (uuid, name, description, eventTime, score) "
          + "VALUES (:uuid, :name, :description, :eventTime, :score)"
          + "ON CONFLICT (eventTime) DO NOTHING")
  void insert(@BindBean EventRow eventRow);

  @SqlUpdate("DELETE FROM events WHERE uuid = :uuid")
  void deleteEvent(UUID uuid);

  @SqlQuery("SELECT * FROM events WHERE uuid = :uuid")
  Optional<EventRow> findby(UUID uuid);

  @SqlUpdate("UPDATE events SET score = :newScore WHERE uuid = :uuid")
  void updateScore(UUID uuid, int newScore);

  @SqlUpdate("UPDATE events SET event_time = :newTime WHERE uuid = :uuid")
  void updateTime(UUID uuid, Instant newTime);

  @SqlUpdate("UPDATE events SET description = :newDescription WHERE uuid = :uuid")
  void updateDescription(UUID uuid, String newDescription);

  @SqlQuery("SELECT * FROM events WHERE event_day = :day")
  Optional<EventRow> getAllEventsOnDay(String day);

  @SqlQuery("SELECT * FROM events")
  List<EventRow> getAll();
}
