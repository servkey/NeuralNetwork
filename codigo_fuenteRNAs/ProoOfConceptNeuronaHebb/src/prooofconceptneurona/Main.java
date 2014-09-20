/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prooofconceptneurona;

import org.neurona.Red;
import org.neurona.data.DataReader;
import org.neurona.data.MatrixUtils;

/**
 *
 * @author servkey
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here


        int[][] matrix = new DataReader().readTrain();
        int[][] matrixTest = new DataReader().readTest();

        int [][] salidas = new int[][]{
            {1,-1,-1,-1,-1,-1,-1},
            {-1,1,-1,-1,-1,-1,-1},
            {-1,-1,1,-1,-1,-1,-1},
            {-1,-1,-1,1,-1,-1,-1},
            {-1,-1,-1,-1,1,-1,-1},
            {-1,-1,-1,-1,-1,1,-1},
            {-1,-1,-1,-1,-1,-1,1},
            {1,-1,-1,-1,-1,-1,-1},
            {-1,1,-1,-1,-1,-1,-1},
            {-1,-1,1,-1,-1,-1,-1},
            {-1,-1,-1,1,-1,-1,-1},
            {-1,-1,-1,-1,1,-1,-1},
            {-1,-1,-1,-1,-1,1,-1},
            {-1,-1,-1,-1,-1,-1,1},
            {1,-1,-1,-1,-1,-1,-1},
            {-1,1,-1,-1,-1,-1,-1},
            {-1,-1,1,-1,-1,-1,-1},
            {-1,-1,-1,1,-1,-1,-1},
            {-1,-1,-1,-1,1,-1,-1},
            {-1,-1,-1,-1,-1,1,-1},
            {-1,-1,-1,-1,-1,-1,1}
        };

        Red r1 = new Red(matrix,matrixTest,salidas,salidas,new double[64],64,7);
        r1.iniciar();

    }

}
