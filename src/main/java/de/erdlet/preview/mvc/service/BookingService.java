package de.erdlet.preview.mvc.service;

import de.erdlet.preview.mvc.model.Booking;
import de.erdlet.preview.mvc.repository.BookingRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Stateless
public class BookingService {

    @Inject
    private BookingRepository bookingRepository;

    /**
     * Return all {@link Booking}s found in the application.
     *
     * @return the found {@link Booking}s in alphabetical order
     */
    public List<Booking> getAllBookings() {
        return bookingRepository.getAll();
    }

    /**
     * Find a single {@link Booking} by its unique booking number.
     *
     * @param bookingNumber the unique booking number of the {@link Booking}
     * @return optional the {@link Booking} or an empty result
     */
    public Optional<Booking> findBookingForBookingNumber(final UUID bookingNumber) {
        return bookingRepository.getByBookingNumber(bookingNumber);
    }

    /**
     * Create a new {@link Booking} with the provided name. For simplicity of this example, the name of a booking
     * is not checked for duplication.
     *
     * @param name the name of the {@link Booking}
     * @return the created {@link Booking}
     */
    public Booking createNewBooking(final String name) {
        final var booking = new Booking(name);
        bookingRepository.add(booking);

        return booking;
    }

    /**
     * Cancel an existing {@link Booking}.
     *
     * @param bookingNumber the booking number of the {@link Booking} to cancel
     * @return true in case the {@link Booking} exists and got cancelled, false if the booking doesn't exist
     */
    public boolean cancelBooking(final UUID bookingNumber) {
        final var optBooking = bookingRepository.getByBookingNumber(bookingNumber);

        var cancelled = false;
        if (optBooking.isPresent()) {
            final var booking = optBooking.get();
            booking.cancel();

            cancelled = bookingRepository.update(booking);
        }

        return cancelled;
    }

    public void setBookingRepository(final BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
}
