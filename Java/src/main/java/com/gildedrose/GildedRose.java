package com.gildedrose;

import static com.gildedrose.Constants.*;
import static com.gildedrose.ItemName.*;

import java.util.Arrays;
import java.util.Objects;

class GildedRose {

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items)
                .forEach(item -> {
                    updateItemSellIn(item);
                    updateItemQuality(item);
                });

    }

    // 아이템 판매기간 변경
    private void updateItemSellIn(Item item) {

        if (item.name.equals(SULFURAS.getValue())) {
            return;
        }

        item.sellIn--;
    }

    // Item 종류에 따라 Quality 업데이트
    private void updateItemQuality(Item item) {
        //1.Aged Brie
        //2.Sulfuras
        //3.Backstage Passes
        //4.Conjured
        //5.그 외
        if (Objects.equals(item.name, AGED_BRIE.getValue())) {
            updateAgedBrieQuality(item);
        } else if (Objects.equals(item.name, SULFURAS.getValue())) {
            updateSulfurasQuality(item);
        } else if (Objects.equals(item.name, BACKSTAGE_PASSES.getValue())) {
            updateBackstagePassesQuality(item);
        } else if (Objects.equals(item.name, CONJURED.getValue())) {
            updateConjuredQuality(item);
        } else {
            updateNormalItemQuality(item);
        }
    }

    //Sulfuras Quality 변경
    private void updateSulfurasQuality(Item item) {
        // Sulfurus는 Qualiry에 변경이 없다.
        return;
    }

    //Aged Brie Quality 변경
    private void updateAgedBrieQuality(Item item) {

        //판매기간 끝나기 전
        //판매기간 끝난 후
        if (!isLessThanSellInStandard(item.sellIn)) {
            item.quality += AGED_BRIE_INCREASE_AMOUNT;
        } else {
           item.quality += AGED_BRIE_INCREASE_AMOUNT * NORMAL_ITEM_DECREASE_RATE_AFTER_SELL_IN_STANDARD;
        }

        checkAndAdjustQuality(item);
    }

    //Backstage Passes Quality 변경
    private void updateBackstagePassesQuality(Item item) {

        //판매기간 끝나기 전
        //판매기간 끝난 후
        if (!isLessThanSellInStandard(item.sellIn)) {
            item.quality += BACKSTAGE_PASSES_INCREASE_AMOUNT * decideBackStagePassesIncreaseRate(item.sellIn);

            checkAndAdjustQuality(item);
        } else {
            item.quality = NORMAL_ITEM_MIN_QUALITY;
        }
    }

    //BackStage 상승 비율 결정
    private int decideBackStagePassesIncreaseRate(int sellIn) {

        //1. 판매기간이 2배기간보다 많이 남은 경우
        //2. 판매기간이 2배기간인 경우
        //3. 판매기간이 3배기간인 경우
        //예외
        if (BACKSTAGE_PASSES_SELL_IN_STANDARD_INCREASING_2_TIMES < sellIn) {
            return BACKSTAGE_PASSES_NORMAL_RATE;
        } else if (BACKSTAGE_PASSES_SELL_IN_STANDARD_INCREASING_3_TIMES < sellIn) {
            return BACKSTAGE_PASSES_INCREASE_2_TIMES;
        } else if (SELL_IN_STANDARD <= sellIn) {
            return BACKSTAGE_PASSES_INCREASE_3_TIMES;
        } else {
            return -1;
        }
    }

    //Conjured Quality 변경
    private void updateConjuredQuality(Item item) {

        //1. 판매 기간이 만료되지 않았을 때
        //2. 판매 기간이 만료되었을 때
        if (!isLessThanSellInStandard(item.sellIn)) {
            item.quality -= NORMAL_ITEM_DECREASE_AMOUNT * CONJURED_DECREASE_RATE;
        } else {
            item.quality -= NORMAL_ITEM_DECREASE_AMOUNT * NORMAL_ITEM_DECREASE_RATE_AFTER_SELL_IN_STANDARD * CONJURED_DECREASE_RATE;
        }

        checkAndAdjustQuality(item);
    }

    //일반 아이템 Quality 변경
    private void updateNormalItemQuality(Item item) {

        //판매 기간이 만료되지 않았을 때
        //판매 기간이 만료되었을 때
        if (!isLessThanSellInStandard(item.sellIn)) {
            item.quality -= NORMAL_ITEM_DECREASE_AMOUNT;
        } else {
            item.quality -= NORMAL_ITEM_DECREASE_AMOUNT * NORMAL_ITEM_DECREASE_RATE_AFTER_SELL_IN_STANDARD;
        }

        checkAndAdjustQuality(item);
    }

    //판매기간이 끝났는 지
    private boolean isLessThanSellInStandard(int sellIn) {

        return sellIn < SELL_IN_STANDARD;
    }

    private void checkAndAdjustQuality(Item item) {

        if (item.quality > NORMAL_ITEM_MAX_QUALITY) {
            item.quality = NORMAL_ITEM_MAX_QUALITY;
        }

        if (item.quality < NORMAL_ITEM_MIN_QUALITY) {
            item.quality = NORMAL_ITEM_MIN_QUALITY;
        }
    }
}

// 품목 별로 변경되도록 리팩토링 하자
// 떨어진 날을 기준으로 감소한다.
