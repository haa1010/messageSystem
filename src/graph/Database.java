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

public class Database {
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