package h3cht;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI {
    private File[] files;
    boolean alreadyClicked;
    public GUI()
    {
        alreadyClicked = false;
    }

    public void renderBaseGUI(String labelText)
    {
        final JFrame frame = new JFrame("[GeoLocation to Image]");
        frame.setSize(300, 200);
        frame.setLocationByPlatform(true);
        final JLabel welcomeLabel = new JLabel(labelText);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(BorderLayout.CENTER, welcomeLabel);
        final JButton chooseFiles = new JButton("Choose Files");
        frame.getContentPane().add(BorderLayout.SOUTH, chooseFiles);
        chooseFiles.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        files = FileHandler.getFiles(frame);
                        if(files != null)
                        {
                            renderWorkingGUI();
                            frame.dispose();
                        }
                    }
                }
        );

        frame.setVisible(true);
    }

    public void renderWorkingGUI()
    {
        final JFrame frame = new JFrame("[GeoLocation to Image]");
        frame.setSize(300, 200);
        frame.setLocationByPlatform(true);
        final JLabel beginLabel = new JLabel("Select \"Start\" to begin");
        beginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(BorderLayout.CENTER, beginLabel);
        final JButton beginButton = new JButton("Start");
        frame.getContentPane().add(BorderLayout.SOUTH, beginButton);
        beginButton.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!alreadyClicked)
                        {
                            alreadyClicked = true;
                            ImageHandler imageHandler = new ImageHandler(frame);
                            for (File f : files) {
                                try
                                {
                                    TaggedImage taggedImage = imageHandler.getTaggedImage(f);
                                    imageHandler.drawString(taggedImage);
                                }
                                catch(Exception exception)
                                {
                                    ErrorHandler.showException(frame, exception);
                                }
                            }
                        }
                        renderBaseGUI("Success, select more files?");
                        alreadyClicked = false;
                        frame.dispose();
                    }
                }
        );
        frame.setVisible(true);
    }
}
