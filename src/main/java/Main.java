import com.openkm.sdk4j.bean.Document;
import com.openkm.sdk4j.exception.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by miguelg on 21/07/16.
 */
public class Main {
    public static void main(String[] args) throws RepositoryException, UserQuotaExceededException, DatabaseException, UnsupportedMimeTypeException, VirusDetectedException, WebserviceException, FileSizeExceededException, UnknowException, AccessDeniedException, IOException, ItemExistsException, PathNotFoundException, ExtensionException, AutomationException {

        //Cliente OPENKM
        //ClientOpenKm openKm = new ClientOpenKm();
        //openKm.getDocuments("/N43");

        //Cliente NUXEO
        GestorDocumentalFacade gestor = new ClientNuxeo();
        //gestor.crearDocumento("/","/home/miguelg/Codigo/gestor-documental/src/main/resources","f1.txt");
        gestor.getDocuments("/");

        InputStream contenido = gestor.getDocument("3aabef11-e219-4a42-8a0e-2cf80ea1f297");
        leerFichero(contenido);

/*        openKm.getFolder("");
        openKm.getDocuments("/N43");
        documentoNuevo = openKm.crearDocumento("/N43", "/home/miguelg/Codigo/gestor-documental/src/main/resources", "f1.txt");
        openKm.getDocuments("/N43");
        InputStream contenido = openKm.getDocument(documentoNuevo.getUuid());
        System.out.println("CONTENIDO DOC NUEVO: "+contenido.toString());*/
    }

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
