package com.lazarev.rediscachejedis.config.jackson;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.collection.spi.PersistentList;
import org.hibernate.collection.spi.PersistentSet;

import java.util.*;

public class PersistentCollectionConverter extends StdConverter<PersistentCollection<?>, Collection<?>> {

    @Override
    public Collection<?> convert(PersistentCollection<?> collection) {
        if(collection instanceof PersistentSet<?> persistentSet){
            return new HashSet<>(persistentSet);
        }
        else if(collection instanceof PersistentList<?> persistentList){
            return new ArrayList<>(persistentList);
        }
        throw new IllegalStateException("Type '%s' not supported".formatted(collection.getClass()));
    }
}
