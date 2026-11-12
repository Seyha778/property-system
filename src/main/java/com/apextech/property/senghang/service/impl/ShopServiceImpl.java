package com.apextech.property.senghang.service.impl;

import com.apextech.property.senghang.entity.ShopFavoritesEntity;
import com.apextech.property.senghang.entity.ShopListingsEntity;
import com.apextech.property.senghang.entity.ShopImageEntity;
import com.apextech.property.senghang.entity.ShopAddressesEntity;
import com.apextech.property.senghang.exception.BadRequestException;
import com.apextech.property.senghang.exception.ResourceNotFoundException;
import com.apextech.property.senghang.model.request.ShopCreateRequest;
import com.apextech.property.senghang.model.request.ShopDeleteRequest;
import com.apextech.property.senghang.model.request.ShopListRequest;
import com.apextech.property.senghang.model.request.ShopUpdateRequest;
import com.apextech.property.senghang.model.response.ListResponse;
import com.apextech.property.senghang.model.response.ProductResponse;
import com.apextech.property.senghang.repository.CommunesRepository;
import com.apextech.property.senghang.repository.DistrictsRepository;
import com.apextech.property.senghang.repository.ProvincesRepository;
import com.apextech.property.senghang.repository.ShopFavoritesRepository;
import com.apextech.property.senghang.repository.ShopImageRepository;
import com.apextech.property.senghang.repository.ShopAddressesRepository;
import com.apextech.property.senghang.repository.ShopListingsRepository;
import com.apextech.property.senghang.repository.ShopCategoryRepository;
import com.apextech.property.senghang.repository.VillageRepository;
import com.apextech.property.senghang.service.ShopService;
import com.apextech.property.senghang.utils.NumberGeneratorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopServiceImpl implements ShopService {

    private final ShopListingsRepository shopListingsRepository;
    private final ShopCategoryRepository shopCategoryRepository;
    private final ShopFavoritesRepository shopFavoritesRepository;
    private final ShopAddressesRepository shopAddressesRepository;
    private final ShopImageRepository shopImageRepository;
    private final ProvincesRepository provincesRepository;
    private final DistrictsRepository districtsRepository;
    private final CommunesRepository communesRepository;
    private final VillageRepository villageRepository;

    @Override
    @Transactional
    public ProductResponse create(ShopCreateRequest request) {
        String listingCode = generateProductNumber();
        String provinceId = request.getProvinceId();
        String districtId = request.getDistrictId();
        String communeId = request.getCommuneId();
        String villageId = request.getVillageId();

        // validate product code
        if (shopListingsRepository.existsByListingCode(listingCode)) {
            throw new ResourceNotFoundException(
                    "Product code already exists:" + listingCode
            );
        }

        // validate category
        if (!shopCategoryRepository.existsByCategoryCode(request.getPropertyType())) {
            throw new ResourceNotFoundException(
                    "Shop Category code not exists:" + request.getPropertyType()
            );
        }

        if (!StringUtils.hasText(provinceId)
                || !StringUtils.hasText(districtId)
                || !StringUtils.hasText(communeId)) {
            throw new BadRequestException(
                    "Please input address!"
            );
        }

        if (!provincesRepository.existsByProvinceId(provinceId)) {
            throw new BadRequestException(
                    "Province Id not exists!"
            );
        }

        if (!districtsRepository.existsByDistrictId(districtId)) {
            throw new BadRequestException(
                    "District Id not exists!"
            );
        }

        if (!communesRepository.existsByCommuneId(communeId)) {
            throw new BadRequestException(
                    "Commune Id not exists!"
            );
        }

        ShopListingsEntity product = ShopListingsEntity.builder()
                .listingCode(listingCode)
                .sellerId("")
                .title(generateSlug(request.getTitle()))
                .propertyType(request.getPropertyType())
                .description(request.getDescription())
                .salePrice(request.getSalePrice())
                .rentalPrice(request.getRentalPrice())
                .comparePrice(request.getComparePrice())
                .currency(request.getCurrency().toUpperCase())
                .status(request.getStatus())
                .isFeatured(Boolean.TRUE.equals(request.getIsFeatured()))
                .publishedAt(OffsetDateTime.now())
                .createdOn(OffsetDateTime.now())
                .build();

        ShopListingsEntity savedProduct = shopListingsRepository.save(product);

        ShopAddressesEntity shopLocation = ShopAddressesEntity.builder()
                .listingCode(listingCode)
                .province(provinceId)
                .district(districtId)
                .commune(communeId)
                .village(villageId)
                .road(request.getRoad())
                .houseNumber(request.getRoad())
                .createdOn(OffsetDateTime.now())
                .build();
        shopAddressesRepository.save(shopLocation);

        ShopImageEntity shopImage = ShopImageEntity.builder()
                .listingCode(listingCode)
                .imageUrl(request.getImageUrl())
                .createdOn(OffsetDateTime.now())
                .build();
        shopImageRepository.save(shopImage);

        log.info("Product created successfully. Product Code: {}", savedProduct.getListingCode());

        return toResponse(savedProduct);
    }

    @Override
    @Transactional
    public ProductResponse update(ShopUpdateRequest request) {
        ShopListingsEntity product = getProduct(request.getListingCode());

        product.setListingCode(request.getListingCode());
        product.setTitle(generateSlug(request.getTitle()));
        product.setPropertyType(request.getPropertyType());
        product.setDescription(request.getDescription());
        product.setSalePrice(request.getSalePrice());
        product.setComparePrice(request.getComparePrice());
        product.setRentalPrice(request.getRentPrice());
        product.setCurrency(request.getCurrency().toUpperCase());
        product.setStatus(request.getStatus());
        product.setFeatured(request.getIsFeatured());
        product.setModifiedOn(OffsetDateTime.now());

        ShopListingsEntity updatedProduct = shopListingsRepository.save(product);

        log.info("Product updated successfully. Product Code: {}", updatedProduct.getListingCode());

        return toResponse(updatedProduct);
    }

    @Override
    public void delete(ShopDeleteRequest request) {
        String listingCode = request.getListingCode();
        ShopListingsEntity deletedProduct = shopListingsRepository.findFirstByListingCode(listingCode)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found: " + listingCode
                ));
        deletedProduct.setStatus("PD");
        shopListingsRepository.save(deletedProduct);

        log.info("Product deleted successfully. Listing Code: {}", listingCode);
    }

    @Override
    @Transactional(readOnly = true)
    public ListResponse<ProductResponse> list(ShopListRequest request) {
        int page = request.getPage() == null ? 0 : request.getPage();
        int size = request.getSize() == null ? 10 : request.getSize();
        String categoryId = request.getPropertyType();
        String keyword = request.getKeyword();

        if (page < 0) {
            throw new ResourceNotFoundException("Page must not be negative");
        }

        if (size < 1 || size > 100) {
            throw new BadRequestException("Page size must be between 1 and 100");
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<ShopListingsEntity> list = shopListingsRepository.findAllByOrderByIdDesc(pageable);

        log.info("Total data size:{}", list.getSize());

        List<ProductResponse> content = list.getContent()
                .stream()
                .map(this::toResponse)
                .toList();

        return ListResponse.<ProductResponse>builder()
                .content(content)
                .totalElements(list.getTotalElements())
                .totalPages(list.getTotalPages())
                .build();
    }

    @Override
    public ListResponse<ProductResponse> listPost(ShopListRequest request) {
        Pageable pageable = PageRequest.of(0, 20);

        Page<ShopListingsEntity> list = shopListingsRepository.findAllBySellerId(request.getSellerId(), pageable);
        log.info("Total list post data size:{}", list.getSize());
        List<ProductResponse> content = list.getContent()
                .stream()
                .map(this::toResponse)
                .toList();

        return ListResponse.<ProductResponse>builder()
                .content(content)
                .totalElements(list.getTotalElements())
                .totalPages(list.getTotalPages())
                .build();
    }

    @Override
    public ListResponse<ProductResponse> listFavorite(ShopListRequest request) {
        Pageable pageable = PageRequest.of(0, 20);

        Page<ShopFavoritesEntity> list = shopFavoritesRepository.findAllByUserId(request.getSellerId(), pageable);
        log.info("Total list favorite data size:{}", list.getSize());

        Optional<ShopListingsEntity> contents = shopListingsRepository.findFirstByListingCode(list.getContent().getFirst().getListingCode());

        List<ProductResponse> content = contents
                .stream()
                .map(this::toResponse)
                .toList();

        return ListResponse.<ProductResponse>builder()
                .content(content)
                .totalElements(list.getTotalElements())
                .totalPages(list.getTotalPages())
                .build();
    }

    private ShopListingsEntity getProduct(String productId) {

        return shopListingsRepository.findFirstByListingCode(productId)
                .orElseThrow(() -> new BadRequestException(
                        "Product not found: " + productId
                ));
    }

    private String generateProductNumber() {
        return NumberGeneratorUtil.generate(
                "ITM",
                shopListingsRepository.nextListingCodeSequence()
        );
    }

    private ProductResponse toResponseByProjection(ProductResponse product) {
        return ProductResponse.builder()
                .id(product.getId())
                .listingCode(product.getListingCode())
                .title(product.getTitle())
                .propertyType(product.getPropertyType())
                .description(product.getDescription())
                .salePrice(product.getSalePrice())
                .rentPrice(product.getRentPrice())
                .comparePrice(product.getComparePrice())
                .currency(product.getCurrency())
                .status(product.getStatus())
                .isFeatured(product.getIsFeatured())
                .createdOn(product.getCreatedOn())
                .build();
    }

    private ProductResponse toResponse(ShopListingsEntity product) {
        return ProductResponse.builder()
                .id(product.getId())
                .listingCode(product.getListingCode())
                .title(product.getTitle())
                .propertyType(product.getPropertyType())
                .description(product.getDescription())
                .salePrice(product.getSalePrice())
                .rentPrice(product.getRentalPrice())
                .comparePrice(product.getComparePrice())
                .currency(product.getCurrency())
                .status(product.getStatus())
                .isFeatured(product.isFeatured())
                .createdOn(product.getCreatedOn())
                .build();
    }

    private String generateSlug(String productName) {
        return productName.trim()
                .toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("(^-|-$)", "");
    }
}
