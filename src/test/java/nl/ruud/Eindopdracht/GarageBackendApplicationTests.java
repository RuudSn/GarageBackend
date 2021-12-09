package nl.ruud.Eindopdracht;



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


import static org.junit.jupiter.api.Assertions.assertNotEquals;


@SpringBootTest()
@ContextConfiguration(classes={GarageBackendApplication.class})
class GarageBackendApplicationTests {

	@Test
	@DisplayName("test")
	void ContextLoadsTest() {

		assertNotEquals(1,2);
	}

}
