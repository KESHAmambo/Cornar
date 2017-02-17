package org.test.controllers.menupage.profilepage;

import org.test.customcomponents.menupage.profilepage.CoursesPageImpl;
import org.test.customcomponents.menupage.profilepage.coursespage.CourseBox;
import org.test.customcomponents.menupage.profilepage.coursespage.StudentCourseBoxImpl;
import org.test.customcomponents.menupage.profilepage.coursespage.TutorCourseBoxImpl;
import org.test.dbservice.DatabaseManager;
import org.test.logic.Course;
import org.test.logic.Profile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by abara on 19.11.2016.
 */
public class CoursesPageController {
    private final CoursesPageImpl coursesPage;

    public CoursesPageController(CoursesPageImpl coursesPage) {
        this.coursesPage = coursesPage;
    }

    public Collection<CourseBox> getCourseBoxes() {
        Collection<Course> courses = DatabaseManager.pullCourses();
        Collection<CourseBox> courseBoxes = new ArrayList<>();
        for(Course course: courses) {
            Profile tutorProfile = course.getTutorProfile();
            if(Profile.getCurrentProfile().equals(tutorProfile)) {
                courseBoxes.add(new TutorCourseBoxImpl(course));
            } else {
                courseBoxes.add(new StudentCourseBoxImpl(course));
            }
        }
        return courseBoxes;
    }
}
