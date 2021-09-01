package sd;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Person extends Remote{

    void listAllPeople() throws RemoteException, Exception;
}
