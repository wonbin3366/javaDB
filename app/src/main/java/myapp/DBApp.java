package myapp;

import java.sql.Connection;

import myapp.config.DBConfig;
import myapp.model.ProductRepository;
import myapp.service.ProductService;

public class DBApp {
    public static void main(String[] args) throws Exception {
        // 1. 커넥션 가져오기
        Connection conn = DBConfig.getConnection();
        // 2. DAO를 메모리에 올리기 레파지토리
        ProductRepository productRepository = new ProductRepository(conn);
        // 3. SERVICE를 메모리에 올리기
        ProductService productService = new ProductService(productRepository, conn);

        productService.상품등록("apple", 2000, 5);

    }
}
