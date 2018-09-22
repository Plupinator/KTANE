/**
 * Created by phil k on 9/15/2018.
 *
 */
package com.kestell.ktane.manual;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 *
 * todo figure out how to call forms within the tabs.
 */
class BombGUI {
    private JTabbedPane tabbedPane1;
    private JRadioButton LedOn;
    private JRadioButton LedOff;
    private JRadioButton RedYes;
    private JRadioButton RedNo;
    private JRadioButton BlueYes;
    private JRadioButton BlueNo;
    private JRadioButton StarNo;
    private JRadioButton StarYes;
    private JButton ResetComplicateWiresButton;
    private JPanel MainPanel;
    private JLabel OutPut;
    private JRadioButton Batteries01;
    private JRadioButton Batteries2;
    private JRadioButton evenSerialNumber;
    private JRadioButton oddSerialNumber;
    private JRadioButton yesParallelPort;
    private JRadioButton noParallelPort;
    private JButton resetEdgeWorkButton;
    private ButtonGroup parallelPort;
    private ButtonGroup serialNumber;
    private ButtonGroup batteries;
    private ButtonGroup Star;
    private ButtonGroup Blue;
    private ButtonGroup Red;
    private ButtonGroup LED;

    /**
     * todo figure out how to link this and the bomb.java
     */
    public BombGUI() {


        ActionListener complicatedWiresListener = actionEvent -> SetComplicatedWireState();
        LedOn.addActionListener(complicatedWiresListener);
        LedOff.addActionListener(complicatedWiresListener);
        RedYes.addActionListener(complicatedWiresListener);
        RedNo.addActionListener(complicatedWiresListener);
        BlueYes.addActionListener(complicatedWiresListener);
        BlueNo.addActionListener(complicatedWiresListener);
        StarYes.addActionListener(complicatedWiresListener);
        StarNo.addActionListener(complicatedWiresListener);
        //StarYes.addActionListener(complicatedWiresListener);
        ResetComplicateWiresButton.addActionListener(actionEvent -> RestComplicatedWireState());

        ActionListener edgeStateListener = actionEvent -> SetEdgeState();
        Batteries01.addActionListener(edgeStateListener);
        Batteries2.addActionListener(edgeStateListener);
        evenSerialNumber.addActionListener(edgeStateListener);
        oddSerialNumber.addActionListener(edgeStateListener);
        yesParallelPort.addActionListener(edgeStateListener);
        noParallelPort.addActionListener(edgeStateListener);
        resetEdgeWorkButton.addActionListener(actionEvent -> RestEdgeState());
    }

    public void SetComplicatedWireState() {
        ComplicatedWires.ComplicatedWireOptions wireState = new ComplicatedWires.ComplicatedWireOptions();
        wireState.LedOff = !LedOn.isSelected();
        wireState.RedNo = !RedYes.isSelected();
        wireState.BlueNo = !BlueYes.isSelected();
        wireState.StarNo = !StarYes.isSelected();

        wireState.LedOn = !LedOff.isSelected();
        wireState.RedYes = !RedNo.isSelected();
        wireState.BlueYes = !BlueNo.isSelected();
        wireState.StarYes = !StarNo.isSelected();


        String output = ComplicatedWires.changed(wireState);
        OutPut.setText(output);
        Bomb.makeMessage(output + "\n");

    }
    public void RestComplicatedWireState(){
        LED.clearSelection();
        Red.clearSelection();
        Blue.clearSelection();
        Star.clearSelection();

        SetComplicatedWireState();
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

    public void SetEdgeState() {
        EdgeWork.EdgeOptions edgeState = new EdgeWork.EdgeOptions();

        edgeState.Batteries01 = !Batteries2.isSelected();
        edgeState.Batteries2 = !Batteries01.isSelected();

        edgeState.evenSerialNumber = !oddSerialNumber.isSelected();
        edgeState.oddSerialNumber = !evenSerialNumber.isSelected();

        edgeState.yesParallelPort = !noParallelPort.isSelected();
        edgeState.noParallelPort = !yesParallelPort.isSelected();


        //String output = ComplicatedWires.changed(wireState);
        //OutPut.setText(output);
        //Bomb.makeMessage(output + "\n");

    }

    public void RestEdgeState(){
        batteries.clearSelection();
        serialNumber.clearSelection();
        parallelPort.clearSelection();

        SetEdgeState();
    }

    public static void main(String[] args) {
        JFrame bombGiu = new JFrame("HelloWorldGUI");
        bombGiu.setContentPane(new BombGUI().MainPanel);
        bombGiu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bombGiu.pack();
        bombGiu.setVisible(true);
    }
}