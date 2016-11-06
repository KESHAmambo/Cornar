package org.test.customcomponents;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import org.test.tamplets.ProfilePage;

/**
 * Created by abara on 06.11.2016.
 */
public class ProfilePageImpl extends ProfilePage {
    public ProfilePageImpl() {
        educationLabel.setValue("word-wrap: break-word; /* IE  */" +
                "white-space: -moz-pre-wrap; afdfasdfadfasdf/* Firefox */" +
                "dfakjdfkajdfkajdfkajsdfkajsdfaksdf" +
                "adfkadkjf faskdjfals dfadk asdkfjalkdfadf adjf aksdf" +
                "asdkfjakdfjadfkjadf");
    }
}
