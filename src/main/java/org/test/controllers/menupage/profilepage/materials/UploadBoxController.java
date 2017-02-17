package org.test.controllers.menupage.profilepage.materials;

import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import org.test.customcomponents.menupage.profilepage.materialspage.FileReceiver;
import org.test.customcomponents.menupage.profilepage.materialspage.UploadBoxImpl;

/**
 * Created by Taras on 28.11.2016.
 */
public class UploadBoxController {
    private final UploadBoxImpl uploadBox;

    public UploadBoxController(UploadBoxImpl uploadBox) {
        this.uploadBox = uploadBox;
    }

    public void createListenersForUploads(Upload uploadButton){
        final long UPLOAD_LIMIT = 10000000;
        uploadButton.addStartedListener(new Upload.StartedListener() {
            @Override
            public void uploadStarted(Upload.StartedEvent event) {
                if (event.getContentLength() > UPLOAD_LIMIT) {
                    Notification.show("Too big file",
                            Notification.Type.ERROR_MESSAGE);
                    uploadButton.interruptUpload();
                }
            }
        });

        uploadButton.addProgressListener(new Upload.ProgressListener() {
            @Override
            public void updateProgress(long readBytes, long contentLength) {
                if (readBytes > UPLOAD_LIMIT) {
                    Notification.show("Too big file",
                            Notification.Type.ERROR_MESSAGE);
                    uploadButton.interruptUpload();
                }
            }
        });
    }

    public void setReceiverToUpload(Upload uploadButton, FileReceiver receiver){
        uploadButton.setReceiver(receiver);
        uploadButton.addSucceededListener(receiver);
        uploadButton.addFailedListener(receiver);
        uploadButton.addProgressListener(receiver);
    }
}
