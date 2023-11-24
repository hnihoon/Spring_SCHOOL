-- cart_sqㅣ.txt

-- 장바구니 테이블
create table cart (
    cartno       number      primary key     --장바구니 번호
   ,product_code number      not null        --상품코드
   ,price        number      not null        --상품가격
   ,qty          number      default 0       --상품수량
   ,id           varchar(15) not null        --아이디
   ,regdate      date        default sysdate --작성일
);

--장바구니 번호 시퀀스 생성
create sequence cart_seq;