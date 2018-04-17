package lt.itakademija.repository;

import org.springframework.stereotype.Repository;

/**
 * Created by mariusg on 2017.03.19.
 */

public interface SequenceGenerator {

    /**
     * Returns the next number in a sequence.
     * <p>
     * First number in a sequence is 1.
     *
     * @return next sequence number.
     */
    Long getNext();

}
