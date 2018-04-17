package lt.akademija.jpaexam.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class GenericRepository {

    @Autowired
    private EntityManager em;

    public <T> List<T> findAll(Class<T> clazz) {
        return em.createQuery("select e from " + clazz.getSimpleName() + " e").getResultList();
    }

    @Transactional
    public <T> T find(Long id, Class<T> clazz) {
        return em.find(clazz, id);
    }

    @Transactional
    public <T> T save(T e) {
        em.persist(e);
        return e;
    }

    @Transactional
    public <T> T update(T e) {
        return em.merge(e);
    }

    @Transactional
    public <T> void delete(Long id, Class<T> clazz) {
        em.remove(find(id, clazz));
    }
}
