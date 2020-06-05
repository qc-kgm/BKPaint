package bkpaint;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {
    public Image image;
    private int preX,preY;
    private int startX,startY;
    private int mouseX,mouseY ;
    private boolean isDrawing;
    private Graphics2D g2d;
    protected boolean IsPressed;
    public Tools currentTool;
    public ToolAttribute currentToolAttribute;
    //public Thread luong;
    //public Color background;
    public DrawPanel(){
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(1900,800));
        addMouseListener(this);
        addMouseMotionListener(this);
        IsPressed=false;
        currentTool= ToolSerial.newTool(1);
        currentToolAttribute =new ToolAttribute(Color.black,2);


    }
//    public void startLuong(){
//    luong =new Thread(new Runnable() {
//        @Override
//        public void run() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//    }); 
//    }
    public void drawShapes(Graphics2D graphics2D,Tools currentTool,int pointX1,int pointY1,int pointX2,int pointY2){
        if(currentTool.option==3){
            graphics2D.setStroke(new BasicStroke(currentToolAttribute.StrokeWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
            graphics2D.drawLine(pointX1,pointY1,pointX2,pointY2);
            repaint();
            //return;
        }
        if(currentTool.option==2){
            graphics2D.setColor(Color.white);
            graphics2D.setStroke(new BasicStroke(currentToolAttribute.StrokeWidth*2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
            graphics2D.drawLine(pointX1,pointY1,pointX2,pointY2);
            repaint();
            //return;
        }
        int tempX,tempY;
        int weight,height;
        if (pointX1 >= pointX2)
        {  // pointX2 is left edge
            tempX = pointX2;
            weight = pointX1 - pointX2;
        }
        else
        {   // pointX1 is left edge
            tempX = pointX1;
            weight = pointX2 - pointX1;
        }
        if (pointY1 >= pointY2)
        {  // pointY2 is top edge
            tempY = pointY2;
            height = pointY1 - pointY2;
        }
        else
        {   // pointY1 is top edge
            tempY = pointY1;
            height = pointY2 - pointY1;
        }
//        if(currentTool.option==3){
//            graphics2D.setStroke(new BasicStroke(currentToolAttribute.StrokeWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
//            graphics2D.drawLine(pointX1, pointY1, pointX2, pointY2);
//            repaint();
//            return;
//            
//        }
        if (currentTool.option == 4) {
            graphics2D.setStroke(new BasicStroke(currentToolAttribute.StrokeWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
            graphics2D.drawOval(tempX, tempY, weight, height);
            //repaint();
            repaintRect(pointX1, pointY1, pointX2, pointY2);
            return;
        }
        if(currentTool.option==5){//
            
            
            graphics2D.setStroke(new BasicStroke(currentToolAttribute.StrokeWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
            
            
            graphics2D.drawRect(tempX, tempY, weight, height);              //draw rectangle
            
            
            repaint();                                                      //repaint to properly display stroke
            return;
        }
    }
    // dùng để vẽ hình khi kéo thả
    public void repaintRect(int pointX1,int pointY1,int pointX2,int pointY2){
        int tempX,tempY;
        int weight,height;
        if (pointX1 >= pointX2)
        {
            tempX = pointX2;
            weight = pointX1 - pointX2;
        }
        else
        {
            tempX = pointX1;
            weight = pointX2 - pointX1;
        }
        if (pointY1 >= pointY2)
        {
            tempY = pointY2;
            height = pointY1 - pointY2;
        }
        else
        {
            tempY = pointY1;
            height = pointY2 - pointY1;
        }
        repaint(tempX,tempY,weight+currentToolAttribute.getStrokeWidth(),height+currentToolAttribute.getStrokeWidth());
        //repaint(tempX,tempY,weight,height);
        //System.out.println("ve lai");
    }
    public void setimage (BufferedImage image1)
    {
        image = image1;
        repaint();
    }
    public void setImage(BufferedImage image1)
    {
        int weight = image1.getWidth();
        int height = image1.getHeight();
        image = new BufferedImage(weight,height,BufferedImage.TYPE_INT_ARGB);
        image = createImage(weight, height);
        repaint();
        Graphics graphics = image.getGraphics();  // Graphics context for isDrawing to OSI.
        graphics.setColor(getBackground());
        graphics.fillRect(0, 0, getSize().width, getSize().height);
        graphics.dispose();
    }

    public void clearImage(BufferedImage image)
    {
        Graphics2D g2 = image.createGraphics();
        g2.setColor(Color.white);
        g2.fillRect(0, 0, image.getWidth(), image.getHeight());
        g2.dispose();
        repaint();
    }

    public Color getCurrentColor()
    {
        if (currentTool.option != 2)
        {
            return currentToolAttribute.getColor();
        }
        else
        {
            return getBackground();
        }
    }

    public void setCurrentColor(Color clr)
    {
        currentToolAttribute.setColor(clr);
    }
    public void createblankpanel(){
        if(image==null) {
            image=createImage(getSize().width,getSize().height);
            Graphics g=image.getGraphics();
            g.setColor(Color.white);
            g.fillRect(0,0,getSize().width,getSize().height);
            g.dispose();
        }
    }

    int i=0;
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
        createblankpanel();
        Graphics2D graphics2D=(Graphics2D) grphcs;
        graphics2D.setColor(getCurrentColor());
        graphics2D.drawImage(image, 0,0, this);
        if(isDrawing && currentTool.option != 1 &&currentTool.option !=2){
            drawShapes(graphics2D, currentTool,startX, startY, mouseX, mouseY);
            
            System.out.println(i);
            i=i+1;
        }
//           if(currentTool.option==3){
//               drawShapes(graphics2D,ToolSerial.newTool(3), startX, startY, mouseX, mouseY);
//               System.out.println(i);
//               i=i+1;
//           }
    }
    
//    public void paintCompoment(Graphics g){
//        createblankpanel();
//        Graphics2D g2=(Graphics2D) g;
//        g.drawImage(image,0,0,this);
//        if(isDrawing && currentTool.option != 1 && currentTool.option !=2 ){
//            //g.setColor(getCurrentColor());
//            drawShapes(g2,currentTool,startX,startY,mouseX,mouseY);
//    }
//    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(isDrawing) return;
        preX=startX=e.getX();
        preY=startY=e.getY();
        //Color tem=getCurrentColor();
       
        g2d=(Graphics2D) image.getGraphics();
        g2d.setColor(getCurrentColor());
        //g2d.setBackground(getBackground());
        isDrawing=true;
        System.out.println("press");
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        if(!isDrawing)
            return;
        
        mouseX=e.getX();
        mouseY=e.getY();
        if(currentTool.option!=1 && currentTool.option!=2 ){
            //repaintRect(startX, startY, preX, preY);
            
            drawShapes(g2d,currentTool,startX,startY,mouseX,mouseY);
            //repaintRect(startX, startY, mouseX, mouseY);
        }
        isDrawing=false;
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        g2d.dispose();
        g2d=null;

    }

    
    //  chú ý lỗi : hiện tại phần pressed , color .stroke đang bị lỗi 
    // hình ảnh khong được lưu lại , hạn chế dùng component.getGraphics , dispose() : hủy bỏ đối tượng graphics 
    // error : dòng 217, 62 
    
    
    
    

int a=0;
    @Override
    public void mouseDragged(MouseEvent e) {
        if (!isDrawing)
            return;

        mouseX = e.getX();   // x-coordinate of mouse.
        mouseY = e.getY();   // y=coordinate of mouse.

        if (currentTool.option == 1)
        {
            drawShapes(g2d, ToolSerial.newTool(3), preX, preY, mouseX, mouseY); // A CURVE is drawn as a series of LINEs.
            //repaintRect(preX, preY, mouseX, mouseY);
            //System.out.println("dang ve");
        }

        else if (currentTool.option == 2)
        {   
            drawShapes(g2d, ToolSerial.newTool(2), preX, preY, mouseX, mouseY);
            //repaintRect(preX, preY, mouseX, mouseY);
            //System.out.println("dang tay ");
        }
//        else {
//            //repaintRect(startX,startY,preX,preY);
//            repaintRect(startX,startY,mouseX,mouseY);
//        }
        else if(currentTool.option==3){
            //drawShapes(g2d, currentTool, preX, preY, mouseX, mouseY);
            
//            repaintRect(startX, startY, preX, preY);
//            repaintRect(startX, startY, mouseX, mouseY);
            repaint();
        }
        else if(currentTool.option==4 ){
            //drawShapes(g2d, ToolSerial.newTool(4), preX, preY, mouseX, mouseY);
            //repaintRect(preX, preY, mouseX, mouseY);
            repaint();
        }
        else if(currentTool.option==5){
            //repaintRect(preX, preY, mouseX, mouseY);
            repaint();
        }
        System.out.println(a);
        a=a+1;
        preX=mouseX;
        preY=mouseY;

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
        @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
