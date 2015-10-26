/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Bray
 */
public class Tablero extends javax.swing.JFrame {
    int filas,columnas;
    JLabel [][] CuadroTablero;
    int [][] elementosTablero;

    /**
     * Creates new form Tablero
     */
    public Tablero() {
        initComponents();     
        
    }
    public Tablero(int x,int y) {
        initComponents();     
        //llenadoTablero(x, y);
        leerXML("");
        imprimirMatriz();
        pintarTablero();
        
    }
    
    public void llenadoTablero(int x,int y)    
    {
        filas=x;
        columnas=y;
        CuadroTablero= new JLabel[filas][columnas];
        PanelTablero.setLayout (new GridLayout (filas,columnas));  // 3 filas y 3 columnas
        for (int i=0;i<filas;i++)
        {
            for (int j=0;j<columnas;j++)
            {
            JLabel cuadro = new JLabel();
            cuadro.setName("Cuadro"+i+","+j);
            cuadro.setBorder(new BevelBorder(1));
            CuadroTablero[i][j]=cuadro;
            PanelTablero.add (CuadroTablero[i][j]);  // Añade los botones de 1 en 1            
            }
        }
        llenadoMatriz();
        llenadoParedesG();            
        
    }
    public void llenadoMatriz()
    {
        elementosTablero= new int[filas][columnas];
        for (int i=0;i<filas;i++)
        {
            for (int j=0;j<columnas;j++)
            {
                elementosTablero[i][j]=0;            
            }
        }
    }
    public void imprimirMatriz()
    {
        
        for (int i=0;i<filas;i++)
        {
            for (int j=0;j<columnas;j++)
            {
                System.out.print(elementosTablero[i][j]);
                if(j<columnas-1)
                    System.out.print(",");
                
            }
            System.out.println();
        }
    }
    public void llenadoParedesG()
    {        
        for (int i=0;i<filas;i++)
        {
            for (int j=0;j<columnas;j++)
            {
                if(i==0||j==0||j==columnas-1||i==filas-1)
                elementosTablero[i][j]=1;            
            }
        }
    }
    public void leerXML(String direccion)
    {
        try {

	File fXmlFile = new File("C:\\Users\\Bray\\Desktop\\ProyectoIA\\Juego.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
			
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();

	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        
        System.out.println("----------------------------");
        NodeList nList = doc.getElementsByTagName("DIMENSION");
        Node nNode = nList.item(0);
        System.out.println("\nCurrent Element :" + nNode.getNodeName());
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            System.out.println("Y : " + eElement.getElementsByTagName("Y").item(0).getTextContent());
            System.out.println("X : " + eElement.getElementsByTagName("X").item(0).getTextContent());
            int posicionY=Integer.parseInt(eElement.getElementsByTagName("Y").item(0).getTextContent());
            int posicionX=Integer.parseInt(eElement.getElementsByTagName("X").item(0).getTextContent());
            llenadoTablero(posicionX, posicionY);
            
        }
        nList = doc.getElementsByTagName("MURO");
        nNode = nList.item(0);
        System.out.println("\nCurrent Element :" + nNode.getNodeName());
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            int i=0;
            while(i<eElement.getElementsByTagName("Y").getLength()&&i<eElement.getElementsByTagName("X").getLength())
            {
            System.out.println("Y : " + eElement.getElementsByTagName("Y").item(i).getTextContent());
            System.out.println("X : " + eElement.getElementsByTagName("X").item(i).getTextContent());
            int posicionY=Integer.parseInt(eElement.getElementsByTagName("Y").item(i).getTextContent());
            int posicionX=Integer.parseInt(eElement.getElementsByTagName("X").item(i).getTextContent());            
            elementosTablero[posicionX][posicionY]=1;            
            i++;
            
            }
        }
        nList = doc.getElementsByTagName("OBSTACULO");
        nNode = nList.item(0);
        System.out.println("\nCurrent Element :" + nNode.getNodeName());
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            int i=0;
            while(i<eElement.getElementsByTagName("Y").getLength()&&i<eElement.getElementsByTagName("X").getLength())
            {
            System.out.println("Y : " + eElement.getElementsByTagName("Y").item(i).getTextContent());
            System.out.println("X : " + eElement.getElementsByTagName("X").item(i).getTextContent());
            
            int posicionY=Integer.parseInt(eElement.getElementsByTagName("Y").item(i).getTextContent());
            int posicionX=Integer.parseInt(eElement.getElementsByTagName("X").item(i).getTextContent());            
            elementosTablero[posicionX][posicionY]=2;            
            i++;
            }
        }
        nList = doc.getElementsByTagName("PUERTA");
        nNode = nList.item(0);
        System.out.println("\nCurrent Element :" + nNode.getNodeName());
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            System.out.println("Y : " + eElement.getElementsByTagName("Y").item(0).getTextContent());
            System.out.println("X : " + eElement.getElementsByTagName("X").item(0).getTextContent());
            int posicionY=Integer.parseInt(eElement.getElementsByTagName("Y").item(0).getTextContent());
            int posicionX=Integer.parseInt(eElement.getElementsByTagName("X").item(0).getTextContent());            
            elementosTablero[posicionX][posicionY]=3;            
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void pintarTablero()
    {
        System.out.println();
        System.out.println(CuadroTablero[0][0].getSize().toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        PanelTablero = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 800));
        setSize(new java.awt.Dimension(1200, 800));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Rockwell Condensed", 0, 18)); // NOI18N
        jLabel1.setText("TIEMPO:");

        jTextField1.setFont(new java.awt.Font("Rockwell Condensed", 0, 18)); // NOI18N
        jTextField1.setText("0");

        jTextField2.setFont(new java.awt.Font("Rockwell Condensed", 0, 18)); // NOI18N
        jTextField2.setText("0");

        jLabel2.setFont(new java.awt.Font("Rockwell Condensed", 0, 18)); // NOI18N
        jLabel2.setText("VIDAS:");

        jTextField3.setFont(new java.awt.Font("Rockwell Condensed", 0, 18)); // NOI18N
        jTextField3.setText("0");

        jLabel3.setFont(new java.awt.Font("Rockwell Condensed", 0, 18)); // NOI18N
        jLabel3.setText("PUNTAJE:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(299, 299, 299)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        PanelTablero.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout PanelTableroLayout = new javax.swing.GroupLayout(PanelTablero);
        PanelTablero.setLayout(PanelTableroLayout);
        PanelTableroLayout.setHorizontalGroup(
            PanelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelTableroLayout.setVerticalGroup(
            PanelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelTablero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelTablero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelTablero.getAccessibleContext().setAccessibleName("");
        PanelTablero.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tablero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel PanelTablero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
