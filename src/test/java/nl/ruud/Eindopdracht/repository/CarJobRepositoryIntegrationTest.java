package nl.ruud.Eindopdracht.repository;

import nl.ruud.Eindopdracht.model.Car;
import nl.ruud.Eindopdracht.model.CarJob;
import nl.ruud.Eindopdracht.model.CarJobStatus;
import nl.ruud.Eindopdracht.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CarJobRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarJobRepository carJobRepository;


    @Test
    public void whenFindByStatusThenReturnCarjob(){

        Customer customer = new Customer("jansen", "jansen@mail", "1234");
        Car car = new Car("123AB", "auto");
        LocalDateTime date = LocalDateTime.of(2020, Month.JANUARY, 18, 10,30);
        CarJob job = new CarJob(  CarJobStatus.PLANNED, date, "test2", customer, car );
        entityManager.persist(job);
        entityManager.flush();

        CarJob found = (CarJob) carJobRepository.findByStatus(CarJobStatus.PLANNED);

        assertThat(found.getStatus()).isEqualTo(CarJobStatus.PLANNED);
    }

}
