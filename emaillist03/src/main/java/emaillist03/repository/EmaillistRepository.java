package emaillist03.repository;

import emaillist03.vo.EmaillistVo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmaillistRepository {
    public Boolean deleteByEmail(String email) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            String sql = "delete" +
                    " from emaillist" +
                    " where email = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, email);

            int count = pstmt.executeUpdate();

            result = count == 1;

        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                System.out.println("error:" + e);
            }
        }

        return result;
    }

    public boolean insert(EmaillistVo vo) {
        boolean result = false;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            String sql = "insert into emaillist values(null, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, vo.getFirstName());
            pstmt.setString(2, vo.getLastName());
            pstmt.setString(3, vo.getEmail());

            int count = pstmt.executeUpdate();

            //6. 결과처리
            result = count == 1;
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                System.out.println("error:" + e);
            }
        }

        return result;
    }

    private Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = "jdbc:mariadb://192.168.64.2/webdb";
            conn = DriverManager.getConnection(url, "webdb", "webdb");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패: " + e);
        }

        return conn;
    }

    public Long count() {
        Long result = 0L;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = "select count(*) from emaillist";
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                System.out.println("error:" + e);
            }
        }

        return result;
    }

    public List<EmaillistVo> findAll() {
        List<EmaillistVo> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = "select id, first_name, last_name, email" +
                    " from emaillist" +
                    " order by id desc";
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong(1);
                String firstName = rs.getString(2);
                String lastNast = rs.getString(3);
                String email = rs.getString(4);

                EmaillistVo vo = new EmaillistVo();
                vo.setId(id);
                vo.setFirstName(firstName);
                vo.setLastName(lastNast);
                vo.setEmail(email);

                result.add(vo);
            }
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                System.out.println("error:" + e);
            }
        }

        return result;
    }
}
