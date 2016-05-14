package ua.kiev.prog.Controller;

import com.mysql.jdbc.Security;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.kiev.prog.Classes.*;
import ua.kiev.prog.Config.SecurityConfiguration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping("/")
public class MyController {


    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = {"/", "/user"}, method = RequestMethod.GET)
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = deviceService.findUser(username);
        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("devices", deviceService.listDevices("all"));
        model.addAttribute("carts", deviceService.listCarts(user));
        model.addAttribute("items", deviceService.items(user));
        return "index";

    }

    @RequestMapping(value = "/login_page", method = RequestMethod.GET)
    public String login() {

        return "login_page";
    }

    @RequestMapping("/403")
    public String denied(Model model) {
        model.addAttribute("message", "Access denied");
        return "login_page";
    }

    @RequestMapping("/photo/{type}")
    public String type(Model model, @PathVariable String type) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = deviceService.findUser(username);
        List<Device> l = deviceService.listDevices(type);
        Random rn = new Random();
        Device d1 = l.get(rn.nextInt(l.size()));
        Device d2 = l.get(rn.nextInt(l.size()));
        Device d3 = l.get(rn.nextInt(l.size()));
        Device d4 = l.get(rn.nextInt(l.size()));
        model.addAttribute("d1", d1);
        model.addAttribute("d2", d2);
        model.addAttribute("d3", d3);
        model.addAttribute("d4", d4);

        List<Cart> l1 = deviceService.listCarts(user);
        List<Device> l2 = new ArrayList<>();
        for (Cart c : l1) {
            l2.add(c.getDevice());
        }

        model.addAttribute("devices", l2);
        model.addAttribute("items", deviceService.items(user));
        model.addAttribute("type", type);

        return "photos";
    }

    @RequestMapping("/onedevice/{id}")
    public String oneDevice(Model model, @PathVariable int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = deviceService.findUser(username);
        Device d = deviceService.findDevice(id);
        model.addAttribute("id", id);
        model.addAttribute("name", d.getName());
        List<Cart> l1 = deviceService.listCarts(user);
        List<Device> l2 = new ArrayList<>();
        for (Cart c : l1) {
            l2.add(c.getDevice());
        }
        model.addAttribute("devices", l2);
        return "one_device_page";
    }

    @RequestMapping(value = "/{type}/name_filter", method = RequestMethod.GET)
    public String nameFilter(@RequestParam String name, @PathVariable String type, Model model) {
        model.addAttribute("devices", deviceService.searchDevices(type, name));
        if (type.equals("all")) {
            return "index";
        } else {
            return type;
        }
    }

    @RequestMapping(value = "/{type}/price_filter", method = RequestMethod.GET)
    public String priceFilter(@RequestParam(required = false, defaultValue = "0") int min,
                              @RequestParam(required = false, defaultValue = "-1") int max,
                              @RequestParam String dir, @PathVariable String type, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = deviceService.findUser(username);
        model.addAttribute("devices", deviceService.priceFilter(type, min, max, dir));
        model.addAttribute("items", deviceService.items(user));
        if (type.equals("all")) {
            return "index";
        } else {
            return type;
        }
    }

    @RequestMapping(value = "/{type}/ram_proc_filter", method = RequestMethod.GET)
    public String ramProcFilter(@RequestParam(value = "proc", required = false) String[] sproc,
                                @RequestParam(value = "ram", required = false) String[] sram,
                                @PathVariable String type, Model model) {
        List<String> proc = new ArrayList<>();
        List<Integer> ram = new ArrayList<>();
        if (sproc != null) {
            for (String p : sproc) {
                if (p != null) {
                    proc.add(p);
                }
            }
        }
        if (sram != null) {
            for (String r : sram) {
                if (r != null) {
                    ram.add(Integer.parseInt(r));
                }
            }
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = deviceService.findUser(username);
        model.addAttribute("items", deviceService.items(user));
        model.addAttribute("devices", deviceService.ramProcFilter(type, ram, proc));
        if (type.equals("all")) {
            return "index";
        } else {
            return type;
        }

    }

    @RequestMapping("/{type}/{manufacturer}/manufacturer_filter")
    public String manufacturerFilter(Model model, @PathVariable String type, @PathVariable String manufacturer) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = deviceService.findUser(username);
        model.addAttribute("items", deviceService.items(user));
        model.addAttribute("devices", deviceService.manufacturerFilter(type, manufacturer));
        return "smartphone";
    }

    @RequestMapping("/photo_add_page")
    public String photoAddPage(Model model) {
        model.addAttribute("devices", deviceService.listDevices("all"));
        return "photo_add_page";
    }

    @RequestMapping(value = "/addphoto", method = RequestMethod.POST)
    public String addPhoto(@RequestParam(value = "device") int id,
                           @RequestParam(value = "photo") MultipartFile photo, Model model) throws IOException {

        Device d = deviceService.findDevice(id);
        deviceService.addPhoto(new Photo(d, photo.getOriginalFilename(), photo.getBytes()));
        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("devices", deviceService.listDevices("all"));
        return "index_admin";
    }

    @RequestMapping("/register_page")

    public String register_page() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam String role, @RequestParam String username,
                           @RequestParam String password1, @RequestParam
                           String password2, Model model) {

        if (password1.equals(password2)) {
            deviceService.addUser(new User(username, password2, true));
            deviceService.addRole(new Role(username, role));
            model.addAttribute("message", "registration success!");
            return "register";
        } else {
            model.addAttribute("message", "passwords are not matching");
            return "register";
        }
    }

    @RequestMapping(value = "/admin")
    public String index_admin(Model model) {

        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("devices", deviceService.listDevices("all"));
        return "index_admin";
    }

    @RequestMapping("/device_add_page")

    public String contactAddPage(Model model) {
        model.addAttribute("types", deviceService.listTypes());
        return "device_add_page";
    }

    @RequestMapping("/type_add_page")
    public String groupAddPage() {
        return "type_add_page";
    }

    @RequestMapping(value = "/type/{type}", method = RequestMethod.GET)
    public String searchByType(@PathVariable String type, Model model) {
        model.addAttribute("devices", deviceService.listDevices(type));
        if (type.equals("all")) {
            return "index";
        } else {
            return type;
        }
    }

    @RequestMapping(value = "/device/delete")
    public String search(@RequestParam(value = "todelete[]") String[] todelete, Model model) {
        for (String d : todelete) {
            if (d != null) {
                deviceService.deleteDevice(Integer.parseInt(d));
            }
        }
        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("devices", deviceService.listDevices("all"));
        return "index_admin";
    }

    @RequestMapping(value = "/adddevice", method = RequestMethod.POST)
    public String deviceAdd(@RequestParam(value = "type") int id,
                            @RequestParam String name,
                            @RequestParam String manufacturer,
                            @RequestParam int price,
                            @RequestParam(value = "ram", required = false, defaultValue = "-1") String ram,
                            @RequestParam(value = "processor", required = false) String processor,
                            Model model) {
        Type type = deviceService.findType(id);
        Device device = new Device(type, name, manufacturer, price, Integer.parseInt(ram), processor);
        deviceService.addDevice(device);
        model.addAttribute("devices", deviceService.listDevices("all"));
        return "index_admin";
    }

    @RequestMapping(value = "/addtype", method = RequestMethod.POST)
    public String groupAdd(@RequestParam String name, Model model) {
        deviceService.addType(new Type(name));

        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("devices", deviceService.listDevices("all"));
        return "index_admin";
    }

    @RequestMapping(value = "/{id}/{n}", method = RequestMethod.GET)
    public String toCart(@PathVariable int id, @PathVariable int n, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = deviceService.findUser(username);
        Device device = deviceService.findDevice(id);
        Cart cart = new Cart(user, device, 1);
        List<Cart> l = deviceService.listCarts(user);
        int count = 1;
        for (Cart c : l) {
            if (c.getDevice().getId() == id) {
                count = c.getItems() + n;
                c.setItems(count);
                if (count >= 1) {
                    deviceService.addCart(c);
                }
            }
        }
        if (count == 1 && n == 1) {
            deviceService.addCart(cart);
        }
        model.addAttribute("carts", deviceService.listCarts(user));
        model.addAttribute("total", deviceService.totalPrice(user));
        return "cart_add_page";
    }

    @RequestMapping(value = "/cart_add_page", method = RequestMethod.GET)
    public String cart(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = deviceService.findUser(username);
        model.addAttribute("items", deviceService.items(user));
        model.addAttribute("carts", deviceService.listCarts(user));
        model.addAttribute("total", deviceService.totalPrice(user));
        return "cart_add_page";
    }

    @RequestMapping(value = "/addorder", method = RequestMethod.POST)
    public String orderAdd(
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String phone,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = deviceService.findUser(username);
        List<Cart> carts = deviceService.listCarts(user);
        for (Cart c : carts) {
            Order order = new Order(user, name, address, phone, c);
            deviceService.addOrder(order);
        }

        model.addAttribute("items", deviceService.items(user));
        model.addAttribute("orders", deviceService.listOrders(user));
        model.addAttribute("total", deviceService.totalPrice(user));
        return "result_page";


    }

    @RequestMapping("/result_page")
    public String result(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = deviceService.findUser(username);
        model.addAttribute("items", deviceService.items(user));
        model.addAttribute("orders", deviceService.listOrders(user));
        model.addAttribute("total", deviceService.totalPrice(user));
        return "result_page";
    }

    @RequestMapping(value = "/order_add_page", method = RequestMethod.GET)
    public String order(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = deviceService.findUser(username);
        model.addAttribute("items", deviceService.items(user));
        model.addAttribute("carts", deviceService.listCarts(user));

        return "order_add_page";
    }

    @RequestMapping(value = "/cart/delete/{id}")
    public String deleteCart(@PathVariable int id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = deviceService.findUser(username);
        deviceService.deleteCart(id);
        model.addAttribute("items", deviceService.items(user));
        model.addAttribute("carts", deviceService.listCarts(user));
        model.addAttribute("total", deviceService.totalPrice(user));
        return "cart_add_page";


    }

    @RequestMapping(value = "/photo/{id}/{n}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> onPhoto(@PathVariable(value = "id") int id, @PathVariable int n) {
        Device d = deviceService.findDevice(id);
        List<Photo> l = deviceService.getPhoto(d);
        Photo p = l.get(n);
        return ResponseEntity.ok(p.getBody());
    }
}










