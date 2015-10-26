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
public class listas {
    
    public static void borrar(ArrayList<nodo> lista, nodo match){
        
        for(int i=0; i<lista.size();i++){
                if(lista.get(i).posX==match.posX && lista.get(i).posY==match.posY){
                    lista.remove(i);
                    break;
                }
        }
        
    }
    
    public static boolean existeFinal(ArrayList<nodo> lista, nodo match){
        boolean existe = false;
        for(int i=0; i<lista.size();i++){
                if(lista.get(i).posX==match.posX && lista.get(i).posY==match.posY){
                    existe = true;
                    lista.remove(i);
                    break;
                }
        }
        return existe;
    }
    
    public static boolean existe(ArrayList<nodo> lista, nodo match){
        boolean existe = false;
        for(int i=0; i<lista.size();i++){
                if(lista.get(i).posX==match.posX && lista.get(i).posY==match.posY){
                    existe = true;
                    break;
                }
        }
        return existe;
    }
    
    public static nodo obtenerPadre(ArrayList<nodo> lista, nodo match){
                nodo padre = null;
        for(int i=0; i<lista.size();i++){
                if(lista.get(i).posX==match.posX && lista.get(i).posY==match.posY){
                    padre = lista.get(i).padre;
                    break;
                }
        }
        return padre;
    }
    
    public static void borrarAbierta(ArrayList<nodo> lista, nodo match){
        
        for(int i=0; i<lista.size();i++){
                if(lista.get(i).padre.posX==match.padre.posX && lista.get(i).padre.posY==match.padre.posY){
                    lista.remove(i);
                    break;
                }
        }
        
    }
}
