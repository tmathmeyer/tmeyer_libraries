package edu.wpi.tmathmeyer.swing.field;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

@SuppressWarnings("serial")
public class JSearchTextField extends JIconTextField implements FocusListener {
    private String textWhenNotFocused;

    /**
     * This sets the length of the bar to be the length of the grey message
     * 
     * @param greyMSG the message to be displayed on the search bar when out of focus
     */
    public JSearchTextField(String greyMSG){
    	this(greyMSG, greyMSG.length());
    }
    
    
    /**
     * This sets the default text to "Search..."
     * 
     * @param i the length that the search bar should be in characters
     */
    public JSearchTextField(int i){
    	this("Search...", i);
    }
    
    /**
     * this sets the default message to "Search..." and the length to 9
     */
    public JSearchTextField(){
    	this("Search...");
    }
    
    /**
     * 
     * @param greyMSG the message to be displayed on the search bar when out of focus
     * @param i the length of the search bar
     */
    public JSearchTextField(String greyMSG, int i) {
        super();
        this.textWhenNotFocused = greyMSG;
        this.addFocusListener(this);
    }

    /**
     * 
     * @return the grey text (non-focused text)
     */
    public String getTextWhenNotFocused() {
        return this.textWhenNotFocused;
    }

    /**
     * 
     * @param newText the text to set as the grey text
     */
    public void setTextWhenNotFocused(String newText) {
        this.textWhenNotFocused = newText;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!this.hasFocus() && this.getText().equals("")) {
            int height = this.getHeight();
            Font prev = g.getFont();
            Font italic = prev.deriveFont(Font.ITALIC);
            Color prevColor = g.getColor();
            g.setFont(italic);
            //g.setColor(UIManager.getColor("textInactiveText"));
            g.setColor(new Color(150,150,150));
            int h = g.getFontMetrics().getHeight();
            int textBottom = (height - h) / 2 + h - 4;
            int x = this.getInsets().left;
            Graphics2D g2d = (Graphics2D) g;
            RenderingHints hints = g2d.getRenderingHints();
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.drawString(textWhenNotFocused, x, textBottom);
            g2d.setRenderingHints(hints);
            g.setFont(prev);
            g.setColor(prevColor);
        }

    }
    
    public void focusGained(FocusEvent e) {
        this.repaint();
    }

    public void focusLost(FocusEvent e) {
        this.repaint();
    }
}
