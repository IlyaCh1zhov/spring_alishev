package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
       return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
//        Person person = null;
//
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from Person where id=?");
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//            person = new Person();
//
//
//            person.setId(resultSet.getInt("id"));
//            person.setName(resultSet.getString("name"));
//            person.setAge(resultSet.getInt("age"));
//            person.setEmail(resultSet.getString("email"));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }


        return jdbcTemplate.query("SELECT * from Person where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
//        try {
//            PreparedStatement statement = connection.prepareStatement("INSERT INTO Person values (1, ?, ?, ?)");
//            statement.setString(2 , person.getName());
//            statement.setInt(3 , person.getAge());
//            statement.setString(4 , person.getEmail());
//
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        jdbcTemplate.update("INSERT INTO Person(name, age,email) values ( ?, ?, ?)",
                person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person person) {
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "UPDATE Person set name =?, age =?, email =? where id=?");
//
//            preparedStatement.setString(1 , person.getName());
//            preparedStatement.setInt(2 , person.getAge());
//            preparedStatement.setString(3, person.getEmail());
//            preparedStatement.setInt(4, id);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        jdbcTemplate.update("UPDATE Person set name =?, age =?, email =? where id=?",
                person.getName(), person.getAge(), person.getEmail(), id);
    }

    public void delete(int id) {
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("delete from person where id=?");
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        jdbcTemplate.update("delete from person where id=?", id);
    }

    public void testMiltipleUpdate() {
        List<Person> people = create1000People();
    }

    private List<Person> create1000People() {
        List<Person> people = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            people.add(new Person(i, "Name " + i, 10 + i, "test"+i+"@email.test"));
        }

        return people;
    }

    public void testBatchUpdate() {
    }
}
