package kr.inhatc.shop.repository;

import kr.inhatc.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ItemRepository extends JpaRepository<Item, Long>,
        QuerydslPredicateExecutor<Item> {

    List<Item> findByItemNm(String itemNm);     // 해당 이름에 대한 상품 리스트 가져오기

    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    List<Item> findByPriceLessThan(Integer price);

    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    //과제 1번
    List<Item> findByPriceIsGreaterThanEqualAndItemNmContains(int price, String itemNm);

    //JPQL
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail); // :itemDetail 변수로 전달

    @Query(value="select * from item i where i.item_detail like %:itemDetail% order by i.price asc", nativeQuery = true)
    List<Item> findByItemDetailNative(@Param("itemDetail") String itemDetail);

    //2번 문제
    @Query("SELECT i FROM Item i WHERE i.price >= :price AND i.itemNm LIKE %:itemNm%")
    List<Item> findItemsByPriceAndItemName(int price, String itemNm);

    //3번 문제
    @Query(value = "SELECT * FROM item i WHERE i.price >= :price AND item_nm LIKE %:itemNm%", nativeQuery = true)
    List<Item> findItemsByPriceAndItemNameNative(@Param("price") int price, @Param("itemNm") String itemNm);


    //4번 문제



}
