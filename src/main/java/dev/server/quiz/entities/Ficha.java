package dev.server.quiz.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Entity
public class Ficha implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario;

    private Date fecha;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actividad_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Actividad actividad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "indicador_id", nullable = true)
    private Indicador indicador;

    @OneToMany(mappedBy = "ficha", fetch = FetchType.LAZY)
    private Set<FichaItem> fichaItems;

    @OneToMany(mappedBy = "ficha", fetch = FetchType.LAZY)
    private Set<Consolidado> consolidados;

    private static final long serialVersionUID = 1L;

    @CreationTimestamp()
    @Column(name = "created_at", updatable=false)
    private Date createdAt;

    @UpdateTimestamp()
    @Column(name = "updated_at")
    private Date updatedAt;
}
