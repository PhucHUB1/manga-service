package com.manganoob.mangaservice.service;

import com.manganoob.mangaservice.dto.response.VipPackageResponse;
import com.manganoob.mangaservice.entity.User;
import com.manganoob.mangaservice.entity.VipPackage;
import com.manganoob.mangaservice.mapper.VipPackageMapper;
import com.manganoob.mangaservice.repository.UserRepository;
import com.manganoob.mangaservice.repository.VipPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VipService {

    @Autowired
    private VipPackageRepository vipPackageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VipPackageMapper vipPackageMapper;

    public void createVipPackage(VipPackage vipPackage) {
        vipPackageRepository.save(vipPackage);
    }

    public void upgradeUserToVip(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setVip(true);
        userRepository.save(user);
    }

    public List<VipPackageResponse> getAllPackages() {
        List<VipPackage> vipPackageList = vipPackageRepository.findAll();
        return vipPackageList.stream().map(vipPackageMapper::vipPackageToVipPackageResponse).collect(Collectors.toList());
    }
}