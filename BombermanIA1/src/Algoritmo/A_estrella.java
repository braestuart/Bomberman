/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmo;

import java.util.ArrayList;


/**
 *
 * @author PANCHO
 */
public class A_estrella {

    public static ArrayList<nodo> abierta = new ArrayList();
    public static ArrayList<nodo> cerrada = new ArrayList();
   

    public static nodo nodoAnterior;
    public static nodo empate;
    public static nodo Inicial;
    public static int Rempate;
    public static boolean exito = false;

    public static void caminoMasCorto(nodo[][] matrix, nodo pActual, nodo pFinal) {
        abierta.clear();
        cerrada.clear();
       
        int count = 0;
        int countA = 0;
        exito = false;
        Rempate = 0;
        while (true) {
            nodoAnterior = pActual;
            cerrada.add(pActual);
            listas.borrar(abierta, pActual);
            pActual = encontrarCercanos(matrix, pActual);
            if (pActual.peso == 10000) {
                if (Rempate == 0) {
                    pActual = empate;
                    Rempate = 1;
                    countA++;
                } else {
                    break;
                }
            }
            if (listas.existeFinal(abierta, pFinal)) {
                exito = true;
                System.out.println("=========================================== Finalizo la busqueda ===========================================");
                break;
            }
            count++;
        }
        System.out.println("Numero de iteraciones: " + count + " Rotura de empate: " + countA + "\n");

    }

    private static nodo encontrarCercanos(nodo[][] matrix, nodo pActual) {
        nodo menor = new nodo(0, 0, 0, 10000, 0, null);

        //=====Horizontales=====
        //Derecha
        menor = agregarCasilla(matrix, +1, 0, pActual, 10, menor);
        //Izquierda
        menor = agregarCasilla(matrix, -1, 0, pActual, 10, menor);

        //=====Verticales=====
        //Arriba
        menor = agregarCasilla(matrix, 0, +1, pActual, 10, menor);
        //Abajo
        menor = agregarCasilla(matrix, 0, -1, pActual, 10, menor);

        //=====Diagonales=====
        //Derecha,Abajo
        menor = agregarCasilla(matrix, +1, +1, pActual, 14, menor);
        //Izquierda,Arriba
        menor = agregarCasilla(matrix, -1, -1, pActual, 14, menor);
        //Izquierda,Abajo
        menor = agregarCasilla(matrix, -1, +1, pActual, 14, menor);
        //Derecha,Arriba
        menor = agregarCasilla(matrix, +1, -1, pActual, 14, menor);
        
        return menor;
    }

    private static nodo agregarCasilla(nodo[][] matrix, int x, int y, nodo Actual, int peso, nodo menor) {
        nodo eval;

        if ((Actual.posX + x >= 0 && Actual.posX + x < matrix.length)
                && (Actual.posY + y >= 0 && Actual.posY + y < matrix.length)
                && (matrix[Actual.posX + x][Actual.posY + y].tipo == 0
                || matrix[Actual.posX + x][Actual.posY + y].tipo == 3)) {

            eval = new nodo(Actual.posX + x, Actual.posY + y, 0, (Actual.H + peso),
                    matrix[Actual.posX + x][Actual.posY + y].H, Actual);
            eval.jlG.setText(Integer.toString(peso));

            if (!listas.existe(cerrada, eval)) {
                if (!listas.existe(abierta, eval)) {
                    if ((eval.peso + eval.H) < (menor.peso + menor.H)) {
                        menor = eval;
                    } else if (((eval.peso + eval.H) == (menor.peso + menor.H))) {
                        if ((eval.H) < (menor.H)) {
                            menor = eval;
                        } else {
                            empate = eval;
                            if (Rempate == 1) {
                                menor = eval;
                                Rempate = 0;
                            }
                        }
                    }
                    abierta.add(eval);
                } 
            }
        }

        return menor;
    }

}
