package org.test.customcomponents.menupage.profilepage.coursespage;

import org.test.logic.Course;
import org.test.logic.Profile;
import org.test.tamplets.menupage.profilepage.coursespage.StudentCourseBox;

/**
 * Created by abara on 18.11.2016.
 */
public class StudentCourseBoxImpl extends StudentCourseBox implements CourseBox {
    private Course course;

    public StudentCourseBoxImpl(Course course) {
        this.course = course;
        fulfillLabels();
    }

    public Course getCourse() {
        return course;
    }

    private void fulfillLabels() {
        courseNameLabel.setValue(course.getCourseName());
        Profile tutorProfile = course.getTutorProfile();
        tutorNameLabel.setValue(tutorProfile.getName() + " " + tutorProfile.getSurname());
        descriptionLabel.setValue(course.getDescription());
    }
}
