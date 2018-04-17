package lt.akademija.jpaexam;

import lt.akademija.jpaexam.core.GenericRepository;
import lt.akademija.jpaexam.core.UniqueEntity;
import lt.akademija.jpaexam.core.UniqueEntityWithAssociation;
import lt.akademija.jpaexam.core.UpdatableEntity;
import lt.akademija.jpaexam.grader.Grader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ExerciseTest {

    @Autowired
    GenericRepository repository;

    @Autowired
    TransactionTemplate tx;
    private Entities entities;

    @Test
    public void gradeNaming() {
        Grader.graded(1, () -> "I hope good naming");
    }

    @Before
    public void setUp() {
        try {
            TestData testData = new TestData();
            UniqueEntityWithAssociation e1 = (UniqueEntityWithAssociation) repository.save(testData.getNewEntity1());
            this.entities = new Entities(e1);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void gradeAssocSave() {
        Grader.graded(1, () -> {


            UniqueEntityWithAssociation e1 = entities.e1;
            assertThat("oneToMany should return some values", e1.oneToMany(), notNullValue());
            assertThat("oneToMany should return some values", e1.oneToMany(), is(not(empty())));

            assertThat(e1.getId(), equalTo(1L));
            return null;
        });
    }

    @Test
    public void gradeUpdate() {

        Grader.graded(1, () -> {
            String oldValue = repository.find(1L, entities.e1.getClass()).oneToMany().iterator().next().getString();

            UniqueEntityWithAssociation e = repository.find(1L, entities.e1.getClass());
            UpdatableEntity firstAssoc = e.oneToMany().iterator().next();

            String newValue = oldValue + "somesuffix";
            firstAssoc.setString(newValue);
            assertThat(firstAssoc.getString(), equalTo(newValue));

            repository.update(e);

            UniqueEntityWithAssociation updated = repository.find(1L, entities.e1.getClass());
            assertThat(
                    "Update using setString should not be saved to database",
                    updated.oneToMany().iterator().next().getString(),
                    equalTo(oldValue));
            return null;
        });
    }

    @Test
    public void gradeDelete() {
        Grader.graded(1, () -> {
            UniqueEntity e1 = repository.find(1L, entities.e1.getClass());
            repository.delete(e1.getId(), e1.getClass());
            assertThat(repository.find(1L, entities.e1.getClass()), nullValue());
            assertThat(repository.findAll(entities.e1.getClass()), is(empty()));
            assertThat(repository.findAll(entities.e1.oneToMany().iterator().next().getClass()), is(empty()));
            return null;
        });
    }

    class Entities {
        UniqueEntityWithAssociation e1;

        public Entities(UniqueEntityWithAssociation e1) {
            this.e1 = e1;
        }
    }
}