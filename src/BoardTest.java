import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * This class contains test methods for Board class 
 */
public class BoardTest {
    
    private Board board;

    @BeforeEach
    public void setup() {
        board= new Board();
    }

    /**
     * Test initializeUI method
     */
    @Test
    public void testInitializeUI() {
        board.initializeUI();

        assertEquals("Climb! Board", board.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, board.getDefaultCloseOperation());
        assertEquals(600, board.getWidth());
        assertEquals(500, board.getHeight());
    }

    /**
     * Test CellClickListener mouseClicked method
     */
    @Test
    public void testmouseClicked() {
        
        board.initializeUI();

        MouseEvent me = new MouseEvent(board, 0, 0, 0, 100, 100, 1, false);
        board.new CellClickListener().mouseClicked(me);
        int size = board.gridColors.size();
        
        assertEquals(1, size);
    }
}