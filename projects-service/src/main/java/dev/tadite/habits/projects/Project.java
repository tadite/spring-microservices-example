package dev.tadite.habits.projects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
@EntityListeners(EntityAuditingEntityListener.class)
public class Project implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "project")
    private Set<Task> tasks;

}
