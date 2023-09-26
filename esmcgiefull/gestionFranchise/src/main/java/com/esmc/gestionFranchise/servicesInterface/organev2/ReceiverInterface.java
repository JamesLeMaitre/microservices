package com.esmc.gestionFranchise.servicesInterface.organev2;


import com.esmc.gestionFranchise.datafomater.AgentFichePosteJSON;
import com.esmc.gestionFranchise.datafomater.PosteTypeStructureJson;


public interface ReceiverInterface {
    void loadReceiverJSON(AgentFichePosteJSON[] data);
    void loadtypeSupportReceiverJSON(String[] data);

    void loadPosteTypeSupportResource(PosteTypeStructureJson[] data);

    void loadPosteSupportResourceJson(AgentFichePosteJSON[] data);
}
