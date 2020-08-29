/*
 * Created by JFormDesigner on Sat Feb 02 22:48:23 CET 2019
 */

package GUI;

import prayer.MainClass;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JWindow extends JFrame {
    private prayer.MainClass Main;
    private boolean wHop = false;
    //private boolean route = false;
    private boolean ChatBot = false;
    public JWindow(MainClass main) {
        this.Main = main;
        initComponents();
        SelectAreaBox.removeAllItems();
        SelectAreaBox.addItem("From Bank");
        SelectAreaBox.addItem("Chaos Temple");
        SelectAreaBox.addItem("Bone Yard");
        SelectAreaBox.addItem("South GraveYard");
        SelectAreaBox.addItem("Current area");
    }
    public String getDo(){
        return SelectAreaBox.getSelectedItem().toString();
    }
    public boolean isTalker(){
        return ChatBot;
    }
    private void StarterBtnActionPerformed(ActionEvent e) {
        Main.setStarter(true);
        setVisible(false);
    }
    private void WorldHopCheckBoxActionPerformed(ActionEvent e) {
        if(WorldHopCheckBox.isSelected()){
            wHop = true;
        }else{
            wHop = false;
        }
    }
    /*private void AlgoActionPerformed(ActionEvent e) {
        if(Algo.isSelected()){
            route = true;
        }else{
            route = false;
        }
    }*/
    private void TalkerActionPerformed(ActionEvent e) {
        if(Talker.isSelected()){
            ChatBot = true;
        }else{
            ChatBot = false;
        }
    }
    public boolean getWhopper(){
        return wHop;
    }
    //public boolean getRoute(){return route;}
    public int getAreaSize(){
        return Integer.parseInt(AreaRangeTextBox.getText());
    }
    private void initComponents() {
        SelectAreaBox = new JComboBox<>();
        StarterBtn = new JButton();
        AreaRangeLabel = new JLabel();
        AreaRangeTextBox = new JTextField();
        WorldHopCheckBox = new JCheckBox();
        //Algo = new JCheckBox();
        Talker = new JCheckBox();
        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- SelectAreaBox ----
        SelectAreaBox.setModel(new DefaultComboBoxModel<>(new String[] {
                "South GraveYard"
        }));
        contentPane.add(SelectAreaBox);
        SelectAreaBox.getPreferredSize().width = SelectAreaBox.getPreferredSize().width + 30;
        SelectAreaBox.setBounds(new Rectangle(new Point(70, 80), SelectAreaBox.getPreferredSize()));

        //---- StarterBtn ----
        StarterBtn.setText("Start");
        StarterBtn.addActionListener(e -> StarterBtnActionPerformed(e));
        contentPane.add(StarterBtn);
        StarterBtn.setBounds(new Rectangle(new Point(70, 160), StarterBtn.getPreferredSize()));
        //---- AreaRangeTextBox ----
        AreaRangeTextBox.setText("8");
        contentPane.add(AreaRangeTextBox);
        AreaRangeTextBox.setBounds(70, 50, 50, StarterBtn.getPreferredSize().height + 5);
        //---- label2 ----
        AreaRangeLabel.setText("Area Size");
        contentPane.add(AreaRangeLabel);
        AreaRangeLabel.setBounds(10, 55, AreaRangeLabel.getPreferredSize().width, 15);
        //----- checkbox2
        WorldHopCheckBox.setText("Disable World-Hop");
        WorldHopCheckBox.addActionListener(e -> WorldHopCheckBoxActionPerformed(e));
        contentPane.add(WorldHopCheckBox);
        WorldHopCheckBox.setBounds(new Rectangle(new Point(50, 110), WorldHopCheckBox.getPreferredSize()));
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
        /*Algo.setText("Route Algorithm");
        Algo.addActionListener(e -> AlgoActionPerformed(e));
        contentPane.add(Algo);
        Algo.setBounds(new Rectangle(new Point(50, 130), Algo.getPreferredSize()));*/
        Talker.setText("ChatBot");
        Talker.addActionListener(e -> TalkerActionPerformed(e));
        contentPane.add(Talker);
        Talker.setBounds(new Rectangle(new Point(50, 130), Talker.getPreferredSize()));

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
        contentPane.setPreferredSize(new Dimension(220, 190));
        pack();
        setLocationRelativeTo(getOwner());

    }


    private JComboBox<String> SelectAreaBox;
    private JButton StarterBtn;
    private JTextField AreaRangeTextBox;
    private JLabel AreaRangeLabel;
    private JCheckBox WorldHopCheckBox;
    //private JCheckBox Algo;
    private JCheckBox Talker;
}
