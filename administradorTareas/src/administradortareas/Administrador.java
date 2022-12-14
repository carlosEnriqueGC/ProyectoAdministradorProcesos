
package administradortareas;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author equipo 3 sistemas operativos
 */
public class Administrador extends javax.swing.JFrame {

     private DefaultTableModel modelo;
    
   
//procedimiento de alineacionde columnas
    private void Alineacion_Columnas(){
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.RIGHT); //establece de que forma se va a alinear las columnas
        jtabla_datos.getColumnModel().getColumn(1).setCellRenderer(Alinear);//alinea columna 1
        jtabla_datos.getColumnModel().getColumn(2).setCellRenderer(Alinear);//alinea columna 2
        jtabla_datos.getColumnModel().getColumn(3).setCellRenderer(Alinear);//alinea columna 3
        jtabla_datos.getColumnModel().getColumn(4).setCellRenderer(Alinear);//alinea columna 4
    }
    
     private void mostrar_procesos(){
    int ICol=0,ICont=0;
        modelo = (DefaultTableModel) jtabla_datos.getModel();
        Object[] Fila = new  Object[5];
        int i=0;
        String StrAuxi = "";
                try {
            String line;
            //se realiza la ejecucion de tasklist.exe para leer los procesos en ejecucion
            Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));//lectura de los datos de buffer obtenidos con tasklist
            //Guardado en la variable Fila cada dato para cada columna
            while ((line = input.readLine()) != null) {
                if(i>=4){
                    ICont=0;
                   while(ICont<=4){
                    String[] sep = line.split("\\s+");
                    if(ICont!=4){
                    Fila[ICont] = sep[ICont];
                    }else{
                       Fila[ICont] = sep[ICont]+" "+sep[ICont+1]; 
                    }
                    ICont++;
                   }
                //agregamos el arreglo de la cada fila a la tabla
                modelo.addRow(Fila);
                //Asignamos y/o aplicamos el modelo a nuestra tabla
                jtabla_datos.setModel(modelo);
                }
        i++;
            }
            input.close();
            Alineacion_Columnas();//llamada a la alineacion de las columnas
            No_procesos.setText(String.valueOf(i));// se imprimen el numero de procesos en ejecucion
        } catch (Exception err) {
            err.printStackTrace();
        }
        
    }
     
      // procedimiento de limpiaeza de la tabla la restablece de a los parametros inisciales
    void LimpiarTabla(){
     
      //DIEGO  
      jtabla_datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "PID", "Tipo de sesi??n ", "N??mero de sesi??n", "Uso de memoria"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
      
    }
     
      //procedimiento de matar o finalzar los procesos
    public void Matar_proceso(){
       //DIEGO "si te da error algo llamado administrador_de_tareas" 
       //"solo cambialo por Administrador despues de corregirlo eliminas este mensaje solo deja tu nombre"
       modelo = (DefaultTableModel) jtabla_datos.getModel();
        //se realiza la lectura de los datos de la columna 0(nombre) de la fila selecionada para matar el proceso
        String StrCelda = String.valueOf(modelo.getValueAt(jtabla_datos.getSelectedRow(), 0));
        if(StrCelda==""){ //si no hay fila selecionada da un error
          JOptionPane.showMessageDialog(null, "ERROR, No se ha selecionado ningun proceso","Error", JOptionPane.INFORMATION_MESSAGE); 
        }else{//de lo contrario realizara el proceso de matar el proceso
        try {
          Process hijo;
          hijo = Runtime.getRuntime().exec("taskkill /F /IM "+StrCelda);//mata el proceso selecionado junto con sus hijos
          hijo.waitFor();// finaiza los procesos hijos
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
}
     
    
    public Administrador() {
        initComponents();
        getContentPane().setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);
        No_procesos.setFocusable(false);
        mostrar_procesos(); 
    }

    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtabla_datos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jIniciar_procesos = new javax.swing.JButton();
        jterminar_procesos = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        No_procesos = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jtabla_datos.setBackground(new java.awt.Color(255, 236, 194));
        jtabla_datos.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jtabla_datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "PID", "Tipo de sesi??n ", "N??mero de sesi??n", "Uso de memoria"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtabla_datos);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Administrador de tareas");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("procesos");

        jIniciar_procesos.setText("ACTUALIZAR PROCESOS");
        jIniciar_procesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jIniciar_procesosActionPerformed(evt);
            }
        });

        jterminar_procesos.setText("FINALIZAR TAREA");
        jterminar_procesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jterminar_procesosActionPerformed(evt);
            }
        });

        jLabel3.setText("TOTAL DE PROCESOS: ");

        No_procesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                No_procesosActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/logo.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(No_procesos, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jIniciar_procesos, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jterminar_procesos, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE))
                        .addGap(42, 42, 42))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(No_procesos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jIniciar_procesos)
                    .addComponent(jterminar_procesos))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jIniciar_procesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jIniciar_procesosActionPerformed

     //JOSHUA
       LimpiarTabla();//limpia la tabla antes de insertr todos los procesos
        mostrar_procesos();//llama al procedimiento de mostrar procesos y los coloca en la tabla
   
    }//GEN-LAST:event_jIniciar_procesosActionPerformed

    private void jterminar_procesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jterminar_procesosActionPerformed

     //JOSHUA
       Matar_proceso();//llama al procedimiento de terminar un proceso
        LimpiarTabla();//limpia la tabla antes de colocar los procesos despues de haber terminado uno
        mostrar_procesos();//coloca de nuevo los procesos que quedaron sin los que se acaban de terminar
    }//GEN-LAST:event_jterminar_procesosActionPerformed

    private void No_procesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_No_procesosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_No_procesosActionPerformed

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
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Administrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField No_procesos;
    private javax.swing.JButton jIniciar_procesos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtabla_datos;
    private javax.swing.JButton jterminar_procesos;
    // End of variables declaration//GEN-END:variables
}
