package com.angel.CombatPlatformer;

import com.angel.CombatPlatformer.window.Window;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Window window = Window.getWindow();

        Thread thread = new Thread(window);
        thread.start();
    }
}