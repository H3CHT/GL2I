package h3cht;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageHandler {
    private final Component parent;
    public ImageHandler(Component parent)
    {
        this.parent = parent;
    }

    private Metadata getMetadata(File image)
    {
        Metadata metadata = null;
        try
        {
            metadata = ImageMetadataReader.readMetadata(image);
        }
        catch(IOException | ImageProcessingException exception)
        {
            ErrorHandler.showException(parent, exception);
        }
        return metadata;
    }

    private Tag[] getGeoTags(Metadata metadata)
    {
        ArrayList<Tag> tags = new ArrayList<>();
        for(Directory directory : metadata.getDirectories())
        {
            if(directory.getName().equalsIgnoreCase("GPS"))
            {
                tags.addAll(directory.getTags());
            }
        }
        return tags.toArray(new Tag[]{});
    }

    public TaggedImage getTaggedImage(File image)
    {

        return new TaggedImage(image, getGeoTags(getMetadata(image)));
    }

    public void drawString(TaggedImage taggedImage) throws IOException
    {
        if(taggedImage.hasValidTags())
        {
            File imageFile = taggedImage.getImage();
            BufferedImage image = ImageIO.read(imageFile);
            Graphics graphics = image.getGraphics();
            int size = image.getHeight() / 50;
            graphics.setFont(graphics.getFont().deriveFont((float) size));
            graphics.drawString(String.format("Lat: %s Long: %s", taggedImage.getLatitude(), taggedImage.getLongitude()),
                    size, image.getHeight() - size);
            graphics.dispose();

            ImageIO.write(image, "jpg",
                    new File(imageFile.getAbsoluteFile().getParent() + "\\" + getStrippedFileName(imageFile.getName()) + "_GL.jpg"));
        }
    }

    private String getStrippedFileName(String fileName)
    {
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }
}
