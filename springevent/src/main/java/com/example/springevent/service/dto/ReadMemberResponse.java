package com.example.springevent.service.dto;

import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadMemberResponse {
    private Long memberNo;
    private String memberName;
    private Long age;

    public static ReadMemberResponse of(Long memberNo, String memberName, Long age) {
        ReadMemberResponse readMemberResponse = new ReadMemberResponse();
        readMemberResponse.setMemberNo(memberNo);
        readMemberResponse.setMemberName(memberName);
        readMemberResponse.setAge(age);
        return readMemberResponse;
    }
}
