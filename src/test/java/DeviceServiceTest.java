import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ua.kiev.prog.dao.DeviceDAO;
import ua.kiev.prog.model.Device;
import ua.kiev.prog.model.Type;
import ua.kiev.prog.service.DeviceService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeviceServiceTest {
    @InjectMocks
    private DeviceService service;

    @Mock
    private DeviceDAO dao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void listDevicesByTypeTest() {
        Type smartphone = new Type("Smartphone");
        Device iphone = new Device(smartphone, "iPhone7", "Apple", 30099, -1, null);
        Device samsung = new Device(smartphone, "Galaxy S7", "Samsung", 22999, -1, null);
        List<Device> list = new ArrayList<>();
        list.add(iphone);
        list.add(samsung);
        when(dao.typeFilter(smartphone.getName(), "asc")).thenReturn(list);
        Assert.assertEquals(service.listDevicesByType(smartphone.getName(), "asc"), list);
        verify(dao).typeFilter(smartphone.getName(), "asc");
    }
}
