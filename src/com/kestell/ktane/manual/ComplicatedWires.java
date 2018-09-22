/**
 * Created by phil k on 9/15/2018.
 * this is for all logic specific to the complicated wires module
 */
package com.kestell.ktane.manual;

public class ComplicatedWires {
    private static void makeMessage(String input){
        if(input != null && input.equals("")){
            System.out.println("" + input);
        }
        else{
            System.out.println("testing");
        }
    }

    public static String changed(ComplicatedWireOptions wireState){
        ComplicatedCutOptions returnValue = evalTree(wireState);

        return returnValue.toOptionString();
    }

    /**
     *
     * //@param //ComplicatedCutOptions CutOptions
     * //@return cut or no cut
     *
     * TODO make this return useful stuff
     */
    public static void evalCut(){
        EdgeWork edgeState = new EdgeWork();
    }

    private static Node complicatedWiresTree(){
        Node root = new Node("Star");

        Node b1 = new Node("Red");
        Node b2 = new Node("Red");
        root.yes = b1;
        root.no  = b2;

        Node c1 = new Node("Blue");
        Node c2 = new Node("Blue");
        Node c3 = new Node("Blue");
        Node c4 = new Node("Blue");
        b1.yes = c1;
        b1.no  = c2;
        b2.yes = c3;
        b2.no  = c4;

        Node d1 = new Node("Led");
        Node d2 = new Node("Led");
        Node d3 = new Node("Led");
        Node d4 = new Node("Led");
        Node d5 = new Node("Led");
        Node d6 = new Node("Led");
        Node d7 = new Node("Led");
        Node d8 = new Node("Led");
        c1.yes = d1;
        c1.no  = d2;
        c2.yes = d3;
        c2.no  = d4;
        c3.yes = d5;
        c3.no  = d6;
        c4.yes = d7;
        c4.no  = d8;

        d1.yesCutKey = 'D';
        d1.noCutKey  = 'P';
        d2.yesCutKey = 'B';
        d2.noCutKey  = 'C';
        d3.yesCutKey = 'P';
        d3.noCutKey  = 'D';
        d4.yesCutKey = 'B';
        d4.noCutKey  = 'C';
        d5.yesCutKey = 'S';
        d5.noCutKey  = 'S';
        d6.yesCutKey = 'B';
        d6.noCutKey  = 'S';
        d7.yesCutKey = 'P';
        d7.noCutKey  = 'S';
        d8.yesCutKey = 'D';
        d8.noCutKey  = 'C';

        return root;
    }


    private static ComplicatedCutOptions evalTree(ComplicatedWireOptions wireState){
        Node root = complicatedWiresTree();
        ComplicatedCutOptions options = new ComplicatedCutOptions();
        options = traverseTree(wireState, root, options);
        return options;
    }

    private static ComplicatedCutOptions traverseTree(ComplicatedWireOptions wireState, Node root, ComplicatedCutOptions options){
        //makeMessage("   root.value" + root.value);
        //makeMessage("      root.yes" + root.yes.toString());
        //makeMessage("      root.no" + root.no.toString());
        if(root == null){
            return options;
        }
        switch (root.value) {
            case "Star":
                if (wireState.StarYes) {
                    makeMessage(" StarYes");
                    traverseTree(wireState, root.yes, options);
                }
                if (wireState.StarNo) {
                    makeMessage(" StarNo");
                    traverseTree(wireState, root.no, options);
                }
                break;
            case "Red":
                if (wireState.RedYes) {
                    makeMessage("  RedYes");
                    traverseTree(wireState, root.yes, options);
                }
                if (wireState.RedNo) {
                    makeMessage("  RedNo");
                    traverseTree(wireState, root.no, options);
                }
                break;
            case "Blue":
                if (wireState.BlueYes) {
                    makeMessage("   BlueYes");
                    //makeMessage("n: BlueYes" + root.yes.value);
                    traverseTree(wireState, root.yes, options);
                }
                if (wireState.BlueNo) {
                    makeMessage("   BlueNo");
                    //makeMessage("n: BlueNo" + root.no.value);
                    traverseTree(wireState, root.no, options);
                }
                break;
            case "Led":
                if (wireState.LedOff) {
                    makeMessage("    LedOff:  " + root.noCutKey);
                    //makeMessage("n:       " + root.noCutKey);
                    if (root.noCutKey == 'B') {
                        options.b = true;
                    } else if (root.noCutKey == 'C') {
                        options.c = true;
                    } else if (root.noCutKey == 'D') {
                        options.d = true;
                    } else if (root.noCutKey == 'P') {
                        options.p = true;
                    } else if (root.noCutKey == 'S') {
                        options.s = true;
                    }
                }
                if (wireState.LedOn) {
                    makeMessage("    LedOn:  " + root.yesCutKey);
                    //makeMessage("y:       " + root.yesCutKey);
                    if (root.yesCutKey == 'B') {
                        options.b = true;
                    } else if (root.yesCutKey == 'C') {
                        options.c = true;
                    } else if (root.yesCutKey == 'D') {
                        options.d = true;
                    } else if (root.yesCutKey == 'P') {
                        options.p = true;
                    } else if (root.yesCutKey == 'S') {
                        options.s = true;
                    }
                }
                return options;
            default:
                makeMessage("ERROR ----------------------------------------------------------------------------------");
        }
        return options;
    }

    static class ComplicatedCutOptions{
        boolean b;
        boolean c;
        boolean d;
        boolean p;
        boolean s;

        ComplicatedCutOptions(){
            this.b = false;
            this.c = false;
            this.d = false;
            this.p = false;
            this.s = false;
        }

        public String toOptionString(){
            String optionString ="";
            if(this.b){
                optionString += "B";
            }
            if(this.c){
                optionString += "C";
            }
            if(this.d){
                optionString += "D";
            }
            if(this.p){
                optionString += "P";
            }
            if(this.s){
                optionString += "S";
            }
            if(optionString.equalsIgnoreCase("")){
                optionString = "EMPTY";
            }
            return optionString;
        }
    }

    static class ComplicatedWireOptions{
        boolean StarYes;//a
        boolean RedYes;//b
        boolean BlueYes;//c
        boolean LedOn;//d
        boolean StarNo;
        boolean RedNo;
        boolean BlueNo;
        boolean LedOff;

        ComplicatedWireOptions(){
            this.StarYes = true;
            this.RedYes = true;
            this.BlueYes = true;
            this.LedOn = true;
            this.StarNo = true;
            this.RedNo = true;
            this.BlueNo = true;
            this.LedOff = true;
        }
    }

    static class Node {
        final String value;
        Node yes;
        Node no;
        char yesCutKey;
        char noCutKey;

        Node(String value) {
            this.value = value;
            this.yes = null;
            this.no = null;
        }
    }
}
