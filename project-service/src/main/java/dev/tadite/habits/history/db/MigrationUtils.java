package dev.tadite.habits.history.db;

import org.flywaydb.core.api.migration.Context;

public class MigrationUtils {

    public static final String SCHEMA_NAME = "schema_name";

    public static String getSchema(Context context) {
        return context.getConfiguration().getPlaceholders().get(SCHEMA_NAME);
    }
}
