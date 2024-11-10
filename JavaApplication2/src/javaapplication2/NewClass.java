/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carlos
 */
public class NewClass {
    public  static void main(String[] args){
        
        Discrete_FitnessFunction funcionAdaptacion = new Discrete_FitnessFunction();
        Discrete_PSO_Swarm swarm = new Discrete_PSO_Swarm(funcionAdaptacion);
        swarm.init();
        
        for(int i =0; i<10; i++){
            swarm.evolve();
        }
        
        //Mostrar mejor solucion 
        for(Allocation asignacion : swarm.getBestPosition())
            System.out.println(asignacion);
    }
}
