package bg.sofia.uni.fmi.mjt.order.server.repository;

import bg.sofia.uni.fmi.mjt.order.server.Response;
import bg.sofia.uni.fmi.mjt.order.server.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MJTOrderRepository implements OrderRepository {

    private static final int INVALID_ORDER_ID = -1;
    private static final int INITIAL_ID_VALUE = 1;
    private int currentOrderId;
    private final List<Order> orders;

    public MJTOrderRepository() {
        orders = new ArrayList<>();
        currentOrderId = INITIAL_ID_VALUE;
    }

    @Override
    public Response request(String size, String color, String destination) {
        //TODO: implement
        return null;
    }

    @Override
    public Response getOrderById(int id) {
        return orders.stream()
                .filter(o -> o.id() == id)
                .findFirst()
                .map(value -> Response.ok(List.of(value)))
                .orElseGet(() -> Response.notFound(id));
    }

    @Override
    public Response getAllOrders() {
        return Response.ok(orders);
    }

    @Override
    public Response getAllSuccessfulOrders() {
        return Response.ok(
                orders.stream()
                        .filter(o -> o.id() != INVALID_ORDER_ID)
                        .toList()
        );
    }

}
