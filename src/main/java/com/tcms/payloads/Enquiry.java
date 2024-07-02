package com.tcms.payloads;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enquiry {
	private Long enquiryId;
	private Long userId;
	private Long replyId;
	private String name;
	private String enquiryContent;
	private LocalDateTime dateCreated;
	private String status;

}
