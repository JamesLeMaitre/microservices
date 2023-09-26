package com.esmc.gestionKsu.serviceinterfaces;

import com.esmc.gestionKsu.entities.Ksu;
import com.esmc.gestionKsu.inputs.NetworkActivationInput;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import java.util.List;

/**
 * {@link Ksu} service interface
 * @author katoh <katohdavid@gmail.com>
 */
public interface KsuServiceInterface   {

    /**
     * Save ksu
     * @param ksu
     */
    public void save(Ksu ksu) ;

    /**
     * Update ksu
     * @param ksu
     */
    public void update(Ksu ksu);

    /**
     * Get ksu by id
     * @param id
     * @return
     */
    public Ksu getById(Long id) ;

    /**
     * Get all ksu
     * @param page
     * @param pagedResourcesAssembler
     * @return
     */
    public PagedModel<EntityModel<Ksu>> getAll(Pageable page, PagedResourcesAssembler<Ksu> pagedResourcesAssembler);

/*    public Ksu ksuParMaBanKsu(Long id);*/

    public Ksu currentKsu();

    public Ksu addKsu(Long idtype, Long idMaBanKsu, Ksu k);

    public Ksu ksuParCodeKsu(String code);

    public Ksu ksuByMaBanKsu(Long id);


    Ksu ksuByInfo(String firstName, String lastName, String numero);

    Ksu ksuByInfoPM(String raisonSocial, String numero);

    PagedModel<EntityModel<Ksu>> getByPieceIdentite(Long id, Pageable page, PagedResourcesAssembler<Ksu> pagedResourcesAssembler);

    PagedModel<EntityModel<Ksu>> getByAbonnement(Long id, Pageable page, PagedResourcesAssembler<Ksu> pagedResourcesAssembler);

    PagedModel<EntityModel<Ksu>> getByBanque(Long id, Pageable page, PagedResourcesAssembler<Ksu> pagedResourcesAssembler);

    PagedModel<EntityModel<Ksu>> getBySecteur(Long id, Pageable page, PagedResourcesAssembler<Ksu> pagedResourcesAssembler);

    PagedModel<EntityModel<Ksu>> getByTypeOperateur(Long id, Pageable page, PagedResourcesAssembler<Ksu> pagedResourcesAssembler);

    PagedModel<EntityModel<Ksu>> getByTypePM(Long id, Pageable page, PagedResourcesAssembler<Ksu> pagedResourcesAssembler);

    //ckeck if ksu exist codeKsu
    boolean isPresent(Long id);

    boolean codeKsuIsPresent(String code);

    Ksu getKsuByActivationCode(String code);

    Ksu clearTempFingerPrintKey(Long id);

    Ksu activateNetwork(NetworkActivationInput networkActivationInput);

    Ksu getKsuByCodeRepresentant(String code);

    Ksu updateKSU(Long id, Ksu ksu);

    Object createUser(Long idKsu);

    List<Ksu> listKsuById(Long idKsu);
}
