//UGonzalez

    /*BlackJack program
    simulates a game of blackjack with simple rules
    some future enhancements would be to cover up the first card of the 
    dealer and maybe some animations for the cards instad of them just
    teleporting there, maybe add a picture to the cards*/

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;

// card class holds the name and value of the card
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

//bank class holds the values for the total amount of money and the current bet amount
class Bank {
    private int total;
    private int bet;
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public int getBet() {
        return bet;
    }
    public void setBet(int bet) {
        if (bet < 25){
            this.bet = 25;

        }else{
            this.bet = bet;
        }
    }
    public Bank() {
        setTotal(500);
        setBet(50);
    }
}


class GamePanel extends JPanel implements KeyListener{
    private Bank b;
    private String money;
    private String bet;
    private ArrayList<Card> house;
    private ArrayList<Card> myHand;
    private String hTotalVal;
    private String myTotalVal;
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
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    // creates the key controls to increase or decrease the bet amount
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            b.setBet(b.getBet() + 5);
            bet = String.format("Bet= %d", b.getBet());

        }else if (e.getKeyCode() == KeyEvent.VK_A) {
            b.setBet(b.getBet() - 5);
            bet = String.format("Bet= %d", b.getBet());
        }else if (e.getKeyCode() == KeyEvent.VK_W) {
            b.setBet(b.getBet() + 25);
            bet = String.format("Bet= %d", b.getBet());
        }else if (e.getKeyCode() == KeyEvent.VK_S) {
            b.setBet(b.getBet() - 25);
            bet = String.format("Bet= %d", b.getBet());
        }
        repaint();
    }
    public GamePanel(ArrayList<Card> house, ArrayList<Card> myHand, Bank b) {
        this.house = house;
        this.myHand = myHand;
        this.b = b;
        setFocusable(true);
        addKeyListener(this);
        bet = "bet =";
        money = "Money=";
        hTotalVal = "Total= ";
        myTotalVal = "Total= ";
    }
    public void paintComponent(Graphics g) {
        int y = 10;
        int x = 10;
        int strX = 50; 
        String hval = Integer.toString(getHVal());
        String mval = Integer.toString(getMyVal());
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
        bet = String.format("Bet= %d", b.getBet());
        money = String.format("Money= %d", b.getTotal());
        hTotalVal = "Total= " + hVal ;
        myTotalVal = "Total= " + myVal;
        g.drawString(hTotalVal, 100, 250);
        g.drawString(myTotalVal, 100, 650);
        g.drawString(money, 500, 650);
        g.drawString(bet, 500, 630);
    

    }

}

class GameFrame extends JFrame {
    private Bank b;
    private GamePanel gpan;
    private ArrayList<Card> myHand;
    private ArrayList<Card> house;
    private ArrayList<Card> cards;
    private int bet;
    private int money;
    private int myVal;
    private int hVal;
    // calculates the total val of both arrays
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
        /**the edit menu houses the options to increase the the total 
         * amount of money you play with
         */
		mnuFile.add(miExit);
        bar.add(mnuFile);
        JMenu mnuEdit = new JMenu("Edit");
        JMenuItem miTotalHigh = new JMenuItem("Total High");
        miTotalHigh.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                b.setTotal(1000);
                repaint();
            }
        });
        mnuEdit.add(miTotalHigh);
        JMenuItem miTotalMed = new JMenuItem("Total Med");
        miTotalMed.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                b.setTotal(500);
                repaint();
            }
        });
        mnuEdit.add(miTotalMed);
        JMenuItem miTotalLow = new JMenuItem("Total Low");
        miTotalLow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                b.setTotal(100);
                repaint();
            }
        });
        mnuEdit.add(miTotalLow);

        bar.add(mnuEdit);
		setJMenuBar(bar);
	}  

    public void configureUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(10,10,800,800);
        setTitle("BlackJack V1.0");
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        gpan = new GamePanel(house, myHand, b);
        c.add(gpan,BorderLayout.CENTER);

        JPanel panSouth = new JPanel();
        panSouth.setLayout(new FlowLayout());
        /**When pressed the user will get another card and if you 
         * exceed 21 you loose and calculates amount won or lost also
         * clears the arrays to start another hand
         * if you dont exceed 21 you can continue to hit or stand
         */
		JButton btnHit = new JButton("HIT");
		panSouth.add(btnHit);
		btnHit.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
                    Card draw = null;
                    Collections.shuffle(cards);
                    myHand.add(cards.get(0));
                    repaint();
                    if (getMyVal() == 21) {
                        JOptionPane.showMessageDialog(null, "BlackJack!\nYou Win!"); 
                        b.setTotal(b.getTotal() + b.getBet());
                        house.clear();
                        myHand.clear();
                        Collections.shuffle(cards);
                        for (int i = 0; i < 2; i++) {
                            draw = cards.get(i);
                            myHand.add(draw);
                        }
                        Collections.shuffle(cards);
                        for (int i = 0; i < 2; i++) {
                            draw = cards.get(i);
                            house.add(draw);
                        }
                        repaint(); 
                    }else if (getMyVal() > 21) {
                        JOptionPane.showMessageDialog(null, "Over 21\nIts A Bust"); 
                        b.setTotal(b.getTotal() - b.getBet()); 
                        house.clear();
                        myHand.clear();
                        Collections.shuffle(cards);
                        for (int i = 0; i < 2; i++) {
                            draw = cards.get(i);
                            myHand.add(draw);
                        }
                        Collections.shuffle(cards);
                        for (int i = 0; i < 2; i++) {
                            draw = cards.get(i);
                            house.add(draw);
                        }
                        repaint();
                    }

				}
			}
        );
        /** gives the Stand Button the ability to calculate if you
         * won or not and to clear the dealer and users array in order
         * to start another hand
         * claculates the amount won or lost
         */
        JButton btnStand = new JButton("STAND");
        panSouth.add(btnStand);
        btnStand.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Card draw = null;
                    while (getHVal() < 18){
                        draw = cards.get(0);
                        house.add(draw);
                        repaint();
                    }
                    if (getMyVal() > getHVal() && getMyVal() < 22) {
                        
                        JOptionPane.showMessageDialog(null, "You Win"); 
                        b.setTotal(b.getTotal() + b.getBet());
                    }else{
                        JOptionPane.showMessageDialog(null, "You Lost"); 
                        b.setTotal(b.getTotal() - b.getBet());
                    }
                    house.clear();
                    myHand.clear();
                    Collections.shuffle(cards);
                    for (int i = 0; i < 2; i++) {
                        draw = cards.get(i);
                        myHand.add(draw);
                    }
                    Collections.shuffle(cards);
                    for (int i = 0; i < 2; i++) {
                        draw = cards.get(i);
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
        b = new Bank();
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
        int y;

        //creates the Card array for the deck
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
                //y = 1000;
                cards.add(new Card(name, val));
            
        }
        //fills two Card arrays for the dealer and the user
        Collections.shuffle(cards);
        for (int i = 0; i < 2; i++) {
            draw = cards.get(i);
            myHand.add(draw);
        }
        Collections.shuffle(cards);
        for (int i = 0; i < 2; i++) {
            draw = cards.get(i);
            house.add(draw);
        }

        GameFrame frm = new GameFrame(cards, myHand, house);
        frm.setVisible(true);
        // shows the rules and controls
        JOptionPane.showMessageDialog(null, "Table Rules:\ndefault bet of 50\n"
            + "minimum bet of 25\ncan change the total in the edit menu\n to 100 ,500, or 1000\n"
            + "Q A change bet by 5\nW S change bet by 25\nDealer will continue until he is over 17");
    }
}