package myapp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ProductRepository {
    private Connection conn;

    public ProductRepository(Connection conn) {
        this.conn = conn;
    }

    public void findById(int id) throws SQLException {
        // 2. 버퍼 접근
        String sql = "select * from product where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 3. 물음표 완성
        pstmt.setInt(1, id);

        // 4. 전송
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            int v1 = rs.getInt("id");
            String name = rs.getString("name");
            int price = rs.getInt("price");
            int qty = rs.getInt("qty");
            Timestamp createdAt = rs.getTimestamp("created_at");
        }
    }

    public void findAll() {

    }

    // DB에 insert
    public void insert(String name, int price, int qty) throws SQLException {
        // 2. 버퍼 접근
        String sql = "insert into product(name, price, qty, created_at) values(?,?,?,now())";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 3. 물음표 완성
        pstmt.setString(1, name);
        pstmt.setInt(2, price);
        pstmt.setInt(3, qty);

        // 4. 전송
        int result = pstmt.executeUpdate();

        // 5. 응답에 대한 처리
        if (result == 1) {
            System.out.println("insert 되었습니다");
        } else {
            System.out.println("insert 실패");
        }

        // 6. 최종 마무리
        pstmt.close();
    }

    public void deleteById(int id) throws SQLException {
        String sql = "delete from product where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        int result = pstmt.executeUpdate();

        if (result == 1) {
            System.out.println("delete 되었습니다");
        } else {
            System.out.println("delete 실패");
        }

        pstmt.close();
    }

    public void uqdateById(int id, String name, int price, int qty) throws SQLException {
        // 버퍼접근
        String sql = "update product set name = ?, price = ?, qty = ? where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 물음표완성
        pstmt.setString(1, name);
        pstmt.setInt(2, price);
        pstmt.setInt(3, qty);
        pstmt.setInt(4, id);

        // 전송
        int result = pstmt.executeUpdate();

        // 응답에 대한 처리
        if (result == 1) {
            System.out.println("update 되었습니다");
        } else {
            System.out.println("update 실패");
        }

        pstmt.close();
    }

}