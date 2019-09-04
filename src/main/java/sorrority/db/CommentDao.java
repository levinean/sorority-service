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
import java.util.UUID;
import org.jdbi.v3.sqlobject.CreateSqlObject;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import sorrority.db.mappers.CommentRowMapper;
import sorrority.db.models.CommentRow;

@RegisterRowMapper(CommentRowMapper.class)
public interface CommentDao {
  @CreateSqlObject
  CommentDao createCommentDao();

  @SqlUpdate(
      "INSERT INTO comments (uuid, comment, event_uuid) "
          + "VALUES (:uuid, :comment, :eventUuid)"
          + "ON CONFLICT (comment,event_uuid) DO NOTHING")
  void insert(@BindBean CommentRow CommentRow);

  @SqlQuery("SELECT EXISTS (SELECT 1 FROM comments WHERE uuid = :uuid)")
  boolean exists(UUID uuid);

  @SqlUpdate("DELETE FROM comments WHERE uuid = :uuid")
  void deleteComment(UUID uuid);

  @SqlUpdate("DELETE FROM comments WHERE event_uuid = :eventUuid")
  void deleteEventComments(UUID eventUuid);

  @SqlQuery("SELECT * FROM comments")
  List<CommentRow> getAll();
}
