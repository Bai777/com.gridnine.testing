import java.util.List;
import java.util.Set;

public interface SegmentFilter {
    void showAllSegments(List<Flight> flights);
    Set<Flight> getFlightUpToTheCurrentPointInTime(List<Flight> flights);
    Set<Flight> getSegmentsWithArrivalDateEarlierThanDepartureDate(List<Flight> flights);
    Set<Flight> getFlightsWhoseTotalTimeSpentOnTheGroundExceedsTwoHours(List<Flight> flights);
}

