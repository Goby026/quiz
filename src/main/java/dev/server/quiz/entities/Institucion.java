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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dre_id", nullable = false)
    private Dre dre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ugel_id", nullable = false)
    private Ugel ugel;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "institucion_nivel"
            ,joinColumns = @JoinColumn(name = "institucion_id")
            ,inverseJoinColumns = @JoinColumn(name = "nivel_id"))
    private Set<Nivel> niveles = new HashSet<>();

//    @OneToOne(mappedBy = "institucion", fetch = FetchType.LAZY)
//    private Actividad actividad;

//    @OneToMany(mappedBy = "institucion", fetch = FetchType.LAZY)
//    private Set<Docente> docentes;

    private static final long serialVersionUID = 1L;

    @CreationTimestamp()
    @Column(name = "created_at", updatable=false)
    private Date createdAt;

    @UpdateTimestamp()
    @Column(name = "updated_at")
    private Date updatedAt;
}
