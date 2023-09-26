package com.esmc.gestionAchatFranchise.servicesinterfaces;

import com.esmc.gestionAchatFranchise.inputs.jsonFil;
import com.esmc.gestionAchatFranchise.inputs.jsonFlboe;
import com.esmc.gestionAchatFranchise.inputs.jsonFlbose;

public interface StatistiqueInterface {

    int getFillDistribution();

    int getFillChaineValeur(Long id);

    int getFillPartenaireOdd(Long id);

    void initFilesFill(jsonFil fil);


    void initDecoupage();

    void initPlugFilesFill();

    void initPlugFlboeFill();

    void initFilesFlboe(jsonFlboe flboe);

    void initFilesFlbose(jsonFlbose flbose);

    double getFillStat(int stage, Long id);

    double getFlboseStat(int stage, Long id);

    double getFlboeStat(int stage, Long id);

    double getFlbemStat(int stage, Long id);

    double getDecoupage(int stage);

    double getDecoupageGeo(int stage, Long id);

    double builder();
}
