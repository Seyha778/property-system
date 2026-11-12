package com.apextech.property.senghang.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadSignatureResponse {
    long timestamp;
    String signature;
    String apiKey;
    String cloudName;
    String folder;
}
