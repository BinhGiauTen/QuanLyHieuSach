package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;
import interfaces.I_NhanVien;

public class Dao_NhanVien implements I_NhanVien{
	
	public Dao_NhanVien() {

	}

	@Override
	public ArrayList<NhanVien> getAllNhanVien() {
		ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "Select * from NhanVien";
			Statement sta = con.createStatement();
			ResultSet rs =sta.executeQuery(sql);
			
			while (rs.next()) {
				String maNhanVien = rs.getString("MANHANVIEN");
				String hoTenNhanVien = rs.getString("HOTENNHANVIEN");
				Date ngaySinh = rs.getDate("NGAYSINH");
				String diaChi = rs.getString("DIACHI");
				String soDienThoai = rs.getString("SODIENTHOAI");
				String email = rs.getString("EMAIL");
				boolean gioiTinh = rs.getBoolean("GIOITINH");
				String chucVu = rs.getString("CHUCVU");
				String tinhTrang = rs.getString("TINHTRANG");
				
				NhanVien nv = new NhanVien(maNhanVien, hoTenNhanVien, ngaySinh, diaChi, soDienThoai, email, gioiTinh, tinhTrang, chucVu);
				dsNV.add(nv);
				
			}
			}catch (Exception e) {
				e.printStackTrace();
		}
		return dsNV;
	}

	@Override
	public NhanVien getTheoMaNV(String maNV) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<NhanVien> getTheoHoTen(String hoTen) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<NhanVien> getTheoSDT(String sdt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean them(NhanVien nv) {
		PreparedStatement sta = null;
		int n=0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "insert into NHANVIEN values(?,?,?,?,?,?,?,?,?)";
			sta = con.prepareStatement(sql);
			sta.setString(1, nv.getMaNhanVien());
			sta.setString(2, nv.getHoTenNhanVien());
			sta.setDate(3, nv.getNgaySinh());
			sta.setString(4, nv.getDiaChi());
			sta.setString(5, nv.getSoDienThoai());
			sta.setString(6, nv.getEmail());
			sta.setBoolean(7, nv.isGioiTinh());
			sta.setString(8, nv.getChucVu());
			sta.setString(9, nv.getTinhTrang());
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n>0;
	}

	@Override
	public boolean capNhat(NhanVien nv) {
		PreparedStatement sta = null;
		int n=0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "update NHANVIEN set HOTENNHANVIEN = ?, NGAYSINH = ?, DIACHI = ?, SODIENTHOAI = ?, EMAIL = ?, GIOITINH = ?, CHUCVU = ?, TINHTRANG = ? where MANHANVIEN = ?";
			sta = con.prepareStatement(sql);
			
			sta.setString(1, nv.getHoTenNhanVien());
			sta.setDate(2, nv.getNgaySinh());
			sta.setString(3, nv.getDiaChi());
			sta.setString(4, nv.getSoDienThoai());
			sta.setString(5, nv.getEmail());
			sta.setBoolean(6, nv.isGioiTinh());
			sta.setString(7, nv.getChucVu());
			sta.setString(8, nv.getTinhTrang());
			sta.setString(9, nv.getMaNhanVien());
			n=sta.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n>0;
	}

	@Override
	public boolean xoa(String maNV) {
		PreparedStatement sta = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "delete from NhanVien where MANHANVIEN = ?";
			sta = con.prepareStatement(sql);

			sta.setString(1, maNV);
			n = sta.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;	}

}
