package de.erdlet.preview.mvc.service;

import java.util.UUID;

import de.erdlet.preview.mvc.fixtures.BookingFixtures;
import de.erdlet.preview.mvc.repository.BookingRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BookingServiceTest {

    private BookingRepository dummyRepository;
    private BookingService systemUnderTest;

    @Before
    public void setUp() {
        dummyRepository = new BookingRepository();
        systemUnderTest = new BookingService();
        systemUnderTest.setBookingRepository(dummyRepository);
    }

    @Test
    public void getAllBookingsReturnsEmpyListWhenNoBookingsAreStored() {
        assertTrue(systemUnderTest.getAllBookings().isEmpty());
    }

    @Test
    public void getAllBookingsReturnsBookingsOrderedAlphabeticallyByName() {
        final var bookingAugMuc = BookingFixtures.getActiveBookingAugMuc();
        final var bookingMucAug = BookingFixtures.getActiveBookingMucAug();
        final var bookingZurMuc = BookingFixtures.getCancelledBookingZurMuc();

        dummyRepository.add(bookingMucAug);
        dummyRepository.add(bookingAugMuc);
        dummyRepository.add(bookingZurMuc);

        final var bookings = systemUnderTest.getAllBookings();

        Assert.assertEquals(bookingAugMuc, bookings.get(0));
        Assert.assertEquals(bookingMucAug, bookings.get(1));
        Assert.assertEquals(bookingZurMuc, bookings.get(2));
    }

    @Test
    public void findBookingByBookingNumberReturnsEmptyResultWhenNoBookingIsFound() {
        assertTrue(systemUnderTest.findBookingForBookingNumber(UUID.randomUUID()).isEmpty());
    }

    @Test
    public void findBookingByBookingNumberReturnsBookingWhenItIsFound() {
        final var booking = BookingFixtures.getActiveBookingMucAug();
        dummyRepository.add(booking);

        final var result = systemUnderTest.findBookingForBookingNumber(booking.getBookingNumber());

        Assert.assertEquals(booking, result.get());
    }

    @Test
    public void createNewBookingStoresBookingInRepository() {
        final var booking = systemUnderTest.createNewBooking("MUC -> PAF");

        Assert.assertEquals(booking, dummyRepository.getByBookingNumber(booking.getBookingNumber()).get());
    }

    @Test
    public void createNewBookingReturnsBookingWithCorrectValues() {
        final String bookingName = "MUC -> PAF";
        final var createdBooking = systemUnderTest.createNewBooking(bookingName);

        assertNotNull(createdBooking.getBookingNumber());
        Assert.assertEquals(bookingName, createdBooking.getName());
        assertFalse(createdBooking.isCancelled());
    }

    @Test
    public void cancelBookingUpdatesBookingInTheStore() {
        final var booking = BookingFixtures.getActiveBookingAugMuc();
        dummyRepository.add(booking);

        systemUnderTest.cancelBooking(booking.getBookingNumber());

        final var updatedBooking = dummyRepository.getByBookingNumber(booking.getBookingNumber());

        assertTrue(updatedBooking.get().isCancelled());
    }

    @Test
    public void cancelBookingUpdatesBookingValuesCorrect() {
        final var booking = BookingFixtures.getActiveBookingAugMuc();
        dummyRepository.add(booking);

        systemUnderTest.cancelBooking(booking.getBookingNumber());

        final var updatedBooking = dummyRepository.getByBookingNumber(booking.getBookingNumber()).get();

        assertTrue(updatedBooking.isCancelled());
    }

    @Test
    public void cancelBookingReturnsTrueAfterUpdate() {
        final var booking = BookingFixtures.getActiveBookingAugMuc();
        dummyRepository.add(booking);

        assertTrue(systemUnderTest.cancelBooking(booking.getBookingNumber()));
    }

    @Test
    public void cancelBookingReturnsFalseWhenNoBookingIsFound() {
        assertFalse(systemUnderTest.cancelBooking(UUID.randomUUID()));
    }
}