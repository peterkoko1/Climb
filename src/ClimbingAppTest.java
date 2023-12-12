import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;


/**
 * JUnit test for the ClimbingApp Class.
 * Specifically testing the 'showLoginScreen' method.
 */
public class ClimbingAppTest {
    
    @Test
    void testShowLoginScreen() {
        // Initialize the ClimbingApp
        ClimbingApp climbingApp = new ClimbingApp();

        // Run the showLoginScreen() method
        climbingApp.showLoginScreen();

        // Retrieve private field 'frame' from the ClimbingApp
        // This is necessary as 'frame' is a private field.
        JFrame frame = null;
        try {
            Field frameField = ClimbingApp.class.getDeclaredField("frame");
            frameField.setAccessible(true);
            frame = (JFrame) frameField.get(climbingApp);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // Testing assertions
        JFrame finalFrame = frame;
        JFrame finalFrame1 = frame;
        JFrame finalFrame2 = frame;
        JFrame finalFrame3 = frame;
        JFrame finalFrame4 = frame;
        JFrame finalFrame5 = frame;
        JFrame finalFrame6 = frame;
        JFrame finalFrame7 = frame;
        assertAll("frame",
                () -> assertNotNull(finalFrame, "Frame Object should not be null after showLoginScreen is called"),
                () -> assertEquals("Login", finalFrame1.getTitle(), "Frame title should be 'Login'"),
                () -> assertEquals(JFrame.EXIT_ON_CLOSE, finalFrame2.getDefaultCloseOperation(), "Default close operation should be EXIT_ON_CLOSE"),
                () -> assertTrue(finalFrame3.isVisible(), "The frame should be set to visible"),
                () -> assertTrue(finalFrame4.isShowing(), "The frame should be currently showing on the screen"),
                () -> assertEquals( new Dimension(300,150), finalFrame5.getSize(), "Frame size should be 300x150"),
                () -> assertTrue(finalFrame6.getContentPane().getLayout() instanceof BorderLayout, "The layout of the content pane should be a BorderLayout"),
                () -> assertEquals(1, finalFrame7.getContentPane().getComponentCount(), "The frame should contain 1 JPanel")
        );
    }
}