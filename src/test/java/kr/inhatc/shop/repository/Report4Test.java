package kr.inhatc.shop.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.inhatc.shop.constant.ItemSellStatus;
import kr.inhatc.shop.entity.Item;
import kr.inhatc.shop.entity.QItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class Report4Test {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EntityManager em;

    public void createItemList(){
        for(int i=1; i<=10; i++){
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(160);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("테스트")
    void findByStockNumberIsGreaterThanEqualAndItemNmContains() { //재고량이 160 이상, 아이템 이름에 5가 포함된 상품 찾기
        createItemList();

        List<Item> itemList = itemRepository.findByStockNumberIsGreaterThanEqualAndItemNmContains(160, "5");
        itemList.forEach((item -> System.out.println(item))); //밑에 foreach문이랑 똑같음
//        for (Item item : itemList) {
//            System.out.println(item);
//        }
    }

    @Test
    @DisplayName("2번 테스트")
    public void findItemsByStockNumberAndItemName(){

        List<Item> itemList = itemRepository.findItemsByStockNumberAndItemName(160, "5");

        for (Item item : itemList) {
            System.out.println(item);
        }
    }

    @Test
    @DisplayName("3번 테스트")
    public void findItemsByStockNumberAndItemNameNative(){

        List<Item> itemList = itemRepository.findItemsByStockNumberAndItemNameNative(160, "5");
        System.out.println(itemList.size());

        for (Item item : itemList) {
            System.out.println(item);
        }
    }

    @Test
    @DisplayName("4번 테스트")
    public void querydslTest(){

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem qItem = QItem.item;


        List<Item> itemList = queryFactory.selectFrom(qItem)
                .where(qItem.stockNumber.goe(160).and(qItem.itemNm.like("%5%")))
                .fetch();

        itemList.forEach(item -> System.out.println(item));

//        for (Item item : itemList) {
//            System.out.println(item);
//        }

    }

}