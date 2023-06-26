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
public class Consolidado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private int cantidadItem;
    private int noCat;
    private int procesoCat;
    private int siCat;
    private int total;
    private double nivelAvance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_item_id", nullable = true)
    private CategoriaItem categoriaItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_id", nullable = true)
    private Ficha ficha;

    private static final long serialVersionUID = 1L;

    @CreationTimestamp()
    @Column(name = "created_at", updatable=false)
    private Date createdAt;

    @UpdateTimestamp()
    @Column(name = "updated_at")
    private Date updatedAt;
}
