/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.neurona;

/**
 *
 * @author servkey
 */
interface Neurona <T,E> {

    public T input(E entrada, int deseado);
    public T process();
    public T getSalida();
}
