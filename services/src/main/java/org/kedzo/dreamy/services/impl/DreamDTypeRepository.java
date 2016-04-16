package org.kedzo.dreamy.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository(value = "dreamDTypeRepository")
public class DreamDTypeRepository {

    @Autowired
    DreamyEntityManager entityManager;

    public void insert(long dreamId, long dreamTypeId) {
        try {
            entityManager.instance().getTransaction().begin();
            Query query = entityManager.instance().createNativeQuery(
                    "INSERT INTO dreams_dream_type(Dream_id, types_id) VALUES (:dream, :typeId)"
            ).setParameter("dream", dreamId).setParameter("typeId", dreamTypeId);

            query.executeUpdate();

            entityManager.instance().getTransaction().commit();
        } catch (Exception e) {
            entityManager.instance().getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

}
