package dev.server.quiz.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@DynamicInsert
@DynamicUpdate
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String dni;
    private String email;
    private Boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;

    private static final long serialVersionUID = 1L;

    @CreationTimestamp()
    @Column(name = "created_at", updatable=false)
    private Date createdAt;

    @UpdateTimestamp()
    @Column(name = "updated_at")
    private Date updatedAt;
}
