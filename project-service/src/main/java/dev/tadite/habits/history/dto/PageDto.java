package dev.tadite.habits.history.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PageDto<T> {
    private List<T> content;
    private int pageSize;
    private int totalPages;
    private int currentPage;
    private long totalElements;

    public static <T1> PageDto<T1> fromEntity(Page<T1> page) {
        if (page == null)
            return PageDto.<T1>builder()
                    .content(Collections.emptyList())
                    .currentPage(0)
                    .pageSize(10)
                    .totalElements(0)
                    .totalPages(0)
                    .build();

        return PageDto.<T1>builder()
                .content(page.getContent())
                .pageSize(page.getSize())
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }
}
