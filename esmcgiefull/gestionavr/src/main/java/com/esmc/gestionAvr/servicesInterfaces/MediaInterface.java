package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.Media;

import java.util.List;

public interface MediaInterface {
    public Media addMedia(Media a);
    public Media updateMedia( Media media);
    public void deleteMedia(Long id) throws Exception;
    public List<Media> listMedia();

    public List<Media> listMediaByAvr(Long id);
}
