package dev.tadite.habits.history.matchers;

import dev.tadite.habits.history.dto.PageDto;
import net.minidev.json.JSONArray;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.Map;
import java.util.function.Predicate;

public class PageDtoMatcher extends BaseMatcher<String> {

    private final PageDto pageDto;
    private final int totalElements;
    private Predicate<JSONArray> contentMatcher;

    public PageDtoMatcher(PageDto pageDto, int totalElements, Predicate<JSONArray> contentMatcher) {
        this.pageDto = pageDto;
        this.totalElements = totalElements;
        this.contentMatcher = contentMatcher;
    }

    public PageDtoMatcher() {
        this.pageDto = null;
        this.totalElements = 0;
    }

    public static PageDtoMatcher empty() {
        return new PageDtoMatcher();
    }

    public static PageDtoMatcher fromPageable(PageDto pageDto,
                                              int totalElements,
                                              Predicate<JSONArray> contentMatcher) {
        return new PageDtoMatcher(pageDto, totalElements, contentMatcher);
    }

    @Override
    public boolean matches(Object actual) {
        Map actualMap = (Map) actual;
        if (pageDto == null) {
            return actualMap.get("totalElements").equals(0);
        } else {
            return actualMap.get("totalElements").equals(totalElements) &&
                    actualMap.get("pageSize").equals(pageDto.getPageSize()) &&
                    contentMatcher.test(((JSONArray)actualMap.get("content")));
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Page not match!");
    }
}
