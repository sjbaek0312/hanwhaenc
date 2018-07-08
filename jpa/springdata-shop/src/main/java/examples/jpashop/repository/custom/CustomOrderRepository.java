package examples.jpashop.repository.custom;

import examples.jpashop.domain.Order;
import examples.jpashop.domain.OrderSearch;

import java.util.List;

/**
 * @author holyeye
 */
public interface CustomOrderRepository {

    public List<Order> search(OrderSearch orderSearch);

}
