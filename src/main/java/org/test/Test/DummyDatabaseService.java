//package org.test.Test;
//
//import org.test.dbservice.DatabaseService;
//import org.test.dbservice.entity.UsersEntity;
//import org.test.logic.Profile;
//
//import java.util.Date;
//
///**
// * Created by abara on 10.11.2016.
// */
//public class DummyDatabaseService implements DatabaseService {
//    @Override
//    public int signUpUser(String firstName, String lastName, String email, Date birthDate, String password, String education) {
//        return 0;
//    }
//
//    @Override
//    public UsersEntity getUser(String email, String password) {
//        return null;
//    }
//
//    @Override
//    public boolean doesUserExist(String email, String password) {
//        return DummyDatabase.doesUserExist(email, password);
//    }
//
//    @Override
//    public void fulfillProfile(Profile profile, String userEmail) {
//        DummyUser user = DummyDatabase.getUserByEmail(userEmail);
//        if(user != null) {
//            profile.setId(user.getId());
//            profile.setName(user.getName());
//            profile.setSurname(user.getSurname());
//            profile.setBirthDate(user.getBirthDate());
//            profile.setEducation(user.getEducation());
//            profile.setEmail(user.getEmail());
//        }
//    }
//}
