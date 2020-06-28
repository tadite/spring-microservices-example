package dev.tadite.habits.projects;

import java.time.LocalDateTime;

public interface Auditable {
    void setCreatedAt(LocalDateTime createdAt);
}
