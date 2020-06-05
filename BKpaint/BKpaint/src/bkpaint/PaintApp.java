package bkpaint;
import javax.swing.*;
import java.awt.*;

//public class PaintApp extends JFrame {
//    public MenuControl menucontrol;
//    public DrawPanel DrawPanel;
//    public ColorPalette ColorPalette;
//    public PaintTool PaintTool;
//    public Stroke Stroke;
//    public JPanel tlx;
//    public PaintApp(){
//        super("BK Paint");
//        //this.setIconImage();
//        menucontrol=new MenuControl();
//        //DrawPanel=new DrawPanel();
//        ColorPalette=new ColorPalette();
//        PaintTool=new PaintTool();
//        Stroke=new Stroke();
//        tlx=new JPanel();
//        tlx.setLayout(new FlowLayout(10,20,10));
//        tlx.setPreferredSize(new Dimension(1900,150));
////        this.add(menucontrol,"North");
////        this.add(PaintTool,"South");
////        this.add(ColorPalette,"West");
////        this.add(Stroke,"East");
////        this.add(DrawPanel,"Center");
//        tlx.add(PaintTool);
//        tlx.add(ColorPalette);
//        tlx.add(Stroke);
//        this.setLayout(new BorderLayout());
//        this.setJMenuBar(menucontrol);
//
//        this.add(tlx,BorderLayout.PAGE_START);
//        this.add(DrawPanel,BorderLayout.CENTER);
//        this.setSize(1900,950);
//
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setVisible(true);
//    }
//}
public class PaintApp extends JPanel{
        //public MenuControl menuControl;
        public ColorPalette ColorPalette;
        public PaintTool PaintTool;
        public Stroke Stroke;
        public PaintApp(){
            ColorPalette=new ColorPalette();
            PaintTool=new PaintTool();
            Stroke=new Stroke();
            this.setLayout(new FlowLayout(10,20,10));
            this.add(PaintTool);
            this.add(ColorPalette);
            this.add(Stroke);  
            
    }
}
