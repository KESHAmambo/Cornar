package org.test.customcomponents.menupage.profilepage.materialspage;


import com.vaadin.ui.Notification;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import org.test.dbservice.DatabaseManager;
import org.test.logic.Profile;

import java.io.*;

/**
 * Created by Taras on 20.11.2016.
 */
public class FileReceiver implements Upload.Receiver, Upload.ProgressListener,
                    Upload.SucceededListener, Upload.FailedListener {
    public File fileToUpload;
    ProgressBar progress;

    public ProgressBar getProgress() {
        return progress;
    }
    public void setProgress(ProgressBar progress) {
        this.progress = progress;
    }

    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        FileOutputStream fileOutputStream = null;
        try {
            //TODO add to virtual file system
            fileToUpload = new File(filename);
            fileOutputStream = new FileOutputStream(fileToUpload);
        } catch (FileNotFoundException e) {
            Notification.show("File doesn't exist");
            return null;
        }
        return fileOutputStream;
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent event) {
        Notification.show("Uploaded");
        String fileNameToUpload = fileToUpload.getName();
        int ownerId = Profile.getCurrentProfile().getId();
        DatabaseManager.saveFile(fileNameToUpload, ownerId);
        progress.setVisible(false);
    }

    @Override
    public void uploadFailed(Upload.FailedEvent event) {
        Notification.show("Upload failed",
                Notification.Type.ERROR_MESSAGE);
    }

    @Override
    public void updateProgress(long readBytes, long contentLength) {
        progress.setVisible(true);
        if (contentLength == -1)
            progress.setIndeterminate(true);
        else {
            progress.setIndeterminate(false);
            progress.setValue(((float)readBytes) /
                    ((float)contentLength));
        }
    }
}
