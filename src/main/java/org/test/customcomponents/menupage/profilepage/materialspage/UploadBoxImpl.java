package org.test.customcomponents.menupage.profilepage.materialspage;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import org.test.tamplets.menupage.profilepage.materialspage.UploadBox;

/**
 * Created by Taras on 20.11.2016.
 */
public class UploadBoxImpl extends UploadBox {

    public UploadBoxImpl() {
        FileReceiver receiver = new FileReceiver();
        receiver.setProgress(progressBar);
        uploadButton.setReceiver(receiver);
        uploadButton.addSucceededListener(receiver);
        uploadButton.addFailedListener(receiver);
        uploadButton.addProgressListener(receiver);
        createListenersForUploads();
    }

    public void createListenersForUploads(){
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
}
