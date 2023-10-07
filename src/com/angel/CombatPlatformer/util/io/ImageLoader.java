package com.angel.CombatPlatformer.util.io;

import javax.swing.*;
import java.awt.*;

public class ImageLoader {

    public static Image loadImage(String filePath){
        return new ImageIcon(filePath).getImage();
    }

}
