package dev.tadite.habits.projects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.CascadeType.ALL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(EntityAuditingEntityListener.class)
public class Task implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "project_id", insertable = false, updatable = false)
    private Integer projectId;

    private LocalDateTime createdAt;

    private Integer parentId;

    @Column(nullable = false)
    private String name;

    private String description;
}
