package com.esmc.gestionAvr.putonchain.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class JavaConvert {
    public <E, D> D entityToDTO(E entity, Class<D> dtoClass) {
        D dto = BeanUtils.instantiateClass(dtoClass);
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public <E, D> E dtoToEntity(D dto, Class<E> entityClass) {
        E entity = BeanUtils.instantiateClass(entityClass);
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

}
