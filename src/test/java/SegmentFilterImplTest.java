import org.junit.jupiter.api.Test;

import java.util.List;

public class SegmentFilterImplTest {
    private final SegmentFilter segmentFilter = new SegmentFilterImpl();
    private final FlightBuilder flightBuilder = new FlightBuilderTest();

    private final List<Flight> flights = flightBuilder.createFlights();

    @Test
    public void getFlightUpToTheCurrentPointInTime(){
        assert flights != null;
        int size = segmentFilter.getFlightUpToTheCurrentPointInTime(flights).size();
        assert 5 == size;
    }

    @Test
    public void getSegmentsWithArrivalDateEarlierThanDepartureDate(){
        assert flights != null;
        int size = segmentFilter.getSegmentsWithArrivalDateEarlierThanDepartureDate(flights).size();
        assert 2 == size;
    }

    @Test
    public void getFlightsWhoseTotalTimeSpentOnTheGroundExceedsTwoHours(){
        assert flights != null;
        int size = segmentFilter.getFlightsWhoseTotalTimeSpentOnTheGroundExceedsTwoHours(flights).size();
        assert 2 == size;
    }

}
