package org.test.customcomponents.menupage.profilepage.materialspage;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import org.test.dbservice.DatabaseManager;
import org.test.logic.Profile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by Taras on 14.12.2016.
 */
public class FileOfUserDownload {
    static public StreamResource getFileStream(String docName) {
        StreamResource.StreamSource source = new StreamResource.StreamSource() {

            public InputStream getStream() {
                byte[] fileData = DatabaseManager.getFileByName(docName, Profile.getCurrentProfile().getId());
                InputStream input = new ByteArrayInputStream(fileData);
                return input;
            }
        };
        StreamResource resource = new StreamResource ( source, docName);
        return resource;
    }
}
