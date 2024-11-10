import java.util.List;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

/**
 * Particle update strategy with resource-aware allocation.
 * 
 * This implementation considers the current resource load on VMs 
 * and prefers less-loaded VMs for allocation, aiming to improve resource utilization.
 * 
 */
public class Discrete_ParticleUpdate {

    private final double COGNITIVE_COEFFICIENT = 0.5;
    private final double SOCIAL_COEFFICIENT = 0.5;
    private final double INERTIA_WEIGHT = 0.7;
    public Random random = new Random();
    // Mapa de utilización de CPU de las VMs
    public Map<ContainerVm, Double> vmCpuUtilization = new HashMap<>();

    public Discrete_ParticleUpdate() {
    }

    /** Update particle's velocity and position with resource balancing */
    public void update(Discrete_PSO_Swarm swarm, Discrete_Particle particle) {
        List<Allocation> position = particle.getPosition();
        List<Allocation> velocity = particle.getVelocity();
        List<Allocation> personalBestPosition = particle.getBestPosition();
        List<Allocation> globalBestPosition = swarm.getBestPosition();

        for (int i = 0; i < position.size(); i++) {
            Allocation currentAllocation = position.get(i);
            Allocation velocityAllocation = velocity.get(i);
            Allocation personalBestAllocation = personalBestPosition.get(i);
            Allocation globalBestAllocation = globalBestPosition.get(i);

            // Retrieve the current VM and Host for this allocation
            ContainerVm currentVm = currentAllocation.getVm();
            ContainerHost currentHost = currentAllocation.getHost();

            // Get the least loaded VM based on resource usage (e.g., CPU utilization)
            ContainerVm leastLoadedVm = getLeastLoadedVm(swarm.getVms());

            // Inertia: Keep some of the current allocation
            if (random.nextDouble() < INERTIA_WEIGHT) {
                velocityAllocation.setVm(currentVm);
                velocityAllocation.setHost(currentHost);
            }

            // Cognitive component: Move towards personal best
            if (random.nextDouble() < COGNITIVE_COEFFICIENT) {
                velocityAllocation.setVm(personalBestAllocation.getVm());
                velocityAllocation.setHost(personalBestAllocation.getHost());
            }

            // Social component: Move towards global best
            if (random.nextDouble() < SOCIAL_COEFFICIENT) {
                velocityAllocation.setVm(globalBestAllocation.getVm());
                velocityAllocation.setHost(globalBestAllocation.getHost());
            }

            // Finally, use the least loaded VM to balance the load
            velocityAllocation.setVm(leastLoadedVm);

            // Update position with new allocation
            Allocation newAllocation = new Allocation(currentAllocation.getContainer(), leastLoadedVm, currentHost);
            position.set(i, newAllocation);
        }

        // Update particle's position and velocity
        particle.setPosition(position);
        particle.setVelocity(velocity);
    }

    // Metodo para obtener la VM con menos carga
    public ContainerVm getLeastLoadedVm(List<ContainerVm> vms) {
        ContainerVm leastLoadedVm = null;
        double minLoad = Double.MAX_VALUE;

        for (ContainerVm vm : vms) {
            // Usar el mapa de utilizaciones de CPU
            double load = vmCpuUtilization.getOrDefault(vm, 0.0); // Obtén la carga o 0.0 si no está definida
            if (load < minLoad) {
                minLoad = load;
                leastLoadedVm = vm;
            }
        }
        return leastLoadedVm;
    }

    // Establece la utilización de CPU de una VM
    public void setVmCpuUtilization(ContainerVm vm, double utilization) {
        vmCpuUtilization.put(vm, utilization);
    }
}


