import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ua.kiev.prog.dao.CartDAO;
import ua.kiev.prog.dao.DeviceDAO;
import ua.kiev.prog.model.Cart;
import ua.kiev.prog.model.Device;
import ua.kiev.prog.model.Type;
import ua.kiev.prog.model.User;
import ua.kiev.prog.service.OrderService;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    @InjectMocks
    OrderService service;

    @Mock
    CartDAO cartDAO;

    @Mock
    DeviceDAO deviceDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    Type type1 = new Type("Desktop");
    Type type2 = new Type("Smartphone");
    User anton = new User("Anton", "Momot", true);


    Device device1 = new Device(type1, "Everest Game", "Everest", 25000, 8, "i7");
    Device device2 = new Device(type2, "iPhone7", "Apple", 30000, -1, null);
    Cart cart1 = new Cart(anton, device1, 2);
    Cart cart2 = new Cart(anton, device2, 1);
    List<Cart> list = new ArrayList<>();

    @Test
    public void listCartsTest() {
        list.add(cart1);
        list.add(cart2);
        when(cartDAO.list(anton)).thenReturn(list);
        Assert.assertEquals(service.listCarts(anton), list);
        verify(cartDAO).list(anton);

    }

    @Test
    public void totalItemsTest() {
        list.add(cart1);
        list.add(cart2);
        Long items = 3l;
        when(deviceDAO.totalItems(anton)).thenReturn(items);
        Assert.assertEquals(service.totalItems(anton), items);
        verify(deviceDAO).totalItems(anton);
    }

    @Test
    public void totalPriceTest() {
        when(deviceDAO.totalPrice(anton)).thenReturn(80000);
        Assert.assertEquals(service.totalPrice(anton), 80000);
        verify(deviceDAO).totalPrice(anton);
    }


}
