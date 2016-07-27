import com.openkm.sdk4j.OKMWebservices;
import com.openkm.sdk4j.OKMWebservicesFactory;
import com.openkm.sdk4j.bean.Document;
import com.openkm.sdk4j.bean.Folder;
import com.openkm.sdk4j.exception.*;
import org.apache.commons.io.IOUtils;

import javax.print.Doc;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by miguelg on 21/07/16.
 */
public class ClientOpenKm extends GestorDocumentalFacade {
    String url = "http://localhost:8080/OpenKM";
    String user = "okmAdmin";
    String pass = "admin";
    String pathRoot = "/okm:root";
    OKMWebservices okm = OKMWebservicesFactory.newInstance(url, user, pass);

    public static void main(String[] args) throws RepositoryException, UnknowException, WebserviceException, PathNotFoundException, DatabaseException {

    }

    public void getFolder(String path) throws PathNotFoundException, RepositoryException, DatabaseException, UnknowException, WebserviceException {
        System.out.println("**********getFolders***********");
        for (Folder fld : okm.getFolderChildren(pathRoot+path)) {
            System.out.println("Fodler -> " + fld.getPath());
        }
    }

    public void getDocuments(String path) throws RepositoryException, UnknowException, WebserviceException, PathNotFoundException, DatabaseException {
        System.out.println("*****getDocuments***********");

                for(Document document:okm.getDocumentChildren(pathRoot+path) ){
                    System.out.println("Documento: "+document.toString());
                }

    }

    public void crearDocumento(String pathKm, String pathFile, String fileName) throws RepositoryException, UserQuotaExceededException, DatabaseException, AutomationException, VirusDetectedException, WebserviceException, FileSizeExceededException, UnknowException, AccessDeniedException, IOException, ItemExistsException, PathNotFoundException, ExtensionException, UnsupportedMimeTypeException {
        System.out.println("*****crearDocumento***********");
        InputStream inputStream = new FileInputStream(pathFile + "/"+fileName);
        Document doc = okm.createDocumentSimple(pathRoot+pathKm+"/"+fileName,inputStream);
        System.out.println("Documento creado: "+doc);
    }

    public InputStream getDocument(String uuid) {

        InputStream inputStream = null;
        try {
            inputStream = okm.getContent(uuid);
        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PathNotFoundException e) {
            e.printStackTrace();
        } catch (AccessDeniedException e) {
            e.printStackTrace();
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (UnknowException e) {
            e.printStackTrace();
        } catch (WebserviceException e) {
            e.printStackTrace();
        }

        return inputStream;


    }

    public void deleteDocument(String path) {

    }
}
