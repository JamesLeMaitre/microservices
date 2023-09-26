package com.esmc.gestionCertification.constant;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
public class PostulerRequest {

/*
     @NotNull
     private Long DetailAgr;
     @NotNull
    private Long  idAppelCandidature;
    @NotNull
    private Long idPoste ;
    @NotNull



 */
    private String diplome ;

    private MultipartFile  lettreMotivation ;

    private MultipartFile curriculumVitae ;
/*
    public PostulerRequest(Long detailAgr, Long idAppelCandidature, Long idPoste, String diplome, MultipartFile lettreMotivation, MultipartFile curriculumVitae) {
        DetailAgr = detailAgr;
        this.idAppelCandidature = idAppelCandidature;
        this.idPoste = idPoste;
        this.diplome = diplome;
        this.lettreMotivation = lettreMotivation;
        this.curriculumVitae = curriculumVitae;
    }

 */

    public PostulerRequest() {
    }

    public PostulerRequest(String diplome, MultipartFile lettreMotivation, MultipartFile curriculumVitae) {
        this.diplome = diplome;
        this.lettreMotivation = lettreMotivation;
        this.curriculumVitae = curriculumVitae;
    }
}
