package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.metal.MetalScrollBarUI;

public class MyScrollBarUI extends MetalScrollBarUI 
{
	private JButton b = new JButton()
	{
		private static final long serialVersionUID = 1L;

		@Override
        public Dimension getPreferredSize() {
            return new Dimension(0, 0);
        }

    };
	 @Override
	 protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) 
	 {
		 Graphics2D g2 = (Graphics2D) g;
		 g2.setColor(Color.gray);
		 g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
		 g2.setColor(Color.decode("#111111"));
		 g2.setStroke(new BasicStroke(4));
		 g2.drawRect(trackBounds.x, trackBounds.y, trackBounds.width-1, trackBounds.height);
	 }

	 @Override
	 protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds)
	 {
		 Graphics2D g2 = (Graphics2D) g;
		 g2.setColor(Color.DARK_GRAY);
		 g2.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
		 g2.setColor(Color.decode("#cccccc"));
		 g2.setStroke(new BasicStroke(5));
		 g2.drawRect(thumbBounds.x, thumbBounds.y, thumbBounds.width-1, thumbBounds.height);
	 }
	 
	 @Override
	    protected JButton createDecreaseButton(int orientation) {
	        return b;
	    }

	    @Override
	    protected JButton createIncreaseButton(int orientation) {
	        return b;
	    }
}

