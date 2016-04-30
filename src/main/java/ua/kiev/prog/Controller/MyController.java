package ua.kiev.prog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.kiev.prog.Classes.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/")
public class MyController {


    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {






        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("devices", deviceService.listDevices("all"));

        model.addAttribute("carts", deviceService.listCarts());
        return "index";

    }
@RequestMapping("/login_page")
    public String login(){
        return "login_page";}



    @RequestMapping("/photo/{type}")
    public String type(Model model, @PathVariable String type) {

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

        List<Cart>l1=deviceService.listCarts();
        List<Device>l2=new ArrayList<>();
        for(Cart c:l1){
            l2.add(c.getDevice());
        }

        model.addAttribute("devices", l2);

        model.addAttribute("type", type);

        return "photos";
    }
    @RequestMapping("/onedevice/{id}")
    public String oneDevice(Model model, @PathVariable int id){
        Device d= deviceService.findDevice(id);

        model.addAttribute("id" , id );
        model.addAttribute("name", d.getName());
        List<Cart>l1=deviceService.listCarts();
        List<Device>l2=new ArrayList<>();
        for(Cart c:l1){
            l2.add(c.getDevice());
        }

        model.addAttribute("devices", l2);
        return "one_device_page";
    }


    @RequestMapping(value = "/filter1", method = RequestMethod.POST)
    public String ramFilter(HttpServletRequest request,    Model model) {
        String[] sram = request.getParameterValues("ram");
        String [] sproc = request.getParameterValues("proc");

        List<Integer> ram = new ArrayList<>();
        List<String> proc = new ArrayList<>();
        if(sram!=null){
        for (String s : sram)

                ram.add(Integer.parseInt(s));}else{
            ram.add(2);
            ram.add(4);
            ram.add(8);
            ram.add(16);

        }

        if(sproc!=null)
            for(String p :sproc){

                proc.add(p);
            }else{
            proc.add("i3");
            proc.add("i5");
            proc.add("i7");

        }
            if(sproc!=null)
            model.addAttribute("processors", proc);
            if(sram!=null)
            model.addAttribute("rams", ram);
            model.addAttribute("devices", deviceService.filter(ram, proc));
        return "desctops";}

    @RequestMapping("/filter2/{type}")
    public String desctopFilter(Model model, @PathVariable String type){

            List<Integer> ram = new ArrayList<>();
        List<String> proc = new ArrayList<>();
        if(type.equals("budget")){
            ram.add(2);
            ram.add(4);
            proc.add("i3");}



         if(type.equals("job")){
        ram.add(4);
            ram.add(8);
            proc.add("i3");
            proc.add("i5");}




         if(type.equals("gamer")){
            ram.add(8);
            ram.add(16);
            proc.add("i5");
            proc.add("i7");}
            model.addAttribute("type" , type);
            model.addAttribute("devices", deviceService.filter(ram, proc));
            return "desctops";


        }
    @RequestMapping("/filter3/{manufactuter}")
    public String mobileFilter(Model model, @PathVariable String manufacturer){
        model.addAttribute("devices", deviceService.listByManufacturer(manufacturer));
        return "smartphones";
    }


    @RequestMapping("/photo_add_page")
    public String photoAddPage(Model model){
        model.addAttribute("devices", deviceService.listDevices("all"));
        return "photo_add_page";
    }

    @RequestMapping(value = "/addphoto" , method = RequestMethod.POST)
    public String addPhoto(@RequestParam (value = "device") int id,
                           @RequestParam(value = "photo") MultipartFile photo, Model model) throws IOException {

        Device d= deviceService.findDevice(id);
        deviceService.addPhoto(new Photo(d, photo.getOriginalFilename(), photo.getBytes()));
        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("devices", deviceService.listDevices("all"));
        return "index_admin";
    }

    @RequestMapping("/register_page")

    public String register_page(){
        return "register";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String register(@RequestParam String username, @RequestParam String password1, @RequestParam
    String password2,Model model){
        String role="USER";
        if(password1.equals(password2)){
        deviceService.addUser(new User(username, password2, true));
        deviceService.addRole(new Role(username,role));
        model.addAttribute("message", "registration success!");
        return "register";}else{
            model.addAttribute("message", "passwords are not matching");
            return "register";
        }
    }


    @RequestMapping(value = "/admin" )
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


    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String pattern, Model model) {
        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("devices", deviceService.searchDevices(pattern));
        return "index_admin";
    }

    @RequestMapping(value = "/type/{type}", method = RequestMethod.GET)
    public String searchByType(@PathVariable String type, Model model) {



            model.addAttribute("devices", deviceService.listDevices(type));
        if(type.equals("desctop")){
            return "desctops";}
        else {return "index";}}




    @RequestMapping(value = "/device/delete", method = RequestMethod.GET)
    public String search(HttpServletRequest request, Model model) {

        String []del = request.getParameterValues("todelete[]");
        for(String d: del){
           deviceService.deleteDevice(Integer.parseInt(d));
        }
        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("devices", deviceService.listDevices(null));
        return "index_admin";
    }

    @RequestMapping(value = "/adddevice", method = RequestMethod.POST)
    public String contactAdd(@RequestParam(value = "type") int sid,
                             @RequestParam String name,
                             @RequestParam String manufacturer,
                             @RequestParam int price,
                             @RequestParam (required = false)int ram,
                             @RequestParam (required = false)String processor,


                             Model model)  {

    Type  type= deviceService.findType(sid);


        Device device = new Device(type, name, manufacturer, price, ram, processor);
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

    @RequestMapping(value = "/addorder", method = RequestMethod.POST)
    public String orderAdd( HttpServletRequest request, @RequestParam String name,
                           @RequestParam String address,
                           @RequestParam String phone,


                           Model model) {
        String[] sid=  request.getParameterValues("cart[]");
        for(String s:sid){
            Cart cart = deviceService.findCart(Integer.parseInt(s));
            Order order = new Order (name, address, phone, cart );
            deviceService.addOrder(order);
        }




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
    }@RequestMapping(value = "/device/{id}/{n}", method = RequestMethod.GET)
    public void onPhoto(HttpServletRequest request, HttpServletResponse response, @PathVariable int id,
                        @PathVariable int n){
        Device d= deviceService.findDevice(id);
        List <Photo>p=deviceService.getPhoto(d);


        byte res[] = p.get(n).getBody();


        response.setContentType("image/jpg");
        try {
            response.getOutputStream().write(res);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}










