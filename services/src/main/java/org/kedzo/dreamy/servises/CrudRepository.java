package org.kedzo.dreamy.servises;

import org.kedzo.dreamy.models.RepositoryEntity;

public interface CrudRepository<T extends RepositoryEntity> {
    long save(T entity);

    T load(long id);

    void delete(T entity);

    long update(T entity);
}
