package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;
import interfaces.I_TaiKhoan;

public class Dao_TaiKhoan implements I_TaiKhoan{
	
	public Dao_TaiKhoan() {

	}

	@Override
	public ArrayList<TaiKhoan> getAllTaiKhoan() {
		ArrayList<TaiKhoan> dsTK = new ArrayList<TaiKhoan>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "Select * from TAIKHOAN";
			Statement sta = con.createStatement();

			ResultSet rs = sta.executeQuery(sql);

			while (rs.next()) {
				String maTK = rs.getString("MATAIKHOAN");
				String email = rs.getString("EMAIL");
				String matKhau = rs.getString("MATKHAU");
				String tenTK = rs.getString("TENTAIKHOAN");
				NhanVien nhanVien = new NhanVien(rs.getString("MANHANVIEN"));

				TaiKhoan tk = new TaiKhoan(maTK, email, matKhau, tenTK, nhanVien);
				dsTK.add(tk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTK;
	}

	@Override
	public TaiKhoan getTheoMaTK(String maTaiKhoan) {
		TaiKhoan tk = null;
		PreparedStatement sta = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from TaiKhoan where MATAIKHOAN = ?";
			sta = con.prepareStatement(sql);
			sta.setString(1, maTaiKhoan);

			ResultSet rs = sta.executeQuery();
			while (rs.next()) {
				String maTK = rs.getString("MATAIKHOAN");
				String email = rs.getString("EMAIL");
				String matKhau = rs.getString("MATKHAU");
				String tenTK = rs.getString("TENTAIKHOAN");
				NhanVien nhanVien = new NhanVien(rs.getString("MANHANVIEN"));

				tk = new TaiKhoan(maTK, email, matKhau, tenTK, nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return tk;
	}

	@Override
	public boolean them(TaiKhoan tk) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "insert into TaiKhoan values(?,?,?,?,?)";
			sta = con.prepareStatement(sql);

			sta.setString(1, tk.getMaTaiKhoan());
			sta.setString(2, tk.getEmail());
			sta.setString(3, tk.getMatKhau());
			sta.setString(4, tk.getTenTaiKhoan());
			sta.setString(5, tk.getNhanVien().getMaNhanVien());
			n = sta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	@Override
	public boolean capNhat(TaiKhoan tk) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "update TaiKhoan set EMAIL = ?, MATKHAU = ?, TENTAIKHOAN = ?, MANHANVIEN =? where MATAIKHOAN = ?";
			sta = con.prepareStatement(sql);

			
			sta.setString(1, tk.getEmail());
			sta.setString(2, tk.getMatKhau());
			sta.setString(3, tk.getTenTaiKhoan());
			sta.setString(4, tk.getNhanVien().getMaNhanVien());
			sta.setString(5, tk.getMaTaiKhoan());
			n = sta.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	@Override
	public TaiKhoan getTKTheoEmail(String partialEmailNhanVien) {
	    TaiKhoan tk = null;
	    PreparedStatement sta = null;
	    try {
	        ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT tk.* FROM TaiKhoan tk JOIN NhanVien nv ON tk.MANHANVIEN = nv.MANHANVIEN WHERE nv.EMAIL LIKE ?";
	        sta = con.prepareStatement(sql);
	        sta.setString(1, "%" + partialEmailNhanVien + "%"); // Tìm kiếm gần đúng

	        ResultSet rs = sta.executeQuery();
	        while (rs.next()) {
	            String maTK = rs.getString("MATAIKHOAN");
	            String email = rs.getString("EMAIL");
	            String matKhau = rs.getString("MATKHAU");
	            String tenTK = rs.getString("TENTAIKHOAN");
	            NhanVien nhanVien = new NhanVien(rs.getString("MANHANVIEN"));

	            tk = new TaiKhoan(maTK, email, matKhau, tenTK, nhanVien);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            sta.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return tk;
	}




}
