import com.google.common.annotations.VisibleForTesting;
import com.openkm.sdk4j.exception.*;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by miguelg on 22/07/16.
 */
public class GestorTest {

    GestorDocumentalFacade openKm = new ClientOpenKm();
    GestorDocumentalFacade gestor = new ClientNuxeo();
    GestorDocumentalFacade box = new ClientBox();

    /***************OPENKM***********************/

    @Test
    public void consultarDirectorioOpenKm () throws RepositoryException, UnknowException, WebserviceException, PathNotFoundException, DatabaseException {
        openKm.getDocuments("/N43");
    }
    @Test
    public void createDocumentOpenKm() throws RepositoryException, UserQuotaExceededException, DatabaseException, UnsupportedMimeTypeException, VirusDetectedException, WebserviceException, FileSizeExceededException, UnknowException, AccessDeniedException, IOException, ItemExistsException, PathNotFoundException, ExtensionException, AutomationException {
        openKm.crearDocumento("/N43", "/home/miguelg/Codigo/gestor-documental/src/main/resources", "f1.txt");
    }

    @Test
    public void consultarDocumentoOpenKm() throws DatabaseException, WebserviceException, RepositoryException, IOException, PathNotFoundException, AccessDeniedException, UnknowException {
        String id="";
        openKm.getDocument(id);
    }

    /***************OPENKM***********************/
    /***************NUXEO***********************/

    @Test
    public void consultarDirectorioNuxeo () throws RepositoryException, UnknowException, WebserviceException, PathNotFoundException, DatabaseException {
        gestor.getDocuments("/");
    }
    @Test
    public void createDocumentNuxeo() throws RepositoryException, UserQuotaExceededException, DatabaseException, UnsupportedMimeTypeException, VirusDetectedException, WebserviceException, FileSizeExceededException, UnknowException, AccessDeniedException, IOException, ItemExistsException, PathNotFoundException, ExtensionException, AutomationException {
        gestor.crearDocumento("/","/home/miguelg/Codigo/gestor-documental/src/main/resources","N43.txt");
    }

    @Test
    public void consultarDocumentoNuxeo() throws DatabaseException, WebserviceException, RepositoryException, IOException, PathNotFoundException, AccessDeniedException, UnknowException {
        gestor.getDocument("3aabef11-e219-4a42-8a0e-2cf80ea1f297");
    }

    @Test
    public void borrarDocumento(){
        gestor.deleteDocument("/Titulo documento.1469183731927");

    }

    /***************NUXEO***********************/



   /******************************BOX*****************************/

   @Test
   public void consultarDirectorioRaizBox () throws RepositoryException, UnknowException, WebserviceException, PathNotFoundException, DatabaseException {
       box.getDocuments("/");
   }

    @Test
    public void consultarDocumentoBox(){
        box.getDocument("75655197046");
    }

    @Test
    public void consultarDocumentoArrayByteBox(){
        box.getArrayByteDocument("75655197046");
    }

    @Test
    public void createDocumentBox() throws RepositoryException, UserQuotaExceededException, DatabaseException, UnsupportedMimeTypeException, VirusDetectedException, WebserviceException, FileSizeExceededException, UnknowException, AccessDeniedException, IOException, ItemExistsException, PathNotFoundException, ExtensionException, AutomationException {
        box.crearDocumento("","/home/miguelg/Codigo/gestor-documental/src/main/resources/","N19.txt");
    }

    @Test
    public void borrarDocumentoBox(){
        box.deleteDocument("75657136914");

    }

    /******************************BOX*****************************/
    /****************PRIVATE**********************/
    private static void leerFichero (InputStream file){
        try {

            System.out.println("Total file size to read (in bytes) : "
                    + file.available());

            int content;
            while ((content = file.read()) != -1) {
                // convert to char and display it
                System.out.print((char) content);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (file != null)
                    file.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
