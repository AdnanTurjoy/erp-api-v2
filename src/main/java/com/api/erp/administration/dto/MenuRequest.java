package com.api.erp.administration.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuRequest {
    private String name;
    private Long parentId; // Optional – if null, it's a root menu
}

