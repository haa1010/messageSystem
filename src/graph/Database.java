package graph;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.postgresql.util.PSQLException;

/**
 * @author Minh Thong- 20176881
 * This is a class to interact with database
 */
public class Database {
	/**
	 * This is function for create new user in database with phone number and gender
	 * @param number user's phone number
	 * @param gender user's gender
	 * @return execute a query to add new user to database
	 */
	public static ResultSet createNewUser(String number, String gender) {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/oop", "hang", "1")) {
			Statement statement = connection.createStatement();
			return statement.executeQuery("insert into users (phone, gender, state) values (" + "'" + number + "'" + ","
					+ "'" + gender + "'" + ",1)");
		} catch (SQLException e) {
			if (!(e instanceof PSQLException)) {
				System.out.println("Connection failure.");
				e.printStackTrace();
			}
			return null;
		}
	}

	/**
	 * This is function for retrieve data in database
	 * @param data (number of boys, girls, wrong messages, deleted users)
	 * @return list of result (number of boys if data = "boys", number of girls if data = "girls"
	 * 							number of wrong messages if data = "wrong" and number of deleted users if data = "del")
	 */
	public static ArrayList<Integer> getData(String data) {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/oop", "hang", "1")) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from graph");
			ArrayList<Integer> result = new ArrayList<>();
			while (resultSet.next()) {
				result.add((resultSet.getInt(data)));
			}
			return result;
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This function is used to get date to display the graph
	 * @return list of date
	 */
	public static ArrayList<String> getDate() {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/oop", "hang", "1")) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from graph");
			ArrayList<String> result = new ArrayList<>();
			while (resultSet.next()) {
				result.add((resultSet.getString("date")));
			}
			return result;
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This function is used to count the wrong messages of user
	 * @return increase wrong column in database by 1 for each wrong message
	 */
	public static ResultSet countWrong() {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/oop", "hang", "1")) {
			Statement statement = connection.createStatement();
			return statement.executeQuery("Update graph set wrong = wrong + 1 where date = CURRENT_DATE ");
		} catch (SQLException e) {
			if (!(e instanceof PSQLException)) {
				System.out.println("Connection failure.");
				e.printStackTrace();
			}
			return null;
		}
	}

	/**
	 * This is function for checking user information (phone number, gender and state)
	 * @param number user's phone number
	 * @return information of user who has phone number equals number
	 */
	public static ArrayList<String> checkUser(String number) {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/oop", "hang", "1")) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from checkUser('" + number + "')");
			ArrayList<String> result = new ArrayList<>();
			while (resultSet.next()) {
				result.add(resultSet.getString("check_phone"));
				result.add(resultSet.getString("check_gender"));
				result.add(resultSet.getString("check_state"));
			}
			return result;
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This function is used to add phone number of a user when he/she requests 3 numbers into database
	 * @param number user's phone number
	 * @return 3 phone numbers for user above
	 */
	public static ArrayList<String> addSentList(String number) {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/oop", "hang", "1")) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("insert into SentList(Receiver, Sent)\n" + "select '" + number
					+ "'" + ", Phone\n" + "from users u\n"
					+ "where Gender not in (select gender from users where Phone = '" + number + "')\n"
					+ "  and State <> 0\n" + "  and u.phone not in (select sent from sentlist where Receiver = '"
					+ number + "')\n" + "limit 3\n" + "returning sent");
			ArrayList<String> result = new ArrayList<>();
			while (resultSet.next()) {
				result.add(resultSet.getString("sent"));
			}
			return result;
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This is function for changing gender of a user
	 * @param number user's phone number
	 * @return execute a query to update user's gender in database
	 */
	public static ResultSet changeGender(String number) {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/oop", "hang", "1")) {
			Statement statement = connection.createStatement();
			return statement.executeQuery("update users set state = 3 where phone = '" + number + "'");
		} catch (SQLException e) {
			if (!(e instanceof PSQLException)) {
				System.out.println("Connection failure.");
				e.printStackTrace();
			}
			return null;
		}
	}

	/**
	 * This is function for changing state of a user (active, delete or rejoin)
	 * @param number user's phone number
	 * @param state user's state
	 * @return execute a query to update user's state in database
	 */
	public static ResultSet changeState(String number, int state) {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/oop", "hang", "1")) {
			Statement statement = connection.createStatement();
			return statement.executeQuery("select changeState('" + number + "'," + state + ")");
		} catch (SQLException e) {
			if (!(e instanceof PSQLException)) {
				System.out.println("Connection failure.");
				e.printStackTrace();
			}
			return null;
		}
	}

	/**
	 * This function is used to update state of a user who has changed his/her gender
	 * @param number user's phone number
	 * @param gender new gender of user
	 * @return execute a query to update user's state in database
	 */
	public static ResultSet rejoin(String number, String gender) {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/oop", "hang", "1")) {
			Statement statement = connection.createStatement();
			return statement.executeQuery(
					"update users set state = 2, gender = '" + gender + "' where phone = '" + number + "'");
		} catch (SQLException e) {
			if (!(e instanceof PSQLException)) {
				System.out.println("Connection failure.");
				e.printStackTrace();
			}
			return null;
		}
	}

	/**
	 * This is function for updating graph daily
	 * @return update number of boys, girls, wrong messages and add a new data row for the next day
	 */
	public static ResultSet updateGraph() {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/oop", "hang", "1")) {
			Statement statement = connection.createStatement();
			return statement.executeQuery(
					"update graph set girls = (select count(id) from users where gender = 'G' and state = '1'),"
							+ "boys = (select count(id) from users where gender = 'B' and state = '1'),"
							+ "del = (select count(id) from users where state = '0')"
							+ "where date = CURRENT_DATE ; insert into graph values (CURRENT_DATE + 1, 0, 0, 0, 0)");
		} catch (SQLException e) {
			if (!(e instanceof PSQLException)) {
				System.out.println("Connection failure.");
				e.printStackTrace();
			}
			return null;
		}
	}


}