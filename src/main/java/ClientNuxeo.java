import com.openkm.sdk4j.exception.*;
import org.nuxeo.client.api.NuxeoClient;
import org.nuxeo.client.api.objects.Document;
import org.nuxeo.client.api.objects.Documents;
import org.nuxeo.client.api.objects.blob.Blob;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by miguelg on 22/07/16.
 */
public class ClientNuxeo extends GestorDocumentalFacade{
    String url = "http://localhost:8080/nuxeo";
    NuxeoClient nuxeoClient = new NuxeoClient(url, "Administrator", "Administrator").timeout(60).transactionTimeout(60).schemas("*").enableDefaultCache();

    public void getFolder(String path)  {
    nuxeoClient.repository().fetchDocumentRoot();
    }

    public void getDocuments(String path) {
        Document folder = nuxeoClient.repository().fetchDocumentByPath(path);
        Documents docs = folder.fetchChildren();
        for (Document doc: docs.getDocuments()){
            System.out.println(String.format("Doc: Id: %s Name: %s Title: %s Repo: %s Path: %s",doc.getId(),doc.getName(),doc.getTitle(),doc.getRepositoryName(),doc.getPath()));
        }
    }

    public void crearDocumento(String pathKm, String pathFile, String fileName)  {
        nuxeoClient.repository().fetchDocumentByPath(pathKm);
        Document doc = new Document(fileName,"File");
        nuxeoClient.repository().createDocumentByPath(pathKm,doc);

    }

    public InputStream getDocument(String uuid)  {
        Document doc = nuxeoClient.repository().fetchDocumentById(uuid);
        Blob blob = doc.fetchBlob();
        try {
            return new FileInputStream(blob.getFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteDocument(String path) {
        Document docToDelete = nuxeoClient.repository().fetchDocumentByPath(path);
        nuxeoClient.repository().deleteDocument(docToDelete);

    }
}
