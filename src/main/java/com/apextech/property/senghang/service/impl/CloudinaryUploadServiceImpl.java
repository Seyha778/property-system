package com.apextech.property.senghang.service.impl;

import com.apextech.property.senghang.model.response.UploadSignatureResponse;
import com.apextech.property.senghang.service.CloudinaryUploadService;
import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryUploadServiceImpl implements CloudinaryUploadService {

    private final Cloudinary cloudinary;

    @Value("${cloudinary.api-secret}")
    private String apiSecret;

    @Override
    public UploadSignatureResponse generateSignature() {

        long timestamp = Instant.now().getEpochSecond();

        Map<String, Object> params = new HashMap<>();
        params.put("timestamp", timestamp);
        params.put("folder", "seng-hang/properties");

        String signature = cloudinary.apiSignRequest(
                params,
                apiSecret
        );

        return UploadSignatureResponse.builder()
                .timestamp(timestamp)
                .signature(signature)
                .apiKey(cloudinary.config.apiKey)
                .cloudName(cloudinary.config.cloudName)
                .folder("seng-hang/properties")
                .build();
    }
}

