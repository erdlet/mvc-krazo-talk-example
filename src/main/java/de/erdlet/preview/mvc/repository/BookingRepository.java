package de.erdlet.preview.mvc.repository;

import de.erdlet.preview.mvc.model.Booking;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class BookingRepository {

    private final Map<UUID, Booking> bookingStore = new HashMap<>();

    @PostConstruct
    public void init() {
        add(new Booking("Augsburg -> Munich"));
        add(new Booking("Munich -> Augsburg"));

        final var cancelledBookingOne = new Booking("Munich -> Berlin");
        cancelledBookingOne.cancel();
        add(cancelledBookingOne);

        final var cancelledBookingTwo = new Booking("Berlin -> Munich");
        cancelledBookingTwo.cancel();
        add(cancelledBookingTwo);
    }

    /**
     * Get all {@link Booking}s from the bookingStore. As no queries are supported, sort the result alphabetically by their name.
     *
     * @return the {@link Booking}s in an alphabetically sorted list
     */
    public List<Booking> getAll() {
        final List<Booking> bookings = new ArrayList<>(bookingStore.values());
        bookings.sort(Comparator.comparing(Booking::getName));

        return bookings;
    }

    public Optional<Booking> getByBookingNumber(final UUID bookingNumber) {
        return Optional.ofNullable(bookingStore.get(bookingNumber));
    }

    public void add(final Booking booking) {
        bookingStore.put(booking.getBookingNumber(), booking);
    }

    public boolean update(final Booking booking) {
        return bookingStore.replace(booking.getBookingNumber(), booking) != null;
    }
}
