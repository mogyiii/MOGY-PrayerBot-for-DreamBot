/*
 * Created by JFormDesigner on Sat Feb 02 22:48:23 CET 2019
 */

package GUI;

import prayer.MainClass;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author kurva anyad
 */
public class JWindow extends JFrame {
    public JWindow() {
        initComponents();
    }
    private prayer.MainClass ctx;
    public JWindow(MainClass main) {
        this.ctx = main;
        initComponents();
        comboBox1.removeAllItems();
        comboBox1.addItem("Banking");
        comboBox1.addItem("Chaos Temple");
        comboBox1.addItem("Current area");
    }
    public String getDo(){
        return comboBox1.getSelectedItem().toString();
    }
    private void button1ActionPerformed(ActionEvent e) {
        ctx.setStarter(true);
        setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - kurva anyad
        comboBox1 = new JComboBox<>();
        button1 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- comboBox1 ----
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "Here",
            "Bank",
            "Chaos Temple"
        }));
        contentPane.add(comboBox1);
        comboBox1.setBounds(new Rectangle(new Point(120, 65), comboBox1.getPreferredSize()));

        //---- button1 ----
        button1.setText("Start");
        button1.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(145, 130), button1.getPreferredSize()));

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - kurva anyad
    private JComboBox<String> comboBox1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
