package uta.com.cateringsystem.service.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import uta.com.cateringsystem.service.beans.AvailableHallList;
import uta.com.cateringsystem.service.beans.Event;
import uta.com.cateringsystem.service.beans.EventStatus;
import uta.com.cateringsystem.service.beans.User;
import uta.com.cateringsystem.service.dao.DbManager;
import uta.com.cateringsystem.service.beans.Hall;


@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DbManagerImpl implements DbManager {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public User getUser(String userName) {
		User user = jdbcTemplate.queryForObject("SELECT  * FROM user"
				+ "	where user.user_status=2" + "	and user.username=?",
				new Object[] { userName }, new BeanPropertyRowMapper<User>(User.class));
		return user;
	}

	@Override
	public boolean requestEvent(Event eventRequest) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(
				"INSERT INTO event " + "(" + "event_name," + "event_date," + "start_time," + "end_time," + "hall_id,"
						+ "event_capacity," + "event_status," + "user_id," + "created_date," + "meal_type_id,"
						+ "food_venue_id," + "meal_formality_id) " + "VALUES " + "(?,?,?,?,?,?,?,?,?,?,?,?,)",
				new Object[] { eventRequest.getEventName(), eventRequest.getEventDate(), eventRequest.getStartTime(),
						eventRequest.getEndTime(), eventRequest.getHallId(), eventRequest.getEventCapacity(),
						eventRequest.getEventStatus(), eventRequest.getUserId(), eventRequest.getCreatedDate(),
						eventRequest.getMealTypeId(), eventRequest.getFoodVenueId() },
				new Object[] { Types.VARCHAR, Types.DATE, Types.TIME, Types.TIME, Types.INTEGER, Types.INTEGER,
						Types.INTEGER, Types.INTEGER, Types.DATE, Types.INTEGER, Types.INTEGER });
		return true;
	}

	@Override
	public boolean createEvent(int eventId, int userId) {
		// TODO Auto-generated method stub
		return updateEvent(eventId, EventStatus.APPROVED, userId);

	}

	@Override
	public boolean rejectEvent(int eventId, int userId) {
		// TODO Auto-generated method stub
		return updateEvent(eventId, EventStatus.REJECTED, userId);

	}

	@Override
	public boolean cancelEvent(int eventId, int userId) {
		// TODO Auto-generated method stub
		return updateEvent(eventId, EventStatus.CANCELLED, userId);

	}

	private boolean updateEvent(int eventId, EventStatus status, int userId) {

		jdbcTemplate.update("update event set event_status=?,modified_date=?,modified_by=? where event_id=?",
				new Object[] { status, new Date(Calendar.getInstance().getTimeInMillis()), userId, eventId },
				new Object[] { Types.VARCHAR, Types.DATE, Types.VARCHAR, Types.INTEGER });
		return true;

	}

	public boolean assignStaffToEvent(final int eventId, final List<Integer> staffList, final int userId) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO event_assignment " + "(event_id, " + "caterer_id, " + "staff_id) " + "VALUES "
				+ "(?,?,?)";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				int staffId = staffList.get(i);
				ps.setInt(1, eventId);
				ps.setInt(2, userId);
				ps.setInt(3, staffId);
			}

			@Override
			public int getBatchSize() {
				return staffList.size();
			}
		});
		return true;
	}



	@Override
	public List<Event> getAssignedEventSummaryList(Date startDate, Date endDate, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, String> getDrinkVenues() {
		return jdbcTemplate.query("select id,name from drink_venue", new ResultSetExtractor<Map>(){
		    @Override
		    public Map<Integer, String> extractData(ResultSet rs) throws SQLException,DataAccessException {
		        HashMap<Integer,String> mapRet= new HashMap<Integer,String>();
		        while(rs.next()){
		            mapRet.put(rs.getInt("id"),rs.getString("name"));
		        }
		        return mapRet;
		    }
		});
	}

	@Override
	public Map<Integer, String> getFoodVenues() {
		return jdbcTemplate.query("select id,name from food_venue", new ResultSetExtractor<Map>(){
		    @Override
		    public Map<Integer, String> extractData(ResultSet rs) throws SQLException,DataAccessException {
		        HashMap<Integer,String> mapRet= new HashMap<Integer,String>();
		        while(rs.next()){
		            mapRet.put(rs.getInt("id"),rs.getString("name"));
		        }
		        return mapRet;
		    }
		});
	}

	@Override
	public Map<Integer, String> getMeals() {
		return jdbcTemplate.query("select id,type from meal", new ResultSetExtractor<Map>(){
		    @Override
		    public Map<Integer, String> extractData(ResultSet rs) throws SQLException,DataAccessException {
		        HashMap<Integer,String> mapRet= new HashMap<Integer,String>();
		        while(rs.next()){
		            mapRet.put(rs.getInt("id"),rs.getString("type"));
		        }
		        return mapRet;
		    }
		});
	}

	@Override
	public List<Hall> getHalls() {
		return jdbcTemplate.query("select * from hall",
				new BeanPropertyRowMapper(Hall.class));
	}

	@Override
	public Map<Integer, String> getMealFormalities() {
		return jdbcTemplate.query("select id,name from meal_formality", new ResultSetExtractor<Map>(){
		    @Override
		    public Map<Integer, String> extractData(ResultSet rs) throws SQLException,DataAccessException {
		        HashMap<Integer,String> mapRet= new HashMap<Integer,String>();
		        while(rs.next()){
		            mapRet.put(rs.getInt("id"),rs.getString("name"));
		        }
		        return mapRet;
		    }
		});
	}

	@Override
	public List<Event> getCatererEventSummaryList(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> getUserEventSummaryList(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AvailableHallList> searchAvailableHalls(Date fromDate, Date toDate, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> searchUser(Map<String, String> filters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteProfile(int userId, int adminId) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("update user set user_status=5 where user_id=? and user_status=2",
				new Object[] { userId},
				new Object[] { Types.INTEGER});
		return true;
		
	}

	@Override
	public boolean updateProfile(User user, int userId) {
		// TODO Auto-generated method stub
		String sql="update user  SET  username = ?, password = ?,"
				+ "		fname = ?, lname = ?, date_of_birth = ?,"
				+ "		address = ?, phone = ?,"
				+ "		email_id = ?, uta_id = ?"
				+ "		WHERE user_id = ? and user_status=2";
		jdbcTemplate.update(sql,
				new Object[] { user.getUsername(),user.getPassword(),
						user.getfName(),user.getLname(),user.getDate_of_birth(),
						user.getAddress(),user.getPhone(),user.getEmail_id(),
						user.getUta_id(),userId},
				new Object[] { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,
						Types.DATE,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,
						Types.INTEGER});
		return true;
	}

	@Override
	public boolean registerUser(User user) {
		String sql = "INSERT INTO user " +
					 "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
					 
			jdbcTemplate.update(sql, new Object[] { user.getUsername(),user.getPassword(),
					user.getfName(),user.getLname(),user.getDate_of_birth(),user.getAddress(),user.getPhone(),
					user.getEmail_id(),user.getUta_id(),1});
			return true;
	}

	@Override
	public List<User> getRegistrationRequestList() {
		return jdbcTemplate.query("select * from user where user_status=1",
				new BeanPropertyRowMapper(User.class));
	}

	@Override
	public boolean approveRegistrationRequest(int userId, int adminId) {
		return approveRejectRegistrationRequest(userId,adminId,2);
	}

	@Override
	public boolean rejectRegistrationRequest(int userId, int adminId) {
		return approveRejectRegistrationRequest(userId,adminId,3);
	}

	private boolean approveRejectRegistrationRequest(int userId, int adminId, int i) {
		jdbcTemplate.update("update user set user_status=? where user_id=? and user_status=1",
				new Object[] { userId,i},
				new Object[] { Types.INTEGER,Types.INTEGER });
		return true;
		
	}

	@Override
	public Map<Integer, String> getUserTypes() {
		return jdbcTemplate.query("SELECT id,type FROM cateringsystem.system_user_master " + 
				"where type <> 'admin'", new ResultSetExtractor<Map>(){
		    @Override
		    public Map<Integer, String> extractData(ResultSet rs) throws SQLException,DataAccessException {
		        HashMap<Integer,String> mapRet= new HashMap<Integer,String>();
		        while(rs.next()){
		            mapRet.put(rs.getInt("id"),rs.getString("type"));
		        }
		        return mapRet;
		    }
		});
	}



}
