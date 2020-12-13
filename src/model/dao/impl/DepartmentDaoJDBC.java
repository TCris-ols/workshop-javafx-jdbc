package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	 Connection conn = null;
	

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insertDepartment(Department obj) {
		PreparedStatement st = null;
		
		try {
			 st = conn.prepareStatement(
					 "INSERT INTO department (Name) VALUES (?) ", Statement.RETURN_GENERATED_KEYS
					 );
			 st.setString(1,obj.getName());
			 
			 int rowsAffected = st.executeUpdate();

			  if(rowsAffected > 0) {
				 ResultSet rs = st.getGeneratedKeys();
				 if(rs.next()) {
					 int id = rs.getInt(1);
					 obj.setId(id);
				 }
				 DB.closeResultSet(rs);
			 }
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}

	@Override
	public void updateDepartment(Department obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("Update Department set Name = ? where Id = ?");
			
			st.setString(1, obj.getName());
			st.setInt(2,obj.getId());
			
			int arrowsAffected = st.executeUpdate();
			if(arrowsAffected > 0) {
				System.out.println("Executed update id: "+ obj.getId());
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		
		try {
			 st = conn.prepareStatement("Delete from Department where id = ?");
			 
			 st.setInt(1,id);
			 
			 int arrowsAffected = st.executeUpdate();
			 if(arrowsAffected > 0) {
				 System.out.println("Delete complete!");
			 }
			 else {
				 System.out.println("No id founded!");
			 }
				 
			 
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	private Department initiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}
	
	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("Select Id DepId, Name DepName from Department where id = ?");
			st.setInt(1, id);
			
			rs = st.executeQuery();
			if(rs.next()) {
				Department dep = initiateDepartment(rs);
				return dep;
			}
			return null;
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

		
	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		
		try {
			st = conn.prepareStatement("SELECT department.Id DepId, department.Name as DepName "
					+"FROM Department "
					+"ORDER BY Name");
			
			 rs = st.executeQuery();
			 
			 List<Department> list = new ArrayList<>();
			 
			 while(rs.next()) {
				 
				 Department obj = initiateDepartment(rs);
				 list.add(obj);
				 
			 }
			 return list;
			 
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
				DB.closeStatement(st);
				DB.closeResultSet(rs);
			
		}
		
			}

	

}
