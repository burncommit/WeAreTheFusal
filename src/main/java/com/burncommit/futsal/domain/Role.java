package com.burncommit.futsal.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

	ROLE_USER("user");

    private String value;
}
