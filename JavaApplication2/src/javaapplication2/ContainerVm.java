
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlos
 */
public class ContainerVm {
    /**
     * The size.
     */
    private long size;
    
    /**
     * The cost.
     */
    private long cost;

    /**
     * The MIPS.
     */
    private double mips;

    /**
     * The number of PEs.
     */
    @SuppressWarnings("unused")
	private int numberOfPes;

    /**
     * The ram.
     */
    @SuppressWarnings("unused")
	private float ram;

    /**
     * The vmm.
     */
    private String vmm;

    /**
     * The host.
     */
    private ContainerHost host;

    /**
     * In migration flag.
     */
    private boolean inMigration;

    /**
     * The current allocated ram.
     */
    private float currentAllocatedRam;

    /**
     * The current allocated mips.
     */
    private List<Double> currentAllocatedMips;

    /**
     * The VM is being instantiated.
     */
    private boolean beingInstantiated;

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
    private final List<? extends Container> containerList = new ArrayList<>();


    /**
     * Tells whether this machine is working properly or has failed.
     */
    private boolean failed;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public double getMips() {
        return mips;
    }

    public void setMips(double mips) {
        this.mips = mips;
    }

    public int getNumberOfPes() {
        return numberOfPes;
    }

    public void setNumberOfPes(int numberOfPes) {
        this.numberOfPes = numberOfPes;
    }

    public float getRam() {
        return ram;
    }

    public void setRam(float ram) {
        this.ram = ram;
    }

    public String getVmm() {
        return vmm;
    }

    public void setVmm(String vmm) {
        this.vmm = vmm;
    }

    public ContainerHost getHost() {
        return host;
    }

    public void setHost(ContainerHost host) {
        this.host = host;
    }

    public boolean isInMigration() {
        return inMigration;
    }

    public void setInMigration(boolean inMigration) {
        this.inMigration = inMigration;
    }

    public float getCurrentAllocatedRam() {
        return currentAllocatedRam;
    }

    public void setCurrentAllocatedRam(float currentAllocatedRam) {
        this.currentAllocatedRam = currentAllocatedRam;
    }

    public List<Double> getCurrentAllocatedMips() {
        return currentAllocatedMips;
    }

    public void setCurrentAllocatedMips(List<Double> currentAllocatedMips) {
        this.currentAllocatedMips = currentAllocatedMips;
    }

    public boolean isBeingInstantiated() {
        return beingInstantiated;
    }

    public void setBeingInstantiated(boolean beingInstantiated) {
        this.beingInstantiated = beingInstantiated;
    }

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

    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "ContainerVm{" + "id=" + id + '}';
    }
    
    
}
