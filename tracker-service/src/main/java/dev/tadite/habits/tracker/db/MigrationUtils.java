package dev.tadite.habits.tracker.db;

import org.flywaydb.core.api.migration.Context;

public class MigrationUtils {

    private static final String SCHEMA_NAME = "schema_name";

    public static String getSchema(Context context) {
        return context.getConfiguration().getPlaceholders().get(SCHEMA_NAME);
    }
}
