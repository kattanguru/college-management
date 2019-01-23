package com.nisum.college.config;

import com.nisum.college.repository.BranchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheConfig {

    private static Logger logger = LoggerFactory.getLogger(BootApplication.class);

    private final String CACHE_CLEAR_CRON = "0 0/5 * * * ?";

    @Autowired
    private BranchRepository branchRepository;

    @Scheduled(cron = CACHE_CLEAR_CRON)
    public void scheduleCron() {
        logger.debug("College Management Cache Removed by Cron Trigger");
        branchRepository.clearCache();
    }
}
