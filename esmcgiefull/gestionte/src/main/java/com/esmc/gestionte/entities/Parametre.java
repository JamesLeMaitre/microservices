package com.esmc.gestionte.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Parametre {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String libelle;

        @Column(nullable = false)
        private Double PRK;

        @Column(nullable = false)
        private Double PCK ;

        @Column(nullable = true)
        private Double MontantCarte = 0.0 ;

        @Column(nullable = true)
        private Double MontantKsu = 0.0 ;

        @Column(name = "param_ma_ban_zero", nullable = true)
        private Double paramMABAnZero = 0.0;

        @Column(name = "param_ma_ban", nullable = true)
        private Double paramMABAn = 0.0;

        @Column(name = "param_ban", nullable = true)
        private Double paramBAn = 0.0;

        @Column(name = "param_ban_opi", nullable = true)
        private Double paramBAnOPI = 0.0;

        @Column(name = "param_mprg_ban", nullable = true)
        private Double paramMPRgBAn = 0.0;

        @Column(name = "param_mprg_opi")
        private Double paramMPRgOPI = 0.0;

        @Column(name = "param_mprg_ban_opi_echus")
        private Double paramMPRgBAnOPIechus= 0.0;

        @Column(name = "param_opi", nullable = true)
        private Double paramOPI = 0.0;

        @Column(name = "param_mprg_ban_opi_non_echus", nullable = true)
        private Double paramMPRgBAnOPInonechus = 0.0;

        @Column(name = "param_bai_opi", nullable = true)
        private Double paramBAiOPI = 0.0;

        @Column(name = "param_bcr", nullable = true)
        private Double paramBCr = 0.0;

        @Column(name = "param_bcr_jour", nullable = true)
        private Double paramBCrjour = 0.0;

        @Column(name = "param_bcr_112", nullable = true)
        private Double paramBCrl112 = 0.0;

        @Column(name = "param_bcr_224", nullable = true)
        private Double paramBCrl224 = 0.0;

        @Column(name = "param_bcri_rmsbcr_224", nullable = true)
        private Double paramBCriRMSBCr224 = 0.0;

        @Column(name = "param_bcnr", nullable = true)
        private Double paramMBCnr = 0.0;

        @Column(name = "param_bcnr56prk65", nullable = true)
        private Double paramBCnr56PRK65 = 0.0;

        @Column(name = "param_bcnr_prk658", nullable = true)
        private Double paramBCnrPRK658= 0.0;

        @Column(name = "date_fin_opi")
        private Date dateFinOpi;

        @Column(nullable = true)
        public  Double venteBCIMaxAmount = 385000000.0;

        @Column(nullable = true)
        public  Double achatBAnMaxAmount = 7000000.0;

        @CreationTimestamp
        @Column(nullable = false)
        private Date dateCreate;

        @UpdateTimestamp
        @Column(nullable = false)
        private Date dateUpdate;




}
