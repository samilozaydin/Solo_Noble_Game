/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.solo_noble_game.ŞamilBilalÖZAYDIN_1921221011_SoloNoble_DataStructure;

/**
 *
 * @author Bilal
 */
public class LinkedList {

    private Node head;
    private int dimension;

    public LinkedList(char word, String num, char peg, int dimension) {
        this.head = new Node(word, num, peg);
        this.dimension = dimension;
    }


    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }
}
