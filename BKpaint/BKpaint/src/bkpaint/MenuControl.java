package bkpaint;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuControl extends JMenuBar {
    //các thành phần của menu
    public JMenu File, Home, Help;
    public JMenuItem New, Open, Save, Exit,About;
    public JFileChooser SelectFile=null;

    public MenuControl() {
        File = new JMenu("File");
        Home = new JMenu("Home");
        Help = new JMenu("Help");
        New = new JMenuItem("New");
        Open = new JMenuItem("Open");
        Save = new JMenuItem("Save");
        Exit = new JMenuItem("Exit");
        About=new JMenuItem("About");
        File.add(New);
        File.add(Open);
        File.add(Save);
        File.add(Exit);
        Help.add(About);
        Menuhandler menuhandler = new Menuhandler();
        New.addActionListener(menuhandler);
        Save.addActionListener(menuhandler);
        Exit.addActionListener(menuhandler);
        About.addActionListener(menuhandler);
        this.add(File);
        this.add(Home);
        this.add(Help);

    }
    public void getFileChooser(){
        if(SelectFile==null){
            SelectFile = new JFileChooser();
            FileNameExtensionFilter filefilter=new FileNameExtensionFilter("All","png");
            SelectFile.setFileFilter(filefilter);
        }
    }

    public static BufferedImage getCurrentImage(Component cpn){
        BufferedImage bimage=new BufferedImage(cpn.getWidth(),cpn.getHeight(),BufferedImage.TYPE_INT_BGR);
        cpn.paint(bimage.getGraphics());
        return bimage;
    }

    class Menuhandler implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == New) {
                BufferedImage bimage = new BufferedImage(bkbkpaint.DrawPanel.getWidth(), bkbkpaint.DrawPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
                bkbkpaint.DrawPanel.clearImage(bimage);
               bkbkpaint.DrawPanel.setImage(bimage);
            }
            if (e.getSource() == Open) {
                getFileChooser();
                int a= SelectFile.showOpenDialog(bkbkpaint.DrawPanel);
                if(a==JFileChooser.APPROVE_OPTION){
                    try {
                        bkbkpaint.DrawPanel.setimage(ImageIO.read(SelectFile.getSelectedFile()));
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,"error");
                    }
                }
            }
            if (e.getSource() == Save) {
                getFileChooser();
                int b= SelectFile.showSaveDialog(bkbkpaint.DrawPanel);
                if(b==JFileChooser.APPROVE_OPTION){
                    File f=SelectFile.getSelectedFile();
                    f=new File(f.getAbsolutePath());
                    BufferedImage bimage=getCurrentImage(bkbkpaint.DrawPanel);
                    try {
                        ImageIO.write(bimage,"png",f);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,"error");
                    }
                }
           }
            if (e.getSource() == Exit) {
                System.exit(0);
            }
            if (e.getSource() == About) {
                JOptionPane.showMessageDialog(null, "Code by QC");
            }


        }
    }
}
