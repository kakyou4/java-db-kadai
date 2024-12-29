package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {
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
			String sql = "UPDATE scores SET score_math = 95, score_english = 80 WHERE id = 5;";
			
			// �_���f�[�^���X�V
			System.out.println("���R�[�h�X�V�����s���܂�");
			int rowCnt = statement.executeUpdate(sql);
			System.out.println(rowCnt + "���̃��R�[�h���X�V����܂���");
			
			// �_�����ɕ��ׂ�
			sql = "SELECT * FROM scores ORDER BY score_math DESC, score_english DESC;";
			ResultSet result = statement.executeQuery(sql);
			System.out.println("���w�E�p��̓_�����������ɕ��בւ��܂���");
			
			// ���בւ����ʂ�\��
			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				int scoreMath = result.getInt("score_math");
				int scoreEnglish = result.getInt("score_english");
				System.out.println(
					result.getRow() + "���ځF���kID=" + id
					+ "�^����=" + name
					+ "�^���w=" + scoreMath
					+ "�^�p��=" + scoreEnglish
				);
			}
		} catch(SQLException e) {
			System.out.println("�G���[�����F" + e.getMessage());
		} finally {
			if( statement != null ) {
				try { statement.close(); } catch(SQLException ignore) {}
			}
			if( con != null ) {
				try { con.close(); } catch(SQLException ignore) {}
			}
		}
	}
}
