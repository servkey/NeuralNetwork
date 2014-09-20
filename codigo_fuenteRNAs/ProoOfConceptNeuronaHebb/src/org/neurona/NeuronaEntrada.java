/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.neurona;
/**
 *
 * @author servkey
 */
public class NeuronaEntrada implements Neurona<Integer, Integer>{
    public static double[] pesos;

    private int entradas;
    private int salidas;
    private int totalSalida;
    private int deseado;
    private int salida;

    public NeuronaEntrada(double[] pesosIniciales, int totalSalida) {
        pesos =  pesosIniciales;
        this.totalSalida = totalSalida;
    }

   private int identidad(int value){
        return value;
    }

    private double net(){
        double net = 0;
        return entradas;
        
    }

    private int F(){
        return identidad((int)net());
    }

    public Integer process() {
        return F();
    }

    
    public Integer input(Integer input, int deseado) {
        this.entradas = input;
       // System.out.println("Entra-> " + deseado + " Salida de neurona de entrada-> " + entradas);
        return entradas;        
    }

    /**
     * @return the entradas
     */
    public int getEntradas() {
        return entradas;
    }

    /**
     * @param entradas the entradas to set
     */
    public void setEntradas(int entradas) {
        this.entradas = entradas;
    }
  
    /**
     * @return the deseado
     */
    public int getDeseado() {
        return deseado;
    }

    /**
     * @param deseado the deseado to set
     */
    public void setDeseado(int deseado) {
        this.deseado = deseado;
    }

    public Integer getSalida() {
      return this.salida;
    }

   }
