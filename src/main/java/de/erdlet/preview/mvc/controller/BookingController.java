package de.erdlet.preview.mvc.controller;

import de.erdlet.preview.mvc.service.BookingService;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.binding.BindingResult;
import javax.mvc.binding.MvcBinding;
import javax.mvc.security.CsrfProtected;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.UUID;

@Path("/bookings")
@Controller
public class BookingController {

    @Inject
    private Models models;

    @Inject
    private BindingResult bindingResult;

    @Inject
    private BookingService bookingService;

    @GET
    public String showIndex() {
        final var bookings = bookingService.getAllBookings();
        models.put("bookings", bookings);

        return "index.jsp";
    }

    @GET
    @Path("/{bookingNumber}")
    public Response showDetailsOfBooking(@PathParam("bookingNumber") final UUID bookingNumber) {
        final var booking = bookingService.findBookingForBookingNumber(bookingNumber);

        if (booking.isPresent()) {
            models.put("booking", booking.get());
            return Response.ok("details.jsp")
                    .build();
        } else {
            models.put("bookingNumber", bookingNumber);
            return Response.status(Status.NOT_FOUND)
                    .entity("404.jsp")
                    .build();
        }
    }

//    ALTERNATIVE WITH @UriRef
//
//    @GET
//    @Path("/{bookingNumber}")
//    @UriRef("showBookingDetails")
//    public Response showDetailsOfBooking(@PathParam("bookingNumber") final UUID bookingNumber) {
//        final var booking = bookingService.findBookingForBookingNumber(bookingNumber);
//
//        if (booking.isPresent()) {
//            models.put("booking", booking.get());
//            return Response.ok("details.jsp")
//                    .build();
//        } else {
//            models.put("bookingNumber", bookingNumber);
//            return Response.status(Status.NOT_FOUND)
//                    .entity("404.jsp")
//                    .build();
//        }
//    }

    @GET
    @Path("/new")
    public String showNewBookingForm() {
        return "form.jsp";
    }

    @POST
    @CsrfProtected
    public String createNewBooking(@FormParam("name") @MvcBinding @NotBlank @Size(min = 5, max = 255) final String name) {
        if (bindingResult.isFailed()) {
            models.put("errors", new ArrayList<>(bindingResult.getAllErrors()));
            return "form.jsp";
        }

        final var booking = bookingService.createNewBooking(name);

        return "redirect:/bookings/" + booking.getBookingNumber();
    }

    @DELETE
    @Path("/{bookingNumber}")
    @CsrfProtected
    public String cancelBooking(@PathParam("bookingNumber") final UUID bookingNumber) {
        final var isCancelled = bookingService.cancelBooking(bookingNumber);

        return isCancelled ? "redirect:/bookings" : "404.jsp";
    }
}
