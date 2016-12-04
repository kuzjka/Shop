package ua.kiev.prog.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ua.kiev.prog.model.*;
import ua.kiev.prog.service.DeviceService;
import ua.kiev.prog.service.UserService;


import java.util.*;
/**
 * Controller for site navigation
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/user"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("carts", deviceService.listCarts(findUser()));
        model.addAttribute("items", deviceService.totalItems(findUser()));
        return "index";
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
        User user = userService.findUser(username);
        return user;
    }
}










