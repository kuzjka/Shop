package ua.kiev.prog.model;


import javax.persistence.*;

@Entity
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    private String name;
    private String address;
    private String phone;
    @ManyToOne
    @JoinColumn(name="cart_id")

    private Cart cart;

    public Order() {
    }

    public Order(User user, String name, String address, String phone, Cart cart) {
        this.user = user;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
