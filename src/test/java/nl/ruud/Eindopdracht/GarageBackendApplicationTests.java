package nl.ruud.Eindopdracht;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertNotEquals;

@SpringBootTest()
@ContextConfiguration(classes={GarageBackendApplication.class})
class GarageBackendApplicationTests {

	@Test
	@DisplayName("Testing if context is set correctly")
	void ContextLoadsTest() {

		assertNotEquals(1,2);
	}

}
