package com.codetech.oauth2.service_interface;

/**
 * Created by Amila Wickramarathne.
 * Date: 8/19/2019
 * Time: 4:11 PM
 */
public interface TransformServiceInterface {
    Object convertToDto(Object entity, String dtoClassName) throws ClassNotFoundException;

    Object convertToEntity(Object dto, String entityClassName) throws ClassNotFoundException;

    Object convertToDtoList(Object entities, String entityClassName) throws ClassNotFoundException;

    Object convertToDtoHashSet(Object entities, String dtoClassName) throws ClassNotFoundException;
}
