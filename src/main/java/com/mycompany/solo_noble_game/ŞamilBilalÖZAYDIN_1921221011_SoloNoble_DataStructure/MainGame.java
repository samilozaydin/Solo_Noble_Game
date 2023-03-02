/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.solo_noble_game.ŞamilBilalÖZAYDIN_1921221011_SoloNoble_DataStructure;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Bilal
 */
public class MainGame extends javax.swing.JFrame {

    private int dimension;
    private Main beginFrame;
    private LinkedList gameList;
    private boolean isClicked;
    private JButton clickedButton;
    private int remain;
    
    public MainGame() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public MainGame(int dimension) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.dimension = dimension;
        this.isClicked = false;

        createTable(dimension);
        createButtons();

        setMiddleFour();
        printLinkedList();

    }

    public void createTable(int n) {
        LinkedList list = new LinkedList('A', "1", 'X', n);
        createNexts(list, n);

        this.gameList = list;
    }

    public void createNexts(LinkedList list, int n) {
        Node temp = list.getHead();
        createChilds(temp, n, 'A');
        for (int i = 0; i < n - 1; i++) {//travelling from B to last one.
            Node node = new Node((char) (i + 66), "" + (char) (49), 'X');
            temp.setNext(node);
            temp = temp.getNext();

            createChilds(temp, n, (char) (i + 66));
        }
    }

    public void createChilds(Node temp, int n, char word) {
        //according to dimension, number can be changed. numbers greater than 9 must be added in different way. 
        if (n < 10) {
            for (int j = 0; j < n - 1; j++) {
                Node node = new Node(word, "" + (char) (j + 48 + 2), 'X');
                temp.setChild(node);
                temp = temp.getChild();
            }
        } else {
            int count = 0;//the first number of the 'num' string
            int counter = 1;//amount of second number. When counter hit 10, it is made 0 and count is increased one. It is 1 becuse first element is already created. 
            for (int j = 0; j < n - 1; j++) {
                Node node;
                counter++;
                if (counter == 10) {
                    count++;
                    counter = 0;
                }
                if (count != 0) {
                    node = new Node(word, "" + String.valueOf(count) + (char) (j + 50 - (count * 10)), 'X');
                } else {
                    node = new Node(word, "" + (char) (j + 50), 'X');
                }

                temp.setChild(node);
                temp = temp.getChild();
            }
        }

    }

    //**********************************************************************************
    public void setMiddleFour() {
        int first = (dimension / 2) - 1;
        int second = dimension / 2;

        setMiddleFourPeg(first, first - 1);
        setMiddleFourPeg(second, first - 1);
        RsetMiddleButtons();
    }

    private void setMiddleFourPeg(int numx, int numy) {
        Node temp = this.gameList.getHead();

        while (numx > 0) {
            temp = temp.getNext();
            numx--;
        }

        while (numy > 0) {
            temp = temp.getChild();
            numy--;
        }

        temp.setChild(temp.getChild().getChild().getChild());
    }

    private void RsetMiddleButtons() {
        int sizeX = dimension / 2;
        int sizeY = sizeX - 1;

        JButton btn = (JButton) this.pnl_game.getComponent(sizeX - 1 + sizeY * dimension);
        btn.setVisible(false);
        btn = (JButton) this.pnl_game.getComponent(sizeX + sizeY * dimension);
        btn.setVisible(false);
        btn = (JButton) this.pnl_game.getComponent(sizeX - 1 + (sizeY + 1) * dimension);
        btn.setVisible(false);
        btn = (JButton) this.pnl_game.getComponent(sizeX + (sizeY + 1) * dimension);
        btn.setVisible(false);
    }

    public void printLinkedList() {//This function regulate pegs position and table apperance
        txta_log.setText("Current situtaion:\n------------------------------------------------------------");
        txta_log.append("\n\t");
        for (int i = 0; i < dimension; i++) {
            txta_log.append((char) (65 + i) + " ");
        }
        txta_log.append("\n");
        for (int i = 0; i < dimension; i++) {
            txta_log.append("" + String.valueOf(i + 1) + "|\t");
            for (int j = 0; j < dimension; j++) {
                RprintPeg((char) (65 + j), String.valueOf(i + 1));
            }
            txta_log.append("\n");

        }
        txta_log.append("\n------------------------------------------------------------");

    }

    private void RprintPeg(char word, String num) {//Find the spesific character for printing as column and row
        Node temp = gameList.getHead();

        while (temp != null) {
            if (temp.getWord() != word) {
                temp = temp.getNext();
            } else {
                break;
            }
        }
        while (temp != null) {
            if (!temp.getNum().equals(num)) {
                temp = temp.getChild();
            } else {
                break;
            }
        }
        if (temp != null) {
            this.txta_log.append("X ");
        } else {
            this.txta_log.append("   ");
        }
    }

    public void createButtons() {
        Node temp = gameList.getHead();

        pnl_game.setLayout(new GridLayout(dimension, dimension, 2, 2));
        txta_log.setText("");

        if (dimension < 10) {
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    JButton newButton = new JButton();
                    newButton.setName("" + (char) (j + 65) + (char) (i + 49));
                    newButton.setText("" + (char) (j + 65) + (char) (i + 49));
                    newButton.setSize(15, 15);
                    pnl_game.add(newButton);

                    buttonsListeners(newButton);
                }
            }
        } else {
            int count = 0;//the first number of the 'num' string
            int counter = 0;//amount of second number. When counter hit 10, it is made 0 and count is increased one.
            for (int i = 0; i < dimension; i++) {
                counter++;
                if (counter == 10) {
                    counter = 0;
                    count++;
                }
                for (int j = 0; j < dimension; j++) {

                    JButton newButton = new JButton();

                    if (count != 0) {
                        newButton.setName("" + (char) (j + 65) + String.valueOf(count) + (char) (i + 49 - (count * 10)));
                        newButton.setText("" + (char) (j + 65) + String.valueOf(count) + (char) (i + 49 - (count * 10)));
                    } else {
                        newButton.setName("" + (char) (j + 65) + (char) (i + 49));
                        newButton.setText("" + (char) (j + 65) + (char) (i + 49));
                    }

                    newButton.setSize(15, 15);
                    pnl_game.add(newButton);
                    buttonsListeners(newButton);

                }
            }
        }
    }

    //**********************************************************************************

   
    private void buttonsListeners(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isClicked) {
                    if (clickedButton.getName().equals(button.getName())) {//if player select the same button, this condition is activated.
                        searchPossibleOnes(button);
                        button.setBackground(null);
                        clickedButton = null;
                    } else if (button.getBackground().getRGB() == (-256)) {//getRGB returns yellow color integer value. it control whether current button is yellow or not 
                        changeButtonColor(clickedButton, true);
                        button.setBackground(null);
                        movement(button);// applying the movement
                        txta_log.append("\nFirst selected peg is: "+clickedButton.getName());
                        txta_log.append("\nPeg moved to :"+button.getName());
                        txta_log.append("\nOperation is successful. You can continue");
                        regulateAllButtons();// remain yellow ones will made unvisible
                        if (!isTherePossibleMove()) {
                            JOptionPane.showMessageDialog(rootPane, "There is no available move. game is concluded.\n\n\tRemain peg: "+remain, "Information", JOptionPane.INFORMATION_MESSAGE);
                        }
                        regulateAllButtons();

                    } else {
                        txta_log.append("\nThis movement is not valid");
                        regulateAllButtons();//for invalid decision, all yellow buttons are recovered
                        clickedButton = null;
                    }
                    isClicked = false;
                } else {//fisrt or after second click condition
                    button.setBackground(Color.RED);
                    isClicked = true;
                    clickedButton = button;
                    searchPossibleOnes(button);

                }

            }
        });
    }

    private void searchPossibleOnes(JButton button) {
        String buttonName = button.getName();
        String num = "";
        char word = buttonName.charAt(0);
        for (int i = 1; i < buttonName.length(); i++) {
            num += buttonName.charAt(i);
        }

        boolean left = false;
        boolean right = false;
        boolean up = false;
        boolean down = false;

        int upDownC = Integer.parseInt(num);
        if (!(word <= 'B') && goToButton((char) (word - 1), num).isVisible()) {//the second left line button from current button must not be lower than B and the next node must be visible. 
            left = isPossible((char) (word - 2), num);

        }
        if (!((char) (word + 2) > (char) (65 + dimension - 1)) && goToButton((char) (word + 1), num).isVisible()) {//the second right line button from current button must be suitable for interval and the next node must be visible.
            right = isPossible((char) (word + 2), num);
        }
        if (upDownC + 2 <= dimension && goToButton(word, String.valueOf(upDownC + 1)).isVisible()) {//the second abouve button from current button must be existed and the above one must be visible
            down = isPossible(word, String.valueOf(upDownC + 2));
        }
        if (upDownC - 2 > 0 && goToButton(word, String.valueOf(upDownC - 1)).isVisible()) {//the second under button from current button must be existed and the under one must be visible
            up = isPossible(word, String.valueOf(upDownC - 2));
        }

        if (!left && !right && !up && !down) {
            button.setBackground(null);
            txta_log.append("\nThis button does not have any movement chance");
            isClicked = false;
            clickedButton = null;
        }
    }

    private boolean isPossible(char word, String num) {
        JButton temp = goToButton(word, num);
        if (!temp.isVisible()) {
            changeButtonColor(temp, false);

            return true;
        } else {
            if (isClicked && temp.getBackground().getRGB() == (-256)) {//getRGB returns -256 for yellow
                changeButtonColor(temp, isClicked);
                return true;
            }
        }
        return false;
    }

    private boolean isTherePossibleMove() {
        JButton btn;
        this.remain =0;
        for (int i = 0; i < dimension * dimension; i++) {
            btn = (JButton) this.pnl_game.getComponent(i);
            if (btn.isVisible()) {
                if (generalControl(btn)) {
                    return true;
                }
                remain++;
            }
        }
        return false;
    }

    private boolean generalControl(JButton button) {
        String buttonName = button.getName();
        String num = "";
        char word = buttonName.charAt(0);
        for (int i = 1; i < buttonName.length(); i++) {
            num += buttonName.charAt(i);
        }

      
        boolean left = false;
        boolean right = false;
        boolean up = false;
        boolean down = false;

        int upDownC = Integer.parseInt(num);
        if (!(word <= 'B') && goToButton((char) (word - 1), num).isVisible()) {//the second left line button from current button must not be lower than B and the next node must be visible. 
            left = true;
        }
        if (!((char) (word + 2) > (char) (65 + dimension - 1)) && goToButton((char) (word + 1), num).isVisible()) {//the second right line button from current button must be suitable for interval and the next node must be visible.
            right = true;
        }
        if (upDownC + 2 <= dimension && goToButton(word, String.valueOf(upDownC + 1)).isVisible()) {
            down = true;
        }
        if (upDownC - 2 > 0 && goToButton(word, String.valueOf(upDownC - 1)).isVisible()) {
            up = true;
        }

        if (!left && !right && !up && !down) {
           return false;
        }
        return true;
    }

    private void changeButtonColor(JButton btn, boolean isSecondSelection) {
        if (isSecondSelection) {
            btn.setBackground(null);

            btn.setVisible(false);
        } else {//pointer button is colored as yellow.
            btn.setVisible(true);
            btn.setBackground(Color.yellow);

        }
    }

    private Node goToNode(char word, String num) {//spesific node is scanned.
        Node node = gameList.getHead();

        while (node != null) {
            if (node.getWord() != word) {
                node = node.getNext();
            } else {
                break;
            }
        }
        while (node != null) {
            if (!node.getNum().equals(num)) {
                node = node.getChild();
            } else {
                break;
            }
        }

        return node;
    }

    private Node goToFather(char word, String num) {//the father of spesific node is scanned 
        Node node = gameList.getHead();

        while (node != null) {
            if (node.getWord() != word) {
                node = node.getNext();
            } else {
                break;
            }
        }
        int num1 = Integer.parseInt(num);
        if (node != null) {
            if (node.getChild() != null) {
                while (node.getChild() != null) {
                    int num2 = Integer.parseInt(node.getChild().getNum());
                    if (num1 - num2 > 0) {//current node father number must be less than current node number. 
                        node = node.getChild();
                    } else {
                        break;
                    }
                }
                int num3 = Integer.parseInt(node.getNum());
                if (num3 > num1) {//if the chosen one is bigger than current one which is yellow button, it is not possible. If C3 is father and current move is C1, it returns C3, this prevent it.
                    return null;
                }
            } else {//If just there is one element in one column...
                int num2 = Integer.parseInt(node.getNum());
                if (num1 - num2 < 0) {// if it is negative, it should return null. It is not father.
                    node = node.getChild();
                }
            }
        }
        return node;
    }

    private Node goToBefore(char word, String num) {//the left from current node is scanned. 
        Node node = gameList.getHead();
        if (word == node.getWord()) {// if the word is A return head.
            return null;
        }
        while (node.getNext() != null) {
            if (node.getNext().getWord() != word) {
                node = node.getNext();
            } else {
                break;
            }
        }
        

        return node;
    }

    private Node goToHeadBefore(char word, String num) {
        Node node = gameList.getHead();
        if (word == node.getWord()) {// if the word is A return head.
            return node;
        }
        while (node.getNext() != null) {
            if (node.getNext().getWord() != word) {
                node = node.getNext();
            } else {
                break;
            }
        }

        return node;
    }

    private JButton goToButton(char word, String num) {
        int sizeX = word - 'A';
        int sizeY = Integer.parseInt(num) - 1;

        return (JButton) (this.pnl_game.getComponent(sizeX + sizeY * dimension));
    }

    private void regulateAllButtons() {//all yellow buttons turns into unvisible
        this.clickedButton.setBackground(null);
        for (int i = 0; i < dimension * dimension; i++) {
            JButton btn = (JButton) this.pnl_game.getComponent(i);
            if (btn.getBackground().getRGB() == (-256)) {
                btn.setBackground(null);
                btn.setVisible(false);
            }
        }
    }

    private void movement(JButton btn) {
        String currentBtnName = btn.getName();
        String firstSelectedBtnName = clickedButton.getName();

        boolean isFirstAbove = false;
        
        char crWord = currentBtnName.charAt(0);
        char fsWord = firstSelectedBtnName.charAt(0);
        char findWord;
        String crNum = "", fsNum = "", findNum = "";
        int curNum = 0;
        int fslNum = 0;
        for (int i = 1; i < currentBtnName.length(); i++) {
            crNum += currentBtnName.charAt(i);
        }
        for (int i = 1; i < firstSelectedBtnName.length(); i++) {
            fsNum += firstSelectedBtnName.charAt(i);
        }
        curNum = Integer.parseInt(crNum);
        fslNum = Integer.parseInt(fsNum);

        if (crWord - fsWord > 0) {//last selected button is righter than first one. If it is equal to 0, nothing changes.
            findWord = (char) (crWord - 1);
        } else if (crWord - fsWord < 0) {//lefter...
            findWord = (char) (crWord + 1);
        } else {
            findWord = crWord;
        }
        if (curNum - fslNum > 0) {//last selected button is above first one. If it is equal to 0, nothing changes.
            findNum = String.valueOf(curNum - 1);
        } else if (curNum - fslNum < 0) {
            isFirstAbove = true;
            findNum = String.valueOf(curNum + 1);
        } else {
            findNum = String.valueOf(curNum);
        }

        JButton findedBtn = goToButton(findWord, findNum);

        addNode(crWord, crNum);

        deleteNode(fsWord, fsNum);

        clickedButton.setBackground(null);
        clickedButton.setVisible(false);
        btn.setVisible(true);
        btn.setBackground(null);

        deleteNode(findWord, findNum);

        findedBtn.setVisible(false);

        printLinkedList();
    }

    private boolean isHeader(Node node) {
        if (node != null) {
            if (node.getNext() != null || isNexted(node)) {
                return true;
            }
        }
        return false;
    }

    private boolean isHead(Node node) {
        return this.gameList.getHead().equals(node);
    }

    private boolean isNexted(Node node) {//originally, it is created to find the last header.
        //but the existince of left node can be finded. 
        Node temp = this.gameList.getHead();
        while (temp.getNext() != null) {
            if (temp.getNext().equals(node)) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    private void deleteNode(char word, String num) {
        Node temp;
        Node node = goToNode(word, num);

        if (isHeader(node)) {

            if (isHead(node)) {//if it is header and the head.
                if (node.getChild() != null) {
                    node.getChild().setNext(node.getNext());
                    this.gameList.setHead(node.getChild());
                } else {
                    this.gameList.setHead(node.getNext());
                }

            } else {//header but not head. B-C-D etc...
                Node beforeNode = goToHeadBefore(word, num);
                if (node.getChild() != null) {//it is header and have child
                    beforeNode.setNext(node.getChild());
                    node.getChild().setNext(node.getNext());

                } else {//header but does not have child
                    beforeNode.setNext(node.getNext());
                    return;
                }

            }
        } else {//node is not header or head.
            temp = goToFather(word, num);
            temp.setChild(node.getChild());
        }
    }

    private void addNode(char word, String num) {
        Node temp;
        Node node = new Node(word, num, 'X');

        if (isFatherExist(word, num)) {
            temp = goToFather(word, num);
            node.setChild(temp.getChild());
            temp.setChild(node);
        } else {
            temp = goToBefore(word, num);
            if (isColumExisted(word)) {//it is header and there is a node that has the same word.
                if (temp == null) {// if the node that will added will be head node...
                    temp = this.gameList.getHead();
                    node.setNext(temp.getNext());
                    node.setChild(temp);
                    temp.setNext(null);
                    this.gameList.setHead(node);
                } else {
                    node.setChild(temp.getNext());
                    node.setNext(temp.getNext().getNext());
                    temp.setNext(node);
                    node.getChild().setNext(null);
                }
            } else {//it is header and there is no node that has the same word such as B-C-D.
                if (temp == null) {// if the node that will added will be head node... assume B1 is created and the head is C2
                    temp = this.gameList.getHead();
                    node.setNext(temp);
                    this.gameList.setHead(node);
                } else {//if the node that will added can be nexted...
                    node.setNext(temp.getNext());
                    temp.setNext(node);
                }
            }

        }
    }

    private boolean isFatherExist(char word, String num) {
        return (goToFather(word, num) != null);
    }

    private boolean isColumExisted(char word) {
        Node node = gameList.getHead();

        while (node != null) {
            if (node.getWord() != word) {
                node = node.getNext();
            } else {
                break;
            }
        }
        return node != null;

    }

    private void printList(int defa) {//this method is created for printing every element of one column. If your dimension is 6, write parameters from 0 to 5  
        Node node = this.gameList.getHead();
        while (defa > 0) {
            if (node != null) {
                node = node.getNext();
            }
            defa--;
        }
        while (node != null) {
            System.out.print(node.getWord() + node.getNum() + " -> ");
            node = node.getChild();
        }
        System.out.println("Null");
        System.out.println("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_background = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txta_log = new javax.swing.JTextArea();
        pnl_gameBackground = new javax.swing.JPanel();
        pnl_game = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 300));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        pnl_background.setBackground(new java.awt.Color(153, 153, 153));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Log", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        txta_log.setEditable(false);
        txta_log.setColumns(20);
        txta_log.setRows(5);
        jScrollPane2.setViewportView(txta_log);

        pnl_gameBackground.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Game", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        pnl_game.setPreferredSize(new java.awt.Dimension(590, 480));
        pnl_game.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout pnl_gameBackgroundLayout = new javax.swing.GroupLayout(pnl_gameBackground);
        pnl_gameBackground.setLayout(pnl_gameBackgroundLayout);
        pnl_gameBackgroundLayout.setHorizontalGroup(
            pnl_gameBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_gameBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_game, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl_gameBackgroundLayout.setVerticalGroup(
            pnl_gameBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_gameBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_game, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnl_backgroundLayout = new javax.swing.GroupLayout(pnl_background);
        pnl_background.setLayout(pnl_backgroundLayout);
        pnl_backgroundLayout.setHorizontalGroup(
            pnl_backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_backgroundLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(pnl_gameBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        pnl_backgroundLayout.setVerticalGroup(
            pnl_backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_backgroundLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnl_backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_backgroundLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                        .addGap(44, 44, 44))
                    .addGroup(pnl_backgroundLayout.createSequentialGroup()
                        .addComponent(pnl_gameBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(96, 96, 96))))
        );

        getContentPane().add(pnl_background);

        jMenu1.setText("Options");

        jMenuItem1.setText("New Game");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.beginFrame.setVisible(true);
        this.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(MainGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnl_background;
    private javax.swing.JPanel pnl_game;
    private javax.swing.JPanel pnl_gameBackground;
    private javax.swing.JTextArea txta_log;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the dimension
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * @param dimension the dimension to set
     */
    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    /**
     * @return the beginFrame
     */
    public Main getBeginFrame() {
        return beginFrame;
    }

    /**
     * @param beginFrame the beginFrame to set
     */
    public void setBeginFrame(Main beginFrame) {
        this.beginFrame = beginFrame;
    }
}
