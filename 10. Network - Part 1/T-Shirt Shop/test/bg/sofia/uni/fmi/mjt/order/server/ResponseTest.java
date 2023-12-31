package bg.sofia.uni.fmi.mjt.order.server;

import bg.sofia.uni.fmi.mjt.order.server.destination.Destination;
import bg.sofia.uni.fmi.mjt.order.server.order.Order;
import bg.sofia.uni.fmi.mjt.order.server.tshirt.Color;
import bg.sofia.uni.fmi.mjt.order.server.tshirt.Size;
import bg.sofia.uni.fmi.mjt.order.server.tshirt.TShirt;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ResponseTest {

    @Test
    void testResponseCreate() {
        Response response = Response.create(4);
        assertEquals(Response.Status.CREATED, response.status());
        assertEquals("ORDER_ID=4", response.additionalInfo());
        assertNull(response.orders());
    }

    @Test
    void testResponseOk() {
        Response response = Response.ok(List.of(
                new Order(1, new TShirt(Size.S, Color.RED), Destination.NORTH_AMERICA),
                new Order(2, new TShirt(Size.XL, Color.BLACK), Destination.EUROPE)
        ));

        assertEquals(Response.Status.OK, response.status());
        assertEquals(2, response.orders().size());
        assertEquals("Successfully retrieved 2 orders.", response.additionalInfo());
        assertEquals("{\"status\":\"OK\", \"additionalInfo\":\"Successfully retrieved 2 orders.\", "
                + "\"orders\":["
                + "{\"id\":1, \"tShirt\":{\"size\":\"S\", \"color\":\"RED\"}, "
                + "\"destination\":\"NORTH_AMERICA\"}, "
                + "{\"id\":2, \"tShirt\":{\"size\":\"XL\", \"color\":\"BLACK\"}, "
                + "\"destination\":\"EUROPE\"}]}", response.toString());
    }

    @Test
    void testResponseDecline() {
        Response response = Response.decline("Declined!");

        assertEquals(Response.Status.DECLINED, response.status());
        assertNull(response.orders());
        assertEquals("Declined!", response.additionalInfo());
    }

    @Test
    void testResponseNotFound() {
        Response response = Response.notFound(24);

        assertEquals(Response.Status.NOT_FOUND, response.status());
        assertNull(response.orders());
        assertEquals("Order with id = 24 does not exist.", response.additionalInfo());
    }

}
