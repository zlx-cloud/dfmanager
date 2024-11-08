package com.bj.dfmanager.config;

import com.bj.dfmanager.entity.HybaseTableInfo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "hybase")
public class HybaseTable {

    private List<HybaseTableInfo> tables;

}
