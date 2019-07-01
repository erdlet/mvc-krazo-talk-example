package de.erdlet.preview.mvc.model;

import java.util.Objects;
import java.util.UUID;

public final class Booking {

    private UUID bookingNumber;
    private String name;
    private Status status;

    public Booking() {
        //necessary to fulfill Java Bean Standard
    }

    public Booking(final String name) {
        this.bookingNumber = UUID.randomUUID();
        this.name = name;
        this.status = Status.ACTIVE;
    }

    public Booking(final UUID bookingNumber, final String name, final Status status) {
        this.bookingNumber = bookingNumber;
        this.name = name;
        this.status = status;
    }

    public UUID getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(final UUID bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public void cancel() {
        this.status = Status.CANCELLED;
    }

    public boolean isCancelled() {
        return this.status == Status.CANCELLED;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Booking booking = (Booking) o;
        return Objects.equals(bookingNumber, booking.bookingNumber) &&
                Objects.equals(name, booking.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingNumber, name);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingNumber=" + bookingNumber +
                ", name='" + name + '\'' +
                '}';
    }

    public enum Status {
        ACTIVE,
        CANCELLED
    }
}
