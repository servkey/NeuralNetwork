/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.neurona;
/**
 *
 * @author servkey
 */
public class NeuronaSalidaDelta implements  Neurona<Double, int[]>{
    public  double[] pesos;
    private int[] entradas ;
    
    private int deseado;
    private double salida;
    private boolean iteracion = true;
    private static boolean modificarPesos = true;

    public NeuronaSalidaDelta(double[] pesosIniciales) {
        double[] pesos = new double[pesosIniciales.length];
        System.arraycopy(pesosIniciales, 0, pesos, 0, pesosIniciales.length);
        this.pesos = pesos;        
    }

    public double[] getPesos(){        
        return pesos;        
    }

    public void updatePesos(double[] pesos){
        this.pesos = pesos;
    }


    public Double input(int[] entrada, int deseado) {

        this.entradas = entrada;
        this.deseado = deseado;
        return this.process();

    }

    private int f_Escalon(double net){
        if (net >= 0)
            return 1;
        else
            return 0;
    }

   
     private double f_TransferenciaSuave(double net){
         return (1-Math.exp(-1*net))/(1+Math.exp(-1*net));
     }


    private double net(){
        double net = 0;
        /*System.out.println("Patron->");
        for (int i = 0; i < entradas.length; i++)
            System.out.print(entradas[i] + ",");

        System.out.println();
        */
        for (int i = 0; i < entradas.length; i++){
           net += entradas[i] * pesos[i];
           // System.out.println("peso " + i + ", " + pesos[i] );
        }
        return net;
    }

   


    private double F(){
        return f_TransferenciaSuave(net());
    }

    
    public Double process() {
        double res = F();
        double fNet =  res;

        try{
            salida = res;
            //Aprendizaje
            if ((deseado - res) != 0 && modificarPesos){
                //System.out.println("Actualizando pesos...");
                for (int i = 0; i < pesos.length; i++){
                    pesos[i] = pesos[i] + (Red.ALPHA * entradas[i]) * ((deseado - fNet) * (1 + fNet) * (1 - fNet));
                    //System.out.println("peso Nuevo" + i + ", " + pesos[i] );
                }
            }
        }catch (Exception e){
          /*
            System.out.println("Error");
            System.out.println("Pesos" + pesos.length);
            System.out.println("Pesos" + entradas.length);*/
            e.printStackTrace();
        }
        return res;
    }

    /**
     * @return the salida
     */
    public Double getSalida() {
        return salida;
    }

    /**
     * @param salida the salida to set
     */
    public void setSalida(double salida) {
        this.salida = salida;
    }

    public void run() {
        while(true){
            if (this.isIteracion()){
               salida = this.process();
            }
        }
    }
   

    /**
     * @return the iteracion
     */
    public boolean isIteracion() {
        return iteracion;
    }

    /**
     * @param iteracion the iteracion to set
     */
    public void setIteracion(boolean iteracion) {
        this.iteracion = iteracion;
    }

    /**
     * @return the modificarPesos
     */
    public static boolean isModificarPesos() {
        return modificarPesos;
    }

    /**
     * @param modificarPesos the modificarPesos to set
     */
    public static void setModificarPesos(boolean modificarPesos) {
        NeuronaSalidaDelta.modificarPesos = modificarPesos;
    }

}
