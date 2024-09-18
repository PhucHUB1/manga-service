package com.manga.identityservice.service;

import com.manga.identityservice.entity.User;
import com.manga.identityservice.entity.VipPackage;
import com.manga.identityservice.repository.UserRepository;
import com.manga.identityservice.repository.VipPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VipService {

    @Autowired
    private VipPackageRepository vipPackageRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    public void createVipPackage(VipPackage vipPackage) {
        vipPackageRepository.save(vipPackage);
    }

    public void upgradeUserToVip(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setVip(true);
        userRepository.save(user);
    }

    public VipPackage findVipPackageById(int vipPackageId) {
        return vipPackageRepository.findById(vipPackageId)
                .orElseThrow(() -> new RuntimeException("VIP Package not found"));
    }
}

