package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Muhriddin", "Mamajonov", "mamajonov_muhriddin@mail.ru");
        User user2 = new User("Oleg", "Tinkoff", "mamajonov_muhriddin@mail.ru");
        Car car1 = new Car("Mersedes", 2023);
        Car car2 = new Car("BMW", 2022);
        user1.setCar(car1);
        car1.setUser(user1);
        user2.setCar(car2);
        car2.setUser(user2);


        userService.add(user1);
        userService.add(user2);



        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user + " " + user.getCar());
        }

       System.out.println(userService.getUserByCarModelAndSeries("BMW", 2022));
        System.out.println(userService.getUserByCarModelAndSeries("Mersedes", 2023));


    }
}