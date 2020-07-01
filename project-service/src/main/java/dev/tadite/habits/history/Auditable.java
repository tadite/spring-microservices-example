package dev.tadite.habits.history;

import java.time.LocalDateTime;

public interface Auditable {
    void setCreatedAt(LocalDateTime createdAt);
}
