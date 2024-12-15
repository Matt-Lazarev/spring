package com.lazarev.postgresstreamingreplication.config.datasource;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceType = TransactionSynchronizationManager.isCurrentTransactionReadOnly()
                ? "read"
                : "write";

        log.info("current dataSourceType : {}", dataSourceType);

        return dataSourceType;
    }

    @Override
    @SneakyThrows
    public void afterPropertiesSet(){
        super.afterPropertiesSet();

        Map<Object, DataSource> resolvedDataSources = this.getResolvedDataSources();
        Collection<DataSource> dataSources = resolvedDataSources.values();
        for(DataSource ds : dataSources){
            ds.getConnection();
        }
    }
}