package au.com.darkside.xserver.Xext;

import au.com.darkside.xserver.XServer;

import java.io.IOException;

import au.com.darkside.xserver.Client;

public class XKeyboard {
    
    // TODO: opcodes.... /

    /**
     * Process a request relating to the X KEYBOARD extension.
     *
     * @param xServer        The X server.
     * @param client         The remote client.
     * @param opcode         The request's opcode.
     * @param arg            Optional first argument.
     * @param bytesRemaining Bytes yet to be read in the request.
     * @throws IOException
     */
    public static void processRequest(XServer xServer, Client client, byte opcode, byte arg, int bytesRemaining) {
        // Implementation of XKB goes here
    }
}
