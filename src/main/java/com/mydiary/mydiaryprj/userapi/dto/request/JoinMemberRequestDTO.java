package com.mydiary.mydiaryprj.userapi.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class JoinMemberRequestDTO {

private String userAccount;

private String userPassword;

private String userNickname;

//private String

}
