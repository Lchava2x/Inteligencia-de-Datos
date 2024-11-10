import java.util.*;

/**
 * 
 * @author carlosandres.mendez
 */
public class Discrete_PSO_Swarm {
    /** Best fitness so far (global best) */
    double bestFitness;
    /** Best position so far (global best) */
    List<Allocation> bestPosition;
    /** Fitness function for this swarm */
	Discrete_FitnessFunction fitnessFunction;
    /** Particle update strategy */
	Discrete_ParticleUpdate particleUpdate;

    private ArrayList<Discrete_Particle> particles;
    
    List<ContainerHost> hosts;
    List<ContainerVm> vms;
    List<Container> containers;

 
    /**
	 * Create a Swarm and set default values
	 * @param numberOfParticles : Number of particles in this swarm (should be greater than 0). 
	 * If unsure about this parameter, try Swarm.DEFAULT_NUMBER_OF_PARTICLES or greater
	 * @param fitnessFunction : Fitness function used to evaluate each particle
	 */
    public Discrete_PSO_Swarm(Discrete_FitnessFunction fitnessFunction) {
        this.fitnessFunction = fitnessFunction;

        // Set up particle update strategy (default: ParticleUpdateSimple) 
	particleUpdate = new Discrete_ParticleUpdate();
        
        bestFitness = Double.NaN;
    }

    /**
	 * Initialize every particle
	 * Warning: maxPosition[], minPosition[], maxVelocity[], minVelocity[] must be initialized and setted
	 */
	public void init() {
            
            particles = new ArrayList<>();
            hosts = new ArrayList<>();
            vms = new ArrayList<>();
            containers = new ArrayList<>();
            
            ContainerHost servidor1 = new ContainerHost();
            servidor1.setId(1);
            hosts.add(servidor1);
            
            ContainerHost servidor2 = new ContainerHost();
            servidor1.setId(2);
            hosts.add(servidor2);
            
            ContainerHost servidor3 = new ContainerHost();
            servidor1.setId(3);
            hosts.add(servidor3);
            
            ContainerVm vm1 = new ContainerVm();
            vm1.setId(1);
            vm1.setRam(4096);
            vm1.setCost(10);
            vms.add(vm1);
            
            ContainerVm vm2 = new ContainerVm();
            vm2.setId(2);
            vm2.setRam(1024);
            vm2.setCost(110);
            vms.add(vm2);
            
            ContainerVm vm3 = new ContainerVm();
            vm3.setId(3);
            vm3.setRam(1024*2);
            vm3.setCost(30);
            vms.add(vm3);
            
            ContainerVm vm4 = new ContainerVm();
            vm4.setId(4);
            vm4.setRam(1024*3);
            vm4.setCost(20);
            vms.add(vm4);
            
            Container c1 = new Container();
            c1.setId(1);
            containers.add(c1);
            
            Container c2 = new Container();
            c2.setId(2);
            containers.add(c2);
            
            Container c3 = new Container();
            c3.setId(3);
            containers.add(c3);
            
            Container c4 = new Container();
            c4.setId(4);
            containers.add(c4);
            
            Container c5 = new Container();
            c5.setId(5);
            containers.add(c5);
            
            Container c6 = new Container();
            c6.setId(6);
            containers.add(c6);
            
            
            Discrete_Particle particula1 = new Discrete_Particle();
            Allocation a111 = new Allocation(c1, vm1, servidor1);
            Allocation a211 = new Allocation(c2, vm1, servidor1);
            Allocation a322 = new Allocation(c3, vm2, servidor2);
            Allocation a432 = new Allocation(c4, vm3, servidor2);
            Allocation a543 = new Allocation(c5, vm4, servidor3);
            Allocation a643 = new Allocation(c6, vm4, servidor3);
            
            particula1.getPosition().add(a111);
            particula1.getPosition().add(a211);
            particula1.getPosition().add(a322);
            particula1.getPosition().add(a432);
            particula1.getPosition().add(a543);
            particula1.getPosition().add(a643);
            
            
            particles.add(particula1);
                
                
            Discrete_Particle particula2 = new Discrete_Particle();
            Allocation b113 = new Allocation(c1, vm1, servidor3);
            Allocation b213 = new Allocation(c2, vm1, servidor3);
            Allocation b433 = new Allocation(c4, vm3, servidor3);
            Allocation b341 = new Allocation(c3, vm4, servidor1);
            Allocation b541 = new Allocation(c5, vm4, servidor1);
            Allocation b641 = new Allocation(c6, vm4, servidor1);
            
            particula2.getPosition().add(b113);
            particula2.getPosition().add(b213);
            particula2.getPosition().add(b433);
            particula2.getPosition().add(b341);
            particula2.getPosition().add(b541);
            particula2.getPosition().add(b641);
            
            
            particles.add(particula2);    
                
                
                
                
                

		//Creamos una particula con la posicion actual del datacenter
//		List<Allocation> position = new ArrayList<>();
//		List<Allocation> velocity = new ArrayList<>();
//
//		if(position.size()>0)
//			particles.add(new Discrete_Particle(position, velocity));


	}

    /**
	 * Evaluate fitness function for every particle 
	 * Warning: particles[] must be initialized and fitnessFunction must be set
	 */
	public void evaluate() {
		if (particles == null) throw new RuntimeException("No particles in this swarm! May be you need to call Swarm.init() method");
		if (fitnessFunction == null) throw new RuntimeException("No fitness function in this swarm! May be you need to call Discrete_PSO_Swarm.setFitnessFunction() method");

		// Initialize
		if (Double.isNaN(bestFitness)) {
			bestFitness = (fitnessFunction.isMaximize() ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
			bestPosition = null;
		}

		//---
		// Evaluate each particle (and find the 'best' one)
		//---
		for (Discrete_Particle particle : particles) {
			// Evaluate particle
			double fit = fitnessFunction.evaluate(particle);
                        
			// Update 'best global' position
			if (fitnessFunction.isBetterThan(bestFitness, fit)) {
				bestFitness = fit; // Copy best fitness, index, and position vector
				if (bestPosition == null) 
                                    bestPosition = new ArrayList<>();
				particle.copyPosition(bestPosition);
			}

		}
	}

    /**
	 * Make an iteration: 
	 * 	- evaluates the swarm 
	 * 	- updates positions and velocities
	 * 	- applies positions and velocities constraints 
	 */
	public void evolve() {
		// Initialize (if not already done)
		if (particles == null) init();

		evaluate(); // Evaluate particles
		update(); // Update positions and velocities
	}

    	/**
	 * Update every particle's position and velocity, also apply position and velocity constraints (if any)
	 * Warning: Particles must be already evaluated
	 */
	public void update() {

            // For each particle...
            for (Discrete_Particle particle : particles) {
                // Update particle's position and speed
                // Apply position and velocity constraints
                particleUpdate.update(this, particle);
            }

	}

    public double getBestFitness() {
        return bestFitness;
    }

    public void setBestFitness(double bestFitness) {
        this.bestFitness = bestFitness;
    }

    public List<Allocation> getBestPosition() {
        return bestPosition;
    }

    public void setBestPosition(List<Allocation> bestPosition) {
        this.bestPosition = bestPosition;
    }

    public Discrete_FitnessFunction getFitnessFunction() {
        return fitnessFunction;
    }

    public void setFitnessFunction(Discrete_FitnessFunction fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }

    public Discrete_ParticleUpdate getParticleUpdate() {
        return particleUpdate;
    }

    public void setParticleUpdate(Discrete_ParticleUpdate particleUpdate) {
        this.particleUpdate = particleUpdate;
    }

    public ArrayList<Discrete_Particle> getParticles() {
        return particles;
    }

    public void setParticles(ArrayList<Discrete_Particle> particles) {
        this.particles = particles;
    }

    public List<ContainerHost> getHosts() {
        return hosts;
    }

    public void setHosts(List<ContainerHost> hosts) {
        this.hosts = hosts;
    }

    public List<ContainerVm> getVms() {
        return vms;
    }

    public void setVms(List<ContainerVm> vms) {
        this.vms = vms;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }
    
    
}
