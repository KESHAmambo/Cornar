package org.test.customcomponents.menupage.profilepage.materialspage;

import com.vaadin.server.FileResource;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Window;
import org.test.dbservice.DatabaseManager;
import org.test.logic.Profile;
import org.test.utils.UIHelper;

import java.io.*;

/**
 * Created by Taras on 15.12.2016.
 */
public class ImageReceiver implements Upload.Receiver,
        Upload.SucceededListener, Upload.FailedListener{

    public File imageToUpload;
    public Image image;
    public Window window;
    private String filename;
    ByteArrayOutputStream os =
            new ByteArrayOutputStream(10240);

    public void setWindow(Window window) {
        this.window = window;
    }


    public Image getImage() {
        return image;
    }
    public void setImage(Image image){
        this.image = image;
    }

    @Override
    public void uploadFailed(Upload.FailedEvent event) {
    }

    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        FileOutputStream fileOutputStream = null;
        this.filename = filename;
        try {
            imageToUpload = new File(filename);
            fileOutputStream = new FileOutputStream(imageToUpload);
        } catch (FileNotFoundException e) {
            Notification.show("Image doesn't exist");
            return null;
        }
        return fileOutputStream;
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent event) {
        image.setVisible(true);
        image.setSource(new FileResource(imageToUpload));
        DatabaseManager.saveImage(Profile.getCurrentProfile().getId(), imageToUpload);
        //TODO update profile image
        Profile.getCurrentProfile().setImageResource(imageToUpload);
        image.markAsDirty();
        UIHelper.showSuccessNotification("Image  has uploaded", "addedCourseNotification");
    }
}
