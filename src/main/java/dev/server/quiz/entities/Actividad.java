package dev.server.quiz.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalTime;
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
public class Actividad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institucion_id", nullable = false)
    private Institucion institucion;

    @NotEmpty
    private String lugar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nivel_id", nullable = false)
    private Nivel nivel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = true)
    private Usuario usuario;

    @NotEmpty
    private String numVisita;

    @Temporal(TemporalType.TIME)
    private LocalTime hora_inicio;

    @Temporal(TemporalType.TIME)
    private LocalTime hora_fin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "canal_id", nullable = false)
    private Canal canal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docente_id", nullable = false)
    private Docente docente;

    @NotEmpty
    private String ciclo;
    @NotEmpty
    private String gradoSeccion;
    @NotEmpty
    private String cantidadEstudiantes;
    @NotEmpty
    private String numSemana;
    @NotEmpty
    private String numActividad;

    private boolean directorioPadres;
    private boolean directorioEstudiantes;

    @NotEmpty
    @Column(name = "competencia", columnDefinition = "TEXT")
    private String competencia;
    @NotEmpty
    private String nombreActividad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medio_id", nullable = false)
    private Medio medio;

    private int cantidadMedio;
    private double porcentajeMedio;

    private static final long serialVersionUID = 1L;

    @CreationTimestamp()
    @Column(name = "created_at", updatable=false)
    private Date createdAt;

    @UpdateTimestamp()
    @Column(name = "updated_at")
    private Date updatedAt;
}