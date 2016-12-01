package ua.kiev.prog.Controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.kiev.prog.Classes.*;

import java.io.IOException;

import java.util.*;

@Controller
@RequestMapping("/")
public class MyController {


    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = {"/", "/user"}, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("types", deviceService.listTypes());

        model.addAttribute("carts", deviceService.listCarts(findUser()));
        model.addAttribute("items", deviceService.totalItems(findUser()));
        return "index";


    }

    @RequestMapping(value = "/login_page", method = RequestMethod.GET)
    public String login(Model model, @RequestParam(required = false) String error) {
        if (error != null) {
            model.addAttribute("message", "Wrong login and password!");
            model.addAttribute("state", "alert alert-danger");
        }
        return "login_page";
    }


    @RequestMapping("/403")
    public String denied(Model model) {
        model.addAttribute("message", "Access denied");
        model.addAttribute("state", "alert alert-danger");
        return "login_page";
    }


    @RequestMapping(value = "/onedevice/{id}", method = RequestMethod.GET)
    public String oneDevice(@PathVariable int id, Model model) {
        Device d = deviceService.findDeviceById(id);
        model.addAttribute("d", d);
        model.addAttribute("c", deviceService.listCarts(findUser()));
        model.addAttribute("items", deviceService.totalItems(findUser()));

        return "one_device_page";
    }

    @RequestMapping(value = "/type/{id}", method = RequestMethod.GET)
    public String nameFilter(@PathVariable int id,
                             Model model) {


        Type type = deviceService.findType(id);

        model.addAttribute("devices", deviceService.listDevices(type));
        model.addAttribute("carts", deviceService.listCarts(findUser()));
        model.addAttribute("items", deviceService.totalItems(findUser()));
        return type.getName().toLowerCase();
    }


    @RequestMapping(value = "/name_filter/{type}", method = RequestMethod.GET)
    public String nameFilter(@PathVariable String type,
                             @RequestParam String name, Model model) {
        model.addAttribute("devices", deviceService.searchDevices(type, name));
        model.addAttribute("items", deviceService.totalItems(findUser()));
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
        model.addAttribute("items", deviceService.totalItems(findUser()));

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
        model.addAttribute("items", deviceService.totalItems(findUser()));
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
        model.addAttribute("items", deviceService.totalItems(findUser()));
        model.addAttribute("devices", deviceService.ramFilter(type, rams, processors));
        if (type.equals("all")) {
            return "index";
        } else {
            return type;
        }
    }

    List<String> manufacturers = new ArrayList<>();

    @RequestMapping("/manufacturer_filter/{type}/{manufacturer}")
    public String manufacturerFilter(Model model, @PathVariable String type,
                                     @PathVariable String manufacturer) {
        if (!manufacturers.contains(manufacturer)) {
            manufacturers.add(manufacturer);

        } else {
            manufacturers.remove(manufacturer);
        }
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("items", deviceService.totalItems(findUser()));
        model.addAttribute("devices", deviceService.manufacturerFilter(type, manufacturers));
        return "smartphone";
    }

    @RequestMapping("/photo_add_page")
    public String photoAddPage(Model model) {
        model.addAttribute("devices", deviceService.listDevices(null));
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
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password2);
            deviceService.addUser(new User(username, hashedPassword, true));
            deviceService.addRole(new Role(username, role));
            model.addAttribute("message", "registration success!");
            model.addAttribute("state", "alert alert-success");

        }
        if (users.size() > 0) {
            model.addAttribute("message", "user already exists!");
            model.addAttribute("state", "alert alert-danger");

        }
        if (!password1.equals(password2)) {
            model.addAttribute("message", "password are not matching");
            model.addAttribute("state", "alert alert-danger");

        }
        return "register";
    }


    @RequestMapping(value = "/admin")
    public String index_admin(Model model) {

        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("devices", deviceService.listDevices(null));
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
        model.addAttribute("devices", deviceService.listDevices(null));
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
        Device d = deviceService.findDeviceByName(name);
        deviceService.addPhoto(new Photo(d, main_photo.getOriginalFilename(), main_photo.getBytes()));
        deviceService.addPhoto(new Photo(d, photo2.getOriginalFilename(), photo2.getBytes()));
        deviceService.addPhoto(new Photo(d, photo3.getOriginalFilename(), photo3.getBytes()));
        deviceService.addPhoto(new Photo(d, photo4.getOriginalFilename(), photo4.getBytes()));


        model.addAttribute("devices", deviceService.listDevices(null));
        return "index_admin";
    }

    @RequestMapping(value = "/edit_device_page/{id}", method = RequestMethod.GET)
    public String editDevicePage(@PathVariable int id, Model model) {
        Device device = deviceService.findDeviceById(id);
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
        Device device = deviceService.findDeviceById(id);
        device.setName(name);
        device.setManufacturer(manufacturer);
        device.setPrice(price);
        deviceService.addDevice(device);
        model.addAttribute("devices", deviceService.listDevices(null));
        return "index_admin";


    }

    @RequestMapping(value = "/addtype", method = RequestMethod.POST)
    public String addType(@RequestParam String name, Model model) {


        deviceService.addType(new Type(name));

        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("devices", deviceService.listDevices(null));
        return "index_admin";
    }

    @RequestMapping(value = "/tocart/{id}/{n}", method = RequestMethod.GET)
    public String toCart(@PathVariable int id, @PathVariable int n, Model model) {
        Device device = deviceService.findDeviceById(id);
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
        model.addAttribute("items", deviceService.totalItems(findUser()));
        model.addAttribute("carts", deviceService.listCarts(findUser()));
        model.addAttribute("total", deviceService.totalPrice(findUser()));
        return "cart_add_page";
    }

    @RequestMapping(value = "/cart_add_page", method = RequestMethod.GET)
    public String cart(
            Model model) {


        model.addAttribute("carts", deviceService.listCarts(findUser()));
        model.addAttribute("total", deviceService.totalPrice(findUser()));
        model.addAttribute("items", deviceService.totalItems(findUser()));
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

        model.addAttribute("items", deviceService.totalItems(findUser()));
        model.addAttribute("orders", deviceService.listOrders(findUser()));
        model.addAttribute("total", deviceService.totalPrice(findUser()));
        return "result_page";


    }

    @RequestMapping("/result_page")
    public String result(Model model) {
        model.addAttribute("items", deviceService.totalItems(findUser()));
        model.addAttribute("orders", deviceService.listOrders(findUser()));
        model.addAttribute("total", deviceService.totalPrice(findUser()));
        return "result_page";
    }

    @RequestMapping(value = "/order_add_page", method = RequestMethod.GET)
    public String order(Model model) {

        model.addAttribute("items", deviceService.totalItems(findUser()));
        model.addAttribute("carts", deviceService.listCarts(findUser()));

        return "order_add_page";
    }

    @RequestMapping(value = "/cart/delete/{id}")
    public String deleteCart(@PathVariable int id, Model model) {
        deviceService.deleteCart(id);
        model.addAttribute("items", deviceService.totalItems(findUser()));
        model.addAttribute("carts", deviceService.listCarts(findUser()));
        model.addAttribute("total", deviceService.totalPrice(findUser()));
        return "cart_add_page";


    }

    @RequestMapping(value = "/photo/{id}/{n}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> onPhoto(@PathVariable int id, @PathVariable int n) {
        Device d = deviceService.findDeviceById(id);


        List<Photo> photos = deviceService.getPhotos(d);

        Photo photo = photos.get(n);


        return ResponseEntity.ok(photo.getBody());
    }

    @RequestMapping(value = "/randomphoto/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> rnPhoto(@PathVariable int id) {

        Type type = deviceService.findType(id);
        List<Device> devices = deviceService.listDevices(type);
        Random random = new Random();
        Device device = devices.get(random.nextInt(devices.size()));
        List<Photo> photos = deviceService.getPhotos(device);
        Photo photo = photos.get(0);

        return ResponseEntity.ok(photo.getBody());
    }

    public User findUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = deviceService.findUser(username);
        return user;
    }


}










