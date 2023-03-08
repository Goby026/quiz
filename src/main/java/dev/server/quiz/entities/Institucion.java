package dev.server.quiz.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@DynamicInsert
@DynamicUpdate
@Entity
public class Institucion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;
    @NotEmpty
    private String lugar;
    @NotEmpty
    private String dre;
    @NotEmpty
    private String ugel;

    @NotEmpty
    private String nivel;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "institucion_nivel"
//            ,joinColumns = @JoinColumn(name = "institucion_id")
//            ,inverseJoinColumns = @JoinColumn(name = "nivel_id"))
//    private Set<Nivel> niveles = new HashSet<>();

    @OneToMany(mappedBy = "institucion", fetch = FetchType.LAZY)
    private Set<Docente> docentes;

    private static final long serialVersionUID = 1L;

    @CreationTimestamp()
    @Column(name = "created_at", updatable=false)
    private Date createdAt;

    @UpdateTimestamp()
    @Column(name = "updated_at")
    private Date updatedAt;
}
