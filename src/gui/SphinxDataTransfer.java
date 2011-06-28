/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SphinxDataTransfer.java
 *
 * Created on Jun 26, 2011, 12:20:55 AM
 */
package gui;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import sphinxGuiInitializer.ComponentInitializer;
import tasks.CopyFileTask;
import utils.ConstantManager;
import utils.FileOperation;

/**
 *
 * @author ahmed
 */
public class SphinxDataTransfer extends javax.swing.JDialog {

    private Image mainIcon = Toolkit.getDefaultToolkit().getImage(ConstantManager.SPHNIX_ICON_PATH);
    private CopyFileTask copyFile;

    /** Creates new form SphinxDataTransfer */
    public SphinxDataTransfer(java.awt.Frame parent, boolean modal, String borderTitle) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(mainIcon);
        jPanel1.setBorder(new TitledBorder(borderTitle));
        jButton2.setText(borderTitle);
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/" + borderTitle + ".png")));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(ConstantManager.SPHNIX_TITLE);
        setResizable(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1588889018.png"))); // NOI18N
        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Export.png"))); // NOI18N
        jButton2.setText("Export");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jProgressBar1.setStringPainted(true);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/transfer.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(86, 86, 86))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        copyFile = null;
        FileOperation fileOp = null;

        if (evt.getActionCommand().equals("Export")) {
            fileOp = new FileOperation();
            String filePath = fileOp.saveFilterFileChooser("Sphinx.dat");
            if (filePath != null) {
                copyFile = new CopyFileTask("C:\\Program Files\\Sphinx\\Sphinx\\data\\Sphinx.dat", filePath);
                copyFile.progressRef(jProgressBar1, this);
                new Thread(copyFile).start();
            }
        } else if (evt.getActionCommand().equals("Set Default")) {
            int choiceDef = JOptionPane.showConfirmDialog(null, "Are you sure you want to permanently set Sphinx to default ?\nThis action will delete all stored data..", "Info", JOptionPane.YES_NO_OPTION);
            if (choiceDef == JOptionPane.YES_OPTION) {
                fileOp = new FileOperation();
                copyFile = new CopyFileTask("C:\\Program Files\\Sphinx\\Sphinx\\data\\default\\Sphinx.dat", "C:\\Program Files\\Sphinx\\Sphinx\\data\\Sphinx.dat");
                copyFile.progressRef(jProgressBar1, this);
                new Thread(copyFile).start();
            }
        } else {

            fileOp = new FileOperation();
            String filePath = fileOp.openFilterFileChooser("Sphinx.dat");
            if (filePath != null) {
                int choiceImp = JOptionPane.showConfirmDialog(null, "Please export your projects first ..\nAre you sure you want to permanently set \nThe new Sphinx data  ?", "Info", JOptionPane.YES_NO_OPTION);
                if (choiceImp == JOptionPane.YES_OPTION) {
                    copyFile = new CopyFileTask(filePath, "C:\\Program Files\\Sphinx\\Sphinx\\data\\Sphinx.dat");
                    copyFile.progressRef(jProgressBar1, this);
                    new Thread(copyFile).start();

                }

            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                SphinxDataTransfer dialog = new SphinxDataTransfer(new javax.swing.JFrame(), true, "test");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}