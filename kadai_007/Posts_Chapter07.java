package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Posts_Chapter07 {

	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement statement = null;
		
		//���[�U�[���X�g
		String[][] postList = {
				{ "1003" , "2023-02-08" , "����̖�͓O��ł����E�E" , "13" },
				{ "1002" , "2023-02-08" , "�����l�ł��I" , "12" },
				{ "1003" , "2023-02-09" , "�������撣��܂��I" , "18" },
				{ "1001" , "2023-02-09" , "�����͋֕��ł���I" , "17" },
				{ "1002" , "2023-02-10" , "��������A�x�ł��ˁI" , "20" }
		};
		
		try {
			// �f�[�^�x�[�X�ɐڑ�
			con = DriverManager.getConnection(
				"jdbc:mysql://localhost/challenge_java",
				"root",
				"****"
			);
			System.out.println("�f�[�^�x�[�X�ڑ�����");
			
			// ���e�f�[�^������
			String sql = "INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES (?, ?, ?, ?);";
			statement = con.prepareStatement(sql);

			System.out.println("���R�[�h�ǉ������s���܂�");
			
			// ���e�f�[�^��ǉ�
			int rowCnt = 0;
			for(int i = 0; i < postList.length; i++) {
				// SQL�N�G����?�����X�g�̃f�[�^�ɒu������
				statement.setString(1, postList[i][0]); //���[�U�[ID
				statement.setString(2, postList[i][1]); //���e����
				statement.setString(3, postList[i][2]); //���e���e
				statement.setString(4, postList[i][3]); //�����ː�

				// SQL�N�G�������s�iDBMS�ɑ��M�j
				rowCnt += statement.executeUpdate();
			}
			System.out.println( rowCnt + "���̃��R�[�h���ǉ�����܂���");
			
			
			// ���e�f�[�^������
			Statement secStatement = null;
			secStatement = con.createStatement();
			sql = "SELECT posted_at, post_content, likes FROM posts WHERE user_id = 1002;";
			
			// SQL�N�G�������s�iDBMS�ɑ��M�j
			ResultSet result = secStatement.executeQuery(sql);
			
			// SQL�N�G���̎��s���ʂ𒊏o
			System.out.println("���[�U�[ID��1002�̃��R�[�h���������܂���");
			while(result.next()) {
				Date posted_at = result.getDate("posted_at");
				String post_content = result.getString("post_content");
				int likes = result.getInt("likes");
				System.out.println(result.getRow()
									+ "���ځF���e������" + posted_at
									+ "�^���e���e��" + post_content
									+ "�^�����ː���" + likes);
			}
			
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
