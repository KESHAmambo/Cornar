package org.test.controllers.menupage;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Window;
import org.test.customcomponents.menupage.UploadImageBoxImpl;
import org.test.customcomponents.menupage.profilepage.materialspage.FileReceiver;
import org.test.customcomponents.menupage.profilepage.materialspage.ImageReceiver;
import org.test.customcomponents.menupage.profilepage.materialspage.UploadBoxImpl;

/**
 * Created by Taras on 15.12.2016.
 */
public class UploadImageBoxController {
    private final UploadImageBoxImpl uploadBox;
    public UploadImageBoxController(UploadImageBoxImpl uploadBox) {
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

    public void setReceiverToUpload(Upload uploadButton, ImageReceiver receiver){
        receiver.setWindow(uploadBox.getWindow());
        uploadButton.setReceiver(receiver);
        uploadButton.addSucceededListener(receiver);
        uploadButton.addFailedListener(receiver);}
}
