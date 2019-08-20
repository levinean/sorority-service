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
import sorrority.db.mappers.SorrorityRowMapper;
import sorrority.db.models.SorrorityRow;

@RegisterRowMapper(SorrorityRowMapper.class)
public interface SorrorityDao {
  @CreateSqlObject
  SorrorityDao createSorrorityDao();

  @SqlUpdate(
      "INSERT INTO sorrorities (uuid, name) "
          + "VALUES (:uuid, :name)"
          + "ON CONFLICT (name) NO NOTHING")
  void insert(@BindBean SorrorityRow sorrorityRow);

  @SqlQuery(
      "SELECT EXISTS (SELECT 1 FROM sorrorities WHERE (name = :name))")
  boolean exists(String name);

  @SqlQuery("SELECT * FROM sorrorities WHERE uuid = :uuid")
  Optional<SorrorityRow> findBy(UUID uuid);

  @SqlUpdate("DELETE FROM sorrorities WHERE uuid = :uuid")
  void deleteSorrority(UUID uuid);

  @SqlQuery("SELECT * FROM sorrorities")
  List<SorrorityRow> getAll();
}
