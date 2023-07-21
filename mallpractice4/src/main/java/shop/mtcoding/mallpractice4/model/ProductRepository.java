package shop.mtcoding.mallpractice4.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository //DAO
public class ProductRepository {
    @Autowired
    private EntityManager em;

    public List<Product> findAll() { // 상품목록(홈)
        Query query = em.createNativeQuery("select * from product_tb", Product.class);
        List<Product> productList = query.getResultList();
        return productList;
    }

    @Transactional
    public void save(String name, int price, int qty) { // 상품 등록
        Query query = em.createNativeQuery("insert into product_tb(name, price, qty) values(:name, :price, :qty)");
        query.setParameter("name", name);
        query.setParameter("price", price);
        query.setParameter("qty", qty);
        query.executeUpdate();
    }


    @Transactional
    public void deleteById(int id){ // 상품 삭제
        Query query = em.createNativeQuery("delete from product_tb where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public Product findById(int id) { // 상품 목록
        Query query = em.createNativeQuery("select * from product_tb where id = :id", Product.class);
        query.setParameter("id", id);
        Product product = (Product) query.getSingleResult();
        return product;
    }

    @Transactional
    public Product updateById(int id, String name, int price, int qty) {

        Product product = findById(id);

        // id 값을 이용해 기존 데이터를 조회하고, name, price, qty 값을 수정한 후 저장
        em.find(Product.class, id);
        if (product != null) {
            product.setName(name);
            product.setPrice(price);
            product.setQty(qty);
            em.merge(product);
        }
        return product;
    }
}
