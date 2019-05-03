package edu.cuit.autumn.util;

public class AutoID {

    public static String getAutoID() {
        String ID = "";
        String[] chars = {"A","B","C","D","E","F","G","H","I",
            "J","K","L","M","N","O","P","Q","R","S","T","U","V",
            "W","X","Y","Z","0","1","2","3","4","5","6","7","8","9"};
        for (int i = 0; i < 10; i ++) {
            int index = (int)(Math.random() * 36);
            ID = ID + chars[index];
        }
        return ID;
    }

}
