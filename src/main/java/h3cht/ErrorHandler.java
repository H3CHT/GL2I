package h3cht;

import javax.swing.*;
import java.awt.*;

public class ErrorHandler {
    public static void showErrorMessage(Component parent, String errorMessage)
    {
        JOptionPane.showMessageDialog(parent, errorMessage, "[GeoLocation to Image] ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public static void showException(Component parent, Exception exception)
    {
        JOptionPane.showMessageDialog(parent, exception.getMessage(), "[GeoLocation to Image] " + exception.toString(), JOptionPane.ERROR_MESSAGE) ;
    }
}
