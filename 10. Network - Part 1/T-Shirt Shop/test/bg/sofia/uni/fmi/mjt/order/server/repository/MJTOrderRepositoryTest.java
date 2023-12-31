package bg.sofia.uni.fmi.mjt.order.server.repository;

import bg.sofia.uni.fmi.mjt.order.server.Response;
import bg.sofia.uni.fmi.mjt.order.server.destination.Destination;
import bg.sofia.uni.fmi.mjt.order.server.order.Order;
import bg.sofia.uni.fmi.mjt.order.server.tshirt.Color;
import bg.sofia.uni.fmi.mjt.order.server.tshirt.Size;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(OrderAnnotation.class)
public class MJTOrderRepositoryTest {

    private static OrderRepository orderRepository;

    @BeforeAll
    static void setUp() {
        orderRepository = new MJTOrderRepository();
        orderRepository.request("M", "RED", "EUROPE");
        orderRepository.request("L", "BLACK", "AUSTRALIA");
        orderRepository.request("XL", "WHITE", "NORTH_AMERICA");
        orderRepository.request("invalid", "invalid", "invalid");
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void testGetAllOrders() {
        Response response = orderRepository.getAllOrders();
        assertEquals(4, response.orders().size());
        assertEquals(Response.Status.OK, response.status());
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void testGetAllSuccessfulOrders() {
        Response response = orderRepository.getAllSuccessfulOrders();
        assertEquals(3, response.orders().size());
        assertEquals(Response.Status.OK, response.status());
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void testRequestWithInvalidParameters() {
        Response response = orderRepository.request(
                "anotherInvalid",
                "anotherInvalid",
                "anotherInvalid"
        );

        assertEquals(5, orderRepository.getAllOrders().orders().size());
        assertEquals(3, orderRepository.getAllSuccessfulOrders().orders().size());
        assertEquals(Response.Status.DECLINED, response.status());
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void testRequestWithValidParameters() {
        Response response = orderRepository.request("S", "WHITE", "AUSTRALIA");

        assertEquals(Response.Status.CREATED, response.status());
        assertEquals(6, orderRepository.getAllOrders().orders().size());
        assertEquals(4, orderRepository.getAllSuccessfulOrders().orders().size());

        Optional<Order> createdOrder = orderRepository.getOrderById(4).orders().stream()
                .findFirst();

        assertTrue(createdOrder.isPresent());
        assertEquals(Size.S, createdOrder.get().tShirt().size());
        assertEquals(Color.WHITE, createdOrder.get().tShirt().color());
        assertEquals(Destination.AUSTRALIA, createdOrder.get().destination());
    }

    @Test
    void testRequestWithNullParametersThrowsIllegalArgumentException() {
        String errorMessage = "request() should throw IllegalArgumentException if size or color or destination is null!";
        assertThrows(IllegalArgumentException.class,
                () -> orderRepository.request(null, null, "Europe"), errorMessage);

        assertThrows(IllegalArgumentException.class,
                () -> orderRepository.request("M", "RED", null), errorMessage);
    }

    @Test
    void testGetOrderByIdReturnsNotFoundForNonExistingId() {
        assertEquals(Response.Status.NOT_FOUND, orderRepository.getOrderById(12).status());
    }

    @Test
    void testGetOrderByIdReturnsCorrectOrder() {
        Optional<Order> order = orderRepository.getOrderById(1).orders().stream()
                .findFirst();

        assertTrue(order.isPresent());
        assertEquals(Destination.EUROPE, order.get().destination());
        assertEquals(Color.RED, order.get().tShirt().color());
        assertEquals(Size.M, order.get().tShirt().size());
    }

    @Test
    void testGetOrderByNonPositiveIdThrowsIllegalArgumentException() {
        String errorMessage = "getOrderById() should throw IllegalArgumentException if id is non-positive!";
        assertThrows(IllegalArgumentException.class,
                () -> orderRepository.getOrderById(0), errorMessage);

        assertThrows(IllegalArgumentException.class,
                () -> orderRepository.getOrderById(-2), errorMessage);
    }

}
