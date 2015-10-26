/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmo;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author PANCHO
 */
public class nodo extends JPanel{
    
    public int posX = 0;
    public int posY = 0;
    /*Tipos de "cuadrito":
    0: campo
    1: obstaculo
    2: punto inicial
    3: punto final*/
    public int tipo = 0;
    public int peso = 0;
    //H: Funcion manhattan
    public int H = 0;
    public nodo padre = null;
    public JLabel jlH;
    public JLabel jlG;
    public JLabel jlF;
     
    public nodo(int X, int Y, int _tipo, int _peso, int _H, nodo _padre){
        
        this.setLayout(null); 

        this.posX=X;
        this.posY=Y;
        this.tipo=_tipo;
        this.peso=_peso;
        this.H=_H;
        this.padre=_padre;   
        jlH= new JLabel();
        jlH.setFont(new java.awt.Font("Tahoma", 1, 8));
        jlH.setBounds(33,25,20,20);        
        jlH.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(jlH);
        jlG= new JLabel();
        jlG.setFont(new java.awt.Font("Tahoma", 1, 8));
        jlG.setBounds(2,25,20,20);        
        jlG.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(jlG);
        jlF= new JLabel();
        jlF.setFont(new java.awt.Font("Tahoma", 1, 8));
        jlF.setBounds(2,0,20,20);        
        jlF.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(jlF);
        
        
    }
    public void LLenarH(){
        jlH.setText(Integer.toString(H));
        if(tipo==1||tipo==2||tipo==3){
            jlF.setText("");
            jlG.setText("");
            jlH.setText("");
        }
        jlH.setBackground(Color.GRAY);
        this.repaint();
    }
    
    public void LLenarG(int F){
        jlG.setText(Integer.toString(F-H));
        if(tipo==1||tipo==2||tipo==3){
             jlF.setText("");
            jlG.setText("");
            jlH.setText("");
        }
        jlG.setBackground(Color.GRAY);
        this.repaint();
    }
    
    public void LLenarF(String G){
        int total=0;
          total=    Integer.parseInt(G)+H;
        jlF.setText(Integer.toString(total));
        if(tipo==1||tipo==2||tipo==3){
            jlF.setText("");
            jlG.setText("");
            jlH.setText("");
        }
        this.repaint();
        jlF.setBackground(Color.GRAY);
    }
    
}
