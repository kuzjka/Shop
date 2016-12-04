package ua.kiev.prog.model;

import javax.persistence.*;

@Entity
@Table(name = "Photos")
public class Photo {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;
    private String name;
    private byte[]body;

    /**
     * Class constructor (default).
     */
    public Photo() {
    }

    /**
     * Class constructor with parameters.
     */
    public Photo(Device device, String name, byte[] body) {
        this.device = device;
        this.name = name;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
