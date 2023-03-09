package dev.server.quiz.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
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

    private String lugar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nivel_id", nullable = false)
    private Nivel nivel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private String numVisita;
    private String hora_inicio;
    private String hora_fin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "canal_id", nullable = false)
    private Canal canal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docente_id", nullable = false)
    private Docente docente;

    private String ciclo;
    private String gradoSeccion;
    private String cantidadEstudiantes;
    private String numSemana;
    private String numActividad;
    private boolean directorioPadres;
    private boolean directorioEstudiantes;
    private String competencia;
    private String nombreActividad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medio_id", nullable = false)
    private Medio medio;

    private int cantidadMedio;

    private static final long serialVersionUID = 1L;

    @CreationTimestamp()
    @Column(name = "created_at", updatable=false)
    private Date createdAt;

    @UpdateTimestamp()
    @Column(name = "updated_at")
    private Date updatedAt;
}