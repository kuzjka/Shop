package ua.kiev.prog;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Devices")
public class Device {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;
    @OneToMany(mappedBy = "device" ,  cascade = CascadeType.ALL , fetch = FetchType.LAZY)

    private List<Cart> carts;
    @OneToOne
    @JoinColumn(name="id")
    private Photo photo;

    private String name;
    private String manufactor;
    private int price;




    public Device() {
    }

    public Device(Type type, Photo photo, String name, String manufactor, int price) {
        this.type = type;
        this.photo = photo;
        this.name = name;
        this.manufactor = manufactor;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufactor() {
        return manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

