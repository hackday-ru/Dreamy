package org.kedzo.dreamy.services.impl;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@Component(value = "entityManager")
public class DreamyEntityManager {
    private EntityManager entityManager;

    public DreamyEntityManager() {
        entityManager = Persistence.createEntityManagerFactory("dreamy").createEntityManager();
    }

    public EntityManager instance() {
        return entityManager;
    }
}
