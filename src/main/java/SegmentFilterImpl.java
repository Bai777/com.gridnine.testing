import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SegmentFilterImpl implements SegmentFilter {
    @Override
    public void showAllSegments(List<Flight> flights) {
        for (Flight flight :
                flights) {
            System.out.println("Flight segments - " + flight.getSegments());
        }
    }

    @Override
    public Set<Flight> getFlightUpToTheCurrentPointInTime(List<Flight> flights) {
        LocalDateTime timeNow = LocalDateTime.now();
        Set<Flight> result = new HashSet<>();
        List<Segment> split = new ArrayList<>();
        for (Flight flight : flights) {
            split.addAll(flight.getSegments());
            while (split.size() > 0) {
                LocalDateTime departureDateTime = (split.get(0).getDepartureDate());
                LocalDateTime arrivalDateTime = (split.remove(0).getArrivalDate());
                if (departureDateTime.isAfter(timeNow)) {
                    showFLight(flight, departureDateTime, arrivalDateTime);
                    result.add(flight);
                }
            }
        }
        return result;
    }

    @Override
    public Set<Flight> getSegmentsWithArrivalDateEarlierThanDepartureDate(List<Flight> flights) {

        Set<Flight> result = new HashSet<>();
        List<Segment> split = new ArrayList<>();
        for (Flight flight : flights ) {
            split.addAll(flight.getSegments());

            while (split.size() > 0) {
                LocalDateTime departureDateTime = split.get(0).getDepartureDate();
                LocalDateTime arrivalDateTime = split.remove(0).getArrivalDate();
                if(arrivalDateTime.isBefore(departureDateTime)) {
                    showFLight(flight, departureDateTime, arrivalDateTime);
                    result.add(flight);
                }
            }

        }
        return result;
    }

    @Override
    public Set<Flight> getFlightsWhoseTotalTimeSpentOnTheGroundExceedsTwoHours(List<Flight> flights) {
        Set<Flight> result = new HashSet<>();
        List<Segment> split = new ArrayList<>();
        for (Flight flight : flights ) {
            split.addAll(flight.getSegments());
            if (split.size() > 2) {
                while (split.size() > 2) {
                    LocalDateTime arrivalDateTime = split.remove(1).getArrivalDate();
                    LocalDateTime departureDateTime = split.remove(1).getDepartureDate();
                    if(departureDateTime.isAfter(arrivalDateTime.plusHours(2))) {
                        showTransfer(flight, arrivalDateTime, departureDateTime);
                        result.add(flight);
                    }
                }
            }
        }
        return result;
    }

    private void showFLight(Flight flight, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime) {
        DateTimeFormatter dateTimeFormatter = getDateFormatter();
        System.out.println( "Flight segments - " + flight.getSegments()  + "\n" + "Departure date-time: " + dateTimeFormatter.format(departureDateTime) + "\n" + "Arrival date-time: " + dateTimeFormatter.format(arrivalDateTime) );
        System.out.println("---------------------------------------------------------------------------");
    }

    private void showTransfer(Flight flight, LocalDateTime arrivalDateTime, LocalDateTime departureDateTime) {
        DateTimeFormatter dateTimeFormatter = getDateFormatter();
        System.out.println( "Flight segments - " + flight.getSegments()  + "\n" + "Arrival date-time: " + dateTimeFormatter.format(arrivalDateTime) + "\n" + "Departure date-time: " + dateTimeFormatter.format(departureDateTime)  );
        System.out.println("---------------------------------------------------------------------------");
    }

    private DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    }
}
