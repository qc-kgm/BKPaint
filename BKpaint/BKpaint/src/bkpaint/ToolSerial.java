package bkpaint;
import java.awt.*;

public class ToolSerial {
    private static Tools Pencil;
    private static Tools Eraser;
    private static Tools Line;
    private static Tools Oval;
    private static Tools Rect;
    private static Tools currenttool;
//    public static final int sPencil =1;
//    public static final int sEraser =2;
//    public static final int sLine=3;
//    public static final int sOval=4;
//    public static final int sRect=5;
    public static Tools newTool(int serial){
        switch (serial)
        {
            case 1: if(Pencil ==null) Pencil =new Tools(serial);
            currenttool= Pencil;
            break;
            case 2: if(Eraser==null) Eraser=new Tools(serial);
            currenttool=Eraser;
            break;
            case 3: if(Line==null) Line=new Tools(serial);
            currenttool=Line;
            break;
            case 4: if(Oval==null) Oval=new Tools(serial);
            currenttool=Oval;
            break;
            case 5: if(Rect==null) Rect=new Tools(serial);
            currenttool=Rect;
            break;
        }
        return currenttool;
    }
}
