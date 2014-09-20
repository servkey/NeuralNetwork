package org.neurona;

public class Red{
    //Valores por default
    private int[][] patronEntrada = {{1,0,0},{1,0,1},{1,1,0},{1,1,1}};
    private int[][] patronEntradaPrueba = {{1,0,0},{1,0,1},{1,1,0},{1,1,1}};
    public final static double ALPHA = 0.65;
    
    private int[][] patronSalida = {{0},{1},{1},{1}};
    private int[][] patronSalidaPrueba = {{0},{1},{1},{1}};

    private double[] pesos = new double[]{1.5, .5, 1.5};

    //Default
    private int numEntrada = 3;
    private int numSalida = 1;

    private NeuronaEntrada[] capaEntrada;
    private NeuronaSalidaHebb[] capaSalida;


    public Red(int[][] patronEntrada, int[][] patronEntradaPrueba,int[][] patronSalida, int[][] patronSalidaPrueba, double[] pesos, int numEntrada, int numSalida){
        this.patronEntrada = patronEntrada;
        this.patronEntradaPrueba = patronEntradaPrueba;
        this.patronSalida = patronSalida;
        this.patronSalidaPrueba = patronSalidaPrueba;
        this.numEntrada = numEntrada;
        this.numSalida = numSalida;
        this.pesos = pesos;
    }

    public Red(){
    }

    
    public void iniciar(){
        capaEntrada = new NeuronaEntrada[this.numEntrada];
        for (int i = 0; i < capaEntrada.length; i++)
            capaEntrada[i] = new NeuronaEntrada(pesos,1);
       
        capaSalida = new NeuronaSalidaHebb[this.numSalida];
        for (int i = 0; i < capaSalida.length; i++)
            capaSalida[i] = new NeuronaSalidaHebb(pesos);

        int index = 1;
        int indexColumns = 0;      

        System.out.println("\n****Hebb**");
        System.out.println("\n****FASE DE ENTRENAMIENTO***\n");

        while (isIterable(capaSalida)){

             System.out.println("**** Epoca: " +  index++);
             int[] salidas = new int[capaEntrada.length];
             for (int i = 0;i < patronEntrada.length; i++){
                for (int j = 0;j < patronEntrada[0].length; j++)
                    salidas[j] = capaEntrada[j].input(patronEntrada[i][j], patronEntrada[i][j]);

                indexColumns = 0;
                for (int i1 = 0; i1 < capaSalida.length; i1++)
                    capaSalida[i1].input(salidas,patronSalida[i][indexColumns++]);                    
             }
        }
        System.out.println("Pesos Obtenidos al final");
        for (int i = 0; i < capaSalida.length; i++){
            System.out.print("Neurona " + i + ": ");
            for (int j = 0; j < capaSalida[i].getPesos().length; j++)
                System.out.print(capaSalida[i].getPesos()[j]+ ",");
            System.out.println();
        }
        test();
    }

    public void test(){
        System.out.println("\n\n\n******************* T e s t ******************");
        int indexColumns = 0;
        int[] salidas = new int[capaEntrada.length];
        int totalEquivocaciones = 0;
        
        for (int i = 0;i < patronEntradaPrueba.length; i++){
           for (int j = 0;j < patronEntradaPrueba[0].length; j++)
              salidas[j] = capaEntrada[j].input(patronEntradaPrueba[i][j], patronEntradaPrueba[i][j]);

           System.out.println("\n");
           imprimirLetra(i);           

           System.out.print("\nDeseado->");
           for (int columns = 0; columns < patronSalidaPrueba[i].length; columns++)
            System.out.print(patronSalidaPrueba[i][columns] + ",");

           indexColumns = 0;
           System.out.print("\nPatron " + i + ": ");
           boolean state = true;

           for (int i1 = 0; i1 < capaSalida.length; i1++){
                System.out.print(capaSalida[i1].input(salidas,patronSalidaPrueba[i][indexColumns++]) + ",");
                if (patronSalidaPrueba[i][indexColumns - 1] != capaSalida[i1].getSalida()){
                    state = false;
                    totalEquivocaciones++;
                }
            }
           System.out.print("\nReconocida: " + state);
        }
        System.out.println("\n\nTotal Reconocidos: " + (21 - totalEquivocaciones));
        System.out.println("\nNo reconocidos: " + totalEquivocaciones);

    }

    public void imprimirLetra(int numero){
        int indexNum = (numero + 1  < 8?1: numero + 1 < 15? 2 : 3);
        switch(numero){
            case 0:
            case 7:
            case 14:
                System.out.print("A ");
                break;
            case 1:
            case 8:
            case 15:
                System.out.print("B ");
                break;
            case 2:
            case 9:
            case 16:
                System.out.print("C ");
                break;
            case 3:
            case 10:
            case 17:
                System.out.print("D ");
                break;
            case 4:
            case 11:
            case 18:
                System.out.print("E ");
                break;
            case 5:
            case 12:
            case 19:
                System.out.print("J ");
                break;

            case 6:
            case 13:
            case 20:
                System.out.print("K ");
                break;
        }
        System.out.print(indexNum);
    }

    
    public static boolean isIterable(NeuronaSalidaHebb[] salidas){
        boolean result = false;
        for (int i = 0; i < salidas.length; i++)
            if (salidas[i].isIteracion()){
                result = salidas[i].isIteracion();
                salidas[i].setIteracion(false);
            }
       return result;
    }
}



