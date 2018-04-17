package lt.akademija.reactexam;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class FlightController {

    private final List<Flight> flights = Collections.synchronizedList(new ArrayList<>());
    private final AtomicLong idGenerator = new AtomicLong(0);

    {
        {
            Flight telAviv = new Flight();
            telAviv.setId(idGenerator.incrementAndGet());
            telAviv.setDate("2017-03-20");
            telAviv.setTime("14:45");
            telAviv.setFlightNumber("BT 610");
            telAviv.setCompany("Air Baltic");
            telAviv.setStatus(Flight.Status.LANDED);
            flights.add(telAviv);
        }
        {
            Flight vilnius = new Flight();
            vilnius.setId(idGenerator.incrementAndGet());
            vilnius.setDate("2017-03-21");
            vilnius.setTime("06:30");
            vilnius.setFlightNumber("FR 1502");
            vilnius.setCompany("Ryanair");
            vilnius.setStatus(Flight.Status.LATE);
            flights.add(vilnius);
        }
        {
            Flight talin = new Flight();
            talin.setId(idGenerator.incrementAndGet());
            talin.setDate("2017-03-19");
            talin.setTime("02:30");
            talin.setFlightNumber("LO 8353");
            talin.setCompany("Turkish Airlines");
            talin.setStatus(Flight.Status.WAITING);
            flights.add(talin);
        }

        for (int i = 0; i < 15; i++) {
            Flight random = new Flight();
            random.setId(idGenerator.incrementAndGet());
            random.setDate("2017-03-19");
            random.setTime("02:30");
            random.setFlightNumber("LO" + i);
            random.setCompany("Turkish Airlines");
            random.setStatus(Flight.Status.WAITING);
            flights.add(random);
        }
    }

    @GetMapping("/api/flights")
    @ApiOperation(value = "Returns all flights that are currently in the list")
    public List<Flight> getFlights() {
        return flights;
    }

    @DeleteMapping("/api/flights/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flights.removeIf(f -> f.getId().equals(id));
    }

}
