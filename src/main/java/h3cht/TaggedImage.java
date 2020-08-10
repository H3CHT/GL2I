package h3cht;

import com.drew.metadata.Tag;

import java.awt.*;
import java.io.File;

public class TaggedImage {
    private final File image;
    private String latitude;
    private String latitudeRef;
    private String longitude;
    private String longitudeRef;

    public TaggedImage(File image, Tag[] tags)
    {
        this.image = image;
        parseTags(tags);
    }

    public File getImage()
    {
        return image;
    }

    public boolean hasValidTags()
    {
        return latitude != null;
    }

    public void parseTags(Tag[] tags)
    {
        for(Tag t : tags)
        {
            switch (t.getTagName())
            {
                case "GPS Latitude Ref":    latitudeRef = t.getDescription();
                                            break;

                case "GPS Latitude":        latitude = t.getDescription();
                                            break;

                case "GPS Longitude Ref":   longitudeRef = t.getDescription();
                                            break;

                case "GPS Longitude":       longitude = t.getDescription();
                                            break;

                default:                    break;
            }
        }
    }

    public String getLatitude()
    {
        return latitudeRef + latitude;
    }

    public String getLongitude()
    {
        return longitudeRef + longitude;
    }
}
