package ru.mastkey.cloudservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id", nullable = false)
    private Workspace workspace;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "relative_path", nullable = false)
    private String relativePath;

    @Column(name = "checksum", nullable = false)
    private String checksum;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}