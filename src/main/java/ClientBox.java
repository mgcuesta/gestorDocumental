import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.openkm.sdk4j.exception.*;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by miguelg on 25/07/16.
 */
public class ClientBox extends GestorDocumentalFacade {
    private static String TOKEN_DESARROLLADOR = "fZsTcRXAVgHt2NEJGZCpyKGQEdzOximM";
    private static BoxAPIConnection api = new BoxAPIConnection(TOKEN_DESARROLLADOR);

    public void getFolder(String path) throws PathNotFoundException, RepositoryException, DatabaseException, UnknowException, WebserviceException {

    }

    public void getDocuments(String path) throws RepositoryException, UnknowException, WebserviceException, PathNotFoundException, DatabaseException {
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        for (BoxItem.Info itemInfo : rootFolder) {
            System.out.format("[%s] %s\n", itemInfo.getID(), itemInfo.getName());

        }
    }

    public void crearDocumento(String pathKm, String pathFile, String fileName) throws RepositoryException, UserQuotaExceededException, DatabaseException, AutomationException, VirusDetectedException, WebserviceException, FileSizeExceededException, UnknowException, AccessDeniedException, IOException, ItemExistsException, PathNotFoundException, ExtensionException, UnsupportedMimeTypeException {
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        FileInputStream stream = new FileInputStream(pathFile+fileName);
        rootFolder.uploadFile(stream, pathKm + fileName);
        stream.close();
    }

    public InputStream getDocument(String uuid) {

        //String idDoc = "75655197046";
        BoxFile file = new BoxFile(api, uuid);
        BoxFile.Info info = file.getInfo();

        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream("descargado_"+info.getName());
            file.download(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        System.out.println(stream);
        return null;
    }

    public byte[] getArrayByteDocument(String uuid) {

        //String idDoc = "75655197046";
        BoxFile file = new BoxFile(api, uuid);
        BoxFile.Info info = file.getInfo();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        file.download(stream);
        byte[] bytes = stream.toByteArray();
        System.out.println("CONTENIDO FICHERO:");
        try {
            System.out.println(stream.toString(Charset.forName("UTF-8").name()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public void deleteDocument(String idDoc) {
        BoxFile file = new BoxFile(api, idDoc);
        file.delete();
    }

    public static void main(String[] args) {

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        for (BoxItem.Info itemInfo : rootFolder) {
            System.out.format("[%s] %s\n", itemInfo.getID(), itemInfo.getName());

        }
    }


}
