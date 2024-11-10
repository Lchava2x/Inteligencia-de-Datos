
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carlos
 */
public class ContainerHost {
    /**
     * The id.
     */
    private int id;

    /**
     * The storage.
     */
    private long storage;


    /**
     * The vm list.
     */
    private final List<? extends ContainerVm> vmList = new ArrayList<>();

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
        return "ContainerHost{" + "id=" + id + '}';
    }
    
    
}
