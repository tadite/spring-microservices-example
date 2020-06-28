package dev.tadite.habits.projects;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;

public class EntityAuditingEntityListener {
    @PrePersist
    public void prePersist(Auditable auditable){
        auditable.setCreatedAt(LocalDateTime.now());
    }
}
