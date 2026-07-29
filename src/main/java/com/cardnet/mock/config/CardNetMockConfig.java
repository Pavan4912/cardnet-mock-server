package com.cardnet.mock.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "cardnet.mock")
public interface CardNetMockConfig {

    String privateAccountKey();

}
