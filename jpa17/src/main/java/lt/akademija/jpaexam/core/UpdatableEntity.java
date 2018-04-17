package lt.akademija.jpaexam.core;

public interface UpdatableEntity extends UniqueEntity {

    String getString();
    void setString(String s);
}
