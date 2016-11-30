package org.test.customcomponents.menupage.profilepage.materialspage;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import org.test.controllers.menupage.profilepage.materials.UploadBoxController;
import org.test.tamplets.menupage.profilepage.materialspage.UploadBox;

/**
 * Created by Taras on 20.11.2016.
 */
public class UploadBoxImpl extends UploadBox {
    private final UploadBoxController controller;
    public UploadBoxImpl() {
        controller = new UploadBoxController(this);
        FileReceiver receiver = new FileReceiver();
        receiver.setProgress(progressBar);
        controller.setReceiverToUpload(uploadButton, receiver);
        controller.createListenersForUploads(uploadButton);
    }
}
