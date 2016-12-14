package org.test.dbservice.impl;

import com.vaadin.data.util.filter.Compare;
import org.hibernate.Query;
import org.hibernate.Session;
import org.test.dbservice.DatabaseManager;
import org.test.dbservice.dao.LessonsDao;
import org.test.dbservice.entity.CoursesEntity;
import org.test.dbservice.entity.LessonsEntity;
import org.test.dbservice.entity.UsersEntity;
import org.test.logic.Course;
import org.test.logic.Lesson;
import org.test.logic.Profile;

import java.util.Date;
import java.util.List;

/**
 * Created by Taras on 30.11.2016.
 */
public class LessonsDaoImpl extends AbstractServiceSession implements LessonsDao {
    @Override
    public int create(LessonsEntity entity) {
        Session session;
        session = openCurrentSessionWithTransaction();
        int count = (int) session.save(entity);
        shutdownCurrentSession();
        return 0;
    }

    @Override
    public void delete(LessonsEntity entity) {
        Session session;
        session = openCurrentSessionWithTransaction();
        session.delete(entity);
        shutdownCurrentSession();
    }

    @Override
    public LessonsEntity getById(int id) {
        LessonsEntity entityById = null;
        Session session;
        session = openCurrentSession();
        entityById = (LessonsEntity) session.get(LessonsEntity.class, id);
        shutdownCurrentSession();
        return entityById;
    }

    //TODO  simplify this
    public Lesson fillLessonInfoById(int id){
        System.out.println("lesson id " + id);
        LessonsEntity lessonsEntity;
        Session session;
        session = openCurrentSession();
        lessonsEntity = (LessonsEntity) session.get(LessonsEntity.class, id);
        System.out.println(lessonsEntity.getLessonId() + " " + lessonsEntity.getLessonName());
        Lesson dummyLesson = new Lesson();
        dummyLesson.setId(-1);
        if (lessonsEntity == null)
            return dummyLesson;
        CoursesEntity coursesEntity = lessonsEntity.getCourse();
        UsersEntity user = coursesEntity.getUser();
        Profile tutorProfile = new Profile();
        tutorProfile.setId(user.getUserId());
        tutorProfile.setName(user.getFirstName());
        tutorProfile.setSurname(user.getLastName());
        tutorProfile.setEmail(user.getEmail());
        Course course = new Course(tutorProfile, coursesEntity.getCourseName()
                                        ,coursesEntity.getCourseDescription());
        Lesson lesson = new Lesson();
        lesson.setName(lessonsEntity.getLessonName());
        lesson.setCost(lessonsEntity.getPrice());
        lesson.setStartDate(lessonsEntity.getStartDate());
        lesson.setEndDate(lessonsEntity.getFinalDate());
        lesson.setCourse(course);
        return lesson;
    }

    @Override
    public void update(LessonsEntity entity) {

    }

    @Override
    public void deleteAll() {

    }

    public List<LessonsEntity> getAllLessonByCourse(Course course){
        Session session = openCurrentSession();
        Query query = session.createQuery("select LessonsEntity from LessonsEntity  as l join l.course ");
        List<LessonsEntity> lessonsEntities = query.list();
            System.err.println(lessonsEntities.toString());
        shutdownCurrentSession();
//        Session session = openCurrentSessionWithTransaction();
//        CoursesEntity coursesEntity = new CoursesEntity();
//        coursesEntity.setCourseId(course.getId());
//        //coursesEntity.setCourseName(course.getName());
//        //coursesEntity.setCourseDescription(course.getDescription());
//        Query query = session.createQuery("from LessonsEntity as l where l.course =:Course");
//        query.setParameter("Course", coursesEntity);
//        List<LessonsEntity> lessonsEntities  = query.list();
//        shutdownCurrentSession();
        return lessonsEntities;
    }
    public void saveLesson(Course course, String nameOfLesson, Date startDate, Date finalDate, Long price){
        UsersEntity userTutor = new UsersEntity();
        Session session = openCurrentSessionWithTransaction();
        Query query = session.createQuery("from UsersEntity as u where u.userId=:ID");
        query.setInteger("ID", course.getTutorProfile().getId());
        userTutor = (UsersEntity) query.uniqueResult();
        shutdownCurrentSession();
        LessonsEntity lesson = new LessonsEntity();
        CoursesEntity coursesEntity = new CoursesEntity();
        coursesEntity.setCourseId(course.getId());
        coursesEntity.setCourseName(course.getName());
        coursesEntity.setCourseDescription(course.getDescription());
        coursesEntity.setUser(userTutor);
        lesson.setCourse(coursesEntity);
        lesson.setLessonName(nameOfLesson);
        lesson.setStartDate(new java.sql.Timestamp(startDate.getTime()));
        lesson.setFinalDate(new java.sql.Timestamp(finalDate.getTime()));
        lesson.setPrice(price);
        create(lesson);
    }
}
