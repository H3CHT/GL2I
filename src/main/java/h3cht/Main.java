package h3cht;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ImageProcessingException, IOException {
        GUI gui = new GUI();
        gui.renderBaseGUI("Welcome to GL2I, please select your files");

    }
}
/*
[directory.getName()] - tag.getTagName() = tag.getDescription()
[GPS] - GPS Version ID = 2.300
[GPS] - GPS Latitude Ref = N
[GPS] - GPS Latitude = 50° 49' 8,59"
[GPS] - GPS Longitude Ref = W
[GPS] - GPS Longitude = 0° 8' 12,45"
[GPS] - GPS Altitude Ref = Sea level
[GPS] - GPS Altitude = 0 metres
[GPS] - GPS Status = Active (Measurement in progress)
 */