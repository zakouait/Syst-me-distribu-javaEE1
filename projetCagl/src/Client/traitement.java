package Client;

import java.io.IOException;
import java.rmi.Remote;

import ejbbdd.Service;

public interface traitement extends Remote {
	public Service traiter(Service S) throws Exception;
}
