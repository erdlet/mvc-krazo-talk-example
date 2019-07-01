package de.erdlet.preview.mvc.fixtures;

import java.util.UUID;

import de.erdlet.preview.mvc.model.Booking;

public class BookingFixtures {

    private static final Booking ACTIVE_BOOKING_MUC_AUG = new Booking(UUID.randomUUID(), "MUC -> AUG", Booking.Status.ACTIVE);
    private static final Booking ACTIVE_BOOKING_AUG_MUC = new Booking(UUID.randomUUID(), "AUG -> MUC", Booking.Status.ACTIVE);
    private static final Booking CANCELLED_BOOKING_ZUR_MUC = new Booking(UUID.randomUUID(), "ZUR -> MUC", Booking.Status.CANCELLED);

    public static Booking getActiveBookingMucAug() {
        return new Booking(ACTIVE_BOOKING_MUC_AUG.getBookingNumber(), ACTIVE_BOOKING_MUC_AUG.getName(), ACTIVE_BOOKING_MUC_AUG.getStatus());
    }

    public static Booking getActiveBookingAugMuc() {
        return new Booking(ACTIVE_BOOKING_AUG_MUC.getBookingNumber(), ACTIVE_BOOKING_AUG_MUC.getName(), ACTIVE_BOOKING_AUG_MUC.getStatus());
    }

    public static Booking getCancelledBookingZurMuc() {
        return new Booking(CANCELLED_BOOKING_ZUR_MUC.getBookingNumber(), CANCELLED_BOOKING_ZUR_MUC.getName(), CANCELLED_BOOKING_ZUR_MUC.getStatus());
    }
}
