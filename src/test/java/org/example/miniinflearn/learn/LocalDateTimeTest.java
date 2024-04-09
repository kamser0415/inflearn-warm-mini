package org.example.miniinflearn.learn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

public class LocalDateTimeTest {

    @Test
    @DisplayName("LocalDateTime의 LocalDate를 가져오면 머가 나올까?")
    void getLocalDate() {
        // given
        LocalDateTime localDateTime = LocalDateTime.of(2023, 4, 15, 10, 12, 12);

        // when
        LocalDate localDate = localDateTime.toLocalDate();

        // then
        assertThat(localDate).isEqualTo("2023-04-15");
    }


    @Test
    @DisplayName("날짜 포멧을 사용해서 DateTime을 가져온다.")
    void toLocalDateTime() {
        // given
        LocalDate now = LocalDate.parse("2024-04-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        assertThat(now).isEqualTo(LocalDate.of(2024, 4, 1));
    }

}
