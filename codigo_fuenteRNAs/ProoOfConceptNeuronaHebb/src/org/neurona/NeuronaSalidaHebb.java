package org.neurona;
/**
 *
 * @author servkey
 */
public class NeuronaSalidaHebb implements  Neurona<Integer, int[]>{
    public  double[] pesos;
    private int[] entradas ;
    
    private int deseado;
    private Integer salida;
    private boolean iteracion = true;
    private static boolean modificarPesos = true;

    public NeuronaSalidaHebb(double[] pesosIniciales) {
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

    public Integer input(int[] entrada, int deseado) {
        
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

     private int f_EscalonLetras(double net){
        if (net > 0)
            return 1;
        else
            return -1;
    }

     
    private double net(){
        double net = 0;
        for (int i = 0; i < entradas.length; i++)
           net += entradas[i] * pesos[i];                   
        return net;
    }

    private int F(){
        return f_EscalonLetras(net());
    }

    public Integer process() {
        int  res = F();
        try{
            salida = res;
            //Aprendizaje
            if ((deseado - res) != 0){
                this.iteracion = true;
                for (int i = 0; i < pesos.length; i++){
                    pesos[i] = pesos[i]+ (Red.ALPHA * (entradas[i])*(deseado - res));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }

    
    /**
     * @return the salida
     */
    public Integer getSalida() {
        return salida;
    }

    /**
     * @param salida the salida to set
     */
    public void setSalida(Integer salida) {
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
        NeuronaSalidaHebb.modificarPesos = modificarPesos;
    }

}
