# 요구 사항
* [o] 모든 아이템은 SellIn 값을 가지며, 이는 아이템을 판매해야하는 (남은) 기간을 나태냅니다.
* [o] 모든 아이템은 Quality 값을 가지며, 이것은 아이템의 가치를 나타냅니다.
* 하루가 지날때마다, 시스템은 두 값(SellIn, Quality)을 1 씩 감소시킵니다.
  * [o] SellIn 감소
  * [o] Quality 감소

* [] 판매하는 나머지 일수가 없어지면, Quality 값은 2배로 떨어집니다.
* [] Quality 값은 0 <= x <= 50이다.
* [o] "Aged Brie"(오래된 브리치즈)은(는) 시간이 지날수록 Quality 값이 올라갑니다.
  * [] "Aged Brie"는 SellIn < 0 이면 두 배 증가.
* Sulfuras는 quality, sellin 불변, quality 값은 80
  * [o] quality 불변
  * [o] sellin 불변
  * [o] quality 80
* "Backstage passes(백스테이지 입장권)"는 "Aged Brie"와 유사하게 SellIn 값에 가까워 질수록 Quality 값이 상승하고, 10일 부터는 매일 2 씩 증가하다, 5일 부터는이 되면 매일 3 씩 증가하지만, 콘서트 종료 후에는 0으로 떨어집니다.
  * case1. sellIn >= 0
    * [o] sellIn이 10일부터는 Quality 2씩 증가
    * [o] sellIn이 5일부터는 Quality 3씩 증가
  * case2. sellin < 0
    * [o] Quality = 0
# 시스템 업데이트 요구 사항

* [o] "Conjured" 아이템은 일반 아이템의 2배의 속도로 품질(Quality)이 저하됩니다.
* [] Item 클래스와 Items 속성은 변경하지 마세요.(UpdateQuality() 메서드와 Items 속성을 정적(static)으로 만드는 것은 괜찮습니다. 저희가 책임질게요.)
