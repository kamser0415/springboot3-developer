package me.kmsung.springbootdeveloper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @DisplayName("회원은 아이디값과 이름을 갖는다.")
    @Test
    void test(){
        //given
        long memberId = 1L;
        String name = "첫번째 회원";
        Member member = new Member(memberId, name);

        //when //then
        assertThat(member.getId()).isEqualTo(memberId);
        assertThat(member.getName()).isEqualTo(name);
    }

}