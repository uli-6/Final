//UGonzalez

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

class Deck {

}

class Card {
    private int val;
    private String name;
    public int getVal() {
        return val;
    }
    public void setVal(int val) {
        this.val = val;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Card(String name, int val) {
        setName(name);
        setVal(val);
    }

}

class Hand {
    //private Card cards;
    private int cardCount;
    private int totalVal;
    private String cardNames;
    public int getCardCount() {
        return cardCount;
    }
    public void setCardCount(int cardCount) {
        this.cardCount = cardCount + getCardCount();
    }
    public int getTotalVal() {
        return totalVal;
    }
    public void setTotalVal(int totalVal) {
        this.totalVal = totalVal + getTotalVal();
    }
    public String getCardNames() {
        return cardNames;
    }
    public void setCarnames(String cardNames) {
        this.cardNames = cardNames + getCardNames();
    }
    public Hand(int cardCount, int totalVal, String cardNames) {
        setCardCount(cardCount);
        setTotalVal(totalVal);
        setCarnames(cardNames);
    }



}

class GamePanel extends JPanel {
    private ArrayList<Card> cards;
    private Hand myHand;
    public GamePanel(ArrayList<Card> cards) {
        this.cards = cards;
    }
    public void paintComponent(Graphics g) {
        int x = 10;
        int strX = 50;
        super.paintComponent(g);
        for (Card s : cards) {
            g.drawRoundRect(x, 10, 100, 200, 30, 30);
            g.drawString(Integer.toString(s.getVal()), strX, 190);
            g.drawString(s.getName(), strX, 170);
            x = x + 110;
            strX = strX + 110;

        }
    }
}

class GameFrame extends JFrame {
	private GamePanel gpan;
    private ArrayList<Card> cards;
    public void configureUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(800,800,800,800);
        setTitle("BlackJack V1.0");
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        gpan = new GamePanel(cards);
        c.add(gpan,BorderLayout.CENTER);
        JPanel panSouth = new JPanel();
        panSouth.setLayout(new FlowLayout());
        c.add(panSouth,BorderLayout.SOUTH);
        
    }
    public GameFrame(ArrayList<Card> cards) {
        this.cards = cards;
        configureUI();
    }

}

public class Test {
    public static void main(String[] args) {
        ArrayList<Card> cards = new ArrayList<Card>();
        int val;
        String name;

        for (int i = 1; i < 14; i++) {
            if (i < 9) {
                val = 10;
            }
            val = i;
            if (i == 1) {
                name = "ace";
            }else if (i == 11) {
                name = "Jack";
            }else if (i == 12) {
                name = "Queen";
            }else if (i == 13) {
                name = "King";
            }else {
                name = Integer.toString(i);
            }
            cards.add(new Card(name, val));
        }
        GameFrame frm = new GameFrame(cards);
		frm.setVisible(true);
    }
}