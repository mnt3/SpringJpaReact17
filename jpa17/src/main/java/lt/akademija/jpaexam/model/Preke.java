package lt.akademija.jpaexam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lt.akademija.jpaexam.core.UniqueEntityWithAssociation;
import lt.akademija.jpaexam.core.UpdatableEntity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by JP17-2 on 2018.04.17.
 */

@Entity
public class Preke implements UpdatableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String pavadinimas;
    double kaina;

    @ManyToOne
    @JsonIgnore
    PrekiuKrepselis prekiuKrepselis;

    public double getKaina() {
        return kaina;
    }

    public void setKaina(double kaina) {
        this.kaina = kaina;
    }

    @Override
    public String getString() {
        return pavadinimas;
    }

    @Override
    public void setString(String s) {

        this.pavadinimas=s;
    }

    @Override
    public Long getId() {
        return this.id;
    }


}
