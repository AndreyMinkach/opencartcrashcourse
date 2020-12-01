package com.opencart.repository;

import com.opencart.datamodel.RegisterModel;
import com.opencart.util.RandomEmailUtil;
import org.apache.commons.lang3.RandomStringUtils;

public class RegisterModelRepository {

    private RegisterModelRepository() {
    }

    public static RegisterModel getRegisterModel() {
        String password = RandomStringUtils.randomAlphabetic(7);
        return RegisterModel.getBuilder()
                .firstName(RandomStringUtils.randomAlphabetic(5))
                .lastName(RandomStringUtils.randomAlphabetic(5))
                .email(RandomEmailUtil.generateRandomEmail())
                .telephone(RandomStringUtils.randomNumeric(10))
                .password(password)
                .passwordConfirm(password)
                .build();
    }
}
