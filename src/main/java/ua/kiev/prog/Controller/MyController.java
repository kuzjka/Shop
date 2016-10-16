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
import java.lang.reflect.Method;
import java.util.*;

@Controller
@RequestMapping("/")
public class MyController {


    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = {"/", "/user"}, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("devices", deviceService.listDevices("all"));
        model.addAttribute("carts", deviceService.listCarts(findUser()));
        model.addAttribute("items", deviceService.items(findUser()));
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


    @RequestMapping("/onedevice/{id}")
    public String oneDevice(Model model, @PathVariable int id) {
        Device d = deviceService.findDevice(id);
        model.addAttribute("id", id);
        model.addAttribute("name", d.getName());
        List<Cart> l1 = deviceService.listCarts(findUser());
        List<Device> l2 = new ArrayList<>();
        for (Cart c : l1) {
            l2.add(c.getDevice());
        }
        model.addAttribute("devices", l2);
        return "one_device_page";
    }

    @RequestMapping(value = "/type/{type}", method = RequestMethod.GET)
    public String nameFilter(@PathVariable String type,
                             Model model) {


        if (type.equals("all")) {
            model.addAttribute("devices", deviceService.listDevices(type));
            return "index";
        } else {


            model.addAttribute("devices", deviceService.listDevices(type));

            return type;
        }
    }

    @RequestMapping(value = "/name_filter/{type}", method = RequestMethod.GET)
    public String nameFilter(@PathVariable String type,
                             @RequestParam String name, Model model) {
        model.addAttribute("devices", deviceService.searchDevices(type, name));
        model.addAttribute("items", deviceService.items(findUser()));
        if (type.equals("all")) {
            return "index";
        } else {
            return type;
        }
    }

    @RequestMapping(value = "/price_filter/{type}", method = RequestMethod.GET)
    public String priceFilter(@RequestParam(required = false, defaultValue = "0") int min,
                              @RequestParam(required = false, defaultValue = "-1") int max,
                              @RequestParam String dir, @PathVariable String type, Model model) {
        model.addAttribute("devices", deviceService.priceFilter(type, min, max, dir));
        model.addAttribute("items", deviceService.items(findUser()));

        if (type.equals("all")) {
            return "index";
        } else {
            return type;
        }
    }

    List<Integer> rams = new ArrayList<>();
    List<String> processors = new ArrayList<>();

    @RequestMapping(value = "/ram_filter/{type}/{sram}",
            method = RequestMethod.GET)

    public String ramFilter(@PathVariable String type,
                            @PathVariable String sram,

                            Model model
    ) {

        Integer ram = Integer.parseInt(sram);


        if (!rams.contains(ram)) {
            rams.add(ram);
        } else if (rams.contains(ram)) {
            rams.remove(ram);
        }


        model.addAttribute("rams", rams);
        model.addAttribute("processors", processors);
        model.addAttribute("items", deviceService.items(findUser()));
        model.addAttribute("devices", deviceService.ramFilter(type, rams, processors));
        if (type.equals("all")) {
            return "index";
        } else {
            return type;
        }
    }

    @RequestMapping(value = "/proc_filter/{type}/{proc}",
            method = RequestMethod.GET)

    public String processorFilter(@PathVariable String type,
                                  @PathVariable String proc,
                                  Model model
    ) {


        if (!processors.contains(proc)) {
            processors.add(proc);
        } else {
            processors.remove(proc);
        }

        model.addAttribute("rams", rams);
        model.addAttribute("processors", processors);
        model.addAttribute("items", deviceService.items(findUser()));
        model.addAttribute("devices", deviceService.ramFilter(type, rams, processors));
        if (type.equals("all")) {
            return "index";
        } else {
            return type;
        }
    }

    List<String> manufacturers = new ArrayList<>();

    @RequestMapping("manufacturer_filter/{type}/{manufacturer}")
    public String manufacturerFilter(Model model, @PathVariable String type,
                                     @PathVariable String manufacturer) {
        if (!manufacturers.contains(manufacturer)) {
            manufacturers.add(manufacturer);

        } else {
            manufacturers.remove(manufacturer);
        }
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("items", deviceService.items(findUser()));
        model.addAttribute("devices", deviceService.manufacturerFilter(type, manufacturers));
        return "smartphone";
    }

    @RequestMapping("/photo_add_page")
    public String photoAddPage(Model model) {
        model.addAttribute("devices", deviceService.listDevices("all"));
        return "photo_add_page";
    }


    @RequestMapping("/register_page")

    public String register_page() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam String role, @RequestParam String username,
                           @RequestParam String password1, @RequestParam
                           String password2, Model model) {
        List<User> users = deviceService.listUsers(username);
        if (users.size() == 0 && password1.equals(password2)) {
            deviceService.addUser(new User(username, password2, true));
            deviceService.addRole(new Role(username, role));
            model.addAttribute("message", "registration success!");

        }
        if (users.size() > 0) {
            model.addAttribute("message", "user already exists!");

        }
        if (!password1.equals(password2)) {
            model.addAttribute("message", "password are not matching");
        }
        return "register";
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
                            @RequestParam MultipartFile main_photo,
                            @RequestParam MultipartFile photo2,
                            @RequestParam MultipartFile photo3,
                            @RequestParam MultipartFile photo4,
                            @RequestParam String name,
                            @RequestParam String manufacturer,
                            @RequestParam int price,
                            @RequestParam(value = "ram", required = false, defaultValue = "-1") String ram,
                            @RequestParam(value = "processor", required = false) String processor,
                            Model model) throws IOException {
        Type type = deviceService.findType(id);


        Device device = new Device(type, name, manufacturer, price,
                Integer.parseInt(ram), processor);
        deviceService.addDevice(device);
        Device d = deviceService.findDevice2(name);
        deviceService.addPhoto(new Photo(d, main_photo.getOriginalFilename(), main_photo.getBytes()));
        deviceService.addPhoto(new Photo(d, photo2.getOriginalFilename(), photo2.getBytes()));
        deviceService.addPhoto(new Photo(d, photo3.getOriginalFilename(), photo3.getBytes()));
        deviceService.addPhoto(new Photo(d, photo4.getOriginalFilename(), photo4.getBytes()));


        model.addAttribute("devices", deviceService.listDevices("all"));
        return "index_admin";
    }

    @RequestMapping(value = "/edit_device_page/{id}", method = RequestMethod.GET)
    public String editDevicePage(@PathVariable int id, Model model) {
        Device device = deviceService.findDevice(id);
        model.addAttribute("type", device.getType().getId());
        model.addAttribute("device", device);
        return "edit_device_page";
    }

    @RequestMapping(value = "/edit_device/{id}", method = RequestMethod.POST)
    public String editDevice(@PathVariable int id,
                             @RequestParam String name,
                             @RequestParam String manufacturer,
                             @RequestParam int price,
                             Model model) {
        Device device = deviceService.findDevice(id);
        device.setName(name);
        device.setManufacturer(manufacturer);
        device.setPrice(price);
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
        Device device = deviceService.findDevice(id);
        Cart cart = new Cart(findUser(), device, 1);
        List<Cart> l = deviceService.listCarts(findUser());
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
        model.addAttribute("carts", deviceService.listCarts(findUser()));
        model.addAttribute("total", deviceService.totalPrice(findUser()));
        return "cart_add_page";
    }

    @RequestMapping(value = "/cart_add_page", method = RequestMethod.GET)
    public String cart(Model model) {
        model.addAttribute("items", deviceService.items(findUser()));
        model.addAttribute("carts", deviceService.listCarts(findUser()));
        model.addAttribute("total", deviceService.totalPrice(findUser()));
        return "cart_add_page";
    }

    @RequestMapping(value = "/addorder", method = RequestMethod.POST)
    public String orderAdd(
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String phone,
            Model model) {
        List<Cart> carts = deviceService.listCarts(findUser());
        for (Cart c : carts) {
            Order order = new Order(findUser(), name, address, phone, c);
            deviceService.addOrder(order);
        }

        model.addAttribute("items", deviceService.items(findUser()));
        model.addAttribute("orders", deviceService.listOrders(findUser()));
        model.addAttribute("total", deviceService.totalPrice(findUser()));
        return "result_page";


    }

    @RequestMapping("/result_page")
    public String result(Model model) {
        model.addAttribute("items", deviceService.items(findUser()));
        model.addAttribute("orders", deviceService.listOrders(findUser()));
        model.addAttribute("total", deviceService.totalPrice(findUser()));
        return "result_page";
    }

    @RequestMapping(value = "/order_add_page", method = RequestMethod.GET)
    public String order(Model model) {

        model.addAttribute("items", deviceService.items(findUser()));
        model.addAttribute("carts", deviceService.listCarts(findUser()));

        return "order_add_page";
    }

    @RequestMapping(value = "/cart/delete/{id}")
    public String deleteCart(@PathVariable int id, Model model) {
        deviceService.deleteCart(id);
        model.addAttribute("items", deviceService.items(findUser()));
        model.addAttribute("carts", deviceService.listCarts(findUser()));
        model.addAttribute("total", deviceService.totalPrice(findUser()));
        return "cart_add_page";


    }

    @RequestMapping(value = "/photo/{id}/{n}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> onPhoto(@PathVariable int id, @PathVariable int n) {
        Device d = deviceService.findDevice(id);

        List<Photo> photos = deviceService.getPhotos(d);
        Photo photo = photos.get(n);

        return ResponseEntity.ok(photo.getBody());
    }

    public User findUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = deviceService.findUser(username);
        return user;
    }


}










