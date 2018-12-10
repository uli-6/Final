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
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JButton;

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
    public String toString() {
        return String.format("%s %d", getName(), getVal());
    }

}

class Bank {
    private int total;
    private int remaining;
    private int bet;
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public int getRemaining() {
        return remaining;
    }
    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
    public int getBet() {
        return bet;
    }
    public void setBet(int bet) {
        this.bet = bet;
    }
    public Bank(int total, int remaining, int bet) {
        total = 1000;
        remaining = 1000;
        bet = 25;
    }
}

class Checker {
    private ArrayList<Card> house;
    private ArrayList<Card> myHand;
    public Checker(ArrayList<Card> house, ArrayList<Card> myHand) {
        this.house = house;
        this.myHand = myHand;
    }
    public void winner() {
        int hVal = 0;
        int myVal = 0;
        for (Card c : house) {
            hVal = hVal + c.getVal();
        }
        for (Card c : myHand) {
            myVal = myVal + c.getVal();
        }
        if (myVal > hVal) {
            JOptionPane.showMessageDialog(null, "You Win");  
        }else{
            JOptionPane.showMessageDialog(null, "You Lost"); 
        }
    }
}

class GamePanel extends JPanel {
    private ArrayList<Card> house;
    private ArrayList<Card> myHand;
    public GamePanel(ArrayList<Card> house, ArrayList<Card> myHand) {
        this.house = house;
        this.myHand = myHand;
    }
    public void paintComponent(Graphics g) {
        int y = 10;
        int x = 10;
        int strX = 50;
        super.paintComponent(g);
        for (Card c : house) {
            g.drawRoundRect(x, y, 100, 200, 30, 30);
            g.drawString(Integer.toString(c.getVal()), strX, 190 + y);
            g.drawString(c.getName(), strX, 170 + y);

            x = x + 110;
            strX = strX + 110;
        }

        x = 10;
        strX = 50;
        for (Card c : myHand) {
            y = 400;

            g.drawRoundRect(x, y, 100, 200, 30, 30);
            g.drawString(Integer.toString(c.getVal()), strX, 190 + y);
            g.drawString(c.getName(), strX, 170 + y);
            
            x = x + 110;
            strX = strX + 110;
        }
    

    }

}

class GameFrame extends JFrame {
    private Bank b;
    private GamePanel gpan;;
    private ArrayList<Card> myHand;
    private ArrayList<Card> house;
    private ArrayList<Card> cards;
    private int bet;
    private int myVal;
    private int hVal;
    public int getMyVal(){
        myVal = 0;
        for (Card c : myHand) {
            myVal = myVal + c.getVal();
        }
        return myVal;
    }
    public int getHVal(){
        hVal = 0;
        for (Card c : house) {
            hVal = hVal + c.getVal();
        }
        return hVal;
    }

	public void configureMenu() {
		JMenuBar bar = new JMenuBar();
		JMenu mnuFile = new JMenu("File");
		JMenuItem miExit = new JMenuItem("Exit");
		miExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnuFile.add(miExit);
        bar.add(mnuFile);
        JMenu mnuEdit = new JMenu("Edit");
        

		setJMenuBar(bar);
	}  

    public void configureUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(10,10,800,800);
        setTitle("BlackJack V1.0");
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        gpan = new GamePanel(house, myHand);
        c.add(gpan,BorderLayout.CENTER);

        JPanel panSouth = new JPanel();
		panSouth.setLayout(new FlowLayout());
		JButton btnHit = new JButton("HIT");
		panSouth.add(btnHit);
		btnHit.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
                    Card draw = null;
                    Collections.shuffle(cards);
                    myHand.add(cards.get(0));
                    //cards.remove(0);
                    repaint();
                    if (getMyVal() == 21) {
                        JOptionPane.showMessageDialog(null, "BlackJack!\nYou Win!"); 
                        house.clear();
                    myHand.clear();
                    Collections.shuffle(cards);
                    for (int i = 0; i < 2; i++) {
                        draw = cards.get(i);
                        //cards.remove(i);
                        myHand.add(draw);
                    }
                    Collections.shuffle(cards);
                    for (int i = 0; i < 2; i++) {
                        draw = cards.get(i);
                        //cards.remove(i);
                        house.add(draw);
                    }
                    repaint(); 
                    }else if (getMyVal() > 21) {
                        JOptionPane.showMessageDialog(null, "Over 21\nIts A Bust");  
                        house.clear();
                    myHand.clear();
                    Collections.shuffle(cards);
                    for (int i = 0; i < 2; i++) {
                        draw = cards.get(i);
                        //cards.remove(i);
                        myHand.add(draw);
                    }
                    Collections.shuffle(cards);
                    for (int i = 0; i < 2; i++) {
                        draw = cards.get(i);
                        //cards.remove(i);
                        house.add(draw);
                    }
                    repaint();
                    }

				}
			}
        );
        JButton btnStand = new JButton("STAND");
        panSouth.add(btnStand);
        btnStand.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Card draw = null;
                    if (getHVal() < 18){
                        draw = cards.get(0);
                        house.add(draw);
                        repaint();
                    }
                    if (getMyVal() > getHVal() && getMyVal() < 22) {
                        
                        JOptionPane.showMessageDialog(null, "You Win");  
                    }else{
                        JOptionPane.showMessageDialog(null, "You Lost"); 
                    }
                    house.clear();
                    myHand.clear();
                    Collections.shuffle(cards);
                    for (int i = 0; i < 2; i++) {
                        draw = cards.get(i);
                        //cards.remove(i);
                        myHand.add(draw);
                    }
                    Collections.shuffle(cards);
                    for (int i = 0; i < 2; i++) {
                        draw = cards.get(i);
                        //cards.remove(i);
                        house.add(draw);
                    }
                    repaint();

                }
            }
        );


        c.add(panSouth,BorderLayout.SOUTH);
        configureMenu();
        
    }
    public GameFrame(ArrayList<Card> cards, ArrayList<Card> myHand, ArrayList<Card> house) {
        this.house = house;
        this.myHand = myHand;
        this.cards = cards;
        configureUI();
    }

}

public class BlackJack {
    public static void main(String[] args) {
        ArrayList<Card> cards = new ArrayList<Card>();
        ArrayList<Card> myHand = new ArrayList<Card>();
        ArrayList<Card> house = new ArrayList<Card>();
        int val;
        String name;
        Card draw;

        for (int i = 1; i < 14; i++) {

                if (i > 9) {
                val = 10;
                }else {
                val = i;
                }

                if (i == 1) {
                    name = "ace";
                }else if (i == 11) {
                    name = "Jack";
                }else if (i == 12) {
                    name = "Queen";
                }else if (i == 13) {
                    name = "King";
                }else {
                    name = "";
                }
                cards.add(new Card(name, val));
                /*
                for (int o = 1; o < 5; o ++) {

                }
                */   
            
        }
        Collections.shuffle(cards);
        for (int i = 0; i < 2; i++) {
            draw = cards.get(i);
            //cards.remove(i);
            myHand.add(draw);
        }
        Collections.shuffle(cards);
        for (int i = 0; i < 2; i++) {
            draw = cards.get(i);
            //cards.remove(i);
            house.add(draw);
        }

        GameFrame frm = new GameFrame(cards, myHand, house);
        frm.setVisible(true);
        JOptionPane.showMessageDialog(null, "Table Rules\nminimum bet off 25\n"
            + "no maximum bet");
    }
}