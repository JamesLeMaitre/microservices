package com.esmc.gestionAchatFranchise.servicesinterfaces;

import com.esmc.gestionAchatFranchise.entities.Canton;
import com.esmc.gestionAchatFranchise.entities.franchise.fill.FranchiseFillChaineDistribution;
import com.esmc.gestionAchatFranchise.entities.franchise.flbOe.FranchiseFlbOeChaineDistribution;
import com.esmc.gestionAchatFranchise.entities.franchise.refereniel.FranchiseReferentiel;
import com.esmc.gestionAchatFranchise.inputs.achatFranchiseInput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AchatServiceInterface {
    Boolean achat_franchise_decoupage_geographique(achatFranchiseInput data);

    Boolean achat_franchise_decoupage_geographique_check(achatFranchiseInput data);

    Boolean achat_franchise_decoupage_centre(achatFranchiseInput data);

    Boolean achat_franchise_decoupage_centre_check(achatFranchiseInput data);
    Canton achat_franchise_decoupage_centre_getcentre(int stage, Long idFranchise);

    List<FranchiseFillChaineDistribution> distributor_fill_available();

    List<FranchiseFlbOeChaineDistribution> distributor_flboe_available();

    List<FranchiseReferentiel> listFranchiseReferenciel(List<Long> ids);
}
