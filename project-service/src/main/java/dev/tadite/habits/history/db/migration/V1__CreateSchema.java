package dev.tadite.habits.history.db.migration;

import dev.tadite.habits.history.db.MigrationUtils;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class V1__CreateSchema extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("create schema if not exists " + MigrationUtils.getSchema(context) + ";");
    }
}