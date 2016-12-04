
package ua.kiev.prog.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Types")
public class Type {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany(mappedBy="type", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Device> devices = new ArrayList<Device>();

    /**
     * Class constructor (default).
     */
    public Type() {}

    /**
     * Class constructor with parameters.
     */
    public Type(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}



