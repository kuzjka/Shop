package ua.kiev.prog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class MyController {
    static final int DEFAULT_TYPE_ID = -1;

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {


        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("devices", deviceService.listDevices(null));


        return "index";

    }
    @RequestMapping("/onedevice")
    public String oneDevice(Model model){
        model.addAttribute("photos", deviceService.getPhoto(null));
        return "one_device_page";
    }

    @RequestMapping("/photo_add_page")
    public String photoAddPage(Model model){
        model.addAttribute("devices", deviceService.listDevices(null));
        return "photo_add_page";
    }

    @RequestMapping(value = "/addphoto" , method = RequestMethod.POST)
    public String addPhoto(@RequestParam (value="device")int id, @RequestParam String name,
                           @RequestParam(value = "photo") MultipartFile photo, Model model) throws IOException {

        Device d= deviceService.findDevice(id);
        deviceService.addPhoto(new Photo(d, photo.getOriginalFilename(), photo.getBytes()));
        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("devices", deviceService.listDevices(null));
        return "index_admin";
    }

    @RequestMapping("/register_page")

    public String register_page(){
        return "register";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String register(@RequestParam String user, @RequestParam String password, Model model){
        String role="USER";
        deviceService.addUser(new User(user, password, true));
        deviceService.addRole(new Role(user,role));
        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("devices", deviceService.listDevices(null));
        return "index";
    }
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String user_index () {





        return "cart_add_page";
    }

    @RequestMapping(value = "/admin" )
    public String index_admin(Model model) {

        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("devices", deviceService.listDevices(null));


        return "index_admin";
    }


    @RequestMapping("/device_add_page/")

    public String contactAddPage(Model model) {
        model.addAttribute("types", deviceService.listTypes());
        return "device_add_page";
    }


    @RequestMapping("/type_add_page")
    public String groupAddPage() {
        return "type_add_page";
    }


    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String pattern, Model model) {
        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("devices", deviceService.searchDevices(pattern));
        return "index_admin";
    }

    @RequestMapping(value = "/searchByType/{type}", method = RequestMethod.GET)
    public String searchByType(@PathVariable String type, Model model) {


        model.addAttribute("types", deviceService.listTypes());
        if (type.equals("all")) {
            model.addAttribute("devices", deviceService.listDevices(null));
        } else {
            model.addAttribute("devices", deviceService.listDevices(type));
        }
        return "index_admin";
    }


    @RequestMapping(value = "/device/delete/{id}", method = RequestMethod.GET)
    public String search(@PathVariable(value = "id") int toDelete, Model model) {

        deviceService.deleteDevice(toDelete);

        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("devices", deviceService.listDevices(null));
        return "index_admin";
    }

    @RequestMapping(value = "/device/add", method = RequestMethod.POST)
    public String contactAdd(@RequestParam(value = "type") int typeId,
                             @RequestParam String name,
                             @RequestParam String manufactor,
                             @RequestParam int price,


                             Model model) throws IOException {
        Type type = (typeId != DEFAULT_TYPE_ID) ? deviceService.findType(typeId) : null;





        Device device = new Device(type,  name, manufactor, price);
        deviceService.addDevice(device);

        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("devices", deviceService.listDevices(null));
        return "index_admin";
    }

    @RequestMapping(value = "/type/add", method = RequestMethod.POST)
    public String groupAdd(@RequestParam String name, Model model) {
        deviceService.addType(new Type(name));

        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("devices", deviceService.listDevices(null));
        return "index_admin";
    }

    @RequestMapping(value = "/{id}/{n}", method = RequestMethod.GET)
    public String toCart(@PathVariable int id, @PathVariable int n, Model model) {

        Device device = deviceService.findDevice(id);

        Cart cart = new Cart(device, 1);
        List<Cart> l = deviceService.listCarts();
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
        model.addAttribute("carts", deviceService.listCarts());
        model.addAttribute("total", deviceService.totalPrice());
        return "cart_add_page";
    }

    @RequestMapping(value = "/cart_add_page", method = RequestMethod.GET)
    public String cart(Model model) {
        model.addAttribute("carts", deviceService.listCarts());
        model.addAttribute("total", deviceService.totalPrice());
        return "cart_add_page";
    }

    @RequestMapping(value = "/orderAdd", method = RequestMethod.POST)
    public String orderAdd(@RequestParam String name,
                           @RequestParam String address,
                           @RequestParam String phone,
                           @RequestParam(value = "cart") int cartId,

                           Model model) {
        Cart cart = deviceService.findCart(cartId);

        Order order = new Order(name, address, phone, cart);
        deviceService.addOrder(order);
        model.addAttribute("carts", deviceService.listCarts());
        model.addAttribute("orders", deviceService.listOrders());
        model.addAttribute("total", deviceService.totalPrice());
        return "result_page";


    }

    @RequestMapping(value = "/order_add_page")
    public String order(Model model) {
        model.addAttribute("carts", deviceService.listCarts());
        return "order_add_page";
    }

    @RequestMapping(value = "/cart/delete/{id}")
    public String deleteCart(@PathVariable int id, Model model) {
        deviceService.deleteCart(id);
        model.addAttribute("carts", deviceService.listCarts());
        model.addAttribute("total", deviceService.totalPrice());
        return "cart_add_page";

    }@RequestMapping(value="/result_page")
    public String result (Model model){
        model.addAttribute("orders", deviceService.listOrders());
        return "result_page";
    }@RequestMapping(value = "/device/{id}", method = RequestMethod.GET)
    public void onPhoto(HttpServletRequest request, HttpServletResponse response, @PathVariable int id){
        Device d= deviceService.findDevice(id);
        List <Photo>p=deviceService.getPhoto(d);

        byte [] res=p.get(0).getBody();

        response.setContentType("image/jpg");
        try {
            response.getOutputStream().write(res);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}







