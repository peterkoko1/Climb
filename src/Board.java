import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * The type Board.
 */
public class Board extends JFrame{
    private static final int ROWS = 20;
    private static final int COLS = 16;
    public Map<Point, Color> gridColors;
    private Color firstColor;
    private Color lastUniqueColor;
    private Point currentStartPoint;

    private JLabel difficultyLabel;

    /**
     * Instantiates a new Board.
     */
    public Board() {
        gridColors = new HashMap<>();
        firstColor = Color.BLUE;
        lastUniqueColor = Color.RED;
        currentStartPoint = null;

        initializeUI();
    }

    public void initializeUI() {
        setTitle("Climb! Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(ROWS, COLS));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                CellPanel panel = new CellPanel(row, col);
                panel.addMouseListener(new CellClickListener());
                boardPanel.add(panel);
            }
        }

        mainPanel.add(boardPanel, BorderLayout.CENTER);

        JButton generateButton = new JButton("Generate Difficulty");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //iterate here to create string with special cases for blue and red to then call the model for prediction
                String difficultyPred = "";
                for(HashMap.Entry <Point,Color> entry : gridColors.entrySet()){
                    Point key = entry.getKey();
                    Color value = entry.getValue();
                    String theX = "";
                    String theY = "";
                    if(key.getX()<10){
                        theX = "0" + theX;
                    }
                    if(key.getY()<10){
                        theY = "0" + theY;
                    }
                    if(value == Color.BLUE){
                        difficultyPred = difficultyPred + "p" + theX + Math.round(key.getX()) + theY + Math.round(key.getY()) + "r42";
                    } else if (value == Color.RED){
                        difficultyPred = difficultyPred + "p" + theX + Math.round(key.getX()) + theY + Math.round(key.getY()) + "r10";
                    } else {
                        difficultyPred = difficultyPred + "p" + theX + Math.round(key.getX()) + theY + Math.round(key.getY()) + "r15";
                    }
                }
                try {
                    RandomForest model = RandomForest.loadModel("C:\\Users\\pkoko\\IdeaProjects\\Climb\\src\\model.ser");
                    difficultyLabel.setText("Difficulty: " + (model.predictFromString(difficultyPred)));

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        generateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Do nothing, let the event pass through
            }
        });

        difficultyLabel = new JLabel("Difficulty: ");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(generateButton);
        buttonPanel.add(difficultyLabel);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private class CellPanel extends JPanel {
        private int row;
        private int col;

        /**
         * Instantiates a new Cell panel.
         *
         * @param row the row
         * @param col the col
         */
        public CellPanel(int row, int col) {
            this.row = row;
            this.col = col;
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Point point = new Point(row, col);
            Color color = gridColors.getOrDefault(point, null);

            if (color != null) {
                setBackground(color);
            } else {
                setBackground(null);
            }
        }
    }
    public class CellClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            CellPanel clickedCell = (CellPanel) e.getComponent();
            Point point = new Point(clickedCell.row, clickedCell.col);

            if (gridColors.containsKey(point)) {
                // If the clicked cell was already selected, clear the grid
                gridColors.clear();
            } else {
                // Set the color for the clicked cell
                if (gridColors.isEmpty()) {
                    // First click, set to blue
                    gridColors.put(point, Color.BLUE);
                } else {
                    // Subsequent clicks, set previous cells to yellow
                    for (Point p : new HashSet<>(gridColors.keySet())) {
                        //System.out.println(gridColors.get(p));
                        if(gridColors.get(p)!=Color.BLUE){
                            gridColors.put(p, Color.YELLOW);
                        }else{
                            gridColors.put(p,Color.BLUE);

                        }
                    }
                    // Set the last clicked cell to red
                    gridColors.put(point, Color.RED);
                }
            }
            repaint();
        }
    }
}

