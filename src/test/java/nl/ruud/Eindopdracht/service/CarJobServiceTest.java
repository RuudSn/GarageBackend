package nl.ruud.Eindopdracht.service;

import nl.ruud.Eindopdracht.GarageBackendApplication;
import nl.ruud.Eindopdracht.model.CarJob;
import nl.ruud.Eindopdracht.repository.CarJobRepository;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest()
@ContextConfiguration(classes= {GarageBackendApplication.class})
public class CarJobServiceTest {


    @Autowired
    private CarJobService carJobService;

    @MockBean
    private CarJobRepository carJobRepository;

    @Mock
    CarJob carJob;






}
