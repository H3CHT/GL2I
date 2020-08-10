package h3cht;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class FileHandler {
    public static File[] getFiles(Component parent)
    {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("[GeoLocation to Image] Select Files");
        jfc.setMultiSelectionEnabled(true);
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG/JPG Images", "jpeg", "jpg");
        jfc.addChoosableFileFilter(filter);
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnValue = jfc.showOpenDialog(parent);

        switch(returnValue)
        {
            case JFileChooser.APPROVE_OPTION:
                return jfc.getSelectedFiles();

            case JFileChooser.CANCEL_OPTION:
                ErrorHandler.showErrorMessage(parent, "Did not select file(s)!");
                return null;

            case JFileChooser.ERROR_OPTION:
                ErrorHandler.showErrorMessage(parent, "Something went wrong!");
                return null;

            default:
                ErrorHandler.showErrorMessage(parent, "This error should never show up. Run while you still can!");
                return null;
        }
    }
}
