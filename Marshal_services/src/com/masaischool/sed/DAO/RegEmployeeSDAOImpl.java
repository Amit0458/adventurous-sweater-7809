package com.masaischool.sed.DAO;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.masaischool.sed.DTO.Complain;
import com.masaischool.sed.DTO.ComplainImpl;
import com.masaischool.sed.DTO.RegisterdEmployee;
import com.masaischool.sed.Exceptions.NoRecordFoundException;
import com.masaischool.sed.Exceptions.SomeThingWrongException;
import com.masaischool.sed.UI.Main;

public class RegEmployeeSDAOImpl implements RegEmployeeDAO {

	@Override
	public boolean registreEmployee(RegisterdEmployee eng) throws SomeThingWrongException {
		Connection connection = null;
		boolean result = false;
		try {
			// get connection
			connection = DbUtility.getConnecton();

			String SELECT_QUERY = "SELECT * FROM regEmployees WHERE emp_id = ?";

			PreparedStatement prepStatment = connection.prepareStatement(SELECT_QUERY);
			
			prepStatment.setInt(1, eng.getEmp_id());
			
			ResultSet rs = prepStatment.executeQuery();
			
			if (DbUtility.isResultSetEmpty(rs)) {
				// Prepare query
				String INSERT_QUERY = "INSERT INTO regemployees (emp_id, usrename, password, rigister_date) VALUES (?,?,?,?)";
				
				prepStatment = connection.prepareStatement(INSERT_QUERY);
				
				prepStatment.setInt(1, eng.getEmp_id());
				prepStatment.setString(2, eng.getUsername());
				prepStatment.setString(3, eng.getPassword());
				prepStatment.setDate(4, Date.valueOf(eng.getRegdate()));

				prepStatment.executeUpdate();
				
				result = true;
				
			} else {
				System.out.println("Employee already regitered");
			}

		} catch (SQLException ex) {
			throw new SomeThingWrongException();
		} finally {
			try {
				DbUtility.closeConnection(connection);
			} catch (SQLException ex) {
				throw new SomeThingWrongException();
			}
		}
		return result;
	}

	@Override
	public boolean employeeLogin(String username, String password)
			throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		boolean result = false;
		try {
			// get connection
			connection = DbUtility.getConnecton();

			// Prepare query
			String SELECT_QUERY = "SELECT * FROM regemployees WHERE usrename = ? AND password = ?";
			PreparedStatement prepStatment = connection.prepareStatement(SELECT_QUERY);

			prepStatment.setString(1, username);
			prepStatment.setString(2, password);

			ResultSet rs = prepStatment.executeQuery();

			if (DbUtility.isResultSetEmpty(rs)) {

			} else {
				result = true;
				rs.next();
				LoggedINUser.loggedInUSerId = rs.getInt("emp_id");
			}

		} catch (SQLException ex) {
			throw new SomeThingWrongException();
		} finally {
			try {
				DbUtility.closeConnection(connection);
			} catch (SQLException ex) {
				throw new SomeThingWrongException();
			}
		}
		return result;
	}

	@Override
	public void registerComplain(Complain comp) throws SomeThingWrongException {
		Connection connection = null;
		try {
			// get connection
			connection = DbUtility.getConnecton();

			// Prepare query
			String INSERT_QUERY = "INSERT INTO Complains (reg_date, comp_desc, raised_by) VALUES (?,?,?)";

			PreparedStatement prepStatment = connection.prepareStatement(INSERT_QUERY);

			prepStatment.setDate(1, Date.valueOf(comp.getRegister_Date()));
			prepStatment.setString(2, comp.getComplain_desc());
			prepStatment.setInt(3, LoggedINUser.loggedInUSerId);

			int respons = prepStatment.executeUpdate();

			if (respons == 0) {
				throw new SomeThingWrongException();
			}

		} catch (SQLException ex) {
			throw new SomeThingWrongException();
		} finally {
			try {
				DbUtility.closeConnection(connection);
			} catch (SQLException ex) {
				throw new SomeThingWrongException();
			}
		}
	}

	@Override
	public Complain checkStatus(Integer complainId) throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		Complain comp = null;
		String SELECT_QUERY = "";
		PreparedStatement prepStatment;
		ResultSet rs;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			SELECT_QUERY = "SELECT C.comp_id ID, C.reg_date Registered,"
					+ " C.comp_desc Problem, E2.emp_name RaisedBy,"
					+ " E.emp_name Engineer, C.closing_date closing,"
					+ " C.Status Status FROM Complains C "
					+ "INNER JOIN employees E ON C.assign_to = E.emp_id "
					+ "INNER JOIN Employees E2 ON C.raised_by = E2.emp_id WHERE C.comp_id = ?";
			
			prepStatment = connection.prepareStatement(SELECT_QUERY);
			
			prepStatment.setInt(1, complainId);

			rs = prepStatment.executeQuery();
			
			if(!DbUtility.isResultSetEmpty(rs)) {
				comp = getComplainObject(rs);
			}else {
				SELECT_QUERY = "SELECT C.comp_id ID, C.reg_date Registered,"
				+ " C.comp_desc Problem, E.emp_name RaisedBy,"
				+ " C.assign_to Engineer, C.closing_date closing,"
				+ " C.Status Status FROM Complains C INNER JOIN employees E "
				+ "ON C.raised_by = E.emp_id WHERE C.comp_id = ? AND C.assign_to IS NULL";
				
				prepStatment = connection.prepareStatement(SELECT_QUERY);
				
				prepStatment.setInt(1, complainId);

				rs = prepStatment.executeQuery();
				
				if(!DbUtility.isResultSetEmpty(rs)) {
					comp = getComplainObject(rs);
				}else {
					throw new NoRecordFoundException("\tNo complains found with complain Id : " + complainId);
				}
			}
		}catch(SQLException ex) {
			throw new SomeThingWrongException();
		}finally {
			try {
				DbUtility.closeConnection(connection);
			}catch(SQLException ex) {
				throw new SomeThingWrongException();
			}
		}
		return comp;
	}

	public List<Complain> showAllComplain() throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		List<Complain> list = null;
		String SELECT_QUERY = "";
		PreparedStatement prepStatment;
		ResultSet rs;
		try {
			//get connection
			connection = DbUtility.getConnecton();
			
			//Prepare query
			SELECT_QUERY = "SELECT C.comp_id ID, C.reg_date Registered,"
					+ " C.comp_desc Problem, E2.emp_name RaisedBy,"
					+ " E.emp_name Engineer, C.closing_date closing,"
					+ " C.Status Status FROM Complains C "
					+ "INNER JOIN employees E ON C.assign_to = E.emp_id"
					+ "INNER JOIN Employees E2 ON C.raised_by = E2.emp_id WHERE C.raised_by = ?";
			
			prepStatment = connection.prepareStatement(SELECT_QUERY);
			
			prepStatment.setInt(1, LoggedINUser.loggedInUSerId);

			rs = prepStatment.executeQuery();
			
			if(!DbUtility.isResultSetEmpty(rs)) {
				list = DbUtility.getAllCompliansFromResultSet(rs);
			}else {
				SELECT_QUERY = "SELECT C.comp_id ID, C.reg_date Registered,"
				+ " C.comp_desc Problem, E.emp_name RaisedBy,"
				+ " C.assign_to Engineer, C.closing_date closing,"
				+ " C.Status Status FROM Complains C INNER JOIN employees E "
				+ "ON C.raised_by = E.emp_id WHERE C.raised_by = ? AND C.assign_to IS NULL";
				
				prepStatment = connection.prepareStatement(SELECT_QUERY);
				
				prepStatment.setInt(1, LoggedINUser.loggedInUSerId);

				rs = prepStatment.executeQuery();
				
				if(!DbUtility.isResultSetEmpty(rs)) {
					list = DbUtility.getAllCompliansFromResultSet(rs);
				}else {
					throw new NoRecordFoundException("\tNo complains found with complain Id : " + LoggedINUser.loggedInUSerId);
				}
			}
		}catch(SQLException ex) {
			throw new SomeThingWrongException();
		}finally {
			try {
				DbUtility.closeConnection(connection);
			}catch(SQLException ex) {
				throw new SomeThingWrongException();
			}
		}
		return list;
	}

	@Override
	public void changePassword(String userName, String oldPassword, String neWpassword)
			throws SomeThingWrongException, NoRecordFoundException {
		Connection connection = null;
		try {
			// get connection
			connection = DbUtility.getConnecton();

			String SELECT_QUERY = "SELECT * FROM regemployees WHERE usrename = ? And password = ? AND emp_id = ?";

			PreparedStatement prepStatment = connection.prepareStatement(SELECT_QUERY);

			// Prepare query
			prepStatment.setString(1, userName);
			prepStatment.setString(2, oldPassword);
			prepStatment.setInt(3, LoggedINUser.loggedInUSerId);

			ResultSet rs = prepStatment.executeQuery();
			if (!DbUtility.isResultSetEmpty(rs)) {

				String UPDATE_QUERY = "UPDATE regemployees SET password = ? WHERE usrename = ? AND emp_id = ?";

				prepStatment = connection.prepareStatement(UPDATE_QUERY);

				prepStatment.setString(1, neWpassword);
				prepStatment.setString(2, userName);
				prepStatment.setInt(3, LoggedINUser.loggedInUSerId);

				int response = prepStatment.executeUpdate();

				if (response == 0) {
					throw new NoRecordFoundException("Please check user name password");
				}
			} else {
				throw new NoRecordFoundException("\tPassword missmatch");
			}
		} catch (SQLException ex) {
			throw new SomeThingWrongException();
		} finally {
			try {
				DbUtility.closeConnection(connection);
			} catch (SQLException ex) {
				throw new SomeThingWrongException();
			}
		}

	}
	private Complain getComplainObject(ResultSet rs) throws SQLException {
		Complain comp = new ComplainImpl();
		rs.next();
		comp.setComplain_id(rs.getInt("ID"));
		comp.setRegister_Date(rs.getDate("Registered").toLocalDate());
		comp.setComplain_desc(rs.getString("Problem"));
		comp.setEmployeeName(rs.getString("RaisedBy"));
		comp.setComplain_status(rs.getString("Status"));
		comp.setEnggName(rs.getString("Engineer"));
		if(rs.getDate("closing") != null)  {
			comp.setClosing_date(rs.getDate("closing").toLocalDate());
		}
		return comp;
	}
	@Override
	public void enigineerLogout() {
		LoggedINUser.loggedInUSerId = 0;
		Main.mainMenu();
	}
}
