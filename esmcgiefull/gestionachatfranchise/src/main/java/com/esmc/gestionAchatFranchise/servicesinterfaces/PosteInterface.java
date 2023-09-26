package com.esmc.gestionAchatFranchise.servicesinterfaces;

import com.esmc.gestionAchatFranchise.entities.PosteFranchise;

import java.util.List;

public interface PosteInterface  {
    List<PosteFranchise> getAll();
    PosteFranchise getById(Long id);

    PosteFranchise create(PosteFranchise data);
    PosteFranchise update(Long id, PosteFranchise data);

    void delete(Long id);

    int getCountAll();
}