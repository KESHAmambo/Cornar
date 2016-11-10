package org.test.customcomponents.menupage.profilepage.materialspage;

import org.test.tamplets.menupage.profilepage.materialspage.DocumentBox;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by abara on 10.11.2016.
 */
public class DocumentBoxImpl extends DocumentBox {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.yyyy HH:mm", Locale.ENGLISH);

    public DocumentBoxImpl(String docName, Date creationDate) {
        nameLabel.setValue(docName);
        dateLabel.setValue(dateFormat.format(creationDate));
    }
}
