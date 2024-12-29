package kadai_004;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Employees_Chapter04 {

	public static void main(String[] args) {

		Connection con = null;
		Statement statement = null;
		
		try {
			// �f�[�^�x�[�X�ɐڑ�
			con = DriverManager.getConnection(
				"jdbc:mysql://localhost/challenge_java",
				"root",
				"****"
			);
			
			System.out.println("�f�[�^�x�[�X�ڑ������F" + con);
			
			// SQL�N�G��������
			statement = con.createStatement();
			String sql = """
					CREATE TABLE employees (
						id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
						name VARCHAR(60) NOT NULL,
						email VARCHAR(255) NOT NULL,
						age INT(11),
						address VARCHAR(255)
					);
					""";
			
			// SQL�N�G�������s
			int rowCnt = statement.executeUpdate(sql);
			System.out.println("�Ј��e�[�u�����쐬���܂����F�X�V���R�[�h��=" + rowCnt );
			
		} catch (SQLException e) {
			System.out.println("�G���[�����F" + e.getMessage());
		} finally {
			if ( statement != null ) {
				try { statement.close(); } catch (SQLException ignore) {}
			}
			if ( con != null ) {
				try { con.close(); } catch (SQLException ignore) {}
			}
		}

	}

}
