package com.github.jianlu8023.example.integration.web.service.impl;

import com.github.jianlu8023.example.integration.web.service.*;

import com.github.jianlu8023.mock.generator.address.*;
import com.github.jianlu8023.mock.generator.mobile.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class MockServiceImpl implements MockService {
    private ChineseMobileNumberGenerator mobileNumberGenerator;

    @Autowired
    public void setMobileNumberGenerator(ChineseMobileNumberGenerator mobileNumberGenerator) {
        this.mobileNumberGenerator = mobileNumberGenerator;
    }

    private ChineseAddressGenerator addressGenerator;

    @Autowired
    public void setAddressGenerator(ChineseAddressGenerator addressGenerator) {
        this.addressGenerator = addressGenerator;
    }

    @Override
    public String mockTel() {
        return mobileNumberGenerator.generate();
    }

    @Override
    public String mockAddr() {
        return addressGenerator.generate();
    }
}
