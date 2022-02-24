import java.util.List;

public class Main {
    public static void main(String[] args) {
//        FlightBuilder flightBuilder = new FlightBuilder();
        List<Flight> flightList = FlightBuilder.createFlights();
        SegmentFilter segmentFilter = new SegmentFilterImpl();
        segmentFilter.showAllSegments(flightList);
        System.out.println("Filter number - 1. Segments with departure date is after than current time");
        System.out.println("---------------------------------------------------------------------------");
        segmentFilter.getFlightUpToTheCurrentPointInTime(flightList);
        System.out.println("Filter number - 2. Segments with arrival date is before than departure time");
        System.out.println("---------------------------------------------------------------------------");
        segmentFilter.getSegmentsWithArrivalDateEarlierThanDepartureDate(flightList);
        System.out.println("Filter number - 3. Transfer time is more than two hours");
        System.out.println("---------------------------------------------------------------------------");
        segmentFilter.getFlightsWhoseTotalTimeSpentOnTheGroundExceedsTwoHours(flightList);

    }
}
