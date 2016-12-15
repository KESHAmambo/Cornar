package org.test.dbservice;

import org.test.customcomponents.menupage.profilepage.materialspage.DocumentBoxImpl;
import org.test.dbservice.dao.*;
import org.test.dbservice.entity.*;
import org.test.dbservice.impl.*;
import org.test.logic.Course;
import org.test.logic.InboxMessage;
import org.test.logic.Lesson;
import org.test.logic.Profile;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class DatabaseServiceImpl implements DatabaseService {
    Logger loggerDB = Logger.getLogger(DatabaseService.class.getName());
    @Override
    public int signUpUser(String firstName, String surname, String email, Date birthDate, String password, String education) {
        int unsuccessOfAddingUser = 1;
        UserDao userDao = new UserDaoImpl();
        if (doesUserExist(email) == true)
            return unsuccessOfAddingUser;
        UsersEntity user = new UsersEntity();
        user.setFirstName(firstName);
        user.setLastName(surname);
        user.setEmail(email);
        user.setPassword(password);
        System.out.println("password");
        System.out.println(password);
        if (birthDate == null) {
             birthDate = new Date();
        }
        user.setBirthDate(new java.sql.Date(birthDate.getTime()));
        user.setPersonDescription(education);
        unsuccessOfAddingUser = userDao.create(user);
        return unsuccessOfAddingUser;
    }

    @Override
    public UsersEntity getUser(String email, String password) {
        UserDao userDao = new UserDaoImpl();
        UsersEntity user = userDao.getByEmailAndPassword(email, password);
        loggerDB.log(Level.INFO, user.getEmail());
        if (user.getUserId() != -1) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public boolean doesUserExist(String email, String password) {
        UsersEntity user = getUser(email, password);
        return user != null;
    }
    @Override
    public boolean doesUserExist(String email) {
        UsersEntity user = getUser(email);
        return user != null;
    }

    private UsersEntity getUser(String email) {
        UserDao userDao = new UserDaoImpl();
        UsersEntity user = userDao.getByEmailForCheck(email);
        loggerDB.log(Level.INFO, user.getEmail());
        if (user.getUserId() != -1) {
            return user;
        } else {
            return null;
        }    }


    private void fulfillProfile(Profile profile, String userEmail) {
        UsersEntity user = new UserDaoImpl().getUserByEmail(userEmail);
        profile = fillProfile(profile, user);
    }

    @Override
    public List<Profile> getAllFriendOfUser(int user_id){
        FriendsDao friendsDao = new FriendsDaoImp();
        List<Profile> friends = new ArrayList<>();
        loggerDB.log(Level.INFO, "find friend of "+ user_id);
        List<FriendsEntity> friendsEntities = friendsDao.getAllFriendBy(user_id);
        loggerDB.log(Level.INFO, String.valueOf(friendsEntities.size()));
        for (FriendsEntity friendFromDB: friendsEntities){
            Profile friend = new Profile();
            UserDao userDao = new UserDaoImpl();
            UsersEntity newFriend = userDao.getByIdForFriends(friendFromDB.getFriendId());
            loggerDB.log(Level.INFO, "friend name " + newFriend.getEmail());
            friend.setName(newFriend.getFirstName());
            friend.setSurname(newFriend.getLastName());
            friend.setEmail(newFriend.getEmail());
            friend.setBirthDate(newFriend.getBirthDate());
            friend.setId(newFriend.getUserId());
            friend.setEducation(newFriend.getPersonDescription());
            ImageDao imageDao = new ImageDaoImpl();
            File image = imageDao.getImageByUserId(friend.getId());
            if (image != null)
                friend.setImageResource(image);
            friends.add(friend);
        }
        return friends;
    }

    @Override
    public byte[] getFileByName(String docName, int ownerId) {
        FilesDao filesDao = new FilesDaoImpl();
        return filesDao.getFileByNameToOwner(docName,ownerId);
    }

    private Profile fillDataToProfile(Profile profile, UsersEntity user){
        profile.setName(user.getFirstName());
        profile.setSurname(user.getLastName());
        profile.setEmail(user.getEmail());
        profile.setId(user.getUserId());
        return profile;
    }

    private Profile fillProfile(Profile profile, UsersEntity user) {
        profile.setName(user.getFirstName());
        profile.setSurname(user.getLastName());
        profile.setEmail(user.getEmail());
        profile.setEducation(user.getPersonDescription());
        profile.setBirthDate(user.getBirthDate());
        profile.setId(user.getUserId());
        ImageDao imageDao = new ImageDaoImpl();
        File image = imageDao.getImageByUserId(user.getUserId());
        if (image != null)
            profile.setImageResource(image);
        profile.setFriends(getAllFriendOfUser(user.getUserId()));
        loggerDB.log(Level.SEVERE, profile.toString());
        return profile;
    }

    @Override
    public List<Profile> getAllUsersWithNameLike(String firstName) {
        UserDao userDao = new UserDaoImpl();
        List<UsersEntity> usersList = userDao.searchUserByName(firstName);
        return createListOfProfileFromUsers(usersList);
    }

    private List<Profile> createListOfProfileFromUsers(List<UsersEntity> usersList) {
        List<Profile> usersProfileList = new ArrayList<>();
        for (UsersEntity user :
                usersList) {
            Profile profileOfUser = new Profile();
            usersProfileList.add(fillProfile(profileOfUser, user));
        }
        return usersProfileList;
    }

    @Override
    public List<Profile> getAllUsersWithSurnameLike(String surnameForSearch) {
        UserDao userDao = new UserDaoImpl();
        List<UsersEntity> usersList = userDao.searchUserBySurname(surnameForSearch);
        return createListOfProfileFromUsers(usersList);
    }

    @Override
    public List<DocumentBoxImpl> pullDocuments(int ownerId) {
        List<DocumentBoxImpl> documents = new ArrayList<>();
        FilesDao fileDao = new FilesDaoImpl();
        List<FilesEntity> files = fileDao.getAllFiles(ownerId);
        for (FilesEntity file : files){
            DocumentBoxImpl document = new DocumentBoxImpl(file.getFileName(),
                                        new Date(file.getCreation_date().getTime()));
            documents.add(document);
        }
        return documents;
    }

    @Override
    public Collection<Course> pullCourses() {
        Set<Course> courses = new HashSet<>();
        CoursesDao coursesDao = new CoursesDaoImpl();
        List<CoursesEntity> entitiesList = coursesDao.getAllCourses(Profile.getCurrentProfile().getId());
        for (CoursesEntity entity: entitiesList){
            Profile tutorProfile = new Profile();
            UsersEntity user = entity.getUser();
            tutorProfile.setId(user.getUserId());
            tutorProfile.setName(user.getFirstName());
            tutorProfile.setSurname(user.getLastName());
            tutorProfile.setEmail(user.getEmail());
            Course course = new Course(tutorProfile, entity.getCourseName(),entity.getCourseDescription());
            course.setId(entity.getCourseId());
            List<Lesson> lessons = pullAllCourseLessons(course);
            if (lessons != null) {
                Set<Lesson> setLessons = new LinkedHashSet<>(lessons);
                course.setLessons(setLessons);
            }
            courses.add(course);
        }
        return courses;
    }

    @Override

    public Course addNewCourse(Course course) {
        CoursesDao courseDao = new CoursesDaoImpl();
        if (course.getName() == null){
            loggerDB.log(Level.SEVERE, null, "Name of course  does not select");
        }
        courseDao.saveCourse(course.getName(),course.getDescription(),course.getTutorProfile().getId());
        return course;
    }

    @Override
    public Lesson addNewLesson(Lesson lesson) {
        LessonsDao dao = new LessonsDaoImpl();
        dao.saveLesson(lesson.getCourse(), lesson.getName(), lesson.getStartDate(), lesson.getEndDate(), lesson.getCost());
        System.out.println(lesson);
        return lesson;
    }

    @Override
    public List<InboxMessage> pullInboxMessages(Profile receiver) {
        List<InboxEntity> messageEntities = new InboxDaoImpl().getMessagesToUser(receiver.getId());
        List<InboxMessage> messages = new ArrayList<>();
        for (InboxEntity entity : messageEntities){
            Profile sender = new Profile();
            UserDao userDao = new UserDaoImpl();
            sender = fillDataToProfile(sender, userDao.getById(entity.getSender_id()));
            InboxMessage message = new InboxMessage(sender, receiver,
                    new Date(entity.getDate().getTime()),
                    entity.getTheme(),
                    entity.getText(),
                    entity.getWasRead());
            messages.add(message);
        }
        return messages;
    }

    @Override
    public Profile getProfile(String email) {
        Profile profile = new Profile();
        fulfillProfile(profile, email);
        return profile;
    }

    @Override
    public void storeInboxMessage(InboxMessage message) {
        InboxDao dao = new InboxDaoImpl();
        dao.saveMessage(message);
    }

    public List<Lesson> pullAllCourseLessons(Course course){
        List<Lesson> lessons = new ArrayList<>();
        CoursesDaoImpl CoursesDao = new CoursesDaoImpl();
        Set<LessonsEntity> lessonsEntities = CoursesDao.getAllLessonByCourse(course);
        for (LessonsEntity lessonEntity: lessonsEntities){
            Lesson lesson = new Lesson();
            lesson.setCourse(course);
            lesson.setId(lessonEntity.getLessonId());
            lesson.setName(lessonEntity.getLessonName());
            lesson.setStartDate(new Date(lessonEntity.getStartDate().getTime()));
            lesson.setEndDate(new Date(lessonEntity.getFinalDate().getTime()));
            lesson.setCost(lessonEntity.getPrice());
            TransactionDao dbTransaction = new TransactionDaoImpl();// not efficient method
            List<TransactionEntity> transactions = dbTransaction.getAllTransactionsToLesson(lesson.getId());
            List<Profile> assignedProfiles = new ArrayList<>();
            for (TransactionEntity transaction : transactions ){
//                UserDao userDao = new UserDaoImpl();
//                UsersEntity user = userDao.getById(transaction.getUserId());
//                Profile student = fillProfile(new Profile(), user);
                Profile student = new Profile();
                student.setId(transaction.getUserId());
                assignedProfiles.add(student);
            }
            lesson.setAssignedStudents(assignedProfiles);
            lessons.add(lesson);
        }
        return lessons;
    }
    @Override
    public List<Lesson> pullAllUserLessons(Profile currentProfile) {
        List<Lesson> lessons = getAllLessonBy(currentProfile.getId());
        List<Lesson> tutorLessons = getAllTutoringLesson(currentProfile);
        lessons.addAll(tutorLessons);
        Set<Course> courses = (Set<Course>) pullCourses();
        for (Course course: courses) {
            if (course.getTutorProfile().equals(currentProfile)){

            }

        }
        for (Lesson lesson:lessons){
            for (Course course: courses) {
                if (lesson.getCourse().equals(course))
                    course.setLessons((Set<Lesson>) lessons);
            }
        }
        return lessons;
    }

    private List<Lesson> getAllTutoringLesson(Profile tutorProfile) {
        LessonsDao lessonDao = new LessonsDaoImpl();
        List<LessonsEntity> lessonEntities = lessonDao.getAllTutorLesson(tutorProfile.getId());
        List<Lesson> lessons = new ArrayList<>();
        for (LessonsEntity entity : lessonEntities){
            Lesson lesson = new Lesson();
            lesson.setId(entity.getLessonId());
            CoursesEntity entityCourse = entity.getCourse();
            Course course = new Course(tutorProfile,
                    entityCourse.getCourseName(),
                    entityCourse.getCourseDescription());
            lesson.setCourse(course);
            lesson.setName(entity.getLessonName());
            lesson.setStartDate(new Date(entity.getStartDate().getTime()));
            lesson.setEndDate(new Date(entity.getFinalDate().getTime()));
            lesson.setCost(entity.getPrice());
            lessons.add(lesson);
        }
        return lessons;
    }

    public Profile fulfillProfile(String userEmail){
        Profile profile = new Profile();
        fulfillProfile(profile, userEmail);
        return profile;
    }

    @Override
    public void saveUserImage(int userId, File image) {
        ImageDao imageDao = new ImageDaoImpl();
        byte[] dataForSaving = new byte[(int)image.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(image);
            fileInputStream.read(dataForSaving);
            fileInputStream.close();
        }catch (FileNotFoundException e){
            loggerDB.log(Level.SEVERE, null, e + " Not found");
        }catch (IOException e) {
            loggerDB.log(Level.SEVERE, null, e.getStackTrace());
        }
        if (dataForSaving != null) {
            imageDao.saveImage(userId, dataForSaving);
            loggerDB.log(Level.INFO,"image has been uploaded");
        }
        else
            loggerDB.log(Level.SEVERE, null, "Your data is cracked");
    }

    public List<Lesson> getAllLessonBy(int userId){
        List<Lesson> lessons = new ArrayList<>();
        TransactionDao dbTransaction = new TransactionDaoImpl();
        List<TransactionEntity> transactions = dbTransaction.getAllTransactionsToUser(userId);
        LessonsDao lessonsDao = new LessonsDaoImpl();
        for (TransactionEntity transaction: transactions) {
            lessons.add(lessonsDao.fillLessonInfoById(transaction.getLessonsId()));
        }
        loggerDB.log(Level.INFO,lessons.toString());
        return lessons;
    }

    @Override
    public void assignProfileToLesson(Lesson lesson, Profile profile) {
        TransactionDao dbTransaction = new TransactionDaoImpl();
        dbTransaction.saveTransaction(lesson.getCourse().getId(), lesson.getId(), profile.getId());
    }

    public void saveFile(String filename, int ownerId) {
        FilesDao filesDao = new FilesDaoImpl();
        File fileToUpload = new File(filename);
        byte[] dataForSaving = new byte[(int)fileToUpload.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(fileToUpload);
            fileInputStream.read(dataForSaving);
            fileInputStream.close();
        }catch (FileNotFoundException e){
            loggerDB.log(Level.SEVERE, null, e);
        }catch (IOException e) {
            loggerDB.log(Level.SEVERE, null, e.getStackTrace());
        }
        if (dataForSaving != null) {
            filesDao.saveFile(filename, dataForSaving, ownerId);
        }
        else
           loggerDB.log(Level.SEVERE, null, "Your data is cracked");
    }

    public void addToFriends(int userId, String friendEmail){
        UserDao userDao = new UserDaoImpl();
        int friendId = userDao.getIdByEmail(friendEmail);
        FriendsDao friendsDao = new FriendsDaoImp();
        friendsDao.addNewFriends(userId, friendId);
        loggerDB.log(Level.INFO, null, "Friends " + friendId + " " + userId + "has been added");
    }
}