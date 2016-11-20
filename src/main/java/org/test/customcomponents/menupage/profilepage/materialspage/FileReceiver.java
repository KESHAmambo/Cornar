package org.test.customcomponents.menupage.profilepage.materialspage;


import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Taras on 20.11.2016.
 */
public class FileReceiver implements Upload.Receiver, Upload.SucceededListener, Upload.FailedListener {
    public File fileToUpload;
    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        FileOutputStream fileOutputStream = null;
        try {
            //TODO add to virtual file system
            fileToUpload = new File(filename);
            fileOutputStream = new FileOutputStream(fileToUpload);
        } catch (FileNotFoundException e) {
            Notification.show("file dosen't exist");
            return null;
        }
        return fileOutputStream;
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent event) {
        Notification.show("succeed");
    }

    @Override
    public void uploadFailed(Upload.FailedEvent event) {
        Notification.show("Upload failed",
                Notification.Type.ERROR_MESSAGE);
    }

}
