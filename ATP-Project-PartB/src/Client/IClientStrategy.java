package Client;

import java.io.InputStream;
import java.io.OutputStream;

/**This is the client strategy interface that all the client interfaces will implement**/
public interface IClientStrategy {
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);
}
