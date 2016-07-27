import com.openkm.sdk4j.bean.Document;
import com.openkm.sdk4j.exception.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by miguelg on 22/07/16.
 */
public abstract class GestorDocumentalFacade {
    abstract void  getFolder(String path) throws PathNotFoundException, RepositoryException, DatabaseException, UnknowException, WebserviceException;

    abstract void getDocuments(String path) throws RepositoryException, UnknowException, WebserviceException, PathNotFoundException, DatabaseException;

    abstract void crearDocumento(String pathKm, String pathFile, String fileName) throws RepositoryException, UserQuotaExceededException, DatabaseException, AutomationException, VirusDetectedException, WebserviceException, FileSizeExceededException, UnknowException, AccessDeniedException, IOException, ItemExistsException, PathNotFoundException, ExtensionException, UnsupportedMimeTypeException;

    abstract InputStream getDocument(String uuid) ;

    abstract void deleteDocument(String path);

    byte[] getArrayByteDocument(String uuid){
        System.out.println("METODO NO IMPLEMENTADO");
        return null;
    }
}
