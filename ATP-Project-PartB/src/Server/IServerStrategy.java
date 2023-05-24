package Server;

import java.io.InputStream;
import java.io.OutputStream;

/**This is the server strategy interface that all the class that implement a server strategy implement**/
public interface IServerStrategy {
    void applyStrategy(InputStream inFromClient, OutputStream outToClient);

}
