package lt.akademija.jpaexam.model;

import lt.akademija.jpaexam.core.UniqueEntityWithAssociation;
import lt.akademija.jpaexam.core.UpdatableEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by JP17-2 on 2018.04.17.
 */
@Entity
public class PrekiuKrepselis implements UniqueEntityWithAssociation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String krespselioPavadinimas;

    @OneToMany
    Set<Preke> krepselis = new HashSet<>();


    public void setId(Long id) {
        this.id = id;
    }

    public String getKrespselioPavadinimas() {
        return krespselioPavadinimas;
    }

    public void setKrespselioPavadinimas(String krespselioPavadinimas) {
        this.krespselioPavadinimas = krespselioPavadinimas;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public Collection<? extends UpdatableEntity> oneToMany() {
        return krepselis;
    }

    public Set<Preke> getKrepselis() {
        return krepselis;
    }

    public void setKrepselis(Set<Preke> krepselis) {
        this.krepselis = krepselis;
    }
}
