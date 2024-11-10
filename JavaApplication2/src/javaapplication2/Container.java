/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carlos
 */
public class Container {
    /**
     * The id.
     */
    private int id;

    /**
     * The storage.
     */
    private long storage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStorage() {
        return storage;
    }

    public void setStorage(long storage) {
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "Container{" + "id=" + id + '}';
    }
    
    
}
