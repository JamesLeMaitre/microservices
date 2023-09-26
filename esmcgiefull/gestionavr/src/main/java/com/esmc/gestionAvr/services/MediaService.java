package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.Media;
import com.esmc.gestionAvr.repositories.MediaRepository;
import com.esmc.gestionAvr.servicesInterfaces.MediaInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MediaService implements MediaInterface {

    @Autowired
    private MediaRepository mediaRepository;

    @Override
    public Media addMedia(Media a) {
        return mediaRepository.save(a);
    }

    @Override
    public Media updateMedia(Media media) {
        return mediaRepository.save(media);
    }

    @Override
    public void deleteMedia(Long id) throws Exception {
        mediaRepository.deleteById(id);
    }

    @Override
    public List<Media> listMedia() {
        return mediaRepository.findAll();
    }

    @Override
    public List<Media> listMediaByAvr(Long id) {
        return mediaRepository.ListMediaByAvr(id);
    }
}
