package net.remgack.testmod.client;

public class ClientHighData {
    private static int playerHigh;

    public static void set(int high) {
        ClientHighData.playerHigh = high;
    }

    public static int getPlayerHigh() {
        return playerHigh;
    }
}