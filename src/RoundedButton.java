import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {
    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFont(new Font("Segoe UI", Font.BOLD, 16));
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        setOpaque(false);

        // Hover and press effects
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { repaint(); }
            public void mouseExited(java.awt.event.MouseEvent e) { repaint(); }
            public void mousePressed(java.awt.event.MouseEvent e) { repaint(); }
            public void mouseReleased(java.awt.event.MouseEvent e) { repaint(); }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        boolean hover = getModel().isRollover();
        boolean pressed = getModel().isPressed();
        Color c1 = new Color(33, 150, 243);
        Color c2 = new Color(30, 136, 229);
        if (hover) {
            c1 = c1.brighter();
            c2 = c2.brighter();
        }
        if (pressed) {
            c1 = c1.darker();
            c2 = c2.darker();
        }
        GradientPaint gp = new GradientPaint(0, 0, c1, getWidth(), getHeight(), c2);
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No border
    }
}
