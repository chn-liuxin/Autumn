package edu.cuit.autumn;

import edu.cuit.autumn.entity.Teacher;
import edu.cuit.autumn.entity.User;
import edu.cuit.autumn.mapper.TeacherMapper;
import edu.cuit.autumn.service.UserService;
import edu.cuit.autumn.service.impl.UserServiceImpl;
import edu.cuit.autumn.util.AutoID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutumnApplicationTests {

    @Test
    public void userServiceTest() {
        UserService userService = new UserServiceImpl();
        User user = new User();
        short identity = 1;
        user.setUserId(AutoID.getAutoID());
        user.setUserIdentity(identity);
        user.setUserName("liuxin");
        user.setUserPassword("123");
        System.out.println(user.toString());
        User user1 = userService.getUserByName(user.getUserName());
        System.out.println(user1.toString());
    }

    @Test
    public void getTeacherByNameTest() {

    }

}
