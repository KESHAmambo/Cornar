package org.test.paymentservice;

import org.test.dbservice.DatabaseManager;
import org.test.logic.Lesson;
import org.test.logic.Profile;

/**
 * Created by abara on 27.11.2016.
 */
public class PaymentService {

    private PaymentService() {

    }

    public static boolean payForLesson(Profile profile, Lesson lesson) {
        //TODO
        boolean wasPaymentAccepted = invokePaymentAPI(lesson.getCost());
        if(wasPaymentAccepted) {
            lesson.getAssignedStudents().add(profile);
            DatabaseManager.assignProfileToLesson(lesson, profile);
        }
        return wasPaymentAccepted;
    }

    private static boolean invokePaymentAPI(double cost) {
        //TODO
        return true;
    }
}
