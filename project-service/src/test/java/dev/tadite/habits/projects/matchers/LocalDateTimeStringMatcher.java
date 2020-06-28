package dev.tadite.habits.projects.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class LocalDateTimeStringMatcher extends BaseMatcher<String> {

    private final LocalDateTime expected;

    public LocalDateTimeStringMatcher(LocalDateTime expected) {
        this.expected = expected;
    }

    @Override
    public boolean matches(Object actual) {
        return LocalDateTime.parse((CharSequence) actual)
                .truncatedTo(ChronoUnit.MILLIS)
                .equals(expected.truncatedTo(ChronoUnit.MILLIS));
    }

    @Override
    public void describeTo(Description description) {

    }
}
