package dev.tadite.habits.history;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
@EntityListeners(EntityAuditingEntityListener.class)
public class Project implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "project")
    @OrderBy("id DESC")
    private List<Task> tasks;

}
