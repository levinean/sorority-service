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

package sorrority;

import com.codahale.metrics.jdbi3.InstrumentedSqlLogger;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayBundle;
import io.dropwizard.flyway.FlywayFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import sorrority.api.resources.AnnouncementResource;
import sorrority.api.resources.ChapterResource;
import sorrority.api.resources.CommentResource;
import sorrority.api.resources.EventResource;
import sorrority.api.resources.MemberResource;
import sorrority.api.resources.PledgeClassResource;
import sorrority.api.resources.ShoutoutResource;
import sorrority.api.resources.SorrorityResource;
import sorrority.db.AnnouncementDao;
import sorrority.db.ChapterDao;
import sorrority.db.CommentDao;
import sorrority.db.EventDao;
import sorrority.db.MembersDao;
import sorrority.db.PledgeClassDao;
import sorrority.db.ShoutoutDao;
import sorrority.db.SorrorityDao;
import sorrority.service.AnnouncementService;
import sorrority.service.ChapterService;
import sorrority.service.CommentService;
import sorrority.service.EventService;
import sorrority.service.MemberService;
import sorrority.service.PledgeClassService;
import sorrority.service.ShoutoutService;
import sorrority.service.SorrorityService;

@Slf4j
public class SorrorityApp extends Application<SorrorityConfig> {
  private static final String APP_NAME = "SorrorityApp";
  private static final String POSTGRES_DB = "postgresql";
  private static final boolean ERROR_ON_UNDEFINED = false;

  public static void main(String[] args) throws Exception {
    new SorrorityApp().run(args);
  }

  @Override
  public String getName() {
    return APP_NAME;
  }

  @Override
  public void initialize(@NonNull Bootstrap<SorrorityConfig> bootstrap) {
    // Enable variable substitution with environment variables.
    bootstrap.setConfigurationSourceProvider(
        new SubstitutingSourceProvider(
            bootstrap.getConfigurationSourceProvider(),
            new EnvironmentVariableSubstitutor(ERROR_ON_UNDEFINED)));

    // Enable Flyway for database migrations.
    bootstrap.addBundle(
        new FlywayBundle<SorrorityConfig>() {
          @Override
          public DataSourceFactory getDataSourceFactory(@NonNull SorrorityConfig config) {
            return config.getDataSourceFactory();
          }

          @Override
          public FlywayFactory getFlywayFactory(@NonNull SorrorityConfig config) {
            return config.getFlywayFactory();
          }
        });
  }

  @Override
  public void run(@NonNull SorrorityConfig config, @NonNull Environment env)
      throws MarquezException {
    migrateDbOrError(config);
    registerResources(config, env);
  }

  private void migrateDbOrError(@NonNull SorrorityConfig config) {
    final Flyway flyway = new Flyway();
    final DataSourceFactory db = config.getDataSourceFactory();
    flyway.setDataSource(db.getUrl(), db.getUser(), db.getPassword());
    // Attempt to perform a database migration. An exception is thrown on failed migration
    // attempts
    // requiring we handle the throwable and apply a repair on the database to fix any
    // issues before terminating.
    try {
      log.info("Migrating database...");
      flyway.migrate();
      log.info("Successfully migrated database.");
    } catch (FlywayException errorOnDbMigrate) {
      log.error("Failed to apply migration to database.", errorOnDbMigrate);
      try {
        log.info("Repairing failed database migration...");
        flyway.repair();
        log.info("Successfully repaired database.");
      } catch (FlywayException errorOnDbRepair) {
        log.error("Failed to apply repair to database.", errorOnDbRepair);
      }

      log.info("Stopping app...");
      // The throwable is not propagating up the stack.
      onFatalError(); // Signal app termination.
    }
  }

  private void registerResources(@NonNull SorrorityConfig config, @NonNull Environment env)
      throws MarquezException {
    final JdbiFactory factory = new JdbiFactory();
    final Jdbi jdbi =
        factory
            .build(env, config.getDataSourceFactory(), POSTGRES_DB)
            .installPlugin(new SqlObjectPlugin())
            .installPlugin(new PostgresPlugin());
    jdbi.setSqlLogger(new InstrumentedSqlLogger(env.metrics()));

    final MembersDao memberDao = jdbi.onDemand(MembersDao.class);
    final EventDao eventDao = jdbi.onDemand(EventDao.class);
    final SorrorityDao sorrorityDao = jdbi.onDemand(SorrorityDao.class);
    final ChapterDao chapterDao = jdbi.onDemand(ChapterDao.class);
    final PledgeClassDao pledgeClassDao = jdbi.onDemand(PledgeClassDao.class);
    final CommentDao commentDao = jdbi.onDemand(CommentDao.class);
    final ShoutoutDao shoutoutDao = jdbi.onDemand(ShoutoutDao.class);
    final AnnouncementDao announcementDao = jdbi.onDemand(AnnouncementDao.class);

    final MemberService memberService = new MemberService(memberDao, chapterDao);
    final EventService eventService = new EventService(eventDao, commentDao);
    final SorrorityService sorrorityService = new SorrorityService(sorrorityDao);
    final PledgeClassService pledgeClassService = new PledgeClassService(pledgeClassDao);
    final ChapterService chapterService = new ChapterService(chapterDao);
    final ShoutoutService shoutoutService = new ShoutoutService(shoutoutDao);
    final AnnouncementService announcementService = new AnnouncementService(announcementDao);
    final CommentService commentService = new CommentService(commentDao);

    env.jersey().register(new MemberResource(memberService));
    env.jersey().register(new EventResource(eventService));
    env.jersey().register(new SorrorityResource(sorrorityService));
    env.jersey().register(new ChapterResource(chapterService));
    env.jersey().register(new PledgeClassResource(pledgeClassService));
    env.jersey().register(new ShoutoutResource(shoutoutService));
    env.jersey().register(new AnnouncementResource(announcementService));
    env.jersey().register(new CommentResource(commentService));
  }
}
