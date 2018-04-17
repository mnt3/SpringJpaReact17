package lt.akademija.jpaexam.core;

import java.util.Collection;

interface EntityWithAssociation {

    Collection<? extends UpdatableEntity> oneToMany();
}
