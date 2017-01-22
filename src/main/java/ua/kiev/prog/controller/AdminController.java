package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.kiev.prog.model.Device;
import ua.kiev.prog.model.Photo;
import ua.kiev.prog.model.Type;
import ua.kiev.prog.service.DeviceService;

import java.io.IOException;
import java.util.List;

/**
 * Controller for site administration
 */

@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "/admin")
    public String index_admin(Model model) {

        model.addAttribute("types", deviceService.listTypes());
        model.addAttribute("devices", deviceService.listDevices());
        return "index_admin";
    }

    @RequestMapping("/device_add_page")

    public String deviceAddPage(Model model) {
        model.addAttribute("devices", deviceService.listDevices());
        model.addAttribute("types", deviceService.listTypes());
        return "device_add_page";
    }

    @RequestMapping("/type_add_page")
    public String typeAddPage() {
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
        model.addAttribute("devices", deviceService.listDevices());
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
        List<Device> list = deviceService.listDevicesByType(type);
        int count = 0;
        for (Device dev : list) {
            if (dev.getName().equalsIgnoreCase(name)) {
                count++;
            }

        }
        if (count == 0) {
            deviceService.addDevice(device);
            Device d = deviceService.findDeviceByName(name);

            deviceService.addPhoto(new Photo(d, main_photo.getOriginalFilename(), main_photo.getBytes()));

            deviceService.addPhoto(new Photo(d, photo2.getOriginalFilename(), photo2.getBytes()));

            deviceService.addPhoto(new Photo(d, photo3.getOriginalFilename(), photo3.getBytes()));

            deviceService.addPhoto(new Photo(d, photo4.getOriginalFilename(), photo4.getBytes()));


            model.addAttribute("devices", deviceService.listDevices());
            return "index_admin";
        }
        if (count > 0) {
            model.addAttribute("message", "such device already exists!");
            model.addAttribute("state", "alert alert-danger");
            return "device_add_page";

        }
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

        model.addAttribute("devices", deviceService.listDevices());
        return "index_admin";


    }

    @RequestMapping(value = "/addtype", method = RequestMethod.POST)
    public String addType(@RequestParam String name, Model model) {

        List<Type> list = deviceService.listTypes();
        Type type = new Type(name);
        int count = 0;
        for (Type t : list) {
            if (t.getName().equalsIgnoreCase(name)) ;
            count++;
        }
        if (count > 0) {
            model.addAttribute("message", "such type already exists!");
            model.addAttribute("state", "alert alert-danger");
            return "type_add_page";
        }
        if (count == 0)
            model.addAttribute("types", deviceService.listTypes());

        model.addAttribute("devices", deviceService.listDevices());
        return "index_admin";
    }
}
