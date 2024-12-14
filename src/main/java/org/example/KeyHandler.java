package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    // The state of the key
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    // A function to change the state of the key
    @Override
    public void keyPressed(KeyEvent e)
    {
        // Get what key has been pressed
        int keyCode = e.getKeyCode();

        // If W and 'up' key has been pressed
        if(keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP)
        {
            upPressed = true;
        }

        // If S and 'down' key has been pressed
        if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN)
        {
            downPressed = true;
        }

        // If A and 'left' key has been pressed
        if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT)
        {
            leftPressed = true;
        }

        // If D and 'right' key has been pressed
        if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT)
        {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        // Get what key has been released
        int keyCode = e.getKeyCode();

        // If W and 'up' key has been released
        if(keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP)
        {
            upPressed = false;
        }

        // If S and 'down' key has been released
        if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN)
        {
            downPressed = false;
        }

        // If A and 'left' key has been released
        if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT)
        {
            leftPressed = false;
        }

        // If D and 'right' key has been released
        if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT)
        {
            rightPressed = false;
        }
    }

    public int getDirection()
    {
        if (upPressed)
            return 0;
        if (leftPressed)
            return 1;
        if (downPressed)
            return 2;
        if (rightPressed)
            return 3;

        return -1;
    }

    public boolean isPressed()
    {
        return upPressed||leftPressed||downPressed||rightPressed;
    }

}
