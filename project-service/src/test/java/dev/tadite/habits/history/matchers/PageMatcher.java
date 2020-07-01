package dev.tadite.habits.history.matchers;

import net.minidev.json.JSONArray;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.function.Predicate;

public class PageMatcher extends BaseMatcher<String> {

    private final Pageable pageable;
    private final int totalElements;
    private Predicate<JSONArray> contentMatcher;

    public PageMatcher(Pageable pageable, int totalElements, Predicate<net.minidev.json.JSONArray> contentMatcher) {
        this.pageable = pageable;
        this.totalElements = totalElements;
        this.contentMatcher = contentMatcher;
    }

    public PageMatcher() {
        this.pageable = null;
        this.totalElements = -1;
    }

    public static PageMatcher empty() {
        return new PageMatcher();
    }

    public static PageMatcher fromPageable(Pageable pageable,
                                           int totalElements,
                                           Predicate<JSONArray> contentMatcher) {
        return new PageMatcher(pageable, totalElements, contentMatcher);
    }

    @Override
    public boolean matches(Object actual) {
        Map actualMap = (Map) actual;
        if (pageable == null) {
            return actualMap.get("number").equals(0);
        } else {
            return actualMap.get("totalElements").equals(totalElements) &&
                    actualMap.get("numberOfElements").equals(pageable.getPageSize()) &&
                    contentMatcher.test(((net.minidev.json.JSONArray)actualMap.get("content")));
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Page not match!");
    }
}
