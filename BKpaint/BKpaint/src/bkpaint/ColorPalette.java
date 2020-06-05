package bkpaint;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.RGBtoHSB;


public class ColorPalette extends JPanel {
    public ColorButton []colorButtons;
    public static JPanel SelectColorPanel;
    public Color[]colorarray;
    //public static Color SelectColor ;
    public JButton editcolor;
    Icon iconedit= new ImageIcon(getClass().getResource("icon/color-wheel.png"));
    public ColorPalette(){
        //**********
        this.setSize(new Dimension(620,140));
        this.setBackground(Color.lightGray);
        colorarray =new Color[20];
        colorButtons=new ColorButton[20];
        //SelectColor =Color.black;
        colorarray[0]= Color.getHSBColor(160,0,240);
        colorarray[1]= Color.getHSBColor(160,0,0);
        colorarray[2]= Color.getHSBColor(220,240,60);
        colorarray[3]= Color.getHSBColor(160,240,120);
        colorarray[4]= Color.getHSBColor(100,50,60);
        colorarray[5]= Color.getHSBColor(40,240,180);
        colorarray[6]= Color.getHSBColor(120,240,120);
        colorarray[7]= Color.getHSBColor(100,200,60);
        colorarray[8]= Color.getHSBColor(20,240,150);
        colorarray[9]= Color.getHSBColor(160,200,50);
        colorarray[10]= Color.getHSBColor(20,80,170);
        colorarray[11]= Color.getHSBColor(90,210,130);
        colorarray[12]= Color.getHSBColor(10,60,130);
        colorarray[13]= Color.getHSBColor(60,160,70);
        colorarray[14]= Color.getHSBColor(20,230,70);
        colorarray[15]= Color.getHSBColor(160,0,180);
        colorarray[16]= Color.getHSBColor(210,60,180);
        colorarray[17]= Color.getHSBColor(70,220,180);
        colorarray[18]= Color.getHSBColor(40,80,160);
        colorarray[19]= Color.getHSBColor(0,180,180);
        setLayout(new BorderLayout());
        SelectColorPanel=new JPanel();
        SelectColorPanel.setBackground(Color.black);
        SelectColorPanel.setPreferredSize(new Dimension(50,50));
        editcolor =new JButton("Edit Color",iconedit);
//        Label label1=new Label("Edit Color");
//        editcolor.add(label1);
        //editcolor.setPreferredSize(new Dimension(100,100));
        editcolor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==editcolor){
                    
                    Color c=JColorChooser.showDialog(bkbkpaint.PaintApp,"Edit Color",bkbkpaint.DrawPanel.getCurrentColor());
                    if(c != null){
                    bkbkpaint.DrawPanel.setCurrentColor(c);
                    //SelectColor=c;
                    SelectColorPanel.setBackground(c);
                    }
                }
            }
        });
        JPanel palette =new JPanel();

        float a[]=Color.RGBtoHSB(245,247,246,null);
        palette.setBackground(Color.getHSBColor(a[0],a[1],a[2]));
        //palette.setBackground(RGBtoHSB(245,246,247,float []sd));

        palette.setLayout(new GridLayout(2,10));
        for(int i=0;i<20;i++){
            colorButtons[i]=new ColorButton(colorarray[i]);
            palette.add(colorButtons[i]);
        }
        this.add(palette,BorderLayout.CENTER);
        this.add(SelectColorPanel ,BorderLayout.LINE_START);
        this.add(editcolor,BorderLayout.LINE_END);

    }
    public void unselectall(){
        for (ColorButton colorbutton1: colorButtons
             ) {
            colorbutton1.select=false;
        }
    }
    public void paintCompoment(Graphics g){
        super.paintComponent(g);
    }


}
