package edu.wpi.tmathmeyer.swing.field;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class JSearchPasswordField extends JIconPasswordField implements FocusListener {

    private String textWhenNotFocused;

    /**
     * 
     * @param greyMSG the default grey message
     */
    public JSearchPasswordField(String greyMSG){
    	this(greyMSG, 0);
    }
    
    /**
     * 
     * @param i the length of the password box
     */
    public JSearchPasswordField(int i){
    	this("password", i);
    }
    
    /**
     * 
     */
    public JSearchPasswordField(){
    	this("password");
    }
    /**
     * 
     * @param greyMSG the default grey message
     * @param i the length of te password box
     */
    public JSearchPasswordField(String greyMSG, int i) {
        super();
        this.textWhenNotFocused = greyMSG;
        this.addFocusListener(this);
    }

    public String getTextWhenNotFocused() {
        return this.textWhenNotFocused;
    }

    public void setTextWhenNotFocused(String newText) {
        this.textWhenNotFocused = newText;
    }

    @SuppressWarnings("deprecation")
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!this.hasFocus() && this.getText().equals("")) {
            int height = this.getHeight();
            Font prev = g.getFont();
            Font italic = prev.deriveFont(Font.ITALIC);
            Color prevColor = g.getColor();
            g.setFont(italic);
            g.setColor(UIManager.getColor("textInactiveText"));
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

    //FocusListener implementation:
    public void focusGained(FocusEvent e) {
        this.repaint();
    }

    public void focusLost(FocusEvent e) {
        this.repaint();
    }
}
