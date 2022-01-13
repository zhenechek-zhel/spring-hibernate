package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("firstName1", "lastName1", "email1");
      Car car1 = new Car("WV", 15);
      user1.setCar(car1);
      car1.setUser(user1);

      User user2 = new User("firstName2", "lastName2", "email2");
      Car car2 = new Car("AUDI", 10);
      user2.setCar(car2);
      car2.setUser(user2);

      User user3 = new User("firstName3", "lastName3", "email3");
      Car car3 = new Car("BMW", 13);
      user3.setCar(car3);
      car3.setUser(user3);


      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      User test = userService.getUserFromCar(car2.getModel(), car2.getSeries()).getCar().getUser();

      System.out.println("--------");
      System.out.println(test.toString());
      System.out.println("--------");

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("User's car = " +user.getCar());
         System.out.println("Email = "+user.getEmail());

         System.out.println();
      }

      context.close();
   }
}
