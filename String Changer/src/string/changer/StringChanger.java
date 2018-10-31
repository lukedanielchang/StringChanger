/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package string.changer;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author ldcha
 */
public class StringChanger extends JFrame {

    // create main JFrame
    private static JFrame mainFrame;

    // create JPanels
    private static JPanel mainPanel;
    private static JPanel sortButtonPanel;
    private static JPanel resultPanel;
    private static JPanel sortOrderPanel;
    private static JPanel numTypePanel;

    // create Labels 
    private static JLabel originalLabel;
    private static JLabel sortedLabel;

    // create text fields
    private static JTextField originalText;
    private static JTextField sortedText;

    // create button to perform Sort method
    private static JButton sortButton;

    // create button groups for panels
    private static ButtonGroup sortButtonGroup;

    // create radio buttons
    private static JRadioButton consonantRadioButton;
    private static JRadioButton vowelRadioButton;

    public StringChanger() {

        // set main frame parameters
        mainFrame = new JFrame("Switcheroo");
        mainFrame.setSize(1000, 400);
        mainFrame.setLocation(500, 100);
        mainFrame.setLayout(new GridLayout(5, 1, 5, 5));

        //set fonts
        Font f = new Font("Roboto", Font.PLAIN, 30);

        // create Panels
        mainPanel = new JPanel();
        sortButtonPanel = new JPanel();
        resultPanel = new JPanel();
        sortOrderPanel = new JPanel();
        numTypePanel = new JPanel();

        // add panels to frame
        mainFrame.add(mainPanel);
        mainFrame.add(sortButtonPanel);
        mainFrame.add(resultPanel);
        mainFrame.add(sortOrderPanel);
        mainFrame.add(numTypePanel);

        // create original list label and text input field
        originalLabel = new JLabel("Original List: ");
        originalText = new JTextField(25);
        originalLabel.setFont(f);
        originalLabel.repaint();
        originalText.repaint();
        originalText.setFont(f);
        mainPanel.add(originalLabel);
        mainPanel.add(originalText);

        // create sort button 
        sortButton = new JButton("Swap");
        sortButton.setFont(f);
        sortButton.repaint();
        sortButton.addActionListener((ActionEvent e) -> {
            swapInput();
        });
        sortButtonPanel.add(sortButton);

        // create sorted label and text output field
        sortedLabel = new JLabel("Result: ");
        sortedText = new JTextField(25);
        sortedLabel.setFont(f);
        sortedText.setFont(f);
        sortedLabel.repaint();
        sortedText.repaint();
        sortedText.setEditable(false);
        resultPanel.add(sortedLabel);
        resultPanel.add(sortedText);

        // create button groups and add to button panel
        sortButtonGroup = new ButtonGroup();

        consonantRadioButton = new JRadioButton("Consonants");
        consonantRadioButton.setSelected(true);
        consonantRadioButton.setFont(f);
        consonantRadioButton.repaint();
        sortButtonGroup.add(consonantRadioButton);
        sortOrderPanel.add(consonantRadioButton);

        vowelRadioButton = new JRadioButton("Vowels");
        vowelRadioButton.setFont(f);
        vowelRadioButton.repaint();
        vowelRadioButton.setSelected(false);
        sortButtonGroup.add(vowelRadioButton);
        sortOrderPanel.add(vowelRadioButton);

        // set GUI to exit on close
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void launch() {
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        StringChanger stringChanger = new StringChanger();
        stringChanger.launch();
    }

    public static void swapInput() {
        if (originalText.getText().equals("")) {
            sortedText.setText("Yo I need some input please");
        } else {
            String originalInput = originalText.getText().trim();
            char originalArray[] = originalInput.toCharArray();

            if (consonantRadioButton.isSelected()) {
                ArrayList<Character> conList = new ArrayList<>();
                conList.add('b');
                conList.add('c');
                conList.add('d');
                conList.add('f');
                conList.add('g');
                conList.add('h');
                conList.add('j');
                conList.add('k');
                conList.add('l');
                conList.add('m');
                conList.add('n');
                conList.add('p');
                conList.add('q');
                conList.add('r');
                conList.add('s');
                conList.add('t');
                conList.add('v');
                conList.add('w');
                conList.add('x');
                conList.add('z');
                conList.add('B');
                conList.add('C');
                conList.add('D');
                conList.add('F');
                conList.add('G');
                conList.add('H');
                conList.add('J');
                conList.add('K');
                conList.add('L');
                conList.add('M');
                conList.add('N');
                conList.add('P');
                conList.add('Q');
                conList.add('R');
                conList.add('S');
                conList.add('T');
                conList.add('V');
                conList.add('W');
                conList.add('X');
                conList.add('Z');

                int i = 0;
                int j = originalArray.length - 1;
                while (i < j) {
                    if (!conList.contains(originalArray[i])) {
                        i++;
                        continue;
                    }
                    if (!conList.contains(originalArray[j])) {
                        j--;
                        continue;
                    }
                    char t = originalArray[i];
                    originalArray[i] = originalArray[j];
                    originalArray[j] = t;
                    i++;
                    j--;
                }
                String resultString = Arrays.toString(originalArray);
                resultString = resultString.substring(1, resultString.length() - 1).replace(","," ");
                sortedText.setText(resultString);

            }

            if (vowelRadioButton.isSelected()) {
                ArrayList<Character> vowList = new ArrayList<>();
                vowList.add('a');
                vowList.add('e');
                vowList.add('i');
                vowList.add('o');
                vowList.add('u');
                vowList.add('A');
                vowList.add('E');
                vowList.add('I');
                vowList.add('O');
                vowList.add('U');

                int i = 0;
                int j = originalArray.length - 1;

                while (i < j) {
                    if (!vowList.contains(originalArray[i])) {
                        i++;
                        continue;
                    }

                    if (!vowList.contains(originalArray[j])) {
                        j--;
                        continue;
                    }

                    char t = originalArray[i];
                    originalArray[i] = originalArray[j];
                    originalArray[j] = t;

                    i++;
                    j--;
                }
                String resultString = Arrays.toString(originalArray);
               resultString = resultString.substring(1, resultString.length() - 1).replace("," ,"");
                sortedText.setText(resultString);

            }
        }
    }
}
