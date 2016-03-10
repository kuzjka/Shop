package ua.kiev.prog;


import java.util.List;

public interface CartDAO {
    void add(Cart cart);
    void delete(int id);
    List<Cart> list();
    Cart findOne(int id);


}
