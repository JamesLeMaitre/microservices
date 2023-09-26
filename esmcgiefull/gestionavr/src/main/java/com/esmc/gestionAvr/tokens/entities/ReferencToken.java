package com.esmc.gestionAvr.tokens.entities;

import com.esmc.gestionAvr.entities.Avr;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class ReferencToken implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private boolean etat = true;

    @Column(nullable = false)
    private String codeRef;

    @Column(nullable = false)
    private String refTokenFront;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

    @ManyToOne
    @JoinColumn
    private Avr avr;

    private Long idParent;

    @JoinTable
    @OneToMany
    @ToString.Exclude
    private Collection<ReferencToken> referencTokens = new ArrayList<>();


}
