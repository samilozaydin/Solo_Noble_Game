/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.solo_noble_game.ŞamilBilalÖZAYDIN_1921221011_SoloNoble_DataStructure;

import javax.swing.JButton;

/**
 *
 * @author Bilal
 */
public class Node {
    private char word;
    private String num;
    private Node next;
    private Node child;
    private char peg;
    
    public Node(char word,String num, char peg){
        this.num= num;
        this.word= word;
        this.peg= peg;
    }
    
    public Node getNext (){
        return next;
    }
    public Node getChild(){
        return child;
    }

    public char getWord() {
        return word;
    }

    public void setWord(char word) {
        this.word = word;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setChild(Node child) {
        this.child = child;
    }

    public char getPeg() {
        return peg;
    }

    public void setPeg(char peg) {
        this.peg = peg;
    }

    /**
     * @return the button
     */
   
}
