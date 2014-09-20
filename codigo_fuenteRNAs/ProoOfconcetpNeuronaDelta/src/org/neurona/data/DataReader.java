/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.neurona.data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author servkey
 */
public class DataReader {

    public int[][] readTrain(){
        return read("TRAIN.DAT");
    }

    public int[][] readTest(){
        return read("TEST.DAT");
    }

    private int[][] read(String nameFile){
        int[][] matrix = new int[21][64];

        try{


                URL  url = getClass().getResource("../../" + nameFile);
                InputStream in = url.openStream();

//                FileReader in = new FileReader("org/" + nameFile);

                //url.openStream();
                Scanner scanner = new Scanner(in);
                //BufferedReader br = new BufferedReader(stream);
                int fila = 0;
                int columna = 0;
                String linea = "";
                //System.out.println(scanner.next());

                matrix[0][0] = 1;
                matrix[matrix.length-1][0] = 1;
                while (scanner.hasNext()){
                    linea = scanner.next().toUpperCase();

                    if (columna == 63){
                        matrix[fila][0] = 1;
                        columna = 0;
                        fila++;
                    }
                    
                    //System.out.println(linea);
                    for (int i = 0; i < linea.length(); i++)
                    {
                        switch(linea.charAt(i)){
                            case '#':
                                matrix[fila][++columna] = 1;
                                break;
                            case '@':
                                matrix[fila][++columna] = 1;
                                break;
                            case '.':
                                matrix[fila][++columna] = -1;
                                break;
                            case 'O':
                                matrix[fila][++columna] = -1;
                                break;
                        }

                   }

            }
          //else{
             //         System.out.println("Null url");
             //}
    
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e1){
            e1.printStackTrace();
        }
        return matrix;
    }
}
