package com.esmc.gestionAvr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionVague {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String label;

        @Column(nullable = true, length = 500)
        private String description;


        @Column(nullable = true)
        private Boolean status= true;

        @CreationTimestamp
        @Column(nullable = false)
        private Date dateCreate;

        @UpdateTimestamp
        @Column(nullable = false)
        private Date dateUpdate;


}
