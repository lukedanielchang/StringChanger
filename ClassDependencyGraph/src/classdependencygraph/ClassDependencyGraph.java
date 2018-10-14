/*
 * Contains main GUI class and launch method, as well as action handlers to
 * create graph and create order to show dependency
 */
package classdependencygraph;

/**
 *
 * @author ldcha
 */
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ClassDependencyGraph extends JFrame implements ActionListener {

    // declare main JFrame
    private static JFrame mainFrame;

    // declare JPanels
    private static JPanel inputPanel;
    private static JPanel classPanel;
    private static JPanel recompilePanel;

    // declare Labels 
    private static JLabel inputLabel;
    private static JLabel classLabel;
    private static JLabel recompileLabel;

    // declare text fields
    private static JTextField inputText;
    private static JTextField classText;
    private static JTextField recompileText;

    // declare button to perform Sort method
    private static JButton directedGraphBtn;
    private static JButton topologicalBtn;

    // boolean that checks if graph was created
    private static boolean graphCreated;
    private DirectedGraph graph;

    public ClassDependencyGraph() {

        // initialize main frame parameters
        mainFrame = new JFrame("Class Dependency Graph");
        mainFrame.setSize(950, 700);
        mainFrame.setLocation(500, 100);
        mainFrame.setLayout(new FlowLayout());

        // initialize panels
        inputPanel = new JPanel();
        classPanel = new JPanel();
        recompilePanel = new JPanel();

        // add panels to frame
        mainFrame.add(inputPanel, Component.LEFT_ALIGNMENT);
        mainFrame.add(classPanel, Component.LEFT_ALIGNMENT);
        mainFrame.add(recompilePanel, Component.LEFT_ALIGNMENT);

        //set Font
        Font f = new Font("Roboto", Font.PLAIN, 26);

        // initialize Labels
        inputLabel = new JLabel("Input File Name: ");
        classLabel = new JLabel("Class to Recompile: ");
        inputLabel.setFont(f);
        inputLabel.repaint();
        classLabel.setFont(f);
        classLabel.repaint();

        // add Labels to panels
        inputPanel.add(inputLabel);
        classPanel.add(classLabel);

        // initialize text fields and set parameters
        inputText = new JTextField();
        inputText.setPreferredSize(new Dimension(200, 50));
        inputText.setFont(f);
        inputText.repaint();

        classText = new JTextField();
        classText.setPreferredSize(new Dimension(200, 50));
        classText.setFont(f);
        classText.repaint();

        recompileText = new JTextField();
        recompileText.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED), "Recompilation Order"));
        recompileText.setPreferredSize(new Dimension(850, 450));
        recompileText.setEditable(false);
        recompileText.setFont(f);
        recompileText.repaint();

        // add text fields to panels
        inputPanel.add(inputText);
        classPanel.add(classText);
        recompilePanel.add(recompileText);

        // initialize buttons
        directedGraphBtn = new JButton("Build Directed Graph");
        directedGraphBtn.setFont(f);
        directedGraphBtn.repaint();
        inputPanel.add(directedGraphBtn);
        topologicalBtn = new JButton("Topological Order");
        topologicalBtn.setFont(f);
        topologicalBtn.repaint();
        classPanel.add(topologicalBtn);

        // build graph button action listener
        directedGraphBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent a) {
                // holds the name of the file from the input file text field
                String file = inputText.getText();

                // instantiates DirectedGraph
                graph = new DirectedGraph();
                
                // checks if the file can be opened
               if (graph.initializeGraph(file)) {
                    JOptionPane.showMessageDialog(null, "Graph Built Sucessfully");
                    graphCreated = true;
                } else {
                    JOptionPane.showMessageDialog(null, "File Did Not Open");

                    // sets variable to true to validate that the user built the
                    // graph first
                    graphCreated = false;
                }
            }
        });

        // build topological order button action listener
        topologicalBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {

                // resets the recompilationOrder label
                recompileText.setText(" ");

                // retrieves the desired class name
                String className = classText.getText();

                // checks if the user built the graph first
                if (graphCreated) {
                    JOptionPane.showMessageDialog(null, "Graph created");

                    // initializes validClassName to false
                    boolean validClassName;

                    // retrieves the list of valid classes
                    validClassName = DirectedGraph.getValidClasses(className);

                    if (validClassName) {

                        //ArrayList recomp represents recompiled order after topological sort of selected class
                        ArrayList<String> recompiledOrder = null;
                        try {
                            recompiledOrder = graph.topologicalOrder(className);
                        } catch (CycleException.message ex) {
                            Logger.getLogger(ClassDependencyGraph.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        //If recompiled order is not empty
                        if (!recompiledOrder.isEmpty()) {

                            //Set output text to contents of recompiled order of class names
                            recompiledOrder.stream().forEach((classNameOutput) -> {
                                recompileText.setText(recompileText.getText() + " " + classNameOutput);
                            });
                        }
                        //If class is not a valid class entry, display to user	
                    } else if (!validClassName) {
                        JOptionPane.showMessageDialog(null, "Enter Valid Class Name");
                    }
                    //If no class has been entered, display to user   
                } else {
                    JOptionPane.showMessageDialog(null, "You Must Build The Graph First");
                }
            }
        });
    }

    public static void launch() {
        // set GUI to visible and exit on close
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        // launch new GUI
        ClassDependencyGraph newGUI = new ClassDependencyGraph();
        newGUI.launch();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
